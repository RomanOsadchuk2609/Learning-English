package com.dariahaze.learning_english.ui.videoLessons;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.VideoLesson;

import java.util.List;

public class VideoLessonRVAdapter extends RecyclerView.Adapter<VideoLessonRVAdapter.ViewHolder> {
    private List<VideoLesson> dataSet;

    public VideoLessonRVAdapter(List<VideoLesson> dataSet) {
        this.dataSet = dataSet;
    }

    public List<VideoLesson> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<VideoLesson> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoLessonRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_large, parent, false);
        return new VideoLessonRVAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoLessonRVAdapter.ViewHolder holder, int position) {
        holder.setItem(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        if(dataSet !=null)
            return dataSet.size();
        else return 0;    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoLesson videoLesson;
        private ConstraintLayout constraintLayout;
        private TextView numberTV, nameTV;


        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            numberTV = constraintLayout.findViewById(R.id.itemNumberTV);
            nameTV = constraintLayout.findViewById(R.id.itemNameTV);
        }

        public void setItem(VideoLesson element) {
            int number = getAdapterPosition()+1;
            numberTV.setText(number+"");
            this.videoLesson = element;
            nameTV.setText(this.videoLesson.getName());

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLesson.getLink()));
                    constraintLayout.getContext().startActivity(browserIntent);
                }
            });
        }
    }
}
