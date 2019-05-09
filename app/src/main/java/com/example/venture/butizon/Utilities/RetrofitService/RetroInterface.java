package com.example.venture.butizon.Utilities.RetrofitService;

import com.example.venture.butizon.Model.List.ClubList;
import com.example.venture.butizon.Model.List.EmployeeFullList;
import com.example.venture.butizon.Model.List.ServiceFullList;
import com.example.venture.butizon.Model.List.ServiceList;
import com.example.venture.butizon.Model.List.SheduleFullList;
import com.example.venture.butizon.Model.List.UserList;
import com.example.venture.butizon.Model.ServiceClub;
import com.example.venture.butizon.Model.ServiceClubRegister;
import com.example.venture.butizon.Model.ServiceEmployee;
import com.example.venture.butizon.Model.ServiceLogin;
import com.example.venture.butizon.Model.ServiceShedule;
import com.example.venture.butizon.Model.ServiceUserRegister;
import com.example.venture.butizon.Model.Update.UpdateClub;
import com.example.venture.butizon.Model.Update.UpdateEmployee;
import com.example.venture.butizon.Model.Update.UpdateService;
import com.example.venture.butizon.Model.Update.UpdateShedule;
import com.example.venture.butizon.Model.UserApp.Update.UserUpdate;
import com.example.venture.butizon.Model.UserApp.UserListid;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetroInterface {
    @POST("android/shop_service.php")
    @FormUrlEncoded
    Call<ServiceClub>serviceclub(@FieldMap HashMap<String,String> hashMap);

    @POST("android/employee.php")
    @FormUrlEncoded
    Call<ServiceEmployee>serviceemployee(@FieldMap HashMap<String,String> hashMap);

    @POST("android/club_insert.php")
    @FormUrlEncoded
    Call<ServiceClubRegister>serviceclubregi(@FieldMap HashMap<String,String> hashMap);


    @POST("android/user_insert.php")
    @FormUrlEncoded
    Call<ServiceUserRegister>serviceuserregi(@FieldMap HashMap<String,String> hashMap);


    @POST("android/getTableById.php")
    @FormUrlEncoded
    Call<ServiceList>servicelist(@FieldMap HashMap<String,String> hashMap);

    @POST("android/login_insert.php")
    @FormUrlEncoded
    Call<ServiceLogin>servicelogin(@FieldMap HashMap<String,String> hashMap);

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<ClubList>clublist(@FieldMap HashMap<String,String> hashMap);

    @POST("android/schedule.php")
    @FormUrlEncoded
    Call<ServiceShedule>serviceshedule(@FieldMap HashMap<String,String> hashMap);


    @POST("android/update_schedule.php")
    @FormUrlEncoded
    Call<UpdateShedule>updateshedule(@FieldMap HashMap<String,String> hashMap);

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<UserList>userlist(@FieldMap HashMap<String,String> hashMap);


    @POST("android/update_shop_service.php")
    @FormUrlEncoded
    Call<UpdateService>updateservice(@FieldMap HashMap<String,String> hashMap);


    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<ServiceFullList>shopservice(@FieldMap HashMap<String,String> hashMap);

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<SheduleFullList>shedule(@FieldMap HashMap<String,String> hashMap);

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<EmployeeFullList>employee(@FieldMap HashMap<String,String> hashMap);



    @POST("android/update_employee.php")
    @FormUrlEncoded
    Call<UpdateEmployee>updateemp(@FieldMap HashMap<String,String> hashMap);

    @POST("android/update_club.php")
    @FormUrlEncoded
    Call<UpdateClub>updateclub(@FieldMap HashMap<String,String> hashMap);

    @POST("android/update_user.php")
    @FormUrlEncoded
    Call<UserUpdate>updateuser(@FieldMap HashMap<String,String> hashMap);


    @POST("android/getTableById.php")
    @FormUrlEncoded
    Call<UserListid>useridlist(@FieldMap HashMap<String,String> hashMap);





}
