package com.venturetech.venture.butizon.UserLogin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.Adapters.User.AdapterSingleShopServices;
import com.venturetech.venture.butizon.Model.Model_Employee_Service;
import com.venturetech.venture.butizon.Model.ServiceRateRegister;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleShop extends AppCompatActivity {
    TextView name,address,phone;
    ImageView shopimage;
    String id,shopname,shopaddress,shopphone;
    RecyclerView recyclerView;
    ArrayList<Model_Employee_Service> services;
    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        setContentView(R.layout.single_shop);
        name =findViewById(R.id.shopname);
        address =findViewById(R.id.address);
        phone =findViewById(R.id.phone);
        button=findViewById(R.id.button);
        shopimage=findViewById(R.id.ShopImage);
        try {
            Intent intent = getIntent();
         id= intent.getStringExtra("id");
         shopaddress =intent.getStringExtra("address");
         shopphone =intent.getStringExtra("phone");
         shopname =intent.getStringExtra("name");
            byte[] imageBytes = Base64.decode(DBTransactionFunctions.getClubImage(id), Base64.DEFAULT);
            Bitmap mbitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            shopimage.setImageBitmap(mbitmap);
         name.setText(shopname);
         address.setText(shopaddress);
         phone.setText("Phone :"+shopphone);
            services = DBTransactionFunctions.getServiceEmployeeDataById(id);
            recyclerView = findViewById(R.id.services);
            int resId = R.anim.layoutanim;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            AdapterSingleShopServices adapter = new AdapterSingleShopServices(SingleShop.this,services,id);
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(SingleShop.this, resId);
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                final RatingBar ratingBar;
                Button cancel,submit;


                final Dialog dialog=new Dialog(SingleShop.this);
                dialog.setContentView(R.layout.rating);
                dialog.show();
                ratingBar=dialog.findViewById(R.id.ratingBar);
                cancel=dialog.findViewById(R.id.btnCancel);
                submit=dialog.findViewById(R.id.btnSubmit);

                submit.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        String rating=String.valueOf(ratingBar.getRating());
                        Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();


                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("clubid",id);
                        hashMap.put("userid",DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("rating",rating);



                        RetroInterface retro=retrofit.create(RetroInterface.class);
                        retro.clubrating(hashMap).enqueue(new Callback<ServiceRateRegister>()
                        {


                            @Override
                            public void onResponse(Call<ServiceRateRegister> call, Response<ServiceRateRegister> response)
                            {

                            }

                            @Override
                            public void onFailure(Call<ServiceRateRegister> call, Throwable t)
                            {
                            dialog.cancel();
                            }
                        });
                        }


                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                    }
                });



            }
        });
    }


}
