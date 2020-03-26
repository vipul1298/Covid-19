package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Social extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        WebView web =findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        String url=getIntent().getExtras().getString("url");
        web.loadUrl(url);

        final ProgressDialog progress = new ProgressDialog(Social.this);
        progress.setMessage("Please wait...");
        progress.setIndeterminate(false);
        progress.setCancelable(false);
        progress.show();
        web.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progress.dismiss();
            }
        });
    }
}
