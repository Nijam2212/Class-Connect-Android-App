package com.example.questionpaper;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuestionPaperActivity extends BaseActivity {

    private EditText etTitle, etDescription, etDate, etUrl;
    private Button btnUpload;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_add_question_paper);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etDate);
        etUrl = findViewById(R.id.etUrl);
        btnUpload = findViewById(R.id.btnUpload);

        dbRef = FirebaseDatabase.getInstance().getReference("QuestionPapers");

        btnUpload.setOnClickListener(v -> uploadPaper());
    }

    private void uploadPaper() {
        String title = etTitle.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String url = etUrl.getText().toString().trim();

        if (title.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Title and Date required", Toast.LENGTH_SHORT).show();
            return;
        }

        String key = title.replaceAll("\\s+", "_").toLowerCase();
        QuestionPaper paper = new QuestionPaper(title, desc, date, url);
        dbRef.child(key).setValue(paper).addOnSuccessListener(aVoid ->
                Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show()
        );
        finish();
    }
}
