package com.y.gui.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * 生成条形码
 */
@Slf4j
public class BarCodeUtil {
    private static final Integer CODE_WIDTH = 400;                 // 基础属性：二维码宽度，单位像素
    private static final Integer CODE_HEIGHT = 90;                // 基础属性：二维码高度，单位像素
    private static final Integer FRONT_COLOR = 0x000000;           // 基础属性：二维码前景色，0x000000 表示黑色
    private static final Integer BACKGROUND_COLOR = 0xFFFFFF;      // 基础属性：二维码背景色，0xFFFFFF 表示白色

    public static void main(String[] args) throws WriterException, IOException {
        String s = generateBarCodeBase64("PUR24092918195100193");
        System.out.println(s);
    }

    public static String generateBarCodeBase64(String code) throws WriterException, IOException {
        //设置生成条形码的内容 （注：条形码信息一定要按照格式，如随便一个商品上的条形码都行，但必须是EAN_13格式）
        HashMap<EncodeHintType, Object> typeObjectHashMap = new HashMap<>();
        typeObjectHashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // typeObjectHashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        typeObjectHashMap.put(EncodeHintType.MARGIN, 1);

        // MultiFormatWriter:用于写入条形码或二维码
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.CODE_128, CODE_WIDTH, CODE_HEIGHT, typeObjectHashMap);

        // java.awt.image.BufferedImage：把条形码信息转换为图像信息
        BufferedImage bufferedImage = new BufferedImage(CODE_WIDTH, CODE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        byte[] bytes = IOUtils.toByteArray(byteArrayInputStream);

        //获取当前项目的路径根目录
        String path = Objects.requireNonNull(BarCodeUtil.class.getResource("/")).getPath();
        //生成文件名及文件的路径
        File filePathAndName = new File(path, new Date().getTime() + ".png");
        ImageIO.write(bufferedImage, "png", filePathAndName);

        return Base64.getEncoder().encodeToString(bytes);
    }
}
