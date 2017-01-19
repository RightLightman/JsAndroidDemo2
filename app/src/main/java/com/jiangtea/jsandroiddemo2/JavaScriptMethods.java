package com.jiangtea.jsandroiddemo2;

import android.content.Context;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class JavaScriptMethods {
    private Context context;
    private Handler mHandler = new Handler();
    public JavaScriptMethods(Context context) {
        this.context = context;
    }

    @JavascriptInterface //android4.2之后，如果不加上该注解，js无法调用android方法（安全）
    public void showToast(final String json) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
