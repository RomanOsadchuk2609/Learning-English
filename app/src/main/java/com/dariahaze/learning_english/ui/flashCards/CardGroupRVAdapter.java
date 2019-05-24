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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
//TODO: DELETE FLESH CARDS ON DELETING CARD SET
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

        }

        public void setItem(CardGroup element) {
            this.cardGroup = element;
            nameTV.setText(this.cardGroup.getName());
            setAmountOfCards(cardGroup.getSize());

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


            if (currentUser!=null && !cardGroup.getPath().contains(Utils.getFormattedUserKey(currentUser.getEmail()))){
                menuButton.setVisibility(View.INVISIBLE);
                constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
            }
            else if (currentUser!=null && cardGroup.getPath().contains(Utils.getFormattedUserKey(currentUser.getEmail()))){
                constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        parent.getActivity().openContextMenu(menuButton);
                        return true;
                    }
                });
            }

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.menu_card_group, popup.getMenu());
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            MaterialDialog dialog;
            final String userKey = Utils.getFormattedUserKey(currentUser.getEmail());
            mCardGroupReference = FirebaseDatabase.getInstance().getReference(cardGroup.getPath());

            switch (item.getItemId()){
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
                                    EditText frontText,  backText;
                                    frontText = view.findViewById(R.id.dialogFrontText);
                                    backText = view.findViewById(R.id.dialogBackText);
                                    FlashCard flashCard = new FlashCard(frontText.getText()+"",
                                            backText.getText()+"");
                                    flashCard.setKey(userKey+"FlashCardId"+Utils.generateStringId());
                                    flashCard.setNumber(cardNumber[0]);

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
    }
}