package com.ete.listacombanco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ete.listacombanco.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background = new Thread() {
            public void run() {
                try {

                    sleep(5000);

                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);


                    finish();
                } catch (Exception e) {
                    Log.e("ERRO",e.getMessage());
                }
            }
        };
        // start thread
        background.start();
    }
}