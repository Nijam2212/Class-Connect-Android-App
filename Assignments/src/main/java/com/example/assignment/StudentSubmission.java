package com.example.assignment;

public class StudentSubmission {
    private String name;
    private String fileUrl;

    public StudentSubmission() { }

    public StudentSubmission(String name, String fileUrl) {
        this.name = name;
        this.fileUrl = fileUrl;
    }

    public String getName() { return name; }
    public String getFileUrl() { return fileUrl; }
}
