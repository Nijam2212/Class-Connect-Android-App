package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewTimetableActivity extends BaseActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_view_timetable);

        tableLayout = findViewById(R.id.tableLayout);

        loadDummyExamTimetable();
        loadDummyLectureTimetable();
    }

    private void loadDummyExamTimetable() {
        addSectionTitle("📘 Exam Timetable");

        // Header row
        TableRow header = new TableRow(this);
        addCell(header, "Date", true);
        addCell(header, "Subject", true);
        addCell(header, "Time", true);
        addCell(header, "Room", true);
        addCell(header, "Note", true);
        tableLayout.addView(header);

        // Dummy rows
        addExamRow("2025-06-10", "Math", "10:00 AM - 12:00 PM", "Room 101", "Bring calculator");
        addExamRow("2025-06-12", "Physics", "1:00 PM - 3:00 PM", "Room 102", "");
        addExamRow("2025-06-15", "Chemistry", "10:00 AM - 12:00 PM", "Room 103", "Lab coat required");
    }

    private void addExamRow(String date, String subject, String time, String room, String note) {
        TableRow row = new TableRow(this);
        addCell(row, date, false);
        addCell(row, subject, false);
        addCell(row, time, false);
        addCell(row, room, false);
        addCell(row, note, false);
        tableLayout.addView(row);
    }

    private void loadDummyLectureTimetable() {
        addSectionTitle("📗 Lecture Timetable");

        // Header row
        TableRow header = new TableRow(this);
        addCell(header, "Date", true);
        addCell(header, "Subject", true);
        addCell(header, "Teacher", true);
        addCell(header, "Time", true);
        addCell(header, "Mode", true);
        addCell(header, "Link/Room", true);
        tableLayout.addView(header);

        // Dummy rows
        addLectureRow("2025-06-05", "Math", "Mr. Smith", "9:00 AM - 10:00 AM", "Offline", "Room 201");
        addLectureRow("2025-06-06", "Physics", "Ms. Johnson", "10:00 AM - 11:00 AM", "Online", "https://zoom.us/j/123456789");
        addLectureRow("2025-06-07", "Chemistry", "Dr. Lee", "11:00 AM - 12:00 PM", "Offline", "Room 202");
    }

    private void addLectureRow(String date, String subject, String teacher, String time, String mode, String linkOrRoom) {
        TableRow row = new TableRow(this);
        addCell(row, date, false);
        addCell(row, subject, false);
        addCell(row, teacher, false);
        addCell(row, time, false);
        addCell(row, mode, false);
        addCell(row, linkOrRoom, false);
        tableLayout.addView(row);
    }

    private void addCell(TableRow row, String text, boolean isHeader) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(16, 10, 16, 10);
        tv.setGravity(Gravity.CENTER);
        if (isHeader) {
            tv.setTypeface(null, Typeface.BOLD);
        }
        row.addView(tv);
    }

    private void addSectionTitle(String title) {
        TableRow titleRow = new TableRow(this);
        TextView tv = new TextView(this);
        tv.setText(title);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setPadding(16, 24, 16, 16);
        tv.setTextSize(18);
        titleRow.addView(tv);
        tableLayout.addView(titleRow);
    }
}
