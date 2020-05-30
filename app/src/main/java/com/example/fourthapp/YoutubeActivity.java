package com.example.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class YoutubeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        WebView webView = (WebView)
                findViewById(R.id.webview);
        webView.loadUrl("https://www.youtube.com/watch?v=IODxDxX7oi4");
    }
}
