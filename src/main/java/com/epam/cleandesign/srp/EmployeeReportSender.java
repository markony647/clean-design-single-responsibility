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

//    private final EmployeeRepresentationService employeeRepresentationService;
//
//    public EmployeeReportSender() {
//        this.employeeRepresentationService = new EmployeeRepresentationService();
//    }

    public void sendEmployeesReport(Connection connection) {
        EmployeeRepresentationService employeeRepresentationService = new EmployeeRepresentationService(connection);
        EmployeeReportMessage message = new EmployeeReportMessage();

        Properties properties = prepareProperties();
        Session session = prepareSession(properties);

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(message.getMessageOwner()));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
            mimeMessage.setSubject(message.getSubject());

            String employeesHtml = employeeRepresentationService.getAllAsHtml(connection);

            mimeMessage.setContent(employeesHtml, "text/html; charset=utf-8");

            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException(e);
        }
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
