package com.dariahaze.learning_english.ui.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.dariahaze.learning_english.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar = findViewById(R.id.toolbarWebView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            webView.loadUrl(bundle.getString("HtmlPath"));
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
