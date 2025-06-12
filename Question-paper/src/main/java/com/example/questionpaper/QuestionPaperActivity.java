package com.example.questionpaper;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionPaperActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;
    private List<QuestionPaper> paperList;
    private QuestionPaperAdapter adapter;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_question_paper);

        recyclerView = findViewById(R.id.rvAssignments);
        fabAdd = findViewById(R.id.fabAddAssignment);

        paperList = new ArrayList<>();
        adapter = new QuestionPaperAdapter(paperList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("QuestionPapers");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                paperList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    QuestionPaper paper = snap.getValue(QuestionPaper.class);
                    paperList.add(paper);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddQuestionPaperActivity.class));
        });
    }
}
