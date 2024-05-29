package com.y.gui.common.utils.file;

import com.y.gui.common.extension.RedisExt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class FilePushUtils implements ApplicationRunner {


    @Resource
    private RedisExt redisExt;

    /**
     * 自执行方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        exec();
    }

    private void exec() throws Exception {
        List<DataTypeEnum> types = DataTypeEnum.getAllEnums();
        while (!types.isEmpty()) {
            for (int i = 0; i < types.size(); i++) {
                DataTypeEnum e = types.get(i);
                Integer type = e.getType();

                // 1.判断数据总条数
                String totalKey = Content.DATA_TOTAL_KEY + type;
                if (!redisExt.hasKey(totalKey)) {
                    continue;
                }
                Long total = Long.valueOf(redisExt.get(totalKey));
                if (0 >= total) {
                    continue;
                }

                // 2.判断数据是否推送完成
                String finishKey = Content.FINISHED_KEY + type;
                if (redisExt.hasKey(finishKey)) {
                    // 生产者数据未生产完成
                    continue;
                }

                // 3.数据推送完成，追加json文件结束的“}”，并将所有json文件推送给远程服务器
                pushFile(type);

                // 4.当所有类型的数据处理完后，整个while就结束了
                types.remove(i--);
            }
            Thread.sleep(Content.SLEEP);// 休眠3分钟，避免CPU飙到70%
        }
    }

    private void pushFile(Integer type) {
        // 1.最后一次写入的文件名
        String fileNameKey = Content.FILE_NAME_KEY + type;
        String fileName = redisExt.get(fileNameKey);

        // 2.为最后一个json文件追加结尾
        String totalKey = Content.DATA_TOTAL_KEY + type;
        String data = "],\"total\":" + Long.valueOf(redisExt.get(totalKey)) % 10000 + "}";
        writeFile(fileName, data);

        // 3.将所有某类型的json文件推送远程
        String fileNamesKey = Content.FILE_NAMES_KEY + type;
        List<Object> fileNames = redisExt.lGet(fileNamesKey, 0, redisExt.lGetListSize(fileNamesKey));
        // todo 推送远程
    }

    /**
     * 测试并发写文件
     * @param args
     */
    public static void main(String[] args) {
        int count = 1000;// 1000个并发
        CountDownLatch down = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            String data = "第" + i + "条数据";
            new Thread(new WriteClass(down, data)).start();
            down.countDown();
        }
    }

    /**
     * 线程执行类
     */
    static class WriteClass implements Runnable {
        private CountDownLatch down;
        private String data;
        public WriteClass (CountDownLatch down, String data) {
            this.down = down;
            this.data = data;
        }

        @Override
        public void run() {
            try {
                down.await();
                writeFile("D:\\data.txt", this.data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 写文件
     * @param data
     */
    public static void writeFile(String fileName, String data) {
        if (!StringUtils.hasText(data)) {
            return;
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName,true)), true);
            pw.println(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != pw) pw.close();
        }
    }
}
