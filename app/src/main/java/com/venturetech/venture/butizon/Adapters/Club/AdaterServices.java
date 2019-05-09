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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.Fragments.Club.EditService;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

public class AdaterServices extends RecyclerView.Adapter<AdaterServices.MyViewHolder> {
    public Context context;
    public  ArrayList<Services> data =new ArrayList<>();
    public AdaterServices(FragmentActivity activity, ArrayList<Services> list) {
        this.context =activity;
        this.data= list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_editservice,viewGroup,false);
        RecyclerView.ViewHolder holder = new AdaterServices.MyViewHolder(view);
        return (MyViewHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        if(data.size()==0) {
            holder.name.setBackgroundResource(R.drawable.no_data_found_banner);
            return;
        }

holder.rate.setText(data.get(i).getRate());
holder.name.setText(data.get(i).getServicename());
holder.edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        final EditText edtname,edtrate;
        Button save;
        try {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialogue_editservicelayout);
            edtname = dialog.findViewById(R.id.servicename);
            edtrate = dialog.findViewById(R.id.serviceRate);
            save = dialog.findViewById(R.id.save);
            edtname.setText(data.get(i).getServicename());
            edtrate.setText(data.get(i).getRate());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strrate,strname;
                    try{
                        strrate =edtrate.getText().toString();
                        strname = edtname.getText().toString();
                        if(strname.length()==0){
                            edtname.setError("Enter Service Name");
                            return;
                        }
                        if(strrate.length()==0){
                            edtname.setError("Enter Service Rate");
                            return;
                        }
                        HashMap<String,String> hashMap=new HashMap<>();
                        hashMap.put("id",data.get(i).getId());
                        hashMap.put("shop_id",DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("service_name",strname);
                        hashMap.put("rate",strrate);
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));

                        boolean flag= RetrofitInterface.AdService(hashMap,context);
                        if (flag){

                        }

                        dialog.cancel();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
});
    }

    @Override
    public int getItemCount() {

        if(data.size()==0)
            return 1;
            else
                return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,rate;
        TextView edit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sername);
            rate =itemView.findViewById(R.id.servrate);
            edit =itemView.findViewById(R.id.servedit);
        }
    }
}
