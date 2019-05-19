package com.dariahaze.learning_english.ui.flashCards;

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
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CardGroupRVAdapter  extends RecyclerView.Adapter<CardGroupRVAdapter.ViewHolder> {
    private List<CardGroup> dataSet;
    private Fragment parent;
    private DatabaseReference mCardGroupReference;
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

            switch (item.getItemId()){
                case R.id.add_card:
                    Toast.makeText(parent.getContext(),"Add Card", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rename:
                    dialog = new MaterialDialog.Builder(constraintLayout.getContext())
                            .title("Rename card set \""+cardGroup.getName()+"\"")
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
                                    mCardGroupReference = FirebaseDatabase.getInstance().getReference(cardGroup.getPath());
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
                                    mCardGroupReference = FirebaseDatabase.getInstance().getReference(cardGroup.getPath());
                                    mCardGroupReference.setValue(null);
                                    dataSet.remove(getAdapterPosition());
                                    notifyItemRemoved(getAdapterPosition());
                                }
                            }).show();
                    break;
            }

            return true;
        }
    }
}