package com.dariahaze.learning_english.ui.flashCards;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.dariahaze.learning_english.utils.Utils;
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
import java.util.UUID;


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
        final List<CardGroup> cardGroupList = new ArrayList<>();
        final CardGroupRVAdapter adapter = new CardGroupRVAdapter(cardGroupList);
        recyclerView.setAdapter(adapter);

        final FloatingActionButton fabAddGroup = view.findViewById(R.id.addCardGroupFab);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    fabAddGroup.hide();
                } else{
                    fabAddGroup.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        fabAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                        .title("Add new card set")
                        .positiveText("Add")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Set Name", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                            }
                        })
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Set Name 2", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                            }
                        })
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(getContext(), dialog.getInputEditText().getText(), Toast.LENGTH_SHORT).show();
                                CardGroup cardGroup = new CardGroup();
                                cardGroup.setKey(Utils.getFormattedUserKey(currentUser.getEmail()) +
                                        UUID.randomUUID().toString());
                                cardGroup.setName(dialog.getInputEditText().getText().toString());
                                cardGroup.setSize(0);
                                if (currentUser!=null){
                                    mUserCardSetReference.child(cardGroup.getKey()).setValue(cardGroup);
                                }
                            }
                        }).show();
            }
        });

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
            String userReference = Utils.getFormattedUserKey(currentUser.getEmail());
            mUserCardSetReference = FirebaseDatabase.getInstance().getReference("cards/"+userReference+"/");

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
