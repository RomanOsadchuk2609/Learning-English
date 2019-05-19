package com.dariahaze.learning_english.ui.grammar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.GrammarElement;

import java.util.List;

public class GrammarRVSmallAdapter extends RecyclerView.Adapter<GrammarRVSmallAdapter.ViewHolder> {
    private List<GrammarElement> dataSet;
    private String topicLarge;

    public void setTopicLarge(String topicLarge) {
        this.topicLarge = topicLarge;
    }

    public GrammarRVSmallAdapter(List<GrammarElement> dataSet) {
        this.dataSet = dataSet;
    }

    public List<GrammarElement> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<GrammarElement> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GrammarRVSmallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_small, parent, false);
        return new GrammarRVSmallAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GrammarRVSmallAdapter.ViewHolder holder, int position) {
        holder.setItem(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        if(dataSet !=null)
            return dataSet.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private GrammarElement grammarElement;
        private ConstraintLayout constraintLayout;
        private TextView  nameTV;


        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            nameTV = constraintLayout.findViewById(R.id.itemSmallNameTV);
        }

        public void setItem(GrammarElement element) {
            this.grammarElement = element;
            nameTV.setText(this.grammarElement.getName());

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(constraintLayout.getContext(), grammarElement.getPath(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent (v.getContext(), WebViewActivity.class);
                    intent.putExtra("HtmlPath",grammarElement.getPath());
                    intent.putExtra("TopicLarge",topicLarge);
                    intent.putExtra("TopicSmall",grammarElement.getName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

