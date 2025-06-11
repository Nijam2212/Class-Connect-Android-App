package com.example.assignment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddAssignmentActivity extends BaseActivity {

    private EditText etTitle, etDescription, etDueDate, etPdfUrl;
    private Button btnSubmit;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_add_assignment);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDueDate = findViewById(R.id.etDueDate);
        etPdfUrl = findViewById(R.id.etPdfUrl);
        btnSubmit = findViewById(R.id.btnSubmitAssignment);

        calendar = Calendar.getInstance();

        etDueDate.setOnClickListener(v -> showDatePicker());

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            String dueDate = etDueDate.getText().toString().trim();
            String pdfUrl = etPdfUrl.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty() || pdfUrl.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a specific key (you can customize the logic here)
            String key = title.replaceAll("\\s+", "_").toLowerCase();

            Assignment assignment = new Assignment(key, title, description, dueDate, pdfUrl);
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Assignments");

            // Check if the key already exists
            ref.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Toast.makeText(AddAssignmentActivity.this, "Assignment with this title already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        ref.child(key).setValue(assignment).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddAssignmentActivity.this, "Assignment Added", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(AddAssignmentActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddAssignmentActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedYear + "-" +
                            String.format("%02d", selectedMonth + 1) + "-" +
                            String.format("%02d", selectedDay);
                    etDueDate.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }
}
