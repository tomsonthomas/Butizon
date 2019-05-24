package com.venturetech.venture.butizon.ForeGroundServices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class WaitingService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            /*    try {
                    HashMap<String, String> hashmap = new HashMap<>();
                    hashmap.put("table", "waiting");
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
                }*/


            }
        },0,0);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
