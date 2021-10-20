package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.service.EmployeeRepresentationService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.util.Properties;

public final class EmployeeReportSender {

    private EmployeeRepresentationService presentationService;

    public void sendEmployeesReport(Connection connection) {
        presentationService = initPresentationService(connection);
        EmployeeReportMessage message = new EmployeeReportMessage();

        Properties properties = prepareProperties();
        Session session = prepareSession(properties);

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(message.getMessageOwner()));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
            mimeMessage.setSubject(message.getSubject());

            String employeesHtml = presentationService.getAllAsHtml();

            mimeMessage.setContent(employeesHtml, "text/html; charset=utf-8");

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
    }

    private EmployeeRepresentationService initPresentationService(Connection connection) {
        return new EmployeeRepresentationService(connection);
    }

    private Session prepareSession(Properties properties) {
        return Session.getDefaultInstance(properties);
    }

    private Properties prepareProperties() {
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        return properties;
    }
}
