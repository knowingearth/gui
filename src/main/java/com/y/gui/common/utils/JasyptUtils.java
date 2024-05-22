package com.y.gui.common.utils;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class JasyptUtils {
    public static void main(String[] args) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        // set config
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        //设置盐值
        config.setPassword("");
        //设置算法配置信息
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setProviderName("SunJCE");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        config.setPoolSize("1");
        //注入配置
        encryptor.setConfig(config);
        //解密
        System.out.println("-----------解密串");
        String decrypted = encryptor.decrypt("");
        System.out.println(decrypted);
        //加密
        System.out.println("-----------加密串");
        String encryptUserName = encryptor.encrypt("");
        System.out.println(encryptUserName);
    }
}
