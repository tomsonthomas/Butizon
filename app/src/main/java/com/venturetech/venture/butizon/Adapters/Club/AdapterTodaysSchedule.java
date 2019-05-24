package com.venturetech.venture.butizon.Adapters.Club;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.venturetech.venture.butizon.Adapters.User.AdapterSpinner;
import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.Model.ModelClubAppointments;
import com.venturetech.venture.butizon.Model.Model_Employee_Service;
import com.venturetech.venture.butizon.Model.Model_shedule;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.Model.UserApp.Appoinmentbook;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.Consatnts;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInstance;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface.setAppoinment;


public class AdapterTodaysSchedule extends RecyclerView.Adapter<com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule.MyHolder> {
        Context context;
    String ShopId;
    ArrayList<Model_Employee_Service> data = new  ArrayList<Model_Employee_Service> ();
    private String employeeid;
    private Calendar date;
    private String timestamp;
    TextView textView, save, cancel;
    Spinner spinner;
    ArrayList<Shop_Service_Details> data1 = new ArrayList<Shop_Service_Details>();
    private String timeformated;

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
        
        ////code here to send data to the server for notifiying the
        // user about the waiting time.
    }
});
myHolder.reschedle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        try {
            employeeid=appointments.get(i).getEmployeeId();
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialoguebooknow);
            textView = dialog.findViewById(R.id.datetime);
            spinner = dialog.findViewById(R.id.spinner_employee);
            save = dialog.findViewById(R.id.save);
            cancel = dialog.findViewById(R.id.cancel);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDateTimePicker(textView);
                }
            });
            // final ArrayList<Employee> arrayList = DBTransactionFunctions.getEmployeeByid(ShopId, data.get(i).getServiceId());
            final ArrayList<Employee> arrayList = new ArrayList<Employee>();
            arrayList.add(new Employee(appointments.get(i).getEmployeeId(),appointments.get(i).getShopid() , appointments.get(i).getServiceId(), appointments.get(i).getEmployyename(), ""));
            AdapterSpinner arrayAdapter = new AdapterSpinner(context, arrayList);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    employeeid = arrayList.get(i).getId();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    employeeid = "";
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String day = Consatnts.getDayOfWeek();
                    final ArrayList<Model_shedule> schedule = DBTransactionFunctions.getSheduleDataByIdAndDay(appointments.get(i).getShopid(), day);
                    ArrayList<String> list= Consatnts.getHour_Minute_AMPM(schedule.get(0).getOptime());
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.set(Calendar.HOUR, Integer.parseInt(list.get(0)));
                    calendar1.set(Calendar.MINUTE, Integer.parseInt(list.get(1)));
                    calendar1.set(Calendar.SECOND, 00);
                    if(list.get(2).equals("AM"))
                        calendar1.set(Calendar.AM_PM,Calendar.AM);
                    else
                        calendar1.set(Calendar.AM_PM,Calendar.PM);

                    ArrayList<String> list1= Consatnts.getHour_Minute_AMPM(schedule.get(0).getCltime());
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.set(Calendar.HOUR, Integer.parseInt(list1.get(0)));
                    calendar2.set(Calendar.MINUTE, Integer.parseInt(list1.get(1)));
                    calendar2.set(Calendar.SECOND, 00);
                    if(list1.get(2).equals("AM"))
                        calendar2.set(Calendar.AM_PM,Calendar.AM);
                    else
                        calendar2.set(Calendar.AM_PM,Calendar.PM);


                    Calendar calendar3 = Calendar.getInstance();
                    ArrayList<String> list3=Consatnts.getHour_Minute_AMPM(timeformated);
                    calendar3.set(Calendar.HOUR, Integer.parseInt(list3.get(0)));
                    calendar3.set(Calendar.MINUTE, Integer.parseInt(list3.get(1)));
                    calendar3.set(Calendar.SECOND, 00);
                    if(list3.get(2).equals("AM"))
                        calendar3.set(Calendar.AM_PM,Calendar.AM);
                    else
                        calendar3.set(Calendar.AM_PM,Calendar.PM);

                    long x = calendar3.getTimeInMillis();
                    long x1 = calendar1.getTimeInMillis();
                    long x2 = calendar2.getTimeInMillis();
                    if (x>x1 && x<x2) {
                        final ProgressDialog progressDialog = new ProgressDialog(context);
                        progressDialog.setMessage("Please Wait...");
                        progressDialog.show();
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", appointments.get(i).getAppointid());
                        hashMap.put("user_id", appointments.get(i).getUserid());
                        hashMap.put("shop_id", appointments.get(i).getShopid());
                        hashMap.put("service_id", data.get(i).getServiceId());
                        hashMap.put("emp_id", employeeid);
                        hashMap.put("app_time", textView.getText().toString());
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                        hashMap.put("status", "1");
                        final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                        retro.book_apmnt(hashMap).enqueue(new Callback<Appoinmentbook>() {
                            @Override
                            public void onResponse(Call<Appoinmentbook> call, Response<Appoinmentbook> response) {
                                Toast.makeText(context, "Appointment Submitted,Waiting for Confirmation", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                                setAppoinment();

                            }

                            @Override
                            public void onFailure(Call<Appoinmentbook> call, Throwable t) {

                            }
                        });

                    }else{
                        Toast.makeText(context, "Sorry ,Shop timing from: "+schedule.get(0).getOptime()+" to "+schedule.get(0).getCltime(), Toast.LENGTH_LONG).show();
                    }                        }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                   Button approve,reschedle;
            public MyHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                service = itemView.findViewById(R.id.apservice);
                name = itemView.findViewById(R.id.apusername);
                phone = itemView.findViewById(R.id.apphone);
                employee = itemView.findViewById(R.id.apemployee);
                update = itemView.findViewById(R.id.apdate);
                approve = itemView.findViewById(R.id.apapprove);
                reschedle = itemView.findViewById(R.id.update);
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


    public void showDateTimePicker(final TextView textView) {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        String myFormat = "dd/MM/yy hh:mm a"; // your own format
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        String  formated_time = sdf.format(date.getTime());
                        textView.setText(formated_time);
                        String myFormat1 = "hh:mm a"; // your own format
                        SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);
                        timeformated = sdf1.format(date.getTime());
                    }
                },currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }



}


