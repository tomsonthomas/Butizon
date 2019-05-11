package com.venturetech.venture.butizon.Utilities;

public class Consatnts {
    public  static  String RUPPEE ="₹ ";



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