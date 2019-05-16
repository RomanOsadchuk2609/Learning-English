package com.dariahaze.learning_english.ui.grammar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.GrammarElement;
import com.dariahaze.learning_english.utils.Utils;

import java.util.List;

public class GrammarRVAdapter extends RecyclerView.Adapter<GrammarRVAdapter.ViewHolder> {
    private String topicLarge;
    private List<GrammarElement> dataSet;

    public void setTopicLarge(String topicLarge) {
        this.topicLarge = topicLarge;
    }

    public GrammarRVAdapter(List<GrammarElement> dataSet) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grammar_element_large, parent, false);
        return new GrammarRVAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        if(dataSet !=null)
            return dataSet.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private int number;
        private GrammarElement grammarElement;
        private ConstraintLayout constraintLayout;
        private TextView numberTV, nameTV;
        private RecyclerView sublistRV;


        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            numberTV = constraintLayout.findViewById(R.id.grammarItemNumberTV);
            nameTV = constraintLayout.findViewById(R.id.grammarItemNameTV);
            sublistRV = constraintLayout.findViewById(R.id.sublistRV);
            sublistRV.setLayoutManager(new LinearLayoutManager(constraintLayout.getContext()));
            sublistRV.setVisibility(View.GONE);
        }

        public void setItem(final GrammarElement element) {
            this.number = getAdapterPosition()+1;
            this.grammarElement = element;
            numberTV.setText(number+"");
            nameTV.setText(this.grammarElement.getName());
            if (!grammarElement.getSubElements().isEmpty()){
                GrammarRVSmallAdapter adapter = new GrammarRVSmallAdapter(grammarElement.getSubElements());
                adapter.setTopicLarge(grammarElement.getName());
                sublistRV.setAdapter(adapter);

                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sublistRV.getVisibility() == View.GONE){
                            sublistRV.setVisibility(View.VISIBLE);
                        }
                        else {
                            sublistRV.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else {
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ///Toast.makeText(constraintLayout.getContext(), grammarElement.getPath(), Toast.LENGTH_SHORT).show();
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
}
