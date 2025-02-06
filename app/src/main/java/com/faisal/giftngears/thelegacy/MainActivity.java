package com.faisal.giftngears.thelegacy;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ConstraintLayout mainLayout = findViewById(R.id.main);

        mainLayout.setOnApplyWindowInsetsListener((view, insets) -> {
            int statusBarHeight = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                statusBarHeight = insets.getSystemGestureInsets().top;
            }
            view.setPadding(0, statusBarHeight, 0, 0);
            return insets;
        });

        WebView webView = findViewById(R.id.webview);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.loadUrl("https://genzis.store/");
    }

    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webview);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}