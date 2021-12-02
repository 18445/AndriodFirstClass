package com.example.androidfirstclass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    public final static String WAY = "way";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layerout);
        drawerLayout = this.findViewById(R.id.drawer_menu);
        linearLayout = this.findViewById(R.id.linearlayout);
        init();
        setting();
        //点击事件
        findViewById(R.id.open_drawer).setOnClickListener(v -> drawerLayout.openDrawer(Gravity.LEFT));
        //关闭事件
        findViewById(R.id.close_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
        //侧滑监听
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //设置拉出布局的宽度
                linearLayout.setX(slideOffset * drawerView.getWidth());

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }


        });
    }
    private void init(){
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
    }

    public void setting(){
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){

        case (R.id.button1):
            Toast.makeText(this, "事件一", Toast.LENGTH_SHORT).show();
            break;
        case (R.id.button2):
            Intent intent2 = new Intent(DrawerActivity.this,RecycleActivity.class);
            Bundle Data4 = new Bundle();
            Data4.putString(WAY,"WAY4");
            intent2.putExtras(Data4);
            startActivity(intent2);
            break;
        case (R.id.button3):
            Intent intent3 = new Intent(DrawerActivity.this,RecycleActivity.class);
            Bundle Data3 = new Bundle();
            Data3.putString(WAY,"WAY3");
            intent3.putExtras(Data3);
            startActivity(intent3);
            break;
        case (R.id.button4):
            Intent intent4 = new Intent(DrawerActivity.this,RecycleActivity.class);
            Bundle Data2 = new Bundle();
            Data2.putString(WAY,"WAY2");
            intent4.putExtras(Data2);
            startActivity(intent4);
            break;
        case (R.id.button5):
            Intent intent5 = new Intent(DrawerActivity.this,RecycleActivity.class);
            Bundle Data1 = new Bundle();
            Data1.putString(WAY,"WAY1");
            intent5.putExtras(Data1);
            startActivity(intent5);
            break;
        case (R.id.button6):
            Intent intent6 = new Intent(DrawerActivity.this,newIntent.class);
            startActivity(intent6);
            break;
        }
    }
}
