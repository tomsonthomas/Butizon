package com.example.venture.butizon.Utilities.RetrofitService;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import com.example.venture.butizon.Fragments.Club.EditEmployee;
import com.example.venture.butizon.Fragments.Club.EditService;
import com.example.venture.butizon.Model.List.ClubList;
import com.example.venture.butizon.Model.List.EmployeeFullList;
import com.example.venture.butizon.Model.List.ServiceFullList;
import com.example.venture.butizon.Model.List.SheduleFullList;
import com.example.venture.butizon.Model.List.UserList;
import com.example.venture.butizon.Model.ServiceClub;
import com.example.venture.butizon.Model.ServiceEmployee;
import com.example.venture.butizon.Model.ServiceShedule;
import com.example.venture.butizon.Model.Update.UpdateClub;
import com.example.venture.butizon.Model.Update.UpdateEmployee;
import com.example.venture.butizon.Model.Update.UpdateService;
import com.example.venture.butizon.Model.Update.UpdateShedule;
import com.example.venture.butizon.databases.DBTransactionFunctions;

import java.util.HashMap;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RetrofitInterface {
    public static boolean flag = false;

    public static void SetUserTables(final Context context) {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("table", "club");

        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retro.clublist(hashMap).enqueue(new Callback<ClubList>() {
            @Override
            public void onResponse(Call<ClubList> call, Response<ClubList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_club");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("club_name", response.body().getInfo().get(i).getName());
                        cv.put("mobile", response.body().getInfo().get(i).getMobile());
                        cv.put("email", response.body().getInfo().get(i).getEmail());
                        cv.put("website", response.body().getInfo().get(i).getWebsite());
                        cv.put("street", response.body().getInfo().get(i).getStreet());
                        cv.put("city", response.body().getInfo().get(i).getCity());
                        cv.put("district", response.body().getInfo().get(i).getDistrict());
                        cv.put("state", response.body().getInfo().get(i).getState());
                        cv.put("country", response.body().getInfo().get(i).getCountry());
                        cv.put("password", response.body().getInfo().get(i).getPassword());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_club", cv);
                    }
                     SetUsrData(context);
                }
            }

            @Override
            public void onFailure(Call<ClubList> call, Throwable t) {


            }
        });

    }

    public static boolean InsertShopService(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        try {

            RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
            retro.serviceclub(hashMap).enqueue(new Callback<ServiceClub>() {
                @Override
                public void onResponse(Call<ServiceClub> call, Response<ServiceClub> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                        flag = true;
                    }


                    SetServiveTable();
                }


                @Override
                public void onFailure(Call<ServiceClub> call, Throwable t) {
                    flag = false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static void SetServiveTable() {

        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("table", "shop_service");

        RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retr.shopservice(hashmap).enqueue(new Callback<ServiceFullList>() {
            @Override
            public void onResponse(Call<ServiceFullList> call, Response<ServiceFullList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_shop_service");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", DBTransactionFunctions.getConfigvalue("userid"));
                        cv.put("service_name", response.body().getInfo().get(i).getServiceName());
                        cv.put("rate", response.body().getInfo().get(i).getRate());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_shop_service", cv);
                    }

                }
            }

            @Override
            public void onFailure(Call<ServiceFullList> call, Throwable t) {

            }
        });
    }

    public static boolean InsertEmployee(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);


        retro.serviceemployee(hashMap).enqueue(new Callback<ServiceEmployee>() {
            @Override
            public void onResponse(Call<ServiceEmployee> call, Response<ServiceEmployee> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(context, "Employee Added", Toast.LENGTH_LONG).show();
                    flag = true;


                }
                setEmployyee();
            }

            @Override
            public void onFailure(Call<ServiceEmployee> call, Throwable t) {
                flag = false;
            }
        });
        return flag;

    }

    public static void setEmployyee() {

        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("table", "employee");

        RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retr.employee(hashmap).enqueue(new Callback<EmployeeFullList>() {
            @Override
            public void onResponse(Call<EmployeeFullList> call, Response<EmployeeFullList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_employee");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", DBTransactionFunctions.getConfigvalue("userid"));
                        cv.put("serviceid", response.body().getInfo().get(i).getServiceId());
                        cv.put("emp_name", response.body().getInfo().get(i).getEmpName());
                        cv.put("phonenumber", response.body().getInfo().get(i).getMobile());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_employee", cv);

                    }
                }
            }

            @Override
            public void onFailure(Call<EmployeeFullList> call, Throwable t) {

            }
        });

    }

    public static boolean InsertShedule(HashMap<String, String> hashMap, Context context) {
        flag = false;

        RetroInterface retroInterface = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retroInterface.updateshedule(hashMap).enqueue(new Callback<UpdateShedule>() {
            @Override
            public void onResponse(Call<UpdateShedule> call, Response<UpdateShedule> response) {
                if (response.isSuccessful()) {
                    flag = true;


                }

            }

            @Override
            public void onFailure(Call<UpdateShedule> call, Throwable t) {
                flag = false;

            }
        });
        return flag;

    }

    public static void SheduleData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("table", "schedule");
        RetroInterface retr = retrofit.create(RetroInterface.class);
        retr.shedule(hashmap).enqueue(new Callback<SheduleFullList>() {
            @Override
            public void onResponse(Call<SheduleFullList> call, Response<SheduleFullList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_schedule");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {


                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", DBTransactionFunctions.getConfigvalue("userid"));
                        cv.put("week_day", response.body().getInfo().get(i).getDay().trim());
                        cv.put("openingtime", response.body().getInfo().get(i).getOpenTime().trim());
                        cv.put("closingtime", response.body().getInfo().get(i).getCloseTime().trim());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_schedule", cv);
                    }
                }
            }

            @Override
            public void onFailure(Call<SheduleFullList> call, Throwable t) {

            }
        });

    }

    public static boolean EditShedule(HashMap<String, String> hashMap, Context context) {


        RetroInterface retroInterface = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retroInterface.serviceshedule(hashMap).enqueue(new Callback<ServiceShedule>() {
            @Override
            public void onResponse(Call<ServiceShedule> call, Response<ServiceShedule> response) {
                flag = true;
            }

            @Override
            public void onFailure(Call<ServiceShedule> call, Throwable t) {
                flag = false;
            }
        });
        return flag;
    }

    public static boolean Updateclubb(HashMap<String, String> hashMap, final Context context) {
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retro.updateclub(hashMap).enqueue(new Callback<UpdateClub>() {
            @Override
            public void onResponse(Call<UpdateClub> call, Response<UpdateClub> response) {
                if (response.isSuccessful()) {
                    flag = true;
                    Toast.makeText(context, "sceess", Toast.LENGTH_SHORT).show();
                    SetData();
                }
            }

            @Override
            public void onFailure(Call<UpdateClub> call, Throwable t) {

            }
        });


        return flag;

    }

    public static void SetData() {


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("table", "club");

        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retro.clublist(hashMap).enqueue(new Callback<ClubList>() {
            @Override
            public void onResponse(Call<ClubList> call, Response<ClubList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_club");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("club_name", response.body().getInfo().get(i).getName());
                        cv.put("mobile", response.body().getInfo().get(i).getMobile());
                        cv.put("email", response.body().getInfo().get(i).getEmail());
                        cv.put("website", response.body().getInfo().get(i).getWebsite());
                        cv.put("street", response.body().getInfo().get(i).getStreet());
                        cv.put("city", response.body().getInfo().get(i).getCity());
                        cv.put("district", response.body().getInfo().get(i).getDistrict());
                        cv.put("state", response.body().getInfo().get(i).getState());
                        cv.put("country", response.body().getInfo().get(i).getCountry());
                        cv.put("password", response.body().getInfo().get(i).getPassword());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_club", cv);
                    }

                }
            }

            @Override
            public void onFailure(Call<ClubList> call, Throwable t) {


            }
        });
    }

    public static boolean AdEmployee(HashMap<String, String> hashMap, final Context context) {
        flag = false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retro.updateemp(hashMap).enqueue(new Callback<UpdateEmployee>() {
            @Override
            public void onResponse(Call<UpdateEmployee> call, Response<UpdateEmployee> response) {
                Toast.makeText(context, "Scess", Toast.LENGTH_LONG).show();
                flag = true;
                EditEmployee.refresh(context);
                setData(context);
            }

            @Override
            public void onFailure(Call<UpdateEmployee> call, Throwable t) {
                flag = false;

            }
        });
        return flag;
    }

    public static void setData(final Context context) {


        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("table", "employee");

        RetroInterface retr = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
        retr.employee(hashmap).enqueue(new Callback<EmployeeFullList>() {
            @Override
            public void onResponse(Call<EmployeeFullList> call, Response<EmployeeFullList> response) {
                if (response.body().getInfo().size() > 0) {
                    DBTransactionFunctions.DB_ClearTable("tb_employee");
                    for (int i = 0; i < response.body().getInfo().size(); i++) {
                        ContentValues cv = new ContentValues();
                        cv.put("id", response.body().getInfo().get(i).getId());
                        cv.put("shopid", DBTransactionFunctions.getConfigvalue("userid"));
                        cv.put("serviceid", response.body().getInfo().get(i).getServiceId());
                        cv.put("emp_name", response.body().getInfo().get(i).getEmpName());
                        cv.put("phonenumber", response.body().getInfo().get(i).getMobile());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_employee", cv);

                    }
                    EditEmployee.refresh(context);
                    setShedule();
                }
            }

            @Override
            public void onFailure(Call<EmployeeFullList> call, Throwable t) {

            }
        });

    }

    public static boolean AdService(HashMap<String, String> hashMap, final Context context) {
flag=false;
        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

        retro.updateservice(hashMap).enqueue(new Callback<UpdateService>() {
            @Override
            public void onResponse(Call<UpdateService> call, Response<UpdateService> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Scess", Toast.LENGTH_LONG).show();
                    SetServiveTable();
                    EditService.refresh(context);
                    flag=true;
                }

            }

            @Override
            public void onFailure(Call<UpdateService> call, Throwable t) {

            }
        });
       return flag;
    }
    public static void SetUsrData(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("table","user");
        RetroInterface retr=retrofit.create(RetroInterface.class);

        retr.userlist(hashmap).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                if (response.body().getInfo().size()>0){

                    DBTransactionFunctions.DB_ClearTable("tb_user");
                    for (int i=0;i<response.body().getInfo().size();i++){
                        ContentValues cv=new ContentValues();
                        cv.put("id",response.body().getInfo().get(i).getId());
                        cv.put("name",response.body().getInfo().get(i).getName());
                        cv.put("mobile",response.body().getInfo().get(i).getMobile());
                        cv.put("gender",response.body().getInfo().get(i).getGender());
                        cv.put("age",response.body().getInfo().get(i).getAge());
                        cv.put("address",response.body().getInfo().get(i).getAddress());
                        cv.put("email",response.body().getInfo().get(i).getEmail());
                        cv.put("password",response.body().getInfo().get(i).getPassword());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_user", cv);
                    }
                    setData(context);
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });


    }
    public static void setShedule() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://beautyclub.nyesteventuretech.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HashMap<String,String> hashmap=new HashMap<>();
        hashmap.put("table","schedule");
        RetroInterface retr=retrofit.create(RetroInterface.class);
        retr.shedule(hashmap).enqueue(new Callback<SheduleFullList>() {
            @Override
            public void onResponse(Call<SheduleFullList> call, Response<SheduleFullList> response) {
                if (response.body().getInfo().size()>0){
                    DBTransactionFunctions.DB_ClearTable("tb_schedule");
                    for (int i=0;i<response.body().getInfo().size();i++){


                        ContentValues cv=new ContentValues();
                        cv.put("id",response.body().getInfo().get(i).getId());
                        cv.put("shopid", DBTransactionFunctions.getConfigvalue("userid"));
                        cv.put("week_day",response.body().getInfo().get(i).getDay().trim());
                        cv.put("openingtime",response.body().getInfo().get(i).getOpenTime().trim());
                        cv.put("closingtime",response.body().getInfo().get(i).getCloseTime().trim());
                        cv.put("updatedtime", System.currentTimeMillis());
                        DBTransactionFunctions.DB_InsertRow("tb_schedule", cv);
                    }

                    SetServiveTable();
                }
            }

            @Override
            public void onFailure(Call<SheduleFullList> call, Throwable t) {

            }
        });

    }


}




