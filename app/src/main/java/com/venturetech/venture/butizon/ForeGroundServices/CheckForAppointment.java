package com.venturetech.venture.butizon.ForeGroundServices;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;

import com.venturetech.venture.butizon.Model.UserApp.List.AppoinmentList;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInstance;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckForAppointment extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        try{
            Timer timer =new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                        try {
                            HashMap<String, String> hashmap = new HashMap<>();
                            hashmap.put("table", "appointment");
                            RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                            retr.set_apmnt(hashmap).enqueue(new Callback<AppoinmentList>() {
                                @Override
                                public void onResponse(Call<AppoinmentList> call, Response<AppoinmentList> response) {
                                    if (response.body().getInfo().size() > 0) {
                                        DBTransactionFunctions.DB_ClearTable("tb_appoinments");
                                        try {
                                            for (int i = 0; i < response.body().getInfo().size(); i++) {
                                                ContentValues cv = new ContentValues();
                                                cv.put("id", response.body().getInfo().get(i).getId());
                                                cv.put("userid", response.body().getInfo().get(i).getUserId());
                                                cv.put("shopid", response.body().getInfo().get(i).getShopId());
                                                cv.put("service_id", response.body().getInfo().get(i).getServiceId());
                                                cv.put("empid", response.body().getInfo().get(i).getEmpId());
                                                cv.put("appoinmenttime", response.body().getInfo().get(i).getAppTime());
                                                cv.put("updatedtime", response.body().getInfo().get(i).getUpdatedTime());
                                                cv.put("status", response.body().getInfo().get(i).getStatus());
                                                DBTransactionFunctions.DB_InsertRow("tb_appoinments", cv);

                                            }
                                        } catch (Exception e) {

                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<AppoinmentList> call, Throwable t) {

                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                }
            },0,5*60*1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
