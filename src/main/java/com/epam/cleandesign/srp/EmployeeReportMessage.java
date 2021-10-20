package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.repository.EmployeeRepository;
import com.epam.cleandesign.srp.service.EmployeeRepresentationService;

import java.sql.Connection;

public class EmployeeReportMessage {

    private final String recipient = "abcd@gmail.com";
    private final String messageOwner = "web@gmail.com";
    private final String subject = "Employees report";
    //private final String content;

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageOwner() {
        return messageOwner;
    }
}
