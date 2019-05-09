package com.venturetech.venture.butizon.ClubApp;

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

import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.Model.Update.UpadateProfilePicture;
import com.venturetech.venture.butizon.Model.UserApp.Update.UserUpdate;
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

public class ClubProfile extends Fragment {
    TextView name, mobile, street, city, district, state, country, email, edit;
    ArrayList<Model_Club> model_club;
    EditText edtname, edtmobile, edtstreet, edtcity, edtdistrict, edtstate, edtcountry, edtemail;
    Button save;
    ArrayList<Model_Club> model_club1;
    String uname, umobile, ustreet, ucity, udistrict, ustate, ucountry, uemail;
    ImageView imageView, imageView1;
    private int IMAGE_PICKER;


    public static Fragment newInstance() {
        return new ClubProfile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.activity_club_profile, container, false );
        name = view.findViewById( R.id.name );
        mobile = view.findViewById( R.id.mobile );
        street = view.findViewById( R.id.street );
        city = view.findViewById( R.id.city );
        district = view.findViewById( R.id.district );
        state = view.findViewById( R.id.state );
        country = view.findViewById( R.id.country );
        email = view.findViewById( R.id.email );
        edit = view.findViewById( R.id.edit );
        imageView = view.findViewById( R.id.logout );
        model_club = new ArrayList<Model_Club>();
        imageView1 = view.findViewById( R.id.clubprofle );


