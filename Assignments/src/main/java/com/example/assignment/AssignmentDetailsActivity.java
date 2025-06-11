package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AssignmentDetailsActivity extends AppCompatActivity {

    private TextView tvTitle, tvDescription, tvDueDate;
    private Button btnSubmit, btnViewPdf;
    private String pdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDescription = findViewById(R.id.tvDetailDescription);
        tvDueDate = findViewById(R.id.tvDetailDueDate);
        btnSubmit = findViewById(R.id.btnSubmitAssignment);
        btnViewPdf = findViewById(R.id.btnViewPdf);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("description");
        String date = intent.getStringExtra("dueDate");
        pdfUrl = intent.getStringExtra("pdfUrl");

        tvTitle.setText(title);
        tvDescription.setText(desc);
        tvDueDate.setText("Due: " + date);

        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(this, "Assignment submitted (mock)", Toast.LENGTH_SHORT).show();
            // You can add logic to upload assignment or mark as submitted
        });

        btnViewPdf.setOnClickListener(v -> {
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl));
            startActivity(pdfIntent);
        });
    }
}
