package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.model.NotificationEmail;

public interface MailService {

    void sendMail(NotificationEmail notificationEmail);

}
