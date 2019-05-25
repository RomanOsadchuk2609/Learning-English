package com.dariahaze.learning_english.ui.statistics;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.UsersStatistics;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    private DatabaseReference statisticsReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    public static StatisticsFragment newInstance() {
        StatisticsFragment fragment = new StatisticsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView amountOfTestsTV, amountOfTopicsTV, amountOfCardSets;
        amountOfCardSets = view.findViewById(R.id.statisticsCardSets);
        amountOfTopicsTV = view.findViewById(R.id.statisticsTopics);
        amountOfTestsTV = view.findViewById(R.id.statisticsTests);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        statisticsReference = FirebaseDatabase.getInstance().getReference("statistics/"
                + Utils.getFormattedUserKey(currentUser.getEmail()));

        statisticsReference.child("tests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer tests = dataSnapshot.getValue(Integer.class);
                if (tests != null){
                    amountOfTestsTV.setText("Amount of successfully passed tests: " + tests +
                            "/" + Utils.AMOUNT_OF_TESTS);
                }
                else {
                    amountOfTestsTV.setText("Amount of successfully passed tests: " + 0 +
                            "/" + Utils.AMOUNT_OF_TESTS);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        statisticsReference.child("topics").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer topics = dataSnapshot.getValue(Integer.class);
                if (topics != null){
                    amountOfTopicsTV.setText("Amount of learned topics: " + topics +
                            "/" + Utils.AMOUNT_OF_TOPICS);
                }
                else {
                    amountOfTopicsTV.setText("Amount of learned topics: " + 0 +
                            "/" + Utils.AMOUNT_OF_TOPICS);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
