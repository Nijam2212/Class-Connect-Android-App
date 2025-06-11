package com.example.assignment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentSubmissionAdapter extends RecyclerView.Adapter<StudentSubmissionAdapter.ViewHolder>  {

    private List<StudentSubmission> submissions;
    private Context context;

    public StudentSubmissionAdapter(List<StudentSubmission> submissions, Context context) {
        this.submissions = submissions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_student_submission, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentSubmission submission = submissions.get(position);
        holder.nameTextView.setText(submission.getName());

        holder.viewFileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(submission.getFileUrl()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return submissions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button viewFileButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvStudentName);
            viewFileButton = itemView.findViewById(R.id.btnViewFile);
        }
    }
}
