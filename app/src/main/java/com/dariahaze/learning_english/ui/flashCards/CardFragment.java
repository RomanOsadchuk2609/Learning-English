package com.dariahaze.learning_english.ui.flashCards;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.FlashCard;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wajahatkarim3.easyflipview.EasyFlipView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    private FlashCard flashCard;
    private int amountOfCards;
    private int cardIndex;
    private boolean isFrontSide = true, isEditable = false, isDeleted = false, isEditing = false;

    private EditText frontTextET, backTextET, descriptionET;
    private TextView cardIndexTV, frontTextTV, backTextTV, sideTV, descriptionTV, deletedTV;
    private ImageButton buttonSave, buttonRemove, buttonBack;
    private LinearLayout editorLayout, backReadLayout, backWriteLayout;
    private DatabaseReference mFlashCardReference;

    FlashCardsPagerAdapter adapter;

    public CardFragment() {
        // Required empty public constructor
    }


    public static CardFragment newInstance(FlashCard flashCard, int cardIndex, int amountOfCards,
                                           boolean isEditable, FlashCardsPagerAdapter adapter) {
        CardFragment fragment = new CardFragment();
        fragment.setAmountOfCards(amountOfCards);
        fragment.setCardIndex(cardIndex);
        fragment.setFlashCard(flashCard);
        fragment.setEditable(isEditable);
        fragment.setAdapter(adapter);
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

        cardIndexTV = view.findViewById(R.id.card_index_tv);
        frontTextTV = view.findViewById(R.id.card_front_tv);
        backTextTV = view.findViewById(R.id.card_back_tv);
        descriptionTV = view.findViewById(R.id.card_description_tv);
        sideTV = view.findViewById(R.id.card_front_back_tv);
        frontTextET = view.findViewById(R.id.editTextFront);
        backTextET = view.findViewById(R.id.editTextBack);
        descriptionET = view.findViewById(R.id.editTextDescription);
        backReadLayout = view.findViewById(R.id.backReadLayout);
        backWriteLayout = view.findViewById(R.id.backWriteLayout);
        deletedTV = view.findViewById(R.id.textViewCardDeleted);

        buttonSave = view.findViewById(R.id.imageButtonSaveCard);
        //buttonRemove = view.findViewById(R.id.imageButtonSaveCard);
        buttonBack = view.findViewById(R.id.imageButtonBack);

        editorLayout = view.findViewById(R.id.imageButtonLayout);

        flipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipView.flipTheView();
                isFrontSide = !isFrontSide;
                if (isFrontSide){
                    sideTV.setText("Front");
                } else {
                    sideTV.setText("Back");
                }
            }
        });

        cardIndexTV.setText(cardIndex + " of " + amountOfCards);
        if (flashCard!=null){
            frontTextTV.setText(flashCard.getFrontText());
            backTextTV.setText(flashCard.getBackText());
            frontTextET.setText(flashCard.getFrontText());
            backTextET.setText(flashCard.getBackText());
            descriptionTV.setText(flashCard.getDescription());
            descriptionET.setText(flashCard.getDescription());
            isDeleted = flashCard.isDeleted();
            if (isDeleted) {
                deletedTV.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit_card) {
            editCard();
            return true;
        } else if (id == R.id.delete_card){
            deleteCard();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteCard(){
        if (isEditable && !isDeleted && !isEditing){
            MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                    .title("Delete this card?")
                    .positiveText("Yes")
                    .negativeText("No")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            mFlashCardReference = FirebaseDatabase.getInstance()
                                    .getReference(flashCard.getPath());
                            mFlashCardReference.setValue(null);
                            adapter.deleteCard(flashCard);
                            Toast.makeText(getContext(),"Card will be deleted",Toast.LENGTH_LONG).show();
                            deletedTV.setVisibility(View.VISIBLE);
                            isDeleted = true;
                        }
                    }).show();
        } else if (isDeleted) {
            Toast.makeText(getContext(),"This card is already deleted!",Toast.LENGTH_LONG).show();
        } else if (isEditing) {
            Toast.makeText(getContext(),"This card is editing now!",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),"You can't delete cards of this set!",Toast.LENGTH_LONG).show();
        }
    }

    private void editCard(){
        if (isEditable && !isDeleted){
            isEditing = true;
            mFlashCardReference = FirebaseDatabase.getInstance()
                    .getReference(flashCard.getPath());

            final String oldFrontText = flashCard.getFrontText(),
                    oldBackText = flashCard.getBackText(),
                    oldDescription = flashCard.getDescription();

            final InputMethodManager imm = (InputMethodManager) getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            editorLayout.setVisibility(View.VISIBLE);
            frontTextTV.setVisibility(View.INVISIBLE);
            frontTextET.setVisibility(View.VISIBLE);
            backReadLayout.setVisibility(View.INVISIBLE);
            backWriteLayout.setVisibility(View.VISIBLE);

            if (isFrontSide){
                frontTextET.requestFocus();
                imm.showSoftInput(frontTextET, InputMethodManager.SHOW_IMPLICIT);
            } else {
                backTextET.requestFocus();
                imm.showSoftInput(backTextET, InputMethodManager.SHOW_IMPLICIT);
            }

            buttonBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isEditing = false;
                    frontTextTV.setText(oldFrontText);
                    backTextTV.setText(oldBackText);
                    descriptionTV.setText(oldDescription);

                    editorLayout.setVisibility(View.INVISIBLE);
                    backWriteLayout.setVisibility(View.INVISIBLE);
                    backReadLayout.setVisibility(View.VISIBLE);
                    frontTextET.setVisibility(View.INVISIBLE);
                    frontTextTV.setVisibility(View.VISIBLE);

                    frontTextET.setText(oldFrontText);
                    backTextET.setText(oldBackText);
                    descriptionET.setText(oldDescription);
                }
            });

            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isEditing = false;
                    flashCard.setFrontText(frontTextET.getText().toString());
                    flashCard.setBackText(backTextET.getText().toString());
                    flashCard.setDescription(descriptionET.getText().toString());

                    frontTextTV.setText(flashCard.getFrontText());
                    backTextTV.setText(flashCard.getBackText());
                    descriptionTV.setText(flashCard.getDescription());

                    editorLayout.setVisibility(View.INVISIBLE);
                    backWriteLayout.setVisibility(View.INVISIBLE);
                    backReadLayout.setVisibility(View.VISIBLE);
                    frontTextET.setVisibility(View.INVISIBLE);
                    frontTextTV.setVisibility(View.VISIBLE);

                    mFlashCardReference.setValue(flashCard);
                }
            });
        } else if (isDeleted) {
            Toast.makeText(getContext(),"This card is already deleted!",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(),"You can't edit cards of this set!",Toast.LENGTH_LONG).show();
        }
    }

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

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public FlashCardsPagerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(FlashCardsPagerAdapter adapter) {
        this.adapter = adapter;
    }
}
