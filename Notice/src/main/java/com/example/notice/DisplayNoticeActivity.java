package com.example.notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayNoticeActivity extends BaseActivity {

    private RecyclerView rvNotices;
    private List<Notice> noticeList;
    private NoticeAdapter adapter;
    private FloatingActionButton fabAddNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_display_notice);

        rvNotices = findViewById(R.id.rvNotices);
        fabAddNotice = findViewById(R.id.fabAddNotice);
        noticeList = new ArrayList<>();

        rvNotices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeAdapter(noticeList);
        rvNotices.setAdapter(adapter);

        // Load data from Firebase
        loadNotices();

        // FAB to open Add Notice screen
        fabAddNotice.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayNoticeActivity.this, AddNoticeActivity.class);
            startActivity(intent);
        });
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
                Toast.makeText(DisplayNoticeActivity.this, "Failed to load notices", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
