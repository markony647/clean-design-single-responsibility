package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.repository.EmployeeRepository;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.util.List;

public final class EmployeeReportSender {

    private final SessionManager sessionManager;
    private final EmployeeReportMessage message;
    private final EmployeeRepository employeeRepository;

    public EmployeeReportSender(Connection connection) {
        sessionManager = new SessionManager();
        employeeRepository = new EmployeeRepository(connection);
        List<Employee> allEmployees = employeeRepository.findAll();
        message = new EmployeeReportMessage(allEmployees);
    }

    public void sendEmployeesReport() {
        try {
            MimeMessage mimeMessage = prepareMimeMessage();
            sendMessage(mimeMessage);
        } catch (MessagingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private void sendMessage(MimeMessage message) throws MessagingException {
        Transport.send(message);
    }

    private MimeMessage prepareMimeMessage() throws MessagingException {
        Session session = sessionManager.prepareSession();
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(message.getAuthor()));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(message.getRecipient()));
        mimeMessage.setSubject(message.getSubject());
        mimeMessage.setContent(message.getContent(), message.getContentType());
        return mimeMessage;
    }
}
