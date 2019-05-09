package com.venturetech.venture.butizon.Adapters.User;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.venturetech.venture.butizon.ClubApp.Register;
import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.Model.Model_Employee_Service;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.Model.UserApp.Appoinmentbook;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.SingleShop;
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

public class AdapterSingleShopServices extends RecyclerView.Adapter<AdapterSingleShopServices.MyHolder> {
    Context context ;
    String ShopId;
    ArrayList<Model_Employee_Service> data = new  ArrayList<Model_Employee_Service> ();
    private String employeeid;
    private Calendar date;
    private String timestamp;
    TextView textView, save, cancel;
    Spinner spinner;
    ArrayList<Shop_Service_Details> data1 = new ArrayList<Shop_Service_Details>();

    public AdapterSingleShopServices(Context con, ArrayList<Model_Employee_Service> services, String id) {
    this.context  =con;
    this.data  =services;
    ShopId = id;
    }
    public AdapterSingleShopServices(SingleShop con, ArrayList<Model_Employee_Service> services, String id) {
    this.context  =con;
    this.data  =services;
    ShopId = id;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_single_shop_service,viewGroup,false);
        RecyclerView.ViewHolder holder = new MyHolder(view);
        return (MyHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
try {
    if(data.size()==0){
        myHolder.imageView.setImageResource(R.drawable.no_data_found_banner);
        myHolder.booknow.setVisibility(View.GONE);
        return;
    }

        Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
        myHolder.imageView.setImageBitmap(imageRounded);


    myHolder.name.setText(data.get(i).getServiceName());
    myHolder.empname.setText("Beautician :" + data.get(i).getEmpName());
    myHolder.rate.setText(Consatnts.RUPPEE + data.get(i).getServiceRate());

    myHolder.booknow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (DBTransactionFunctions.getConfigvalue("user_type").equals("2")){
                final AlertDialog.Builder adb = new AlertDialog.Builder(context);
                adb.setTitle("Sorry!");
                adb.setIcon(R.drawable.login);
                adb.setMessage("You need to Login first! ");
                adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(context, Register.class);
                        context.startActivity(intent);
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                adb.show();
            }else {
                try {
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
                    arrayList.add(new Employee(data.get(i).getEmpId(), ShopId, data.get(i).getServiceId(), data.get(i).getEmpName(), ""));
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
/*
                ContentValues cv = new ContentValues();
                cv.put("userid",DBTransactionFunctions.getConfigvalue("userid"));
                cv.put("shopid",data.get(0).getId());
                cv.put("service_id",data.get(0).getId_Service());
                cv.put("empid",employeeid);
                cv.put("appoinmenttime",textView.getText().toString());
                cv.put("updatedtime",System.currentTimeMillis());
                cv.put("status","0");
                DBTransactionFunctions.DB_InsertRow("tb_appoinments",cv);
                Toast.makeText(SingleItemView.this,"Appoinment Submitted,Waiting for Conformation",Toast.LENGTH_LONG).show();
                dialog.cancel();
                finish();*/
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("user_id", DBTransactionFunctions.getConfigvalue("userid"));
                            hashMap.put("shop_id", ShopId);
                            hashMap.put("service_id", data.get(i).getServiceId());
                            hashMap.put("emp_id", employeeid);
                            hashMap.put("app_time", textView.getText().toString());
                            hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                            hashMap.put("status", "0");
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


                        }
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
        }
    });


}catch (Exception e){
    e.printStackTrace();
}


    }

    @Override
    public int getItemCount() {
        if(data.size()==0)
            return 1;
else
    return data.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,empname,rate,booknow;
        LinearLayout layout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.serviceimage);
            name =itemView.findViewById(R.id.servicesname);
            empname =itemView.findViewById(R.id.employeename);
            rate =itemView.findViewById(R.id.servicerate);
            booknow =itemView.findViewById(R.id.booknow);
            layout =itemView.findViewById(R.id.servicelayout);
        }
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
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }


}
