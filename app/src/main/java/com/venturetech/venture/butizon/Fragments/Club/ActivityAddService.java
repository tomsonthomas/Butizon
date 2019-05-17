package com.venturetech.venture.butizon.Fragments.Club;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityAddService extends Fragment {
    EditText name,rate;
    Button save;
    Spinner gender;
    ArrayList<String> genderlist= new ArrayList<String>();
    String gen="---Select---";
    public static Fragment newInstance() {
        return new ActivityAddService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_service,container,false);
            name= view.findViewById(R.id.servicename);
            rate= view.findViewById(R.id.serviceRate);
            save =  view.findViewById(R.id.save);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strrate,strname;
                    try{
                        strrate =rate.getText().toString();
                        strname = name.getText().toString();
                        if(strname.length()==0){
                            name.setError("Enter Service Name");
                            return;
                        }
                        if(strrate.length()==0){
                            rate.setError("Enter Service Rate");
                            return;
                        }

                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("shop_id", DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("service_name", strname);
                        //hashMap.put("gender", gen);
                        hashMap.put("rate", strrate);
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                        name.setText("");
                        rate.setText("");
                        boolean flag=  RetrofitInterface.InsertShopService(hashMap,getActivity());
                        if (flag){
                            name.setText("");
                            rate.setText("");
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        return (view);
    }


}
