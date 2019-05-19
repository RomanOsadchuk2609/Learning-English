package com.dariahaze.learning_english.ui.videoLessons;


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
import com.dariahaze.learning_english.ui.grammar.GrammarRVAdapter;
import com.dariahaze.learning_english.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoLessonsFragment extends Fragment {


    public VideoLessonsFragment() {
        // Required empty public constructor
    }

    public static VideoLessonsFragment newInstance() {
        VideoLessonsFragment fragment = new VideoLessonsFragment();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_lessons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.video_lessons_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoLessonRVAdapter adapter = new VideoLessonRVAdapter(Utils.videoLessons);
        recyclerView.setAdapter(adapter);
    }
}
