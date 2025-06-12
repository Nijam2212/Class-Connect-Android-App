package com.example.questionpaper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class QuestionPaperAdapter extends RecyclerView.Adapter<QuestionPaperAdapter.ViewHolder> {

    private List<QuestionPaper> paperList;
    private Context context;

    public QuestionPaperAdapter(List<QuestionPaper> paperList, Context context) {
        this.paperList = paperList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question_paper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionPaper paper = paperList.get(position);
        holder.textTitle.setText(paper.getTitle());
        holder.textDate.setText("Date: " + paper.getDate());
        holder.textDescription.setText(paper.getDescription());

        holder.cardView.setOnClickListener(v -> {
            if (paper.getFileUrl() != null && !paper.getFileUrl().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(paper.getFileUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paperList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDate, textDescription;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textQuestionPaperTitle);
            textDate = itemView.findViewById(R.id.textQuestionPaperDate);
            textDescription = itemView.findViewById(R.id.textQuestionPaperDescription);
            cardView = itemView.findViewById(R.id.questionPaperCard);
        }
    }
}
