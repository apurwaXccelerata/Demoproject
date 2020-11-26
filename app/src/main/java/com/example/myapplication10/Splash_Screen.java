package com.example.myapplication10;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Splash_Screen extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Thread th=new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent obj = new Intent(Splash_Screen.this, Login_Form.class);
                    startActivity(obj);
                }

            }
        };
        th.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }

    }
