package com.example.assignment;


public class Assignment {
    private String title;
    private String description;
    private String dueDate;
    private String pdfUrl;
    private String id;

    public Assignment() {
        // Default constructor required for Firebase
    }

    public Assignment(String id, String title, String description, String dueDate, String pdfUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.pdfUrl = pdfUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
}

