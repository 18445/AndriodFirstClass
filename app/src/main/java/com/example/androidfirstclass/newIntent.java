package com.example.androidfirstclass;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class newIntent extends AppCompatActivity {
    private Button mBtnNot;
    private int mWinWidth; // 屏幕宽度值
    private int mWinHeight; // 屏幕高度值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        initView();
        initEvent();
        hide();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        mWinWidth = displayMetrics.widthPixels;
        mWinHeight = displayMetrics.heightPixels;
    }
    private void initView() {
        mBtnNot = findViewById(R.id.button);
    }
    private void initEvent() {
        mBtnNot.setOnClickListener(v -> {
            float x = new Random().nextInt(mWinWidth- 20 );
            float y = new Random().nextInt(mWinHeight- 20 );
            v.setX(x);
            v.setY(y);
        });
    }
    private void hide(){
        ActionBar actionBar=getSupportActionBar();
            if(actionBar!=null){
            actionBar.hide();
        }
    }
}
