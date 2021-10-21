package com.epam.cleandesign.srp;

public class HtmlTableBuilder {

    private final StringBuilder table;

    public HtmlTableBuilder() {
        table = new StringBuilder();
    }

    public void addHeader() {
        table.append("<table>").append("<tr><th>Employee</th><th>Position</th></tr>");
    }

    public void addFooter() {
        table.append("</table>");
    }

    public void addRow(String row) {
        table.append(row);
    }

    public String getTable() {
        return table.toString();
    }
}
