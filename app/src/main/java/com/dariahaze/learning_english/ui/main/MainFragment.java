package com.dariahaze.learning_english.ui.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.ui.grammar.GrammarFragment;
import com.dariahaze.learning_english.ui.grammar.GrammarPagesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView grammarCV = view.findViewById(R.id.grammar_CV);
        CardView testsCV = view.findViewById(R.id.tests_CV);
        CardView flashCardsCV = view.findViewById(R.id.flash_cards_CV);

        grammarCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.main_content, GrammarPagesFragment.newInstance())
                        .commit();
            }
        });
    }
}
