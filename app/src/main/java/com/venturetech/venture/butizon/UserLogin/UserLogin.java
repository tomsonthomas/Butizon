package com.venturetech.venture.butizon.UserLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserApp.FirstPage;

public class UserLogin extends AppCompatActivity {
    ImageView login,register,backk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        login=findViewById(R.id.click);
        register=findViewById(R.id.register);
        backk=findViewById(R.id.back);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLogin.this,UserActivity.class);
                startActivity(intent);


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLogin.this,ShopRegister.class);
                startActivity(intent);

            }
        });
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserLogin.this,FirstPage.class);
                startActivity(intent);
            }
        });



    }
}
