package com.y.gui.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ImageUtils {

    /**
     * <img alt="图片" src="data:image/png;base64,/9j/4AAQSkZJ..."/>
     * @param imgUrl
     * @return
     * @throws IOException
     */
    public static String urlToBase64(String imgUrl) {
        if (!StringUtils.hasText(imgUrl) &&
                !(imgUrl.startsWith("http") || imgUrl.startsWith("https"))) {
            return null;
        }

        String base64 = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            // 读取图片的数据到ByteArrayOutputStream
            InputStream in = connection.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = out.toByteArray();
            base64 = new String(Base64.encodeBase64(imageBytes));
        } catch (IOException e) {
            log.info("ImageUtils.urlToBase64, imgUrl:{}", imgUrl, e);
        }
        return base64;
    }

    public static void main(String[] args) {
        System.out.println(urlToBase64("https://tkjohn.github.io/flowable-userguide/images/getting.started.bpmn.process.png"));

        for (int i = 0; i < 5; i++) {
            System.out.println((int)(Math.random()*100000000));
        }

    }
}
