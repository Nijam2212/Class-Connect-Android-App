package com.example.notes;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends BaseActivity {

    private EditText editTitle, editDescription, editFileUrl;
    private Button btnUpload;
    private DatabaseReference notesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(
                R.layout.activity_add_note);

        editTitle = findViewById(R.id.editNoteTitle);
        editDescription = findViewById(R.id.editNoteDescription);
        editFileUrl = findViewById(R.id.editNoteFileUrl);
        btnUpload = findViewById(R.id.btnUploadNote);

        notesRef = FirebaseDatabase.getInstance().getReference("Notes");

        btnUpload.setOnClickListener(v -> uploadNote());
    }

    private void uploadNote() {
        String title = editTitle.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String fileUrl = editFileUrl.getText().toString().trim();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (TextUtils.isEmpty(title)) {
            editTitle.setError("Title is required");
            return;
        }

        String noteId = title.replaceAll("\\s+", "_").toLowerCase();
        Note note = new Note(title, description, date, fileUrl);

        notesRef.child(noteId).setValue(note)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Note uploaded", Toast.LENGTH_SHORT).show();
                    finish();  // Go back to notes list
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}
