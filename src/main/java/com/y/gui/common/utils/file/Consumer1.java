package com.y.gui.common.utils.file;

import com.y.gui.common.extension.RedisExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

@Component
public class Consumer1 {
    @Resource
    private RedisExt redisExt;

    private Long pageSize = 10000L;

    private Integer type = DataTypeEnum.BJ.getType();

    private String totalKey = Content.DATA_TOTAL_KEY + type;

    private String fileNameKey = Content.FILE_NAME_KEY + type;

    public void onMessage(String data) {
        // 消费第1条数据，需要新建文件
        if (!redisExt.hasKey(totalKey)) {
            createFile();
        }

        // 判断是否该分页了
        Long total = redisExt.incr(totalKey);
        if (0 == total % pageSize) {
            createPageFile(data);
            return;
        }

        // 追加文件内容
        writeFile(redisExt.get(fileNameKey), data);
    }

    /**
     * 获取锁
     */
    private void getLock() {
        String lockKey = Content.LOCK_KEY + type;
        if (redisExt.hasKey(lockKey)) {
            throw new RuntimeException("未获取到锁");
        }

        boolean lock = redisExt.setNx(lockKey, 0, 1L);
        if (!lock) {
            throw new RuntimeException("未获取到锁");
        }
    }

    /**
     * 释放锁
     */
    private void releaseLock() {
        String lockKey = Content.LOCK_KEY + type;
        redisExt.del(lockKey);
    }

    public void createFile() {
        // 1.获取分布式锁
        getLock();

        // 2.创建文件
        String fileName = type + "_" + redisExt.get(totalKey);
        String data = "{\"dataType\":" + type + ", \"datas\":[";
        writeFile(fileName, data);

        // 3.当前写的文件名放入缓存
        redisExt.set(fileNameKey, fileName);

        // 4.释放锁
        releaseLock();
    }

    public void createPageFile(String data) {
        // 1.获取分布式锁
        getLock();

        // 2.旧文件结束
        String oldFileName = redisExt.get(fileNameKey);
        data += "], \"total\":10000}";
        writeFile(oldFileName, data);

        // 3.新文件开始
        String newFileName = type + "_" + redisExt.get(totalKey);
        String newData = "{\"dataType\":" + type + ", \"datas\":[";
        redisExt.set(fileNameKey, newFileName);
        writeFile(newFileName, newData);

        // 4.释放锁
        releaseLock();
    }

    /**
     * 数据写文件
     * @param fileName
     * @param data
     */
    public void writeFile(String fileName, String data) {
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
    }
}
