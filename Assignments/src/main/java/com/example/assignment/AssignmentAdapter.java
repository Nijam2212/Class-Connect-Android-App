package com.example.assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {

    private List<Assignment> assignmentList;
    private OnAssignmentClickListener listener;

    public interface OnAssignmentClickListener {
        void onAssignmentClick(Assignment assignment);

    }

    public AssignmentAdapter(List<Assignment> assignmentList, OnAssignmentClickListener listener) {
        this.assignmentList = assignmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignmentList.get(position);
        holder.tvTitle.setText(assignment.getTitle());
        holder.tvDescription.setText(assignment.getDescription());
        holder.tvDueDate.setText("Due: " + assignment.getDueDate());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAssignmentClick(assignment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDueDate;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvAssignmentTitle);
            tvDescription = itemView.findViewById(R.id.tvAssignmentDescription);
            tvDueDate = itemView.findViewById(R.id.tvAssignmentDueDate);
        }
    }
}
