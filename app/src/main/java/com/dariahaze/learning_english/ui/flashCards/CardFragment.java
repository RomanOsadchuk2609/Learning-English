package com.dariahaze.learning_english.ui.flashCards;


import android.annotation.SuppressLint;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private boolean isButtonsVisible = false;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EasyFlipView flipView = view.findViewById(R.id.flipView);

        final TextView cardIndexTV, frontTextTV, backTextTV, sideTV;
        cardIndexTV = view.findViewById(R.id.card_index_tv);
        frontTextTV = view.findViewById(R.id.card_front_tv);
        backTextTV = view.findViewById(R.id.card_back_tv);
        sideTV = view.findViewById(R.id.card_front_back_tv);

        final EditText frontTextET, backTextET;
        frontTextET = view.findViewById(R.id.editTextFront);
        backTextET = view.findViewById(R.id.editTextBack);

        final ImageButton buttonSave, buttonRemove, buttonBack;
        buttonSave = view.findViewById(R.id.imageButtonSaveCard);
        buttonRemove = view.findViewById(R.id.imageButtonSaveCard);
        buttonBack = view.findViewById(R.id.imageButtonBack);

        flipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipView.flipTheView();
                if (isFrontSide){
                    sideTV.setText("Front");
                } else {
                    sideTV.setText("Back");
                }
                isFrontSide = !isFrontSide;
            }
        });

        flipView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.findViewById(R.id.imageButtonLayout).setVisibility(View.VISIBLE);
                frontTextET.setVisibility(View.VISIBLE);
                backTextET.setVisibility(View.VISIBLE);
                frontTextTV.setVisibility(View.INVISIBLE);
                backTextTV.setVisibility(View.INVISIBLE);
                return true;
            }
        });


        cardIndexTV.setText(cardIndex + " of " + amountOfCards);
        if (flashCard!=null){
            frontTextTV.setText(flashCard.getFrontText());
            backTextTV.setText(flashCard.getBackText());
            frontTextET.setText(flashCard.getFrontText());
            backTextET.setText(flashCard.getBackText());
        }

        /*frontTextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.imageButtonLayout).setVisibility(View.VISIBLE);
                frontTextET.setVisibility(View.VISIBLE);
                backTextET.setVisibility(View.VISIBLE);
                frontTextTV.setVisibility(View.INVISIBLE);
                backTextTV.setVisibility(View.INVISIBLE);
            }
        });


        backTextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.findViewById(R.id.imageButtonLayout).setVisibility(View.VISIBLE);
                frontTextET.setVisibility(View.VISIBLE);
                backTextET.setVisibility(View.VISIBLE);
                frontTextTV.setVisibility(View.INVISIBLE);
                backTextTV.setVisibility(View.INVISIBLE);
            }
        });*/


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
