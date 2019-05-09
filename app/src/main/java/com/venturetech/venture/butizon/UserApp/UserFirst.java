package com.venturetech.venture.butizon.UserApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Splash;
import com.venturetech.venture.butizon.StartActivity.Sliding;
import com.venturetech.venture.butizon.UserLogin.UserActivity;

public class UserFirst extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_first);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {


                    Intent i = new Intent(UserFirst.this,
                            FirstPage.class);

                    startActivity(i);

                }
            }
        };
        timer.start();

    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}

