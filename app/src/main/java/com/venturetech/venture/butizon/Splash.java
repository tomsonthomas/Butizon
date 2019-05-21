package com.venturetech.venture.butizon;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.venturetech.venture.butizon.ClubApp.ClubMain;
import com.venturetech.venture.butizon.StartActivity.Sliding;
import com.venturetech.venture.butizon.UserLogin.UserActivity;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

public class Splash extends AppCompatActivity {
DBTransactionFunctions dbTransactionFunctions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        setContentView(R.layout.activity_splash);
        dbTransactionFunctions =new DBTransactionFunctions(getApplicationContext());




                                          if(isNetworkAvailable(Splash.this))
                                          {
                                              if(DBTransactionFunctions.getConfigvalue("inserterd").equals("1")){

                                              }else {
                                                  DBTransactionFunctions.insertConfigValues();
                                                  DBTransactionFunctions.updateConfigvalues("inserterd","1");
                                              }

                                              if(DBTransactionFunctions.getConfigvalue("login_status").equals("1")){
                                                  if(DBTransactionFunctions.getConfigvalue("user_type").equals("1")){
                                                      Intent intent=new Intent(Splash.this,ClubMain.class);
                                                      startActivity(intent);
                                                      finish();
                                                  }else {
                                                      Intent intent1 = new Intent(Splash.this, UserActivity.class);
                                                      startActivity(intent1);
                                                      finish();
                                                  }
                                              }else{
                                                  try {
                                                      Thread timer = new Thread() {
                                                          public void run() {
                                                              try {
                                                                  sleep(3000);
                                                              } catch (InterruptedException e) {
                                                                  e.printStackTrace();
                                                              } finally {
                                                                  if(isNetworkAvailable(Splash.this)) {
                                                                      Intent i = new Intent(Splash.this, Sliding.class);
                                                                      startActivity(i);
                                                                  }else {
                                                                      Toast.makeText(Splash.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                                                                  }

                                                              }
                                                          }
                                                      };
                                                      timer.start();

                                                  }catch (Exception e){
                                                      e.printStackTrace();
                                                  }
                                              }
                                          }else {
                                              Toast.makeText(Splash.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();


                                          }
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}

