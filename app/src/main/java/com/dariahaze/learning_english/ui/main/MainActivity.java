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
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private boolean mainFragmentIsOpened = true;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setStatusBarColor(getResources().getColor(R.color.colorVioletDark));

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        //02.06.2019 20:00
        if (new Date().getTime() > 1559505600000L){
            finish();
            System.exit(0);
        }

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            openSignInActivity();
        }
        else {

            fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.main_content, MainFragment.newInstance()).commit();
            init();
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }

    private void init(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.drawer_layout).setFitsSystemWindows(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        TextView usernameTV = headerLayout.findViewById(R.id.usernameTV);
        Log.d("SIGN_IN", "currentUser:" + currentUser.getDisplayName());
        usernameTV.setText(currentUser.getDisplayName());
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_total) {
            fm.beginTransaction().replace(R.id.main_content, MainFragment.newInstance()).commit();
            mainFragmentIsOpened = true;
            navigationView.getMenu().getItem(0).setChecked(true);
        } else if (id == R.id.nav_grammar_in_use) {
            setTheme(R.style.AppThemePink);
            setContentView(R.layout.activity_main);
            init();
            navigationView.getMenu().getItem(1).setChecked(true);
            fm.beginTransaction().replace(R.id.main_content, GrammarPagesFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_grammar_test) {
            setTheme(R.style.AppThemeViolet);
            setContentView(R.layout.activity_main);
            init();
            navigationView.getMenu().getItem(2).setChecked(true);
            fm.beginTransaction().replace(R.id.main_content, TestsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_flash_cards) {
            setTheme(R.style.AppThemeBlue);
            setContentView(R.layout.activity_main);
            init();
            navigationView.getMenu().getItem(3).setChecked(true);
            fm.beginTransaction().replace(R.id.main_content, FlashCardsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        }else if (id == R.id.nav_video_lessons) {
            setTheme(R.style.AppThemeGreen);
            setContentView(R.layout.activity_main);
            init();
            fm.beginTransaction().replace(R.id.main_content, VideoLessonsFragment.newInstance()).commit();
            mainFragmentIsOpened = false;
        } else if (id == R.id.nav_user_statistics) {
            setTheme(R.style.AppThemeYellow);
            setContentView(R.layout.activity_main);
            init();
            navigationView.getMenu().getItem(4).setChecked(true);
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
