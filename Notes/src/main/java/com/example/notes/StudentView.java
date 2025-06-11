package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StudentView extends BaseActivity {

    private RecyclerView rvNotes;
        private NoteAdapter adapter;
        private List<Note> noteList;
        private DatabaseReference notesRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentLayout(R.layout.activity_notes);

            rvNotes = findViewById(R.id.rvNotes);




            noteList = new ArrayList<>();
            adapter = new NoteAdapter(noteList, this);
            rvNotes.setLayoutManager(new LinearLayoutManager(this));
            rvNotes.setAdapter(adapter);

            notesRef = FirebaseDatabase.getInstance().getReference("Notes");


            loadNotes();
        }

        private void loadNotes() {
            notesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    noteList.clear();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Note note = ds.getValue(Note.class);
                        noteList.add(note);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
    }
