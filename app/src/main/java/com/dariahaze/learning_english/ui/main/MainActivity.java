package com.dariahaze.learning_english.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.model.CardGroup;
import com.dariahaze.learning_english.ui.flashCards.FlashCardsFragment;
import com.dariahaze.learning_english.ui.grammar.GrammarPagesFragment;
import com.dariahaze.learning_english.ui.registration.SignInActivity;
import com.dariahaze.learning_english.ui.statistics.StatisticsFragment;
import com.dariahaze.learning_english.ui.tests.TestsFragment;
import com.dariahaze.learning_english.ui.videoLessons.VideoLessonsFragment;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private boolean mainFragmentIsOpened = true;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("cards/admin/cardgroupID");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                CardGroup cardGroup = dataSnapshot.getValue(CardGroup.class);
                System.out.println(cardGroup);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("DB", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);





        if (currentUser == null){
            openSignInActivity();
        }
        else {


            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_content, MainFragment.newInstance()).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.getMenu().getItem(0).setChecked(true);

            View headerLayout = navigationView.getHeaderView(0);
            TextView usernameTV = headerLayout.findViewById(R.id.usernameTV);
            Log.d("SIGN_IN", "currentUser:" + currentUser.getDisplayName());
            usernameTV.setText(currentUser.getDisplayName());

        }
    }

    @Override
    public void onBackPressed() {
        System.out.println("mainFragmentIsOpened = " + mainFragmentIsOpened);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } /*else if (!mainFragmentIsOpened){
            fm.beginTransaction().replace(R.id.main_content, MainFragment.newInstance()).commit();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.getMenu().getItem(0).setChecked(true);
        } */else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_total) {
            fm.beginTransaction().replace(R.id.main_content, MainFragment.newInstance()).commit();
            mainFragmentIsOpened = true;
        } else if (id == R.id.nav_grammar_in_use) {
            fm.beginTransaction().replace(R.id.main_content, GrammarPagesFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_grammar_test) {
            fm.beginTransaction().replace(R.id.main_content, TestsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_flash_cards) {
            fm.beginTransaction().replace(R.id.main_content, FlashCardsFragment.newInstance()).commit();

            /*Intent intent = new Intent (getApplicationContext(), CardPagerActivity.class);
            startActivity(intent);*/
            mainFragmentIsOpened = false;
        }else if (id == R.id.nav_video_lessons) {
            fm.beginTransaction().replace(R.id.main_content, VideoLessonsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_user_statistics) {
            fm.beginTransaction().replace(R.id.main_content, StatisticsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_share) {
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            share.putExtra(Intent.EXTRA_SUBJECT, "This is really nice app for learning English!");
            share.putExtra(Intent.EXTRA_TEXT, Utils.APP_LINK);

            startActivity(Intent.createChooser(share, "Share link via..."));
        } else if (id == R.id.nav_sign_out) {
            mAuth.signOut();
            openSignInActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openSignInActivity(){
        Intent intent = new Intent(this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.startActivity (intent);
        this.finishActivity (0);
    }

}
