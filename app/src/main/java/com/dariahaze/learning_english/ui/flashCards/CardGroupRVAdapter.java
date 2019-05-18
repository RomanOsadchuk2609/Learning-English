package com.dariahaze.learning_english.ui.flashCards;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;

import java.util.List;

public class CardGroupRVAdapter  extends RecyclerView.Adapter<CardGroupRVAdapter.ViewHolder> {
    private List<CardGroup> dataSet;

    public CardGroupRVAdapter(List<CardGroup> dataSet) {
        this.dataSet = dataSet;
    }

    public List<CardGroup> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<CardGroup> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardGroupRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_group, parent, false);
        return new CardGroupRVAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardGroupRVAdapter.ViewHolder holder, int position) {
        holder.setItem(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        if (dataSet != null)
            return dataSet.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardGroup cardGroup;
        private LinearLayout linearLayout;
        private TextView amountOfCards, nameTV;


        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            linearLayout = itemView;
            amountOfCards = linearLayout.findViewById(R.id.amount_of_cards);
            nameTV = linearLayout.findViewById(R.id.card_group_name);
        }

        public void setItem(CardGroup element) {
            if (element.getSize()>1){
                amountOfCards.setText(element.getSize() + " cards");
            }
            else if (element.getSize() == 1){
                amountOfCards.setText(itemView.getResources().getString(R.string.one_card));
            }
            else {
                amountOfCards.setText(itemView.getResources().getString(R.string.empty_card_group));
            }
            this.cardGroup = element;
            nameTV.setText(this.cardGroup.getName());

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (linearLayout.getContext(), CardPagerActivity.class);
                    intent.putExtra("CardGroup", cardGroup);
                    linearLayout.getContext().startActivity(intent);
                }
            });
        }
    }
}