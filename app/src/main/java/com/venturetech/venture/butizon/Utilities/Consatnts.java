package com.venturetech.venture.butizon.Utilities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;

public class Consatnts {
    public  static  String RUPPEE ="₹ ";
    private static String day1;

    public static String getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                day1="Sunday";
                break;
            case Calendar.MONDAY:
                day1="Monday";
                break;
            case Calendar.TUESDAY:
                day1="Tuesday";
                break;
            case Calendar.WEDNESDAY:
                day1="Wednesday";
                break;
            case Calendar.THURSDAY:
                day1="Thursday";
                break;
            case Calendar.FRIDAY:
                day1="Friday";
                break;
            case Calendar.SATURDAY:
                day1="Saturday";
                break;

        }
        return day1;
    }

    public static ArrayList<String> getHour_Minute_AMPM(String time){
        ArrayList<String> list=new ArrayList<>();
        try{

            String[] time1 = time.split ( ":" );
            String[] time3 = time.split ( " " );
            String[] time4 = time1[1].split ( " " );
            String hour =  time1[0].trim() ;
            String min =  time4[0].trim() ;
            String  am = time3[1].trim();
            list.add(hour);
            list.add(min);
            list.add(am);
        }catch (Exception e){

        }
        return list;
    }

    public static class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

        private int height;

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
            height = child.getHeight();
            return super.onLayoutChild(parent, child, layoutDirection);
        }

        @Override
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                           BottomNavigationView child, @NonNull
                                                   View directTargetChild, @NonNull View target,
                                           int axes, int type)
        {
            return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

        @Override
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                                   @NonNull View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed,
                                   @ViewCompat.NestedScrollType int type)
        {
            if (dyConsumed > 0) {
                slideDown(child);
            } else if (dyConsumed < 0) {
                slideUp(child);
            }
        }

        private void slideUp(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(0).setDuration(2);
        }

        private void slideDown(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(height).setDuration(2);
        }
    }



   /* public interface ApiInterface {
        @Multipart
        @POST("user/signup")
        Call<UserModelResponse> updateProfilePhotoProcess(@Part("email") RequestBody email, @Part("password") RequestBody password, @Part("profile_pic\"; filename=\"pp.png\" ") RequestBody file);
    }

    File file = new File(imageUri.getPath());
  //  RequestBody reqFile = RequestBody.create(okhttp3.MediaType.parse("image/*"), file);
    RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
    RequestBody email = RequestBody.create(MediaType.parse("text/plain"), "upload_test4@gmail.com");
    RequestBody password = RequestBody.create(MediaType.parse("text/plain"), "123456789");

    Call<UserModelResponse> call = apiService.updateProfilePhotoProcess(email,password,reqFile);
call.enqueue(new Callback<UserModelResponse>() {
        @Override
        public void onResponse(Call<UserModelResponse> call, Response<UserModelResponse> response) {
            String TAG = response.body().toString();

            UserModelResponse userModelResponse = response.body();
            UserModel userModel = userModelResponse.getUserModel();

            Log.d("MainActivity","user image = "+userModel.getProfilePic());

        }

        @Override
        public void onFailure(Call<UserModelResponse> call, Throwable t) {
            Toast.makeText(MainActivity.this,""+TAG,Toast.LENGTH_LONG).show();

        }
    });

*/
}