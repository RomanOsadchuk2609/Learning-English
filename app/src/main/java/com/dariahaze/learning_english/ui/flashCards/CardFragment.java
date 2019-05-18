package com.dariahaze.learning_english.ui.flashCards;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.FlashCard;
import com.wajahatkarim3.easyflipview.EasyFlipView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    private FlashCard flashCard;
    private boolean isFront = true;
    private int amountOfCards;
    private int cardIndex;
    private boolean isFrontSide = true;

    public CardFragment() {
        // Required empty public constructor
    }


    public static CardFragment newInstance(FlashCard flashCard, int cardIndex, int amountOfCards) {
        CardFragment fragment = new CardFragment();
        fragment.setAmountOfCards(amountOfCards);
        fragment.setCardIndex(cardIndex);
        fragment.setFlashCard(flashCard);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EasyFlipView flipView = view.findViewById(R.id.flipView);

        flipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipView.flipTheView();
                isFrontSide = !isFrontSide;
            }
        });

        TextView cardIndexTV, frontTextTV, backTextTV;
        cardIndexTV = view.findViewById(R.id.card_index_tv);
        frontTextTV = view.findViewById(R.id.card_front_tv);
        backTextTV = view.findViewById(R.id.card_back_tv);

        cardIndexTV.setText(cardIndex + " of " + amountOfCards);
        if (flashCard!=null){
            frontTextTV.setText(flashCard.getFrontText());
            backTextTV.setText(flashCard.getBackText());
        }
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.edit_card) {
            Toast.makeText(getContext(),"Edit Card",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public FlashCard getFlashCard() {
        return flashCard;
    }

    public void setFlashCard(FlashCard flashCard) {
        this.flashCard = flashCard;
    }

    public int getAmountOfCards() {
        return amountOfCards;
    }

    public void setAmountOfCards(int amountOfCards) {
        this.amountOfCards = amountOfCards;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }
}
