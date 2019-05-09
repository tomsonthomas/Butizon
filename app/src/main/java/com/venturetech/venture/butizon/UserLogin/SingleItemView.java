package com.venturetech.venture.butizon.UserLogin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.venturetech.venture.butizon.Adapters.User.AdapterFeedback;
import com.venturetech.venture.butizon.Adapters.User.AdapterSpinner;
import com.venturetech.venture.butizon.ClubApp.Register;
import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.Model.Feedback;
import com.venturetech.venture.butizon.Model.Model_Appointments;
import com.venturetech.venture.butizon.Model.Model_shedule;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.Model.UserApp.Appoinmentbook;
import com.venturetech.venture.butizon.Model.UserApp.List.FeedbackListt;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetroInterface;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInstance;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface.sentFeedback;
import static com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface.setAppoinment;

public class SingleItemView extends AppCompatActivity {
    String Serviceid,shopid;
    RecyclerView recyclerView;
    ImageView imageView;
    Calendar date;
    String timestamp;
    String employeeid,feed;
    TextView servicename,name,address,timing,rate,booknow,viewfeedback,sendfeedback;
    ArrayList<Shop_Service_Details> data = new ArrayList<Shop_Service_Details>();
    ArrayList<Model_shedule> schedule = new ArrayList<Model_shedule>();
    private String day1;
    ArrayList<FeedbackListt> feedbackListts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN);
        setContentView(R.layout.activity_single_itemview);
        imageView = findViewById(R.id.serviceimage);
        servicename = findViewById(R.id.servicesname);
        name = findViewById(R.id.shopname);
        address = findViewById(R.id.serviceaddress);
        timing = findViewById(R.id.shoptime);
        rate = findViewById(R.id.servicerate);
        recyclerView=findViewById(R.id.recycler_view);
        booknow = findViewById(R.id.booknow);
        viewfeedback=findViewById(R.id.view_feedback);
        sendfeedback=findViewById(R.id.send_feedback);
        feedbackListts =new ArrayList<FeedbackListt>();
        try{
            Intent intent= getIntent();
            Serviceid = intent.getStringExtra("serid");
            shopid  = intent.getStringExtra("shopid");
            data = DBTransactionFunctions.getShopServiceEmployeeDataByID(shopid,Serviceid);
        }catch (Exception e){

        }
        feedbackListts = DBTransactionFunctions.getFeedback(data.get(0).getId(),data.get(0).getId_Service());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        AdapterFeedback adapterAppointments =new AdapterFeedback(getApplicationContext(),feedbackListts);
        recyclerView.setAdapter(adapterAppointments);
        if(feedbackListts.size()==0)
            viewfeedback.setVisibility(View.INVISIBLE);
        viewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewfeedback.getText().toString().equals("View feedback")){
                    recyclerView.setVisibility(View.VISIBLE);
                    viewfeedback.setText("Hide feedback");
                }
                else {
                    recyclerView.setVisibility(View.GONE);
                    viewfeedback.setText("View feedback");
                }

            }
        });
        sendfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final EditText feedback;
                TextView backcancel,send;
                final Dialog dialog = new Dialog(SingleItemView.this);
                dialog.setContentView(R.layout.dialoguefeedback);
                feedback=dialog.findViewById(R.id.wright_feedback);
                backcancel=dialog.findViewById(R.id.feedcancel);
                send=dialog.findViewById(R.id.send);
                backcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        feed=feedback.getText().toString();
                        HashMap<String,String>hashMap=new HashMap<>();