        model_club = DBTransactionFunctions.getClubEditData();
        if (model_club.size() == 0) {

        } else {
            //set all the  clubs data
            name.setText( "NAME : " + model_club.get( 0 ).getName() );
            mobile.setText( "MOBILE : " + model_club.get( 0 ).getMobile() );
            street.setText( "STREET : " + model_club.get( 0 ).getStreet() );
            city.setText( "CITY : " + model_club.get( 0 ).getCity() );
            district.setText( "DISTRICT : " + model_club.get( 0 ).getDistrict() );
            state.setText( "STATE : " + model_club.get( 0 ).getState() );
            country.setText( "COUNTRY : " + model_club.get( 0 ).getCountry() );
            email.setText( "EMAIL : " + model_club.get( 0 ).getEmail() );
            if ((model_club.get(0).getP_image()+"").equals("null")){

            }else {
                byte[] imageBytes = Base64.decode(model_club.get(0).getP_image(), Base64.DEFAULT);
                Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageView1.setImageBitmap(decodedImage);
            }

        }
        imageView1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();

            }
        } );

        edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog( getActivity() );
                dialog.setContentView( R.layout.edituser );
                edtname = dialog.findViewById( R.id.name );
                edtmobile = dialog.findViewById( R.id.mobile );
                edtstreet = dialog.findViewById( R.id.street );
                edtcity = dialog.findViewById( R.id.city );
                edtdistrict = dialog.findViewById( R.id.district );
                edtstate = dialog.findViewById( R.id.state );
                edtcountry = dialog.findViewById( R.id.country );
                edtemail = dialog.findViewById( R.id.country );
                save = dialog.findViewById( R.id.email );

                model_club1 = new ArrayList<Model_Club>();
                model_club1 = DBTransactionFunctions.getClubEditData();
                edtname.setText( model_club1.get( 0 ).getName() );
                edtmobile.setText( model_club1.get( 0 ).getMobile() );
                edtstreet.setText( model_club1.get( 0 ).getStreet() );
                edtcity.setText( model_club1.get( 0 ).getCity() );
                edtdistrict.setText( model_club1.get( 0 ).getDistrict() );
                edtstate.setText( model_club1.get( 0 ).getState() );
                edtcountry.setText( model_club1.get( 0 ).getCountry() );
                edtemail.setText( model_club1.get( 0 ).getEmail() );


                save.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean flag = true;
                        uname = edtname.getText().toString();
                        umobile = edtmobile.getText().toString();
                        ustreet = edtstreet.getText().toString();
                        ucity = edtcity.getText().toString();
                        udistrict = edtdistrict.getText().toString();
                        udistrict = edtdistrict.getText().toString();
                        ustate = edtstate.getText().toString();
                        ucountry = edtcountry.getText().toString();
                        uemail = edtemail.getText().toString();

                        if (uname.length() == 0) {
                            edtname.setError( "Invalid" );
                            return;
                        }
                        if (umobile.length() == 0) {
                            edtmobile.setError( "Invalid" );
                            return;
                        }
                        if (ustreet.length() == 0) {
                            edtstreet.setError( "Invalid" );
                            return;
                        }
                        if (ucity.length() == 0) {
                            edtcity.setError( "Invalid" );
                            return;
                        }
                        if (udistrict.length() == 0) {
                            edtdistrict.setError( "Invalid" );
                            return;
                        }
                        if (ustate.length() == 0) {
                            edtstate.setError( "Invalid" );
                            return;
                        }
                        if (ucountry.length() == 0) {
                            edtcountry.setError( "Invalid" );
                            return;
                        }
                        if (uemail.length() == 0) {
                            edtemail.setError( "Invalid" );
                            return;
                        }


                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put( "id", DBTransactionFunctions.getConfigvalue( "userid" ) );
                        hashMap.put( "name", uname );
                        hashMap.put( "mobile", umobile );
                        hashMap.put( "street", ustreet );
                        hashMap.put( "city", ucity );
                        hashMap.put( "district", udistrict );
                        hashMap.put( "state", ustate );
                        hashMap.put( "country", ucountry );
                        hashMap.put( "email", DBTransactionFunctions.getConfigvalue( "user_name" ) );
                        hashMap.put( "password", DBTransactionFunctions.getConfigvalue( "password" ) );
                        hashMap.put( "updated_time", String.valueOf( System.currentTimeMillis() ) );


                        RetroInterface retro = RetrofitInstance.getRetrofitInstance().create( RetroInterface.class );

                        retro.updateuser( hashMap ).enqueue( new Callback<UserUpdate>() {
                            @Override
                            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText( getContext(), "User Data Updated", Toast.LENGTH_SHORT ).show();
                                    RetrofitInterface.SetUsrData( getActivity() );
                                    name.setText( "NAME : " + uname );
                                    mobile.setText( "MOBILE : " + umobile );
                                    street.setText( "STREET : " + ustreet );
                                    city.setText( "CITY : " + ucity );
                                    district.setText( "DISTRICT : " + udistrict );
                                    state.setText( "STATE : " + ustate );
                                    country.setText( "COUNTRY : " + ucountry );
                                    email.setText( "COUNTRY : " + uemail );
                                    dialog.cancel();
                                }

                            }

                            @Override
                            public void onFailure(Call<UserUpdate> call, Throwable t) {

                            }
                        } );


                    }
                } );
                dialog.show();
            }
        } );
        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBTransactionFunctions.updateConfigvalues( "login_status", "0" );
                Intent intent = new Intent( getActivity(), ClubLogin.class );
                startActivity( intent );
                getActivity().finish();
            }


        } );
        return view;
    }


    private void OpenGallery() {
        Intent getImageIntent = new Intent( Intent.ACTION_GET_CONTENT );
        getImageIntent.setType( "image/*" );
        startActivityForResult( getImageIntent, IMAGE_PICKER );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER && resultCode == RESULT_OK) {
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
            hashMap.put("uid", DBTransactionFunctions.getConfigvalue("userid"));
            hashMap.put("p_image", encodedImage);
            hashMap.put("user_type", DBTransactionFunctions.getConfigvalue("user_type"));
            RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);

            retro.set_image(hashMap).enqueue(new Callback<UpadateProfilePicture>() {
                @Override
                public void onResponse(Call<UpadateProfilePicture> call, Response<UpadateProfilePicture> response) {
                    if (response.body().getStatus() == 1) {
                        progressBar.cancel();
                        Toast.makeText(getActivity(), "Profile photo updated", Toast.LENGTH_SHORT).show();
                        RetrofitInterface.SetUserTables(getActivity());
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
