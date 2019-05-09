package com.venturetech.venture.butizon.Utilities.RetrofitService;

import com.venturetech.venture.butizon.Model.Feedback;
import com.venturetech.venture.butizon.Model.List.ClubList;
import com.venturetech.venture.butizon.Model.List.EmployeeFullList;
import com.venturetech.venture.butizon.Model.List.ServiceFullList;
import com.venturetech.venture.butizon.Model.List.ServiceList;
import com.venturetech.venture.butizon.Model.List.SheduleFullList;
import com.venturetech.venture.butizon.Model.List.UserList;
import com.venturetech.venture.butizon.Model.ServiceClub;
import com.venturetech.venture.butizon.Model.ServiceClubRegister;
import com.venturetech.venture.butizon.Model.ServiceEmployee;
import com.venturetech.venture.butizon.Model.ServiceLogin;
import com.venturetech.venture.butizon.Model.ServiceRateRegister;
import com.venturetech.venture.butizon.Model.ServiceShedule;
import com.venturetech.venture.butizon.Model.ServiceUserRegister;
import com.venturetech.venture.butizon.Model.Update.UpadateProfilePicture;
import com.venturetech.venture.butizon.Model.Update.UpdateClub;
import com.venturetech.venture.butizon.Model.Update.UpdateEmployee;
import com.venturetech.venture.butizon.Model.Update.UpdateService;
import com.venturetech.venture.butizon.Model.Update.UpdateShedule;
import com.venturetech.venture.butizon.Model.UserApp.Appoinmentbook;
import com.venturetech.venture.butizon.Model.UserApp.List.AppoinmentList;
import com.venturetech.venture.butizon.Model.UserApp.List.ServiceFeedbackList;
import com.venturetech.venture.butizon.Model.UserApp.Update.UserUpdate;
import com.venturetech.venture.butizon.Model.UserApp.UserListid;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetroInterface {

    //method to register shop service

    @POST("android/shop_service.php")
    @FormUrlEncoded
    Call<ServiceClub>serviceclub(@FieldMap HashMap<String, String> hashMap);

    //method to add employee

    @POST("android/employee.php")
    @FormUrlEncoded
    Call<ServiceEmployee>serviceemployee(@FieldMap HashMap<String, String> hashMap);

    // method to register club

    @POST("android/club_insert.php")
    @FormUrlEncoded
    Call<ServiceClubRegister>serviceclubregi(@FieldMap HashMap<String, String> hashMap);

    @POST("android/club_rating.php")
    @FormUrlEncoded
    Call<ServiceRateRegister>clubrating(@FieldMap HashMap<String, String> hashMap);


    //method to register user

    @POST("android/user_insert.php")
    @FormUrlEncoded
    Call<ServiceUserRegister>serviceuserregi(@FieldMap HashMap<String, String> hashMap);

    //method to get services by using club id

    @POST("android/getTableById.php")
    @FormUrlEncoded
    Call<ServiceList>servicelist(@FieldMap HashMap<String, String> hashMap);

    // used to list login data

    @POST("android/login_insert.php")
    @FormUrlEncoded
    Call<ServiceLogin>servicelogin(@FieldMap HashMap<String, String> hashMap);

    //to get club data

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<ClubList>clublist(@FieldMap HashMap<String, String> hashMap);

    @POST("android/schedule.php")
    @FormUrlEncoded
    Call<ServiceShedule>serviceshedule(@FieldMap HashMap<String, String> hashMap);

    //to update shedule data

    @POST("android/update_schedule.php")
    @FormUrlEncoded
    Call<UpdateShedule>updateshedule(@FieldMap HashMap<String, String> hashMap);

    //to get all user data

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<UserList>userlist(@FieldMap HashMap<String, String> hashMap);

    // to update services

    @POST("android/update_shop_service.php")
    @FormUrlEncoded
    Call<UpdateService>updateservice(@FieldMap HashMap<String, String> hashMap);

//to get shop services

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<ServiceFullList>shopservice(@FieldMap HashMap<String, String> hashMap);

    // get shop schedule

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<SheduleFullList>shedule(@FieldMap HashMap<String, String> hashMap);

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<EmployeeFullList>employee(@FieldMap HashMap<String, String> hashMap);

//to update employee data

    @POST("android/update_employee.php")
    @FormUrlEncoded
    Call<UpdateEmployee>updateemp(@FieldMap HashMap<String, String> hashMap);

    //to update club data

    @POST("android/update_club.php")
    @FormUrlEncoded
    Call<UpdateClub>updateclub(@FieldMap HashMap<String, String> hashMap);

    //to update user data

    @POST("android/update_user.php")
    @FormUrlEncoded
    Call<UserUpdate>updateuser(@FieldMap HashMap<String, String> hashMap);

    // to get user data using id;

    @POST("android/getTableById.php")
    @FormUrlEncoded
    Call<UserListid>useridlist(@FieldMap HashMap<String, String> hashMap);

    //to get appointment details

    @POST("android/appointment.php")
    @FormUrlEncoded
    Call<Appoinmentbook>book_apmnt(@FieldMap HashMap<String, String> hashMap);

    // to insert appointment details

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<AppoinmentList>set_apmnt(@FieldMap HashMap<String, String> hashMap);

    //to update appointment details

    @POST("android/update_appointment.php")
    @FormUrlEncoded
    Call<UserUpdate>updateAppointment(@FieldMap HashMap<String, String> hashMap);

    //to insert feedback data

    @POST("android/feedback.php")
    @FormUrlEncoded
    Call<Feedback>set_feed(@FieldMap HashMap<String, String> hashMap);

    //to get all feed back data

    @POST("android/getTable.php")
    @FormUrlEncoded
    Call<ServiceFeedbackList>set_listfeed(@FieldMap HashMap<String, String> hashMap);

 @POST("android/profile_picture.php")
    @FormUrlEncoded
    Call<UpadateProfilePicture>set_image(@FieldMap HashMap<String, String> hashMap);

}
