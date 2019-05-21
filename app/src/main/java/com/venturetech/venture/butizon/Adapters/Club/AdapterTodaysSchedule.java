package com.venturetech.venture.butizon.Adapters.Club;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.venturetech.venture.butizon.Model.ModelClubAppointments;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
    public class AdapterTodaysSchedule extends RecyclerView.Adapter<com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder> {
        Context context;
        ArrayList<ModelClubAppointments> appointments = new ArrayList<ModelClubAppointments>();
        public AdapterTodaysSchedule(FragmentActivity activity, ArrayList<ModelClubAppointments> appointments) {
            this.context = activity;
            this.appointments =appointments;
        }

        @NonNull
        @Override
        public com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adaptertodaysschedule,viewGroup,false);
            RecyclerView.ViewHolder holder = new com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder(view);
            return (com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder) holder;

        }

        @Override
        public void onBindViewHolder(@NonNull final com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder myHolder, final int i) {
            if(appointments.size()==0) {
                myHolder.image.setImageResource(R.drawable.no_data_found_banner);
                return;
            }


            myHolder.service.setText(appointments.get(i).getServiceName());
            myHolder.name.setText(appointments.get(i).getName());
            myHolder.phone.setText(appointments.get(i).getMobile());
            myHolder.employee.setText(appointments.get(i).getEmployyename());

myHolder.approve.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        ////code here to send data to the server for notifiying the user about the waiting time.
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
            TextView service,name,phone,employee;
                    EditText update;
                   Button approve;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                service = itemView.findViewById(R.id.apservice);
                name = itemView.findViewById(R.id.apusername);
                phone = itemView.findViewById(R.id.apphone);
                employee = itemView.findViewById(R.id.apemployee);
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
                Toast.makeText(context,ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                ex.printStackTrace();
            }
        }


        public String getdate(String time){
            String list="";
            try{

                String[] time1 = time.split ( " " );
                String hour =  time1[0].trim();
                String min =  time1[1].trim();
                String  am = time1[2].trim();
            }catch (Exception e){

            }
            return list;
        }

    }


