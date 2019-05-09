package com.venturetech.venture.butizon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.venturetech.venture.butizon.StartActivity.Sliding;

public class Choosing extends AppCompatActivity {
    TextView chose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
       chose=findViewById(R.id.text);
        chose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Choosing.this,Sliding.class);
                startActivity(intent);
            }
        });
    }
}
