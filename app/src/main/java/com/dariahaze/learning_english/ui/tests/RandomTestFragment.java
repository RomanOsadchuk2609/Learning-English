package com.dariahaze.learning_english.ui.tests;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.ui.flashCards.CardPagerActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RandomTestFragment extends Fragment {


    public RandomTestFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random_test, container, false);
    }

    public static Fragment newInstance(){
        return new RandomTestFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Button start = view.findViewById(R.id.buttonStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (start.getContext(), TestActivity.class);
                start.getContext().startActivity(intent);
            }
        });
    }
}
