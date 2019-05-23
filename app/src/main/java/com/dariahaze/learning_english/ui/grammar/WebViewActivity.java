package com.dariahaze.learning_english.ui.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.dariahaze.learning_english.R;

import java.util.Objects;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if (bundle.getString("HtmlPath")!=null){
            String path = bundle.getString("HtmlPath");
            String fileName = path.substring(path.lastIndexOf('/')+1,path.lastIndexOf('.'));
            path = path.replaceFirst(fileName,fileName.toUpperCase());
            webView.loadUrl(path);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
