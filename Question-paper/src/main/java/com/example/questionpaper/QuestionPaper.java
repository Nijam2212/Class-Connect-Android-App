package com.example.questionpaper;

public class QuestionPaper {
    private String title;
    private String description;
    private String date;
    private String fileUrl;

    public QuestionPaper() {} // Needed for Firebase

    public QuestionPaper(String title, String description, String date, String fileUrl) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.fileUrl = fileUrl;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getFileUrl() { return fileUrl; }
}
