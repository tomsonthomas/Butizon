package com.venturetech.venture.butizon.Fragments.Club;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

public class EditClub extends Fragment {
    EditText name,mobile,street,city,district,state,country,website,email;
    Button save;
    ArrayList<Model_Club> model_club;
    String usertype,uname,uage,umobile,uaddress,upassword,uemail,uwebsite,ucity,ustreet,ucountry,ustate,udistrict,upass,gender;
    public static Fragment newInstance(){
        return  new EditClub();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.edit_company_profile,container,false);
       name = view.findViewById(R.id.name);
       mobile = view.findViewById(R.id.mobile);
       street = view.findViewById(R.id.Street);
       city = view.findViewById(R.id.City);
       district = view.findViewById(R.id.District);
       state = view.findViewById(R.id.State);
       country = view.findViewById(R.id.Country);
       website = view.findViewById(R.id.Website);
       save =view.findViewById(R.id.login);
        model_club = new ArrayList<Model_Club>();
        model_club =DBTransactionFunctions.getUserData();
        name.setText(model_club.get(0).getName());
        mobile.setText(model_club.get(0).getMobile());
        street.setText(model_club.get(0).getStreet());
        city.setText(model_club.get(0).getCity());
        district.setText(model_club.get(0).getDistrict());
        state.setText(model_club.get(0).getState());
        country.setText(model_club.get(0).getCountry());
        website.setText(model_club.get(0).getState());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag1 = true;

                uname = name.getText().toString();
                umobile = mobile.getText().toString();
                uwebsite = website.getText().toString();
                ucity = city.getText().toString();
                ustreet = street.getText().toString();
                ucountry = country.getText().toString();
                ustate = state.getText().toString();
                udistrict = district.getText().toString();
                    if (uname != null && uname.length()<3){
                        name.setError("Invalid");
                        return;
                    }
                    if (mobile.getText().toString().equals("")){
                        mobile.setError("Invalid");
                        return;
                    }

                    if (street.getText().toString().equals("")){
                        street.setError("Invalid");
                        return;
                    } if (city.getText().toString().equals("")){
                        city.setError("Invalid");
                    return;
                    }if (district.getText().toString().equals("")){
                        district.setError("Invalid");
                    return;
                    }if (state.getText().toString().equals("")){
                        state.setError("Invalid");
                    return;
                    }if (country.getText().toString().equals("")){
                        country.setError("Invalid");
                    return;
                    }

                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("id",DBTransactionFunctions.getConfigvalue("userid"));
                hashMap.put("name",uname);
                hashMap.put("mobile",umobile);
                hashMap.put("email",DBTransactionFunctions.getConfigvalue("user_name"));
                hashMap.put("website",uwebsite);
                hashMap.put("street",ustreet);
                hashMap.put("city",ucity);
                hashMap.put("district",udistrict);
                hashMap.put("state",ustate);
                hashMap.put("country",ucountry);
                hashMap.put("password",DBTransactionFunctions.getConfigvalue("password"));
                hashMap.put("updated_time",String.valueOf(System.currentTimeMillis()));

                boolean flag= RetrofitInterface.Updateclubb(hashMap,getActivity());
                if (flag){
                    name.setText("");
                    mobile.setText("");
                    website.setText("");
                    city.setText("");
                    street.setText("");
                    country.setText("");
                    state.setText("");
                    district.setText("");
                }
        }
        });

        return (view);
    }
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
