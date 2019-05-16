package com.dariahaze.learning_english.ui.flashCards;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.ui.grammar.GrammarFragment;


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

}
