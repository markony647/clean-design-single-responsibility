package com.epam.cleandesign.srp;

public class EmployeeReportMessage {

    private final String content;

    public EmployeeReportMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
