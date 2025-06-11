package com.example.notes;

public class Note {
    private String title, description, date, fileUrl;

    public Note() {}  // Required for Firebase

    public Note(String title, String description, String date, String fileUrl) {
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
