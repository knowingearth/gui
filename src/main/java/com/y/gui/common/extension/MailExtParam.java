package com.y.gui.common.extension;

import lombok.Data;

import java.util.List;

@Data
public class MailExtParam {
    /**
     * 收件人
     */
    private String recipient;

    /**
     * 收件人
     */
    private List<String> recipients;

    /**
     * 抄送
     */
    private List<String> cc;

    /**
     * 密送
     */
    private List<String> bcc;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;
}
