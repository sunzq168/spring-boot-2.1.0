package com.sun.zq.mail;

import com.sun.zq.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public boolean sendMail(List<User> users) {
        users.forEach(user -> {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            try {
                message.setFrom(from);
                message.setTo(user.getEmail());
                message.setSubject("今日地瓜特卖");
                message.setText("今天地瓜特卖，你知道吗？");

                this.mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                log.error("send mail fail, name={}", user.getName());
            }
        });

        return true;
    }
}
