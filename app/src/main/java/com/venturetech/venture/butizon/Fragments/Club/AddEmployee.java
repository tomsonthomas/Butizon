package com.venturetech.venture.butizon.Fragments.Club;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.venturetech.venture.butizon.ClubApp.Register;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

public class AddEmployee extends Fragment implements AdapterView.OnItemSelectedListener {
    ArrayList<Services> services;
    ArrayList<String>arrayList=new ArrayList<String>();

    EditText empl_name,empl_phone;
    Spinner service_id;
    Button empl_save;
    String shop_ser_id,shop_emp_name,Shop_emp_phone;

    public static Fragment newInstance() {
        return new AddEmployee();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.addemployee,container,false);

        empl_name=view.findViewById(R.id.emp_name);
        empl_phone=view.findViewById(R.id.emp_phone);
        service_id=view.findViewById(R.id.spinner);
        empl_save=view.findViewById(R.id.emp_save);
        RetrofitInterface.setEmployyee();
        empl_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop_emp_name=empl_name.getText().toString();
                Shop_emp_phone=empl_phone.getText().toString();

                if (Register.validateLastName(shop_emp_name)){

                }else {
                    empl_name.setError("Invalid Name");
                    return;
                }
                if (Register.isValidPhone(Shop_emp_phone)&& Shop_emp_phone.length()==10&& (!Shop_emp_phone.contains("+91"))){

                }else {
                    empl_phone.setError("Invalid Phone number");
                    return;
                }

                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("shop_id",DBTransactionFunctions.getConfigvalue("userid"));
                hashMap.put("service_id",shop_ser_id);
                hashMap.put("emp_name",shop_emp_name);
                hashMap.put("mobile",Shop_emp_phone);
                hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                boolean flag= RetrofitInterface.InsertEmployee(hashMap,getActivity());
                empl_name.setText("");
                empl_phone.setText("");
                if (flag){
                    Toast.makeText(getActivity(),"Employee Added",Toast.LENGTH_LONG).show();
                    empl_name.setText("");
                    empl_phone.setText("");
                }

            }
        });
        services=new ArrayList<Services>();
        service_id.setOnItemSelectedListener(this);
        services=DBTransactionFunctions.getServices();
        for (int i=0;i<services.size();i++){
            arrayList.add(services.get(i).getServicename());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service_id.setAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getActivity(), services.get(position).getId(), Toast.LENGTH_SHORT).show();
        shop_ser_id=services.get(position).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
