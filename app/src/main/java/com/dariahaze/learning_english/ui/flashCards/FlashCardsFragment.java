package com.dariahaze.learning_english.ui.flashCards;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardsFragment extends Fragment {


    private DatabaseReference mCommonCardSetReference;
    private DatabaseReference mUserCardSetReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public FlashCardsFragment() {
        // Required empty public constructor
    }

    public static FlashCardsFragment newInstance() {
        FlashCardsFragment fragment = new FlashCardsFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flash_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCommonCardSetReference = FirebaseDatabase.getInstance().getReference("cards/admin");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        RecyclerView recyclerView = view.findViewById(R.id.card_groups_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /*CardGroup cardGroup = new CardGroup("Group", new ArrayList<FlashCard>(Arrays.asList(
                new FlashCard("AAA","AAA AAA"),
                new FlashCard("BBB","BBB BBB"),
                new FlashCard("CCC","CCC CCC"),
                new FlashCard("DDD","DDD DDD"),
                new FlashCard("EEE","EEE EEE"),
                new FlashCard("FFF","FFF FFF")
        )));*/

        final List<CardGroup> cardGroupList = new ArrayList<>();
        //cardGroupList.add(cardGroup);

        final CardGroupRVAdapter adapter = new CardGroupRVAdapter(cardGroupList);
        recyclerView.setAdapter(adapter);

        mCommonCardSetReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // A new data item has been added, add it to the list
                CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                String key = dataSnapshot.getKey();
                System.out.println("KEY: "+key);
                cardGroup.setKey(key);
                if (!cardGroupList.contains(cardGroup)){
                    cardGroupList.add(cardGroup);
                    //adapter.getDataSet().add(cardGroup);
                    adapter.notifyItemInserted(adapter.getDataSet().size()-1);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("DB", "onChildChanged:" + dataSnapshot.getKey());

                // A data item has changed
                CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("DB", "onChildRemoved:" + dataSnapshot.getKey());

                // A data item has been removed
                CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d("DB", "onChildMoved:" + dataSnapshot.getKey());

                // A data item has changed position
                CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("DB", "onCancelled", databaseError.toException());
                //Toast.makeText(getApplicationContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });

        if (currentUser!=null){
            String userReference = currentUser.getEmail();
            userReference = userReference.replaceAll("."," ");
            mUserCardSetReference = FirebaseDatabase.getInstance().getReference("cards/admin/"+userReference);

            mUserCardSetReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    // A new data item has been added, add it to the list
                    CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                    String key = dataSnapshot.getKey();
                    cardGroup.setKey(key);
                    if (!cardGroupList.contains(cardGroup)){
                        cardGroupList.add(cardGroup);
                        //adapter.getDataSet().add(cardGroup);
                        adapter.notifyItemInserted(adapter.getDataSet().size()-1);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d("DB", "onChildChanged:" + dataSnapshot.getKey());

                    // A data item has changed
                    CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d("DB", "onChildRemoved:" + dataSnapshot.getKey());

                    // A data item has been removed
                    CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d("DB", "onChildMoved:" + dataSnapshot.getKey());

                    // A data item has changed position
                    CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("DB", "onCancelled", databaseError.toException());
                    //Toast.makeText(getApplicationContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
