package com.example.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentView extends AppCompatActivity {

    private RecyclerView rvNotices;
    private List<Notice> noticeList;
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);
        rvNotices = findViewById(R.id.rvNotices);
        
        noticeList = new ArrayList<>();

        rvNotices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeAdapter(noticeList);
        rvNotices.setAdapter(adapter);

        // Load data from Firebase
        loadNotices();
    }

    private void loadNotices() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Notices");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noticeList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Notice notice = snap.getValue(Notice.class);
                    if (notice != null) {
                        noticeList.add(notice);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentView.this, "Failed to load notices", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
