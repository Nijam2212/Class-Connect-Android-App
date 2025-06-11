package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TeacherAssignmentSubmissionsActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private StudentSubmissionAdapter adapter;
    private List<StudentSubmission> submissionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_teacher_assignment_submissions);

        recyclerView = findViewById(R.id.submissionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Dummy list for testing
        submissionList = new ArrayList<>();
        submissionList.add(new StudentSubmission("Student 1", "https://example.com/dummy1.pdf"));
        submissionList.add(new StudentSubmission("Student 2", "https://example.com/dummy2.pdf"));
        submissionList.add(new StudentSubmission("Student 3", "https://example.com/dummy3.pdf"));

        adapter = new StudentSubmissionAdapter(submissionList, this);
        recyclerView.setAdapter(adapter);

        // ✅ Uncomment this block when you want to fetch from Firebase instead
        /*
        assignmentId = getIntent().getStringExtra("assignmentId");
        DatabaseReference submissionsRef = FirebaseDatabase.getInstance()
                .getReference("Assignments")
                .child(assignmentId)
                .child("submissions");

        submissionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                submissionList.clear();
                for (DataSnapshot studentSnap : snapshot.getChildren()) {
                    StudentSubmission submission = studentSnap.getValue(StudentSubmission.class);
                    submissionList.add(submission);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TeacherAssignmentSubmissionsActivity.this, "Failed to load submissions.", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}
