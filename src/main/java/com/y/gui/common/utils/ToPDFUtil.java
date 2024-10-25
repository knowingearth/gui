package com.y.gui.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.font.FontProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ToPDFUtil {
    private static final String MARK = "中国企业";
    private static Configuration config;

    public static void main(String[] args) throws Exception {
        Map<String, Object> datas = new HashMap<>();
        datas.put("name", "王五");
        datas.put("gender", "男");
        datas.put("age", 20);
        datas.put("address", "哀牢山郊区");

        List<Map<String, Object>> liseDatas = new ArrayList<>();
        datas.put("listDatas", liseDatas);
        for (int i = 0; i < 32; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put("a", RandomUtil.randomNumbers(5));
            m.put("b", RandomUtil.randomNumbers(5));
            m.put("c", RandomUtil.randomNumbers(5));
            //m.put("d", RandomUtil.randomNumbers(5));
            m.put("e", RandomUtil.randomNumbers(5));
            m.put("f", RandomUtil.randomNumbers(5));
            m.put("g", RandomUtil.randomNumbers(5));
            m.put("h", RandomUtil.randomNumbers(5));
            m.put("i", RandomUtil.randomNumbers(5));
            liseDatas.add(m);
        }
        datas.put("listDatasTotal", liseDatas.size());

        // 本地logo图片
        datas.put("img", getImgBase64());
        // web图片
        String imgUrl = "http://pic.qqbizhi.com/allimg/202401/1jxlbjkiyq5m1jsbfezvu82k_1920x1080.jpg";
        datas.put("bgImg", ImageUtils.urlToBase64(imgUrl));

        // 生成pdf文件
        byte[] byteArray = convertToPdf("test.ftl", datas);

        // 输出pdf文件
        FileUtil.writeBytes(byteArray, new File("D:\\pdf\\test.pdf"));
    }

    /**
     * 模板填充数据并转为PDF
     * @param templeteName 模板名称
     * @param datas 填充的数据
     * @return
     */
    public static byte[] convertToPdf(String templeteName, Map<String, Object> datas) {
        try {
            Template template = config.getTemplate(templeteName);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // 使用iText生成PDF
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);

            // 水印儿
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new Watermark(MARK));
            // 页眉
            pdf.addEventHandler(PdfDocumentEvent.INSERT_PAGE, new Header(MARK));
            // 页脚
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new Footer());
            // PDF尺寸
            PageSize a4 = PageSize.A4;
            pdf.setDefaultPageSize(new PageSize(a4.getWidth(), a4.getHeight() - 20));

            // 处理模板并生成内容
            StringWriter htmlWriter = new StringWriter();
            template.process(datas, htmlWriter);
            String html = htmlWriter.toString();

            // PDF转换参数
            ConverterProperties converterProperties = new ConverterProperties();
            FontProvider fontProvider = new FontProvider();

            // 默认使用的字体：微软雅黑
            fontProvider.addFont(PdfFontFactory.createFont("/pdf/font/msyh.ttc,1", "Identity-H").getFontProgram(), "Identity-H");
            // PDF中如果需要显示加粗的字体
            fontProvider.addFont(PdfFontFactory.createFont("/pdf/font/msyhbd.ttc,1", "Identity-H").getFontProgram(), "Identity-H");
            // fontProvider.addFont(PdfFontFactory.createFont("/pdf/font/simhei.ttf", PdfEncodings.IDENTITY_H).getFontProgram(), PdfEncodings.IDENTITY_H);
            converterProperties.setFontProvider(fontProvider);


            HtmlConverter.convertToPdf(html, pdf, converterProperties);

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取logo图片
     * @return 图片信息
     */
    private static String getImgBase64() {
        String base64 = null;
        try {
            File file = new File(System.getProperty("user.dir") + "/src/main/resources/pdf/files/logo.png");
            InputStream inputStream = Files.newInputStream(file.toPath());
            byte[] bytes = IOUtils.toByteArray(inputStream);
            base64 = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            log.error("ToPDFUtil.getImgBase64, IOException, e:", e);
        }
        return base64;
    }

    /**
     * 页眉
     */
    @AllArgsConstructor
    static class Header implements IEventHandler {
        private final String text;

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            PdfDocument document = docEvent.getDocument();

            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
            try {
                PdfExtGState extGState = new PdfExtGState();
                extGState.setFillOpacity(1f); // 设置填充透明度为0.05
                double alpha = Math.toRadians(0); // 倾斜角度
                double tanAlpha = Math.tan(alpha);
                //可根据页面宽高设置水印
                canvas.saveState()
                        .concatMatrix(1, tanAlpha, 0, 1, 0, 0)
                        .beginText()
                        .resetFillColorGray()
                        .setExtGState(extGState)
                        .moveText(35, 810)
                        .setFontAndSize(PdfFontFactory.createFont("/pdf/font/msyh.ttc,1", "Identity-H"), 10)
                        .showText(text)
                        .endText()
                        .restoreState();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 水印儿
     */
    @AllArgsConstructor
    static class Watermark implements IEventHandler {
        private final String text;

        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            PdfDocument document = docEvent.getDocument();

            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
            try {
                PdfExtGState extGState = new PdfExtGState();
                extGState.setFillOpacity(0.05f); // 设置填充透明度为0.05
                double alpha = Math.toRadians(7); // 倾斜角度
                double tanAlpha = Math.tan(alpha);
                //可根据页面宽高设置水印
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 3; j++) {
                        canvas.saveState()
                                .concatMatrix(1, tanAlpha, 0, 1, 0, 0)
                                .beginText()
                                .resetFillColorGray()
                                .setExtGState(extGState)
                                .moveText(35 + j * 200, i * 100)
                                .setFontAndSize(PdfFontFactory.createFont("/pdf/font/msyh.ttc,1", "Identity-H"), 30)
                                .showText(text)
                                .endText()
                                .restoreState();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 页脚儿
     */
    static class Footer implements IEventHandler {
        @Override
        public void handleEvent(Event event) {
            PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
            PdfPage page = docEvent.getPage();
            Rectangle pageSize = page.getPageSize();
            page.setMediaBox(new Rectangle(pageSize.getWidth(), pageSize.getHeight() - 20));
            PdfDocument document = docEvent.getDocument();
            int pageNumber = document.getPageNumber(page);
            int totalNumber = document.getPageNumber(document.getLastPage());
            String footerText = "　　　　　　　　　　　　　　　　　　　　　　　第" + pageNumber + "页　　　　　　　　　　　　　　　　　　　　　　　共" + totalNumber + "页";

            PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
            try {
                PdfExtGState extGState = new PdfExtGState();
                extGState.setFillOpacity(1f); // 设置填充透明度为0.05
                double alpha = Math.toRadians(0); // 倾斜角度
                double tanAlpha = Math.tan(alpha);
                //可根据页面宽高设置水印
                canvas.saveState()
                        .concatMatrix(1, tanAlpha, 0, 1, 0, 0)
                        .beginText()
                        .resetFillColorGray()
                        .setExtGState(extGState)
                        .moveText(35, 40)
                        .setFontAndSize(PdfFontFactory.createFont("/pdf/font/msyh.ttc,1", "Identity-H"), 10)
                        .showText(footerText)
                        .endText()
                        .restoreState();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    /*
     * 初始化freemarker
     */
    static {
        try {
            config = new Configuration(Configuration.VERSION_2_3_30);
            config.setDefaultEncoding(StandardCharsets.UTF_8.name());
            // 设置模板文件的路径
            config.setClassForTemplateLoading(Configuration.class, "/pdf/template");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            log.info("init freemarker configuration Exception, e:", e);
        }
    }

}
