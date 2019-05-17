package com.venturetech.venture.butizon.UserLogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.venturetech.venture.butizon.Adapters.User.AdapterViewAllShops;
import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public  class Nearbyshops extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Shop_Service_Details> shop_service_details;
    ArrayList<Model_Club> shop_details,temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.nearbyshops);
        recyclerView = findViewById(R.id.Sevicelist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText =findViewById(R.id.search_service);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(Nearbyshops.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        shop_details= DBTransactionFunctions.getNearByShop();
        AdapterViewAllShops adapter = new AdapterViewAllShops( Nearbyshops.this, shop_details );
        recyclerView.setAdapter(adapter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                for (int i =0;i<shop_details.size();i++){
                    if(shop_details.get(i).getName().contains(s)){

                  ///   temp.add(new Model_Club(shop_details.get(i).getId(),shop_details.get(i).getId(),shop_details.get(i).getId()))
                    }
                }

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
