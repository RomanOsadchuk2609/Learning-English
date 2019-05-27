package com.dariahaze.learning_english.ui.grammar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class GrammarRVAdapter extends RecyclerView.Adapter<GrammarRVAdapter.ViewHolder> {
    private String topicLarge;
    private List<GrammarElement> dataSet;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    public void setTopicLarge(String topicLarge) {
        this.topicLarge = topicLarge;
    }

    public GrammarRVAdapter(List<GrammarElement> dataSet) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_large, parent, false);
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
        private DatabaseReference learnedTopicsReference;
        private int learnedChildes = 0;

        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            numberTV = constraintLayout.findViewById(R.id.itemNumberTV);
            nameTV = constraintLayout.findViewById(R.id.itemNameTV);
            sublistRV = constraintLayout.findViewById(R.id.sublistRV);
            sublistRV.setLayoutManager(new LinearLayoutManager(constraintLayout.getContext()));
            sublistRV.setVisibility(View.GONE);
        }

        public void setItem(final GrammarElement element) {
            this.number = getAdapterPosition()+1;
            this.grammarElement = element;
            numberTV.setText(number+"");
            nameTV.setText(this.grammarElement.getName());

            learnedTopicsReference = FirebaseDatabase.getInstance().getReference(
                    "learnedTopics/" + Utils.getFormattedUserKey(currentUser.getEmail())
                            + "/" + Utils.getFormattedTopicPath(grammarElement.getPath()));
            learnedTopicsReference.keepSynced(true);

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

                learnedTopicsReference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Boolean isChecked = dataSnapshot.getValue(Boolean.class);
                        if (isChecked!=null && isChecked){
                            learnedChildes++;
                        } else if (isChecked!=null && !isChecked){
                            learnedChildes--;
                        }
                        setBackground();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Boolean isChecked = dataSnapshot.getValue(Boolean.class);
                        if (isChecked!=null && isChecked){
                            learnedChildes++;
                        } else if (isChecked!=null && !isChecked){
                            learnedChildes--;
                        }
                        setBackground();
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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

                learnedTopicsReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean isChecked = dataSnapshot.getValue(Boolean.class);
                        if (isChecked!=null && isChecked){
                            constraintLayout.setBackground(constraintLayout.getContext()
                                    .getDrawable(R.drawable.item_background_large_learned_pink));
                        }
                        else {
                            constraintLayout.setBackground(constraintLayout.getContext()
                                    .getDrawable(R.drawable.item_background_large));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }

        private void setBackground(){
            if (learnedChildes == grammarElement.getSubElements().size()){
                constraintLayout.setBackground(constraintLayout.getContext()
                        .getDrawable(R.drawable.item_background_large_learned_pink));
            }
            else {
                constraintLayout.setBackground(constraintLayout.getContext()
                        .getDrawable(R.drawable.item_background_large));
            }
        }
    }
}
