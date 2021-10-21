package com.epam.cleandesign.srp;

import com.epam.cleandesign.srp.service.EmployeeRepresentationService;

import java.util.List;

public class EmployeeReportMessage {

    private final String recipient = "abcd@gmail.com";
    private final String author = "web@gmail.com";
    private final String subject = "Employees report";
    private final String contentType = "text/html; charset=utf-8";
    private final String content;

    public EmployeeReportMessage(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }
}
