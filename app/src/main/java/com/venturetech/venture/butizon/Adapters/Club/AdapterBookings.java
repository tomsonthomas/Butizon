package com.venturetech.venture.butizon.Adapters.Club;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.Model.ModelClubAppointments;
import com.venturetech.venture.butizon.Model.UserApp.Appoinmentbook;
import com.venturetech.venture.butizon.Model.UserApp.Update.UserUpdate;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInstance;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface.setAppoinment;

public class AdapterBookings extends RecyclerView.Adapter<AdapterBookings.MyHolder> {
    Context context;
    ArrayList<ModelClubAppointments> appointments = new ArrayList<ModelClubAppointments>();
    public AdapterBookings(FragmentActivity activity, ArrayList<ModelClubAppointments> appointments) {
        this.context = activity;
        this.appointments =appointments;
    }

    @NonNull
    @Override
    public AdapterBookings.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterclubappointments,viewGroup,false);
        RecyclerView.ViewHolder holder = new AdapterBookings.MyHolder(view);
        return (AdapterBookings.MyHolder) holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBookings.MyHolder myHolder, final int i) {
        if(appointments.size()==0) {
            myHolder.image.setImageResource(R.drawable.no_data_found_banner);
            myHolder.cancel.setVisibility(View.GONE);
            myHolder.approve.setVisibility(View.GONE);
            return;
        }

        myHolder.service.setText(appointments.get(i).getServiceName());
        myHolder.name.setText(appointments.get(i).getName());
        myHolder.date.setText("Date:"+appointments.get(i).getAppTime());
        myHolder.phone.setText(appointments.get(i).getMobile());
        myHolder.employee.setText(appointments.get(i).getEmployyename());
        if(appointments.get(i).getStatus().equals("2")){
            myHolder.cancel.setText("Appointment Cancelled");
            myHolder.cancel.setEnabled(false);
            myHolder.approve.setVisibility(View.GONE);
        } else if(appointments.get(i).getStatus().equals("1")){
            myHolder.approve.setText("Appointment Approved");
            myHolder.approve.setEnabled(false);
            myHolder.cancel.setVisibility(View.GONE);
        }
        myHolder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",appointments.get(i).getAppointid());
                hashMap.put("user_id",DBTransactionFunctions.getConfigvalue("userid"));
                hashMap.put("shop_id",appointments.get(i).getShopid());
                hashMap.put("service_id",appointments.get(i).getServiceId());
                hashMap.put("emp_id",appointments.get(i).getEmployeeId());
                hashMap.put("app_time",appointments.get(i).getAppTime());
                hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                hashMap.put("status","2");
                final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                retro.updateAppointment(hashMap).enqueue(new Callback<UserUpdate>() {
                    @Override
                    public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                        Toast.makeText(context,"Appointment Cancelled",Toast.LENGTH_LONG).show();
                        myHolder.cancel.setText("Appointment Cancelled");
                        myHolder.approve.setVisibility(View.GONE);

                        setAppoinment();

                    }

                    @Override
                    public void onFailure(Call<UserUpdate> call, Throwable t) {

                    }
                });

            }
        });

        myHolder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",appointments.get(i).getAppointid());
                hashMap.put("user_id",appointments.get( i ).getUserid());
                hashMap.put("shop_id",appointments.get(i).getShopid());
                hashMap.put("service_id",appointments.get(i).getServiceId());
                hashMap.put("emp_id",appointments.get(i).getEmployeeId());
                hashMap.put("app_time",appointments.get(i).getAppTime());
                hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                hashMap.put("status","1");
                final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                retro.updateAppointment(hashMap).enqueue(new Callback<UserUpdate>() {
                    @Override
                    public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                        Toast.makeText(context,"Appointment Approved",Toast.LENGTH_LONG).show();

                        sendSMS( appointments.get( i ).getMobile(),appointments.get( i ).getServiceName(),appointments.get( i ).getAppTime() );


                        myHolder.approve.setText("Appointment Approved");
                        myHolder.cancel.setVisibility(View.GONE);
                        setAppoinment();

                    }

                    @Override
                    public void onFailure(Call<UserUpdate> call, Throwable t) {

                    }
                });
            }
        });

        myHolder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id",appointments.get(i).getAppointid());
                hashMap.put("user_id",appointments.get( i ).getUserid());
                hashMap.put("shop_id",appointments.get(i).getShopid());
                hashMap.put("service_id",appointments.get(i).getServiceId());
                hashMap.put("emp_id",appointments.get(i).getEmployeeId());
                hashMap.put("app_time",appointments.get(i).getAppTime());
                hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                hashMap.put("status","2");
                final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                retro.book_apmnt(hashMap).enqueue(new Callback<Appoinmentbook>() {
                    @Override
                    public void onResponse(Call<Appoinmentbook> call, Response<Appoinmentbook> response) {
                        Toast.makeText(context,"Appointment Cancelled",Toast.LENGTH_LONG).show();
                        setAppoinment();

                    }

                    @Override
                    public void onFailure(Call<Appoinmentbook> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(appointments.size()==0)
            return 1;
        else
            return appointments.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView service,name,phone,date,employee,cancel,update,approve;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            service = itemView.findViewById(R.id.apservice);
            name = itemView.findViewById(R.id.apusername);
            phone = itemView.findViewById(R.id.apphone);
            employee = itemView.findViewById(R.id.apemployee);
            date = itemView.findViewById(R.id.apdate);
            cancel = itemView.findViewById(R.id.cancel);
            update = itemView.findViewById(R.id.apdate);
            approve = itemView.findViewById(R.id.apapprove);

        }
    }

    public void sendSMS(String phoneNo,String serviceName, String date) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null,
                    "Your appointment for "+serviceName+ "on " +date+" has been confirmed,on time will be appreciated.have a nice day \n \n" +
                            "Thanks & Regards-\n" +DBTransactionFunctions.getUsername(DBTransactionFunctions.getConfigvalue("userid")), null, null);
            Toast.makeText(context, "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(context,ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

}
