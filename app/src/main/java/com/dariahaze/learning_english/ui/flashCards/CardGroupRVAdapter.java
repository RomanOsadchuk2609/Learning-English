package com.dariahaze.learning_english.ui.flashCards;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CardGroupRVAdapter  extends RecyclerView.Adapter<CardGroupRVAdapter.ViewHolder> {
    private List<CardGroup> dataSet;
    private Fragment parent;
    private DatabaseReference mCardGroupReference;
    private DatabaseReference mFlashCardsReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public CardGroupRVAdapter(List<CardGroup> dataSet, Fragment parent) {
        this.dataSet = dataSet;
        this.parent = parent;
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
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
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
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


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {
        private CardGroup cardGroup;
        private ConstraintLayout constraintLayout;
        private TextView amountOfCards, nameTV;
        private ImageButton menuButton;
        private boolean isChecked = false;
        private boolean isMutable;
        private DatabaseReference learnedCardGroupsReference ;
        private DatabaseReference statisticsCardSets ;
        private int learnedSets = 0;
        private int maxCardNumber;

        public ViewHolder(ConstraintLayout itemView) {
            super(itemView);
            constraintLayout = itemView;
            amountOfCards = constraintLayout.findViewById(R.id.amount_of_cards);
            nameTV = constraintLayout.findViewById(R.id.card_group_name);
            menuButton = constraintLayout.findViewById(R.id.imageButtonContextMenu);
            menuButton.setOnCreateContextMenuListener(this);
            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.getActivity().openContextMenu(menuButton);
                }
            });

            statisticsCardSets = FirebaseDatabase.getInstance().getReference("statistics/" +
                    Utils.getFormattedUserKey(currentUser.getEmail()) + "/cardSets");

            statisticsCardSets.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Integer amount = dataSnapshot.getValue(Integer.class);
                    if (amount!=null){
                        learnedSets = amount;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        public void setItem(CardGroup element) {
            this.cardGroup = element;
            nameTV.setText(this.cardGroup.getName());
            maxCardNumber = cardGroup.getMaxCardNumber();
            setAmountOfCards(cardGroup.getSize());

            learnedCardGroupsReference = FirebaseDatabase.getInstance()
                    .getReference("learnedCardSets/" +
                            Utils.getFormattedUserKey(currentUser.getEmail()) + "/" +
                            cardGroup.getKey());

            learnedCardGroupsReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean isLearned = dataSnapshot.getValue(Boolean.class);
                    if (isLearned!=null){
                        isChecked = isLearned;
                        setBackground();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cardGroup.getSize() == 0){
                        Toast.makeText(v.getContext(), "Card set is empty!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent (constraintLayout.getContext(), CardPagerActivity.class);
                        intent.putExtra("CardGroup", cardGroup);
                        constraintLayout.getContext().startActivity(intent);
                    }
                }
            });
            constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    parent.getActivity().openContextMenu(menuButton);
                    return true;
                }
            });

            isMutable = currentUser!=null && cardGroup.getPath().contains(Utils.getFormattedUserKey(currentUser.getEmail()));

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.menu_card_group, popup.getMenu());
            popup.getMenu().findItem(R.id.item_learned_group).setChecked(isChecked);
            if (!isMutable) {
                popup.getMenu().findItem(R.id.add_card).setVisible(false);
                popup.getMenu().findItem(R.id.rename).setVisible(false);
                popup.getMenu().findItem(R.id.delete).setVisible(false);
            }
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            MaterialDialog dialog;
            final String userKey = Utils.getFormattedUserKey(currentUser.getEmail());
            mCardGroupReference = FirebaseDatabase.getInstance().getReference(cardGroup.getPath());

            switch (item.getItemId()){
                case R.id.item_learned_group:
                    isChecked = !isChecked;
                    item.setChecked(!item.isChecked());
                    if (isChecked){
                        learnedSets++;
                    } else if (learnedSets>0){
                        learnedSets --;
                    }
                    statisticsCardSets.setValue(learnedSets);
                    setBackground();
                    learnedCardGroupsReference.setValue(isChecked);
                    break;
                case R.id.add_card:
                    final int[] cardNumber = {cardGroup.getSize() + 1};
                    dialog = new MaterialDialog.Builder(constraintLayout.getContext())
                            .autoDismiss(false)
                            .title("Add card #"+ cardNumber[0])
                            .customView(R.layout.dialog_add_flash_card, true)
                            .positiveText("Add")
                            .negativeText("Close")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    View view = dialog.getCustomView();
                                    EditText frontText,  backText, description;
                                    frontText = view.findViewById(R.id.dialogFrontText);
                                    backText = view.findViewById(R.id.dialogBackText);
                                    description = view.findViewById(R.id.dialogDescription);
                                    FlashCard flashCard = new FlashCard(frontText.getText()+"",
                                            backText.getText()+"");
                                    flashCard.setDescription(description.getText().toString());
                                    flashCard.setKey(userKey+"FlashCardId"+Utils.generateStringId());
                                    maxCardNumber++;
                                    flashCard.setNumber(maxCardNumber);
                                    cardGroup.setMaxCardNumber(maxCardNumber);

                                    mFlashCardsReference = FirebaseDatabase.getInstance()
                                            .getReference("flashCards/"+cardGroup.getKey()+"/"+flashCard.getKey());
                                    mFlashCardsReference.setValue(flashCard);
                                    cardGroup.setSize(cardGroup.getSize()+1);
                                    mCardGroupReference.setValue(cardGroup);
                                    notifyItemChanged(getAdapterPosition());
                                    cardNumber[0]++;
                                    dialog.setTitle("Add card #"+ cardNumber[0]);
                                    frontText.getText().clear();
                                    backText.getText().clear();
                                    description.getText().clear();
                                    setAmountOfCards(cardNumber[0]-1);
                                    frontText.requestFocus();
                                    InputMethodManager imm = (InputMethodManager) dialog.getContext()
                                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.showSoftInput(frontText, InputMethodManager.SHOW_IMPLICIT);
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    notifyDataSetChanged();
                                }
                            })
                            .show();
                    break;
                case R.id.rename:
                    dialog = new MaterialDialog.Builder(constraintLayout.getContext())
                            .title("Rename card set \""+cardGroup.getName()+"\"  ")
                            .positiveText("Rename")
                            .negativeText("Cancel")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .input("New Name", "", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    // Do something
                                }
                            })
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    cardGroup.setName(dialog.getInputEditText().getText().toString());

                                    mCardGroupReference.setValue(cardGroup);
                                    notifyItemChanged(getAdapterPosition());
                                }
                            }).show();
                    break;
                case R.id.delete:
                    dialog = new MaterialDialog.Builder(constraintLayout.getContext())
                            .title("Delete card set \""+cardGroup.getName()+"\"?")
                            .positiveText("Yes")
                            .negativeText("No")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    mCardGroupReference.setValue(null);
                                    mFlashCardsReference = FirebaseDatabase.getInstance()
                                            .getReference("flashCards/"+cardGroup.getKey());
                                    mFlashCardsReference.setValue(null);
                                    learnedCardGroupsReference.setValue(null);
                                    if (isChecked){
                                        learnedSets--;
                                        statisticsCardSets.setValue(learnedSets);
                                    }
                                    dataSet.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            }).show();
                    break;
            }

            return true;
        }

        private void setAmountOfCards(int amount){
            if (amount>1){
                amountOfCards.setText(amount + " cards");
            }
            else if (cardGroup.getSize() == 1){
                amountOfCards.setText(itemView.getResources().getString(R.string.one_card));
            }
            else {
                amountOfCards.setText(itemView.getResources().getString(R.string.empty_card_group));
            }
        }

        private void setBackground(){
            if (isChecked){
                constraintLayout.setBackground(constraintLayout.getContext()
                        .getDrawable(R.drawable.item_background_large_learned));
            }
            else {
                constraintLayout.setBackground(constraintLayout.getContext()
                        .getDrawable(R.drawable.item_background_large));
            }
        }
    }
}