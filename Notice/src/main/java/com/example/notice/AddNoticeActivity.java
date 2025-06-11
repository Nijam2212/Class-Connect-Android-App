package com.example.notice;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class AddNoticeActivity extends BaseActivity {

    EditText etTitle, etDescription;
    TextView tvDate;
    Button btnPickDate, btnUpload;
    String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_add_notice);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        tvDate = findViewById(R.id.tvDate);
        btnPickDate = findViewById(R.id.btnPickDate);
        btnUpload = findViewById(R.id.btnUpload);

        btnPickDate.setOnClickListener(v -> showDatePicker());

        btnUpload.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Enter title");
                etTitle.requestFocus();
                return;
            }

            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Pick a date", Toast.LENGTH_SHORT).show();
                return;
            }
            String key = title.replaceAll("\\s+", "_").toLowerCase();
            Notice notice = new Notice(title, desc, selectedDate);
            FirebaseDatabase.getInstance().getReference("Notices")
                    .child(key)
                    .setValue(notice)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "Notice uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, day) -> {
            selectedDate = day + "/" + (month + 1) + "/" + year;
            tvDate.setText("Date: " + selectedDate);
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
}
