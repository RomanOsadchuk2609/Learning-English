package com.dariahaze.learning_english.ui.grammar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.dariahaze.learning_english.R;
import com.dariahaze.learning_english.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {


    private DatabaseReference statisticsReference;
    private DatabaseReference learnedTopicsReference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private int learnedTopics=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        System.out.println("ON CREATE");

        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbarRandomTests);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        TextView topicLarge = findViewById(R.id.topicLargeTV);
        TextView topicSmall = findViewById(R.id.topicSmallTV);
        WebView webView = findViewById(R.id.topicWV);

        if (bundle.getString("TopicLarge")!=null){
            topicLarge.setText(bundle.getString("TopicLarge"));
        }
        if (bundle.getString("TopicSmall")!=null){
            topicSmall.setText(bundle.getString("TopicSmall"));
        }
        String path = bundle.getString("HtmlPath");
        learnedTopicsReference = FirebaseDatabase.getInstance().getReference(
                "learnedTopics/" + Utils.getFormattedUserKey(currentUser.getEmail()) +"/"
                        + Utils.getFormattedTopicPath(path));
        String fileName = path.substring(path.lastIndexOf('/')+1,path.lastIndexOf('.'));
        path = path.replaceFirst(fileName,fileName.toUpperCase());
        webView.loadUrl(path);

        statisticsReference = FirebaseDatabase.getInstance().getReference("statistics/"
                + Utils.getFormattedUserKey(currentUser.getEmail()) + "/topics");

        statisticsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer topics = dataSnapshot.getValue(Integer.class);
                if (topics!=null){
                    learnedTopics = topics;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        System.out.println("onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_learned, menu);

        learnedTopicsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean isChecked = dataSnapshot.getValue(Boolean.class);
                if (isChecked!=null){
                    menu.findItem(R.id.item_learned).setChecked(isChecked);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId() == R.id.item_learned) {
            item.setChecked(!item.isChecked());
            learnedTopicsReference.setValue(item.isChecked());
            if (item.isChecked()){
                learnedTopics++;
                statisticsReference.setValue(learnedTopics);
            } else {
                learnedTopics--;
                statisticsReference.setValue(learnedTopics);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
