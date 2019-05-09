package com.venturetech.venture.butizon.UserLogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.venturetech.venture.butizon.Adapters.User.AdapterActivityViewAllServices;
import com.venturetech.venture.butizon.Adapters.User.AdapterViewAllShops;
import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class ViewAllShops extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Model_Club> shop_details,temp;
    AdapterViewAllShops adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        setContentView(R.layout.activity_viewa_ll_services);
        recyclerView = findViewById(R.id.Sevicelist);
        editText =findViewById(R.id.search_service);
         shop_details=DBTransactionFunctions.getClubData();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(ViewAllShops.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        adapter = new AdapterViewAllShops(ViewAllShops.this,shop_details);
        recyclerView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                temp =new ArrayList<Model_Club>();
            String name = editText.getText().toString();
            for(int i = 0 ; i<shop_details.size();i++){
            if(shop_details.get(i).getName().toLowerCase().contains(name.toLowerCase())){
            temp.add(new Model_Club(shop_details.get(i).getId(),
            shop_details.get(i).getName(),
            shop_details.get( i ).getCategory(),
            shop_details.get(i).getMobile(),
            shop_details.get(i).getEmail(),
            shop_details.get(i).getWebsite(),
            shop_details.get(i).getStreet(),
            shop_details.get(i).getCity(),
            shop_details.get(i).getState(),
            shop_details.get(i).getCountry(),
            shop_details.get(i).getDistrict()));
            }
            }
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layoutanim);
                recyclerView.setLayoutAnimation(animation);
            adapter = new AdapterViewAllShops(ViewAllShops.this,temp);
            recyclerView.setAdapter(adapter);

            }
            });
    }
}