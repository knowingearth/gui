package com.y.gui.common.extension;

import com.y.gui.common.annotations.CLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Slf4j
@Component
public class MailExt {
    @Value("${spring.mail.username}")
    private String from;
    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     * @param param
     * @return
     */
    @CLog
    public Boolean sendMail(MailExtParam param) {
        if (!StringUtils.hasText(param.getRecipient()) && CollectionUtils.isEmpty(param.getRecipients())) {
            log.info("MailExt.sendMail, 收件人信息缺失");
            return Boolean.FALSE;
        }
        if (!StringUtils.hasText(param.getSubject()) || !StringUtils.hasText(param.getText())) {
            log.info("MailExt.sendMail, 缺少邮件主题或内容");
            return Boolean.FALSE;
        }
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            if (StringUtils.hasText(param.getRecipient())) {
                message.setTo(param.getRecipient());
            }
            if (!CollectionUtils.isEmpty(param.getRecipients())) {
                message.setTo(param.getRecipients().toArray(new String[0]));
            }
            if (!CollectionUtils.isEmpty(param.getCc())) {
                message.setCc(param.getCc().toArray(new String[0]));
            }
            if (!CollectionUtils.isEmpty(param.getBcc())) {
                message.setBcc(param.getBcc().toArray(new String[0]));
            }
            message.setSubject(param.getSubject());
            message.setText(param.getText());
            mailSender.send(message);
            return Boolean.TRUE;
        } catch (MailException e) {
            log.error("MailExt.sendMail, e:", e);
            return Boolean.TRUE;
        }
    }
}
