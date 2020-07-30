package com.example.convenientweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Slide slide=new Slide();
        slide.setSlideEdge(Gravity.LEFT);

        //进入动画
        getWindow().setEnterTransition(slide);
        //退出动画
        getWindow().setExitTransition(slide);

        //利用timer让此界面延迟3秒后跳转，timer有一个线程，这个线程不断执行task
        Timer timer=new Timer();//Timer类是JDK中提供的一个定时器功能，使用时会在主线程之外开启一个单独的线程执行指定任务，任务可以执行一次或者多次
        //TimerTask实现runnable接口，TimerTask类表示在一个指定时间内执行的task
        TimerTask task=new TimerTask() {
            @Override
            public void run() {//跳转主界面的任务代码写在TimerTask的run()方法中
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                finishAfterTransition();
            }
        };
        timer.schedule(task,1000);//timer.schedule用于开启TimerTask类 传递两个参数，第一个参数为TimerTask的对象，第二个参数为TimerTask和run()之间的时间差为3秒。
        //设置这个task在延迟3秒后自动执行，4秒就改为4000

    }
}