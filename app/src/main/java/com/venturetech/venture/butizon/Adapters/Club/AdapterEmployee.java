package com.venturetech.venture.butizon.Adapters.Club;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.Fragments.Club.EditEmployee;
import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterEmployee  extends RecyclerView.Adapter<AdapterEmployee.MyViewHolder>{
    public Context context;
    public  ArrayList<Employee> data =new ArrayList<>();
    public  ArrayList<Services> service =new ArrayList<>();
    public  ArrayList<Services> updatedservice =new ArrayList<>();
    ArrayList<String>arrayList=new ArrayList<String>();
    EditText empl_name,empl_phone;
    Spinner service_id;
    Button empl_save;
    String name;
    String shop_ser_id,shop_emp_name,Shop_emp_phone;

    public AdapterEmployee(FragmentActivity activity, ArrayList<Employee> list) {
        this.context =activity;
        this.data= list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_editemployee,viewGroup,false);
        RecyclerView.ViewHolder holder = new MyViewHolder(view);
        return (MyViewHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.epname.setText(data.get(i).getEmp_name());
        myViewHolder.epphone.setText(data.get(i).getEmp_phone());
        myViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
               final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.editbox);
                empl_name=dialog.findViewById(R.id.emp_name);
                empl_phone=dialog.findViewById(R.id.emp_phone);
                service_id=dialog.findViewById(R.id.spinner);
                empl_save=dialog.findViewById(R.id.emp_save);

                empl_name.setText(data.get(i).getEmp_name());
                empl_phone.setText(data.get(i).getEmp_phone());
                empl_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop_emp_name=empl_name.getText().toString();
                        Shop_emp_phone=empl_phone.getText().toString();

                        if (shop_emp_name.length()==0){
                            empl_name.setError("Enter Employee Name");
                        }
                        if (Shop_emp_phone.length()==0){
                            empl_phone.setError("Enter Employee Phone number");
                        }
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("id",data.get(i).getId());
                        hashMap.put("shop_id",DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("service_id",shop_ser_id);
                        hashMap.put("emp_name",shop_emp_name);
                        hashMap.put("mobile",Shop_emp_phone);
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));

                        boolean flag= RetrofitInterface.AdEmployee(hashMap,context);
                        if (flag){

                        }



                        dialog.cancel();

                    }
                });

try {
    name = DBTransactionFunctions.getServiceName(data.get(i).getService_id());
    service = DBTransactionFunctions.getServices();
    updatedservice = getUpdatedServiceData(service, name, data.get(i).getService_id());
}catch (Exception e){
    e.printStackTrace();
}
                arrayList.add(name);
                for (int i=0;i<updatedservice.size();i++){
                    if(!updatedservice.get(i).getServicename().equals(name)){
                        arrayList.add(updatedservice.get(i).getServicename());
                    }
                }
                final ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_spinner_item,arrayList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                service_id.setAdapter(arrayAdapter);
                service_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        //Toast.makeText(context, updatedservice.get(position).getId(), Toast.LENGTH_SHORT).show();
                         shop_ser_id = updatedservice.get(position).getId();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                dialog.show();


            }
        });



    }

    private ArrayList<Services> getUpdatedServiceData(ArrayList<Services> service, String name, String service_id) {
        ArrayList<Services> serv=new ArrayList<Services>();
        try{
            serv.add(new Services(service_id,"",name,""));
            for(int i = 0; i<service.size();i++){
                if(!service.get(i).getServicename().equals(name)){
                    serv.add(new Services(service.get(0).getId(),"",service.get(i).getServicename(),""));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return serv;
    }


    @Override
    public int getItemCount() {
            return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView edit;
        TextView epname,epphone;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            epname = itemView.findViewById(R.id.empname);
            epphone =itemView.findViewById(R.id.empphone);
            edit =itemView.findViewById(R.id.servedit);
        }
    }

}
