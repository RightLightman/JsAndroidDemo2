package com.jiangtea.jsandroiddemo2;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebview = (WebView) findViewById(R.id.wv);

        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);//打开js和安卓通信
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                try {
                    //当页面加载完成后，调用js方法
                    //mWebview.loadUrl("javascript:方法名(参数)");
                    JSONObject json = new JSONObject();
                    json.put("name", "安卓");
                    json.put("city", "上海");
                    mWebview.loadUrl("javascript:showMessage("+json.toString()+")");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });


        mWebview.addJavascriptInterface(new JavaScriptMethods(this), "daihao");

        mWebview.loadUrl("file:///android_asset/index.html");//本地模板
//        mWebview.loadUrl("http://");//在线模板
    }
}
