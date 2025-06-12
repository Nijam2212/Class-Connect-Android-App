package com.example.timetable;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddTimetableActivity extends BaseActivity {

    private EditText subjectEditText, examDateEditText, examTimeEditText, roomEditText, noteEditText;
    private EditText teacherEditText, lectureDateEditText, startTimeEditText, endTimeEditText;
    private EditText linkEditText, classroomEditText;
    private RadioGroup modeToggle, modeGroup;
    private RadioButton examRadio, lectureRadio, onlineRadio, offlineRadio;
    private LinearLayout examLayout, lectureLayout;
    private MaterialButton saveButton, showButton;
    private DatabaseReference timetableRef;

    private String selectedType = "exam";  // Default type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_add_timetable);

        // Initialize views
        modeToggle = findViewById(R.id.modeToggle);
        examRadio = findViewById(R.id.examRadio);
        lectureRadio = findViewById(R.id.lectureRadio);

        subjectEditText = findViewById(R.id.subjectEditText);
        examDateEditText = findViewById(R.id.examDateEditText);
        examTimeEditText = findViewById(R.id.examTimeEditText);
        roomEditText = findViewById(R.id.roomEditText);
        noteEditText = findViewById(R.id.noteEditText);

        teacherEditText = findViewById(R.id.teacherEditText);
        lectureDateEditText = findViewById(R.id.lectureDateEditText);
        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        linkEditText = findViewById(R.id.linkEditText);
        classroomEditText = findViewById(R.id.classroomEditText);
        examLayout = findViewById(R.id.examLayout);
        lectureLayout = findViewById(R.id.lectureLayout);

        saveButton = findViewById(R.id.saveButton);
        showButton = findViewById(R.id.showButton);

        timetableRef = FirebaseDatabase.getInstance().getReference("timetables");

        // Date and Time Pickers
        setupDateTimePickers();

        modeToggle.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.examRadio) {
                selectedType = "exam";
                examLayout.setVisibility(View.VISIBLE);
                lectureLayout.setVisibility(View.GONE);
            } else {
                selectedType = "lecture";
                examLayout.setVisibility(View.GONE);
                lectureLayout.setVisibility(View.VISIBLE);
            }
        });



        saveButton.setOnClickListener(v -> saveTimetable());
        showButton.setOnClickListener(v -> {
            // TODO: Implement ShowTimetableActivity
            Intent intent=new Intent(AddTimetableActivity.this, ViewTimetableActivity.class);
            startActivity(intent);
        });
    }

    private void setupDateTimePickers() {
        examDateEditText.setOnClickListener(v -> showDatePicker(examDateEditText));
        examTimeEditText.setOnClickListener(v -> showTimePicker(examTimeEditText));
        lectureDateEditText.setOnClickListener(v -> showDatePicker(lectureDateEditText));
        startTimeEditText.setOnClickListener(v -> showTimePicker(startTimeEditText));
        endTimeEditText.setOnClickListener(v -> showTimePicker(endTimeEditText));
    }

    private void showDatePicker(EditText target) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String date = String.format(Locale.getDefault(), "%02d-%02d-%04d", dayOfMonth, month + 1, year);
            target.setText(date);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void showTimePicker(EditText target) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
            target.setText(time);
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePicker.show();
    }

    private void saveTimetable() {
        String subject = subjectEditText.getText().toString().trim();
        if (subject.isEmpty()) {
            subjectEditText.setError("Subject required");
            return;
        }

        String standard = "5th Satandard"; // In future: dynamic based on teacher/student login

        if (selectedType.equals("exam")) {
            String date = examDateEditText.getText().toString().trim();
            String time = examTimeEditText.getText().toString().trim();
            String room = roomEditText.getText().toString().trim();
            String note = noteEditText.getText().toString().trim();

            Map<String, Object> examData = new HashMap<>();
            examData.put("subject", subject);
            examData.put("date", date);
            examData.put("time", time);
            examData.put("room", room);
            examData.put("note", note);

            timetableRef.child(standard).child("exam").child(subject).setValue(examData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Exam saved", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        } else {
            String teacher = teacherEditText.getText().toString().trim();
            String date = lectureDateEditText.getText().toString().trim();
            String startTime = startTimeEditText.getText().toString().trim();
            String endTime = endTimeEditText.getText().toString().trim();
            String link = linkEditText.getText().toString().trim();
            String classroom = classroomEditText.getText().toString().trim();

            // Get day of week from date
            String day = getDayOfWeek(date);

            Map<String, Object> lectureData = new HashMap<>();
            lectureData.put("subject", subject);
            lectureData.put("teacher", teacher);
            lectureData.put("date", date);
            lectureData.put("day", day);
            lectureData.put("startTime", startTime);
            lectureData.put("endTime", endTime);

            timetableRef.child(standard).child("lecture").child(subject).setValue(lectureData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(this, "Lecture saved", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    private String getDayOfWeek(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            Date date = sdf.parse(dateStr);
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            return dayFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
}
