package com.y.gui.common.utils;

import com.y.gui.common.extension.RedisExt;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FilePushUtils implements ApplicationRunner {
    /**
     * 某种类型的数据总条数
     */
    private static final String DATA_TOTAL = "DATA_TOTAL_";
    /**
     * 某种类型的数据是否推送完成
     */
    private static final String FINISHED_KEY = "FINISHED_";
    /**
     * 某种类型的数据最后一次写入的文件名
     */
    private static final String FILE_NAME_KEY = "FILE_NAME_";
    /**
     * 某种类型的数据全部文件名
     */
    private static final String FILE_NAMES_KEY = "FILE_NAMES_";

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
                String totalKey = DATA_TOTAL + type;
                if (!redisExt.hasKey(totalKey)) {
                    continue;
                }
                Long total = Long.valueOf(redisExt.get(totalKey));
                if (0 >= total) {
                    continue;
                }

                // 2.判断数据是否推送完成
                String finishKey = FINISHED_KEY + type;
                if (redisExt.hasKey(finishKey)) {
                    // 数据未推送完成
                    continue;
                }

                // 3.数据推送完成，追加json文件结束的“}”，并将所有json文件推送给远程服务器
                pushFile(type);

                // 4.当所有类型的数据处理完后，整个while就结束了
                types.remove(i--);
            }
            Thread.sleep(1000 * 60 * 3);// 休眠3分钟
        }
    }

    private void pushFile(Integer type) {
        // 1.最后一次写入的文件名
        String fileNameKey = FILE_NAME_KEY + type;
        String fileName = String.valueOf(redisExt.get(fileNameKey));

        // 2.为最后一个json文件追加结尾
        String totalKey = DATA_TOTAL + type;
        String data = "\"total\":" + Long.valueOf(redisExt.get(totalKey)) % 10000 + "}";
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName,true)), true);
            pw.println(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != pw) {
                pw.close();
            }
        }

        // 3.将所有某类型的json文件推送远程
        String fileNamesKey = FILE_NAMES_KEY + type;
        List<Object> fileNames = redisExt.lGet(fileNamesKey, 0, redisExt.lGetListSize(fileNamesKey));
        // todo 推送远程
    }



    /**
     * 20种数据类型 枚举
     */
    @Getter
    @AllArgsConstructor
    enum DataTypeEnum {
        BJ(1, "北京地区数据"),
        TJ(2, "天津地区数据"),
        HB(3, "河北地区数据");
        private Integer type;
        private String desc;

        public static String getDescByType(Integer type) {
            if (null == type) {
                return null;
            }
            return Stream.of(values()).filter(e -> e.getType().equals(type)).findFirst().map(DataTypeEnum::getDesc).orElse(null);
        }

        public static List<DataTypeEnum> getAllEnums() {
            return Stream.of(values()).collect(Collectors.toList());
        }
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
                writeFile(this.data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 写文件
     * @param data
     */
    public static void writeFile(String data) {
        if (!StringUtils.hasText(data)) {
            return;
        }
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("D:\\data.txt",true)), true);
            pw.println(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != pw) {
                pw.close();
            }
        }
    }
}
