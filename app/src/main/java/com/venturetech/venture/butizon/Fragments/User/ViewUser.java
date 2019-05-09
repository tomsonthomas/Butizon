package com.venturetech.venture.butizon.Fragments.User;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.ClubApp.ClubLogin;
import com.venturetech.venture.butizon.Model.Update.UpadateProfilePicture;
import com.venturetech.venture.butizon.Model.UserApp.Update.UserUpdate;
import com.venturetech.venture.butizon.Model.UserModel;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInstance;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ViewUser extends Fragment
{
    TextView name,mobile,age,address,gender,edit;
    ArrayList<UserModel> model_user;
    EditText edtname,edtmobile,edtgender,edtage,edtaddress;
    Button save;
    ArrayList<UserModel> model_user1;
    String uname,umobile,ugender,uage,uaddress;
    ImageView imageView,imageView1 ;
    private int IMAGE_PICKER;



    public static Fragment newInstance()
    {
   return new ViewUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View view=inflater.inflate(R.layout.viewuser,container,false);
        name=view.findViewById(R.id.name);
        mobile=view.findViewById(R.id.mobile);
        age=view.findViewById(R.id.age);
        address=view.findViewById(R.id.address);
        gender=view.findViewById(R.id.gender);
        edit=view.findViewById(R.id.edit);
        imageView=view.findViewById(R.id.logout);
        model_user = new ArrayList<UserModel>();
        imageView1=view.findViewById( R.id.userprofle );




        model_user =DBTransactionFunctions.getEditData();
        if (model_user.size()==0)
        {

        }
        else
            {
            //set all the  user data
            name.setText("NAME : "+model_user.get(0).getName());
            mobile.setText("MOBILE : "+model_user.get(0).getMobile());
            gender.setText("GENDER : "+model_user.get(0).getGender());
            age.setText("AGE : "+model_user.get(0).getAge());
            address.setText("ADDRESS : "+model_user.get(0).getAddress());
                byte[] imageBytes = Base64.decode(model_user.get(0).getP_image(), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView1.setImageBitmap(decodedImage);


        }

        imageView1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OpenGallery();


            }
        } );



edit.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View view)
    {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.edituser);
        edtname=dialog.findViewById(R.id.name);
        edtmobile=dialog.findViewById(R.id.mobile);
        edtgender=dialog.findViewById(R.id.gender);
        edtage=dialog.findViewById(R.id.age);
        edtaddress=dialog.findViewById(R.id.address);
        save=dialog.findViewById(R.id.login);

        model_user1 = new ArrayList<UserModel>();
        model_user1 =DBTransactionFunctions.getEditData();
        edtname.setText(model_user1.get(0).getName());
        edtmobile.setText(model_user1.get(0).getMobile());
        edtgender.setText(model_user1.get(0).getGender());
        edtage.setText(model_user1.get(0).getAge());
        edtaddress.setText(model_user1.get(0).getAddress());

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean flag = true;
                uname = edtname.getText().toString();
                umobile = edtmobile.getText().toString();
                ugender = edtgender.getText().toString();
                uage = edtage.getText().toString();
                uaddress = edtaddress.getText().toString();

                if (uname.length() == 0)
                {
                    edtname.setError("Invalid");
                    return;
                }
                if (umobile.length() == 0)
                {
                    edtmobile.setError("Invalid");
                    return;
                }
                if (ugender.length() == 0)
                {
                    edtgender.setError("Invalid");
                    return;
                }
                if (uage.length() == 0)
                {
                    edtage.setError("Invalid");
                    return;
                }
                if (uaddress.length() == 0)
                {
                    edtaddress.setError("Invalid");
                    return;
                }


                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",DBTransactionFunctions.getConfigvalue("userid"));
                hashMap.put("name",uname);
                hashMap.put("mobile",umobile);
                hashMap.put("gender",ugender);
                hashMap.put("age",uage);
                hashMap.put("address",uaddress);
                hashMap.put("email",DBTransactionFunctions.getConfigvalue("user_name"));
                hashMap.put("password",DBTransactionFunctions.getConfigvalue("password"));
                hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));

//service used to updated the user details

                RetroInterface retro=RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

                retro.updateuser(hashMap).enqueue(new Callback<UserUpdate>()

                {
                    @Override
                    public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response)
                    {
                        if (response.isSuccessful())

                        {
                            Toast.makeText(getContext(),"User Data Updated",Toast.LENGTH_SHORT).show();
                            RetrofitInterface.SetUsrData(getActivity());
                            name.setText("NAME : "+uname);
                            mobile.setText("MOBILE : "+umobile);
                            gender.setText("GENDER : "+ugender);
                            age.setText("AGE : "+uage);
                            address.setText("ADDRESS : "+uaddress);
                            dialog.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserUpdate> call, Throwable t)
                    {

                    }
                });


            }

        });


        dialog.show();
    }
});
imageView.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View view) {
        DBTransactionFunctions.updateConfigvalues("login_status","0");
        Intent intent = new Intent(getActivity(),ClubLogin.class);
        startActivity(intent);
        getActivity().finish();
    }
});

        return view;
    }

    private void OpenGallery(){
        Intent getImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getImageIntent .setType("image/*");
        startActivityForResult(getImageIntent , IMAGE_PICKER );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== IMAGE_PICKER  && resultCode == RESULT_OK) {
            Uri fullPhotoUri = data.getData();
            imageView1.setImageURI(fullPhotoUri);

            final Uri imageUri = data.getData();
             InputStream imageStream = null;
            try {
                imageStream = getActivity().getContentResolver().openInputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            final Dialog progressBar = new Dialog(getActivity());
            progressBar.setContentView(R.layout.progressdialogue);
            progressBar.show();
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            String encodedImage = encodeImage(selectedImage);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("uid",DBTransactionFunctions.getConfigvalue("userid"));
            hashMap.put("p_image",encodedImage);
            hashMap.put("user_type",DBTransactionFunctions.getConfigvalue("user_type"));
            RetroInterface retro=RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

            retro.set_image(hashMap).enqueue(new Callback<UpadateProfilePicture>() {
                @Override
                public void onResponse(Call<UpadateProfilePicture> call, Response<UpadateProfilePicture> response) {
                    if(response.body().getStatus()==1)
                    {
                        progressBar.cancel();
                        Toast.makeText(getActivity(), "Profile photo updated", Toast.LENGTH_SHORT).show();
                        RetrofitInterface.SetUsrData(getActivity());
                    }

                }

                @Override
                public void onFailure(Call<UpadateProfilePicture> call, Throwable t) {
                    progressBar.cancel();
                }
            });


        }
    }
    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }



}
