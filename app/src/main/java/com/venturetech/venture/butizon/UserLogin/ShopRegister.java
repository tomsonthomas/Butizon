package com.venturetech.venture.butizon.UserLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.venturetech.venture.butizon.ClubApp.ClubMenu;
import com.venturetech.venture.butizon.ClubApp.PhotoGallary.MultiPhotoSelectActivity;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserApp.FirstPage;

public class ShopRegister extends AppCompatActivity {
    Button register;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        register=findViewById(R.id.login);
        textView=findViewById(R.id.photo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopRegister.this,MultiPhotoSelectActivity.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShopRegister.this,ClubMenu.class);
                startActivity(intent);
            }
        });


    }
}
