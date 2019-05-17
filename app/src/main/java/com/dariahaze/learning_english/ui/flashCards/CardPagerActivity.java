package com.dariahaze.learning_english.ui.flashCards;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.model.FlashCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class CardPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pager);

        Toolbar toolbar = findViewById(R.id.toolbarCardPager);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager cardsPager = findViewById(R.id.cardPager);
        FlashCardsPagerAdapter adapter = new FlashCardsPagerAdapter(
                getSupportFragmentManager(),
                new CardGroup("Group", new ArrayList<FlashCard>(Arrays.asList(
                        new FlashCard("AAA","AAA AAA"),
                        new FlashCard("BBB","BBB BBB"),
                        new FlashCard("CCC","CCC CCC"),
                        new FlashCard("DDD","DDD DDD"),
                        new FlashCard("EEE","EEE EEE"),
                        new FlashCard("FFF","FFF FFF")
                ))));
        cardsPager.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
