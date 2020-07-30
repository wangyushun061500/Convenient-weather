package com.example.convenientweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends /*AppCompat*/Activity {
    private WebView webView;
    private TextView textView;
//    private String mareLoginUrl = "file:///android_asset/index.html";
    private String mareLoginUrl = "https://m.moji.com/  ";

    @Override
    protected void onCreate(Bundle savedInsstanceState){
        super.onCreate(savedInsstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        

        webView = (WebView) findViewById(R.id.webview1);
        textView = (TextView) findViewById(R.id.tv0);

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.loadUrl(mareLoginUrl);

        //覆盖WebView默认使用第三方或者系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        Timer timer=new Timer();//Timer类是JDK中提供的一个定时器功能，使用时会在主线程之外开启一个单独的线程执行指定任务，任务可以执行一次或者多次
        TimerTask task=new TimerTask() {
            public void run() {//跳转主界面的任务代码写在TimerTask的run()方法中
                webView.post(new Runnable(){
                    @Override
                    public void run() {
                        webView.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                    }
                });
            }
        };
        timer.schedule(task,1500);//timer.schedule用于开启TimerTask类 传递两个参数，第一个参数为TimerTask的对象，第二个参数为TimerTask和run()之间的时间差为3秒。
        //设置这个task在延迟3秒后自动执行，4秒就改为4000

    }
    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;

        }

        return  super.onKeyDown(keyCode, event);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            // 隐藏导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            // 全屏(隐藏状态栏)
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            // 使用沉浸式必须加这个flag
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }

}

