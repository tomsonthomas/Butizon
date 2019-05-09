package com.venturetech.venture.butizon.ClubApp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.venturetech.venture.butizon.Model.ServiceClubRegister;
import com.venturetech.venture.butizon.Model.ServiceUserRegister;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText nam,mob,age,addr,ema,pass,website,city,street,country,state,district;
    TextInputLayout l1,l2,l3,l4,l5,l6,l7;
    Button regi;
    String email;
    RadioButton radioButton;
    LinearLayout layout,layout1;
    RadioButton radioButton1,radioButton2;
    RadioGroup radioGroup,group,categoryGroup;

    String usertype,uname,uage,umobile,uaddress,upassword,uemail,uwebsite,ucity,ustreet,ucountry,ustate,udistrict,upass,gender="Male",category="Men";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_first);
        android.support.v7.app.ActionBar a = getSupportActionBar();
        a.hide();
l1=findViewById(R.id.lay1);
l2=findViewById(R.id.lay2);
l3=findViewById(R.id.lay3);
l4=findViewById(R.id.lay4);
l5=findViewById(R.id.lay5);
l6=findViewById(R.id.lay6);


        nam=findViewById(R.id.name);
        mob=findViewById(R.id.mobile);
        age=findViewById(R.id.age);
        addr=findViewById(R.id.address);
        ema=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        website=findViewById(R.id.Website);
        street=findViewById(R.id.Street);
        state=findViewById(R.id.State);
        city=findViewById(R.id.City);
        layout= findViewById(R.id.genderlayout);
        layout1=findViewById( R.id.categorylayout );
        country=findViewById(R.id.Country);
        district=findViewById(R.id.District);
        regi=findViewById(R.id.login);
        group=findViewById(R.id.gender);
        radioGroup =findViewById(R.id.radiogroup);
        categoryGroup=findViewById( R.id.category );
        usertype ="1";
        layout.setVisibility(View.GONE);
        age.setVisibility(View.GONE);
        addr.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radiobuton1) {
                    l1.setVisibility(View.VISIBLE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.VISIBLE);
                    l4.setVisibility(View.VISIBLE);
                    l5.setVisibility(View.VISIBLE);
                    l6.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.GONE);
                    layout1.setVisibility( View.VISIBLE );
                    age.setVisibility(View.GONE);
                    addr.setVisibility(View.GONE);
                    usertype ="1";

                } else if(checkedId == R.id.radiobuton2) {
                    layout1.setVisibility( View.GONE );
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.GONE);
                    l4.setVisibility(View.GONE);
                    l5.setVisibility(View.GONE);
                    l6.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    age.setVisibility(View.VISIBLE);
                    addr.setVisibility(View.VISIBLE);
                    usertype ="0";

                }
            }

        });
        categoryGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId == R.id.men) {
                    category ="Men";
                } else if(checkedId == R.id.women) {

                    category ="Women and kids";

                }else if(checkedId == R.id.both) {

                    category ="Both";

                }

            }
        } );
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.male) {
                    gender ="Male";
                } else if(checkedId == R.id.female) {

                    gender ="Female";

                }else if(checkedId == R.id.other) {

                    gender ="Other";

                }
            }

        });
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog progressBar = new Dialog(Register.this);
                progressBar.setContentView(R.layout.progressdialogue);
                progressBar.show();
                boolean flag = true;
                uname = nam.getText().toString();
                 uemail = ema.getText().toString();
                 upass = pass.getText().toString();
                  uage= age.getText().toString();
                 umobile = mob.getText().toString();
                 uaddress = addr.getText().toString();
                 uwebsite = website.getText().toString();
                 ucity = city.getText().toString();
                 ustreet = street.getText().toString();
                 ucountry = country.getText().toString();
                 ustate = state.getText().toString();
                 udistrict = district.getText().toString();
                 if(usertype.equals("1")){
                     if (uname != null && uname.length() < 3)
                     {
                         nam.setError("name should contain atleast 3 characters");
                         flag= false;
                         progressBar.cancel();

                     }
                     if (umobile!= null && umobile.length() < 10) {
                         mob.setError("Invalid mobile");
                         flag= false;
                         progressBar.cancel();
                     }
                     if (ustreet!=null&&ustreet.length()<3)
                     {
                         street.setError(" street name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();
                     }

                     if (ucity!=null&&ucity.length()<3)
                     {
                         city.setError("city name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();
                     }
                     if (udistrict!=null&&udistrict.length()<3)
                     {
                         district.setError("district name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();
                     }
                     if (ustate!=null&&ustate.length()<3)
                     {
                         state.setError("state name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();
                     }
                     if (ucountry!=null&&ucountry.length()<3)
                     {
                         country.setError("country name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();
                     }

                     if (isValidMail(ema.getText().toString())) {
                         email = ema.getText().toString();

                     } else {
                         ema.setError("Invalid Email Id");
                         progressBar.cancel();
                     }
                     if(DBTransactionFunctions.isEmailFound("tb_club","email",ema.getText().toString()))
                     {
                         email = ema.getText().toString();
                     }
                     else
                         {
                         ema.setError("Email Id already exist");
                             progressBar.cancel();

                             return;

                     }
                     if (!isValidPassword( upass ))
                     {
                         pass.setError("password must contain integer,special characters, upper and lower case letters");
                         flag=false;
                         progressBar.cancel();

                     }

                 }else {
                     if (uname != null && uname.length()<3)
                     {
                         nam.setError("name should contain atleast 3 characters");
                         flag=false;
                         progressBar.cancel();

                     }
                     if (umobile!= null && umobile.length() < 10)
                     {
                         mob.setError("Invalid mobile");
                         flag= false;
                         progressBar.cancel();

                     }


                     if (uage!=null && uage.length()>2)
                     {
                         age.setError("please enter a valid age");
                         flag=false;
                         progressBar.cancel();

                     }
                     if (uaddress!=null&&uaddress.length()<3 )
                     {
                         addr.setError("address should contain atleast 3 characters");
                         flag = false;
                         progressBar.cancel();

                     }

                     if (isValidMail(ema.getText().toString())) {
                         email = ema.getText().toString();

                     }
                     else
                         {
                             progressBar.cancel();

                         ema.setError("Invalid Email Id");
                     }

                     if(DBTransactionFunctions.isEmailFound("tb_user","email",ema.getText().toString())){
                             email = ema.getText().toString();
                         }
                         else{
                             ema.setError("Email Id already exist");
                         progressBar.cancel();

                         }


                 }

                if (flag) {
                    ContentValues cv = new ContentValues();
                    if(usertype.equals("1"))
                    {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("name",uname);
                        hashMap.put( "category",category );
                        hashMap.put("mobile",umobile);
                        hashMap.put("email",uemail);
                        hashMap.put("website",uwebsite);
                        hashMap.put("street",ustreet);
                        hashMap.put("city",ucity);
                        hashMap.put("district",udistrict);
                        hashMap.put("state",ustate);
                        hashMap.put("country",ucountry);
                        hashMap.put("password",upass);
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));

                        RetroInterface retro=retrofit.create(RetroInterface.class);
                        retro.serviceclubregi(hashMap).enqueue(new Callback<ServiceClubRegister>() {
                            @Override
                            public void onResponse(Call<ServiceClubRegister> call, Response<ServiceClubRegister> response)
                            {
                                progressBar.cancel();

                                nam.setText("");
                                ema.setText("");
                                pass.setText("");
                                age.setText("");
                                mob.setText("");
                                addr.setText("");
                                website.setText("");
                                city.setText("");
                                street.setText("");
                                country.setText("");
                                state.setText("");
                                district.setText("");
                                Toast.makeText(getApplicationContext(),"Club registration Successfull,Please use the email and password to login", (Toast.LENGTH_LONG)).show();
                                Intent intent=new Intent(Register.this,ClubLogin.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ServiceClubRegister> call, Throwable t) {
                                progressBar.cancel();

                            }
                        });
                    }else {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("name",uname);
                        hashMap.put("mobile",umobile);
                        hashMap.put("gender",gender);
                        hashMap.put("age",uage);
                        hashMap.put("address",uaddress);
                        hashMap.put("email",uemail);
                        hashMap.put("password",upass);
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));

                        RetroInterface retro=retrofit.create(RetroInterface.class);

                        retro.serviceuserregi(hashMap).enqueue(new Callback<ServiceUserRegister>() {
                            @Override
                            public void onResponse(Call<ServiceUserRegister> call, Response<ServiceUserRegister> response) {
                                nam.setText("");
                                ema.setText("");
                                pass.setText("");
                                age.setText("");
                                addr.setText("");
                                mob.setText("");
                                progressBar.cancel();

                                Toast.makeText(getApplicationContext(), "User registration Successfull,Please use the email and password to login", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(Register.this,ClubLogin.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ServiceUserRegister> call, Throwable t) {
                                progressBar.cancel();

                            }
                        });

                    }

                }

            }
        });



    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(Register.this,ClubLogin.class);
        startActivity(intent);
        finish();

    }

    public static boolean isValidMail(String email) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isValidPhone(String email) {
        return Patterns.PHONE.matcher(email).matches();
    }

    public static boolean validateLastName( String lastName ) {
        return lastName.matches( "[a-zA-z]+([ '-][a-zA-Z]+)*" ); }


    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}
