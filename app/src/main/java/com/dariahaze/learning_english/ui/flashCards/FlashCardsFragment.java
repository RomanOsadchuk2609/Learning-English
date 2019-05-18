package com.dariahaze.learning_english.ui.flashCards;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.dariahaze.learning_english.ui.grammar.GrammarFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlashCardsFragment extends Fragment {


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
        RecyclerView recyclerView = view.findViewById(R.id.card_groups_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CardGroup cardGroup = new CardGroup("Group", new ArrayList<FlashCard>(Arrays.asList(
                new FlashCard("AAA","AAA AAA"),
                new FlashCard("BBB","BBB BBB"),
                new FlashCard("CCC","CCC CCC"),
                new FlashCard("DDD","DDD DDD"),
                new FlashCard("EEE","EEE EEE"),
                new FlashCard("FFF","FFF FFF")
        )));

        List<CardGroup> cardGroupList = new ArrayList<>();
        cardGroupList.add(cardGroup);

        CardGroupRVAdapter adapter = new CardGroupRVAdapter(cardGroupList);
        recyclerView.setAdapter(adapter);
    }
}
