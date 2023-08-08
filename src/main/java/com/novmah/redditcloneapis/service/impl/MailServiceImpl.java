package com.novmah.redditcloneapis.service.impl;

import com.novmah.redditcloneapis.exception.RedditCloneException;
import com.novmah.redditcloneapis.model.NotificationEmail;
import com.novmah.redditcloneapis.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparation = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparation);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RedditCloneException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
    }

}
