package com.sxau.novel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.view.WindowManager;

public class WelcomeActivity extends BaseActivity {
    private Timer Timer = new Timer();
    private static final int times = 3000;
    private CircleProgressbar CircleProgressBar;
    private long OPEN_SCREEN_TIME=3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        CircleProgressBar = (CircleProgressbar) findViewById(R.id.skipBtn);
        CircleProgressBar.setTimeMillis(OPEN_SCREEN_TIME);
        //CircleProgressBar 播放动画
        CircleProgressBar.play();
        //mCircleProgressBar 跳过按钮点击事件
        CircleProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,LoginMainActivity.class);
                startActivity(intent);
            }
        });

        //使用计时器Task来等待秒跳转
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginMainActivity.class);
                startActivity(intent);
            }
        };
        Timer.schedule(timerTask,OPEN_SCREEN_TIME);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginMainActivity.class);
                startActivity(intent);
                //切换Activity时的动画,这个函数有两个参数，一个参数是第一个activity进入时的动画，另外一个参数则是第二个activity退出时的动画。
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        }, times);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
