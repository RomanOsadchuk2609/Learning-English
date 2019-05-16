package com.dariahaze.learning_english.ui.grammar;


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
import com.dariahaze.learning_english.utils.Utils;


public class GrammarOtherFragment extends Fragment {


    public GrammarOtherFragment() {
        // Required empty public constructor
    }
    public static GrammarOtherFragment newInstance() {
        GrammarOtherFragment fragment = new GrammarOtherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grammar_other, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.grammar_other_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GrammarRVAdapter adapter = new GrammarRVAdapter(Utils.grammarOtherElements);
        adapter.setTopicLarge("Other Grammar");
        recyclerView.setAdapter(adapter);
    }
}
