package com.dariahaze.learning_english.ui.flashCards;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardPagerActivity extends AppCompatActivity {
    private CardGroup cardGroup;
    private Query mFlashCardsReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pager);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbarCardPager);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final ViewPager cardsPager = findViewById(R.id.cardPager);
        Bundle bundle = getIntent().getExtras();
        cardGroup = (CardGroup) bundle.get("CardGroup");

        String userKey = Utils.getFormattedUserKey(currentUser.getEmail());
        boolean isEditable = cardGroup.getKey().contains(userKey);
        List<FlashCard> flashCardList = new ArrayList<>();
        final FlashCardsPagerAdapter adapter = new FlashCardsPagerAdapter(
                getSupportFragmentManager(),flashCardList,cardGroup,isEditable);
        cardsPager.setAdapter(adapter);

        mFlashCardsReference = FirebaseDatabase.getInstance()
                .getReference("flashCards/"+cardGroup.getKey()).orderByChild("number");
        mFlashCardsReference.keepSynced(true);


        mFlashCardsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FlashCard flashCard = dataSnapshot.getValue(FlashCard.class);
                flashCard.setKey(dataSnapshot.getKey());
                flashCard.setPath("flashCards/"+cardGroup.getKey()+"/"+flashCard.getKey());
                adapter.addCard(flashCard);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                FlashCard flashCard = dataSnapshot.getValue(FlashCard.class);
                flashCard.setKey(dataSnapshot.getKey());
                flashCard.setPath("flashCards/"+cardGroup.getKey()+"/"+flashCard.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.flash_card_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } /*else if (id == R.id.edit_card) {
            Toast.makeText(getApplicationContext(),"Edit Card",Toast.LENGTH_SHORT).show();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
}
