package kz.sdu.hackernewsclient.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import kz.sdu.hackernewsclient.R;

public class PostWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_web);

        String url = getIntent().getStringExtra("url");

        WebView mWebView = findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.loadUrl(url);
    }
}
