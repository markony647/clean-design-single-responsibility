package com.epam.cleandesign.srp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.epam.cleandesign.srp.EmployeeReportMessageConfiguration.*;

public final class EmployeeReportSender {

    private final SessionManager sessionManager;

    public EmployeeReportSender(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void send(EmployeeReportMessage message) {
        try {
            MimeMessage mimeMessage = prepareMimeMessage(message);
            sendMessage(mimeMessage);
        } catch (MessagingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void sendMessage(MimeMessage message) throws MessagingException {
        Transport.send(message);
    }

    private MimeMessage prepareMimeMessage(EmployeeReportMessage message) throws MessagingException {
        Session session = sessionManager.prepareSession();
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(AUTHOR));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT));
        mimeMessage.setSubject(SUBJECT);
        mimeMessage.setContent(message.getContent(), CONTENT_TYPE);
        return mimeMessage;
    }
}
