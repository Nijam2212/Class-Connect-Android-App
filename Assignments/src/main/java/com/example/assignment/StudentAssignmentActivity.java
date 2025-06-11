package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentAssignmentActivity extends BaseActivity {

    private RecyclerView rvStudentAssignments;
    private AssignmentAdapter adapter;
    private List<Assignment> assignmentList;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_student_assignment);

        rvStudentAssignments = findViewById(R.id.rvStudentAssignments);
        assignmentList = new ArrayList<>();
        adapter = new AssignmentAdapter(assignmentList,
                this::openAssignmentDetails);
        rvStudentAssignments.setLayoutManager(new GridLayoutManager(this, 2));
        rvStudentAssignments.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("Assignments");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assignmentList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Assignment assignment = snap.getValue(Assignment.class);
                    assignmentList.add(assignment);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentAssignmentActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openAssignmentDetails(Assignment assignment) {
        Intent intent = new Intent(this, AssignmentDetailsActivity.class);
        intent.putExtra("title", assignment.getTitle());
        intent.putExtra("description", assignment.getDescription());
        intent.putExtra("dueDate", assignment.getDueDate());
        intent.putExtra("pdfUrl", assignment.getPdfUrl());
        startActivity(intent);
    }
}
