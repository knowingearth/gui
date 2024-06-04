package com.y.gui.common.utils.file;

import com.y.gui.common.extension.RedisExt;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public abstract class ConsumerParent {
    @Resource
    private RedisExt redisExt;

    /**
     * 处理消息
     * @param type
     * @param data
     */
    protected void doMessage(Integer type, String data) throws Exception {
        // 消费第1条数据，需要新建文件
        String totalKey = Content.DATA_TOTAL_KEY + type;
        if (!redisExt.hasKey(totalKey)) {
            createFile(type, data);
            return;
        }

        // 判断是否该分页了
        Long total = redisExt.get(totalKey);
        if (0 == total % Content.PAGE_SIZE) {
            createPageFile(type, data);
            return;
        }

        // 追加文件内容
        data = "," + data;
        writeFile(type, data);
        redisExt.incr(totalKey);
    }

    /**
     * 创建新文件
     * @param type
     * @param data
     */
    protected void createFile(Integer type, String data) throws Exception {
        // 1.获取分布式锁
        getLock(type);

        // 2.创建文件
        String totalKey = Content.DATA_TOTAL_KEY + type;
        String fileName = type + "_" + redisExt.get(totalKey);
        data = "{\"dataType\":" + type + ", \"datas\":[" + data;
        writeFile(type, data);

        // 3.当前写的文件名放入缓存
        String fileNameCacheKey = Content.FILE_NAME_KEY + type;
        redisExt.set(fileNameCacheKey, fileName);
        redisExt.incr(totalKey);

        // 4.释放锁
        releaseLock(type);
    }

    /**
     * 结束旧文件，创建新文件
     * @param type
     * @param data
     */
    protected void createPageFile(Integer type, String data) throws Exception {
        String totalKey = Content.DATA_TOTAL_KEY + type;
        String fileNameKey = Content.FILE_NAME_KEY + type;
        // 1.获取分布式锁
        getLock(type);

        // 2.旧文件结束
        String oldFileName = redisExt.get(fileNameKey);
        String end = "], \"total\":10000}";
        writeFile(type, end);

        // 3.新文件开始
        String newFileName = type + "_" + redisExt.get(totalKey);
        data = "{\"dataType\":" + type + ", \"datas\":[" + data;
        redisExt.set(fileNameKey, newFileName);
        writeFile(type, data);
        redisExt.incr(totalKey);

        // 4.释放锁
        releaseLock(type);
    }

    /**
     * 数据写文件
     * @param type
     * @param data
     */
    protected void writeFile(Integer type, String data) throws Exception {
        String fileNameKey = Content.FILE_NAME_KEY + type;
        String fileName = redisExt.get(fileNameKey);
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName,true)), true)) {
            pw.println(data);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取锁
     */
    protected void getLock(Integer type) {
        String lockKey = Content.LOCK_KEY + type;
        if (redisExt.hasKey(lockKey)) {
            throw new RuntimeException("未获取到锁");
        }

        boolean lock = redisExt.setNx(lockKey, 0, 3L);// 锁3秒过期
        if (!lock) {
            throw new RuntimeException("未获取到锁");
        }
    }

    /**
     * 释放锁
     */
    private void releaseLock(Integer type) {
        String lockKey = Content.LOCK_KEY + type;
        redisExt.del(lockKey);
    }
}