//                        ContentValues cv = new ContentValues();
//                        cv.put("userid",DBTransactionFunctions.getConfigvalue("userid"));
//                        cv.put("shopid",data.get(0).getId());
//                        cv.put("message",feed);
//                        cv.put("status","0");
//                        DBTransactionFunctions.DB_InsertRow("tb_feedback",cv);
                        hashMap.put("user_id",DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("shop_id",data.get(0).getId());
                        hashMap.put("service_id",data.get(0).getId_Service());
                        hashMap.put("message",feed);
                        hashMap.put("status","0");
                        final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                        retro.set_feed(hashMap).enqueue(new Callback<Feedback>() {
                            @Override
                            public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                                Toast.makeText(getApplicationContext(),"FeedBack send Succeccfully",Toast.LENGTH_LONG).show();
                                dialog.cancel();
                                sentFeedback();
                                finish();
                            }

                            @Override
                            public void onFailure(Call<Feedback> call, Throwable t) {

                            }
                        });


                    }
                });

                dialog.show();
            }
        });
        try{
            String day = getDayOfWeek();
            schedule =DBTransactionFunctions.getSheduleDataByIdAndDay(shopid,day);
            name.setText(data.get(0).getClub_name());
            servicename.setText(data.get(0).getService_name());
            address.setText(data.get(0).getStreet()+" , "+data.get(0).getCity()+" , "+data.get(0).getState());
            rate.setText("₹ "+data.get(0).getRate());
            if(schedule.size()>0)
            timing.setText("Open: "+schedule.get(0).getOptime()+" to "+schedule.get(0).getCltime());
            else
                timing.setText("Open:  -- to -- ");


            booknow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (DBTransactionFunctions.getConfigvalue("user_type").equals("2")) {
            final AlertDialog.Builder adb = new AlertDialog.Builder(SingleItemView.this);
            adb.setTitle("Sorry!");
            adb.setIcon(R.drawable.login);
            adb.setMessage("You need to Login first! ");
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(SingleItemView.this, Register.class);
                    SingleItemView.this.startActivity(intent);
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            adb.show();
        }else {
            final TextView textView, save, cancel;
            Spinner spinner;
            final Dialog dialog = new Dialog(SingleItemView.this);
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
            final ArrayList<Employee> arrayList = DBTransactionFunctions.getEmployeeByid(data.get(0).getId(), data.get(0).getId_Service());
            AdapterSpinner arrayAdapter = new AdapterSpinner(SingleItemView.this, arrayList);
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

//                ContentValues cv = new ContentValues();
//                cv.put("userid",DBTransactionFunctions.getConfigvalue("userid"));
//                cv.put("shopid",data.get(0).getId());
//                cv.put("service_id",data.get(0).getId_Service());
//                cv.put("empid",employeeid);
//                cv.put("appoinmenttime",textView.getText().toString());
//                cv.put("updatedtime",System.currentTimeMillis());
//                cv.put("status","0");
//                DBTransactionFunctions.DB_InsertRow("tb_appoinments",cv);
//                Toast.makeText(SingleItemView.this,"Appoinment Submitted,Waiting for Conformation",Toast.LENGTH_LONG).show();
//                dialog.cancel();
//                finish();

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("user_id", DBTransactionFunctions.getConfigvalue("userid"));
                    hashMap.put("shop_id", data.get(0).getId());
                    hashMap.put("service_id", data.get(0).getId_Service());
                    hashMap.put("emp_id", employeeid);
                    hashMap.put("app_time", textView.getText().toString());
                    hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                    hashMap.put("status", "0");
                    final RetroInterface retro = RetrofitInstance.getRetrofitInstance().create(RetroInterface.class);
                    retro.book_apmnt(hashMap).enqueue(new Callback<Appoinmentbook>() {
                        @Override
                        public void onResponse(Call<Appoinmentbook> call, Response<Appoinmentbook> response) {
                            Toast.makeText(getApplicationContext(), "Appoinment Submitted,Waiting for Conformation", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                            setAppoinment();
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Appoinmentbook> call, Throwable t) {

                        }
                    });


                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });

            dialog.show();
        }

    }
});

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  String getDayOfWeek(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                day1="Sunday";
                break;
            case Calendar.MONDAY:
                day1="Monday";
                break;
            case Calendar.TUESDAY:
                day1="Tuesday";
                break;
            case Calendar.WEDNESDAY:
                day1="Wednesday";
                break;
            case Calendar.THURSDAY:
                day1="Thursday";
                break;
            case Calendar.FRIDAY:
                day1="Friday";
                break;
            case Calendar.SATURDAY:
                day1="Saturday";
                break;

        }
        return day1;
    }

    public void showDateTimePicker(final TextView textView) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(SingleItemView.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(SingleItemView.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        String myFormat = "dd/MM/yy hh:mm a"; // your own format
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        String  formated_time = sdf.format(date.getTime());
                        timestamp=formated_time;
                        textView.setText(timestamp);
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
