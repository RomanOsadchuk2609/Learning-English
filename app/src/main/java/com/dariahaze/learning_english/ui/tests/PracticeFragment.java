package com.dariahaze.learning_english.ui.tests;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.PracticeTest;
import com.dariahaze.learning_english.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PracticeFragment extends Fragment {


    public PracticeFragment() {
        // Required empty public constructor
    }

    public static PracticeFragment newInstance() {
        PracticeFragment fragment = new PracticeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObjectMapper mapper = new ObjectMapper();
        PracticeTest practiceTest;
        try {
            practiceTest = mapper.readValue(getActivity().getAssets().open(Utils.PRACTICE_TESTS_PATH+"Adverbs.json"), PracticeTest.class);
            System.out.println(practiceTest);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT OPENED");
        }
    }
}
