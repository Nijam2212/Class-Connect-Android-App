package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AssignmentAdapter.OnAssignmentClickListener {

    private RecyclerView rvAssignments;
    private FloatingActionButton fabAdd;
    private AssignmentAdapter adapter;
    private List<Assignment> assignmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);

        rvAssignments = findViewById(R.id.rvAssignments);
        fabAdd = findViewById(R.id.fabAddAssignment);

        assignmentList = new ArrayList<>();
        adapter = new AssignmentAdapter(assignmentList, this); // 👈 Correct this cast
        rvAssignments.setLayoutManager(new GridLayoutManager(this, 2));
        rvAssignments.setAdapter(adapter);

        loadAssignmentsFromFirebase();

        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddAssignmentActivity.class));
        });
    }

    @Override
    public void onAssignmentClick(Assignment assignment) {
        // ✅ Correct: open submission list
        Intent intent = new Intent(MainActivity.this, TeacherAssignmentSubmissionsActivity.class);
        intent.putExtra("assignmentId", assignment.getId());
        startActivity(intent);
    }

    private void loadAssignmentsFromFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Assignments");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assignmentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Assignment assignment = dataSnapshot.getValue(Assignment.class);
                    if (assignment != null) {
                        assignmentList.add(assignment);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

