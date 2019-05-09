package com.venturetech.venture.butizon.UserLogin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.venturetech.venture.butizon.Adapters.User.AdapterActivityViewAllServices;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;


public class ActivityViewAllServices extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Shop_Service_Details> shop_service_details,temp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        setContentView(R.layout.activity_viewa_ll_services);


        recyclerView = findViewById(R.id.Sevicelist);
        editText =findViewById(R.id.search_service);
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        shop_service_details = DBTransactionFunctions.getShopServiceEmployeeData();
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(ActivityViewAllServices.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterActivityViewAllServices adapter = new AdapterActivityViewAllServices(ActivityViewAllServices.this,shop_service_details);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(adapter);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager keyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(editText, 0);
                return true;
            }

        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                temp = new ArrayList<Shop_Service_Details>();
                String name=editText.getText().toString();
                for(int i= 0 ; i< shop_service_details.size() ;i++){
                    if((shop_service_details.get(i).getService_name().toLowerCase().contains(name.toLowerCase()))){
                        temp.add(new Shop_Service_Details(shop_service_details.get(i).getId(),
                                shop_service_details.get(i).getClub_name(),
                                shop_service_details.get( i ).getCategory(),
                                shop_service_details.get(i).getShop_Mobile(),
                                shop_service_details.get(i).getCity(),
                                shop_service_details.get(i).getStreet(),
                                shop_service_details.get(i).getEmail(),
                                shop_service_details.get(i).getState(),
                                shop_service_details.get(i).getId_Service(),
                                shop_service_details.get(i).getService_name(),
                                shop_service_details.get(i).getRate(),
                                shop_service_details.get(i).getId_Emp(),
                                shop_service_details.get(i).getEmp_name(),
                                shop_service_details.get(i).getEmp_mobile()
                        ));
                    }
                }
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layoutanim);
                recyclerView.setLayoutAnimation(animation);
                AdapterActivityViewAllServices adapter = new AdapterActivityViewAllServices(ActivityViewAllServices.this,temp);
                recyclerView.setAdapter(adapter);

            }
        });
    }
}
