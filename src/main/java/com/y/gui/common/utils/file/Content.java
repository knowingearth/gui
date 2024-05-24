package com.y.gui.common.utils.file;

public class Content {
    /**
     * 睡眠时间3分钟
     */
    public static final Long SLEEP = Long.valueOf(1000 * 60 * 3);
    /**
     * 10分钟
     */
    public static final Long TEN = Long.valueOf(60 * 10);


    /**
     * 某种类型的数据总条数
     */
    public static final String DATA_TOTAL_KEY = "DATA_TOTAL_";
    /**
     * 某种类型的数据是否推送完成
     */
    public static final String FINISHED_KEY = "FINISHED_";
    /**
     * 某种类型的数据最后一次写入的文件名
     */
    public static final String FILE_NAME_KEY = "FILE_NAME_";
    /**
     * 某种类型的数据全部文件名
     */
    public static final String FILE_NAMES_KEY = "FILE_NAMES_";

    public static final String LOCK_KEY = "LOCK_";
}
