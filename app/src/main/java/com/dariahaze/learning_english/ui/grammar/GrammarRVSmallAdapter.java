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
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GrammarRVSmallAdapter extends RecyclerView.Adapter<GrammarRVSmallAdapter.ViewHolder> {
    private List<GrammarElement> dataSet;
    private String topicLarge;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public void setTopicLarge(String topicLarge) {
        this.topicLarge = topicLarge;
    }

    public GrammarRVSmallAdapter(List<GrammarElement> dataSet) {
        this.dataSet = dataSet;
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
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
        private DatabaseReference learnedTopicsReference;

        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            nameTV = constraintLayout.findViewById(R.id.itemSmallNameTV);
        }

        public void setItem(GrammarElement element) {
            this.grammarElement = element;
            nameTV.setText(this.grammarElement.getName());

            learnedTopicsReference = FirebaseDatabase.getInstance().getReference(
                    "learnedTopics/" +Utils.getFormattedUserKey(currentUser.getEmail()) +"/"
                            + Utils.getFormattedTopicPath(grammarElement.getPath()));

            learnedTopicsReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean isChecked = dataSnapshot.getValue(Boolean.class);
                    if (isChecked!=null && isChecked){
                        constraintLayout.setBackground(constraintLayout.getContext()
                                .getDrawable(R.drawable.item_background_small_learned_pink));
                    }
                    else {
                        constraintLayout.setBackground(constraintLayout.getContext()
                                .getDrawable(R.drawable.item_background_small));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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

