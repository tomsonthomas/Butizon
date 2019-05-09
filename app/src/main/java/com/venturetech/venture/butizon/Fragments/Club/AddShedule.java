package com.venturetech.venture.butizon.Fragments.Club;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.venturetech.venture.butizon.Model.Model_shedule;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static com.venturetech.venture.butizon.Utilities.RetrofitService.RetrofitInterface.flag;

public class AddShedule extends Fragment {
    TextView sunop,suncl,monop,moncl,thuop,thucl,wedop,wedcl,thesop,thescl,friop,fricl,satop,satcl;
    Button sumbit;
    //String []days={"Sunday","Monday","Tuesday","Wednesday","Thesday","Friday","Saturday"};
    ArrayList<Model_shedule>obj=new ArrayList<Model_shedule>();
    String value;
    ArrayList<Model_shedule>modelShedules;
    public static Fragment newInstance() {
        return new AddShedule();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.addshhedule,container,false);
    sunop=view.findViewById(R.id.suno);
    suncl=view.findViewById(R.id.sunc);
    monop=view.findViewById(R.id.mono);
    moncl=view.findViewById(R.id.monc);
    thuop=view.findViewById(R.id.tuso);
    thucl=view.findViewById(R.id.tusc);
    wedop=view.findViewById(R.id.wedo);
    wedcl=view.findViewById(R.id.wedc);
    thesop=view.findViewById(R.id.thuro);
    thescl=view.findViewById(R.id.thurc);
    friop=view.findViewById(R.id.frio);
    fricl=view.findViewById(R.id.fric);
    satop=view.findViewById(R.id.sato);
    satcl=view.findViewById(R.id.satc);
    sumbit=view.findViewById(R.id.submit);
    Bundle bundle=this.getArguments();
   value=bundle.getString("edit");
    if (value.equals("0"))
    {
        modelShedules=new ArrayList<Model_shedule>();
        modelShedules=DBTransactionFunctions.getSheduleData();
        setData(modelShedules);
    }
    sunop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(sunop,getActivity());
            }
    });
        suncl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(suncl,getActivity());
            }
        });
        monop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
              Settime(monop,getActivity());
            }
        });
        moncl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(moncl,getActivity());
            }
        });
        thuop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(thuop,getActivity());
            }
        });
        thucl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(thucl,getActivity());
            }
        });
        wedop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(wedop,getActivity());
            }
        });
        wedcl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(wedcl,getActivity());
            }
        });
        thesop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(thesop,getActivity());
            }
        });
        thescl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(thescl,getActivity());
            }
        });
        friop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(friop,getActivity());
            }
        });
        fricl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(fricl,getActivity());
            }
        });
        satop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(satop,getActivity());
            }
        });
        satcl.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { // TODO Auto-generated method stub
                Settime(satcl,getActivity());
            }
        });
        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sundayop=sunop.getText().toString();
                String sundaycl=suncl.getText().toString();
                String mondayop=monop.getText().toString();
                String mondaycl=moncl.getText().toString();
                String thursdayop=thuop.getText().toString();
                String thursdaycl=thucl.getText().toString();
                String wednesdayop=wedop.getText().toString();
                String wednesdaycl=wedcl.getText().toString();
                String thesdayop=thesop.getText().toString();
                String thesdaycl=thescl.getText().toString();
                String fridayop=friop.getText().toString();
                String fridaycl=fricl.getText().toString();
                String saturdayop=satop.getText().toString();
                String saturdaycl=satcl.getText().toString();
                if (sundayop.length()>0) {
                    if (sundaycl.length()== 0){
                        // sunop.setError("Please fill the Field");
                        suncl.setError("Please fill the Field");
                    return;
                }else {
                        suncl.setError(null);
                        if (value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Sunday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Sunday", sundayop, sundaycl));
                                }
                            }
                        }else {
                            obj.add(new Model_shedule("Sunday", sundayop, sundaycl));
                        }
                    }
                }else
                {
                    if (value.equals("0")) {
                    for (int i = 0; i < modelShedules.size(); i++) {
                        if (modelShedules.get(i).getDay().equals("Sunday")) {
                            obj.add(new Model_shedule(modelShedules.get(i).getId(), "Sunday", sundayop, sundaycl));
                        }
                    }
                }else {
                    obj.add(new Model_shedule("Sunday", sundayop, sundaycl));
                }

                }
                if (mondayop.length()>0){
                    if(mondaycl.length()==0) {
                        //  monop.setError("Please fill the Field");
                        moncl.setError("Please fill the Field");
                        return;
                    }else {
                        moncl.setError(null);
                        if(value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Monday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Monday", mondayop, mondaycl));
                                }

                            }
                        }else {
                            obj.add(new Model_shedule("Monday", mondayop, mondaycl));
                        }
                    }
                }
                else { if(value.equals("0")) {
                    for (int i = 0; i < modelShedules.size(); i++) {
                        if (modelShedules.get(i).getDay().equals("Monday")) {
                            obj.add(new Model_shedule(modelShedules.get(i).getId(), "Monday", mondayop, mondaycl));
                        }

                    }
                }else {
                    obj.add(new Model_shedule("Monday", mondayop, mondaycl));
                }
                }
                if (thursdayop.length()>0){
                    if(thursdaycl.length()==0) {
                      //  thuop.setError("Please fill the Field");
                        thucl.setError("Please fill the Field");
                        return;
                    }else {
                        thucl.setError(null);
                        if(value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Tuesday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Tuesday", thursdayop, thursdaycl));
                                }
                            }
                        }else {
                            obj.add(new Model_shedule("Tuesday", thursdayop, thursdaycl));

                        }
                    }
                }
                else {
                    if(value.equals("0")) {
                        for (int i = 0; i < modelShedules.size(); i++) {
                            if (modelShedules.get(i).getDay().equals("Tuesday")) {
                                obj.add(new Model_shedule(modelShedules.get(i).getId(), "Tuesday", thursdayop, thursdaycl));
                            }
                        }
                    }else {
                        obj.add(new Model_shedule("Tuesday", thursdayop, thursdaycl));

                    }

                }

                if (wednesdayop.length()>0){
                    if(wednesdaycl.length()==0) {
                        // wedop.setError("Please fill the Field");
                        wedcl.setError("Please fill the Field");
                        return;
                    }
                    else {
                        wedcl.setError(null);
                        if(value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Wednesday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Wednesday", wednesdayop, wednesdaycl));
                                }

                            }
                        } else {
                            obj.add(new Model_shedule("Wednesday", wednesdayop, wednesdaycl));
                        }
                    }
                }
               else {
                    if(value.equals("0")) {
                        for (int i = 0; i < modelShedules.size(); i++) {
                            if (modelShedules.get(i).getDay().equals("Wednesday")) {
                                obj.add(new Model_shedule(modelShedules.get(i).getId(), "Wednesday", wednesdayop, wednesdaycl));
                            }

                        }
                    } else {
                        obj.add(new Model_shedule("Wednesday", wednesdayop, wednesdaycl));
                    }
                }
                if (thesdayop.length()>0){
                    if(thesdaycl.length()==0) {
                        //thesop.setError("Please fill the Field");
                        thescl.setError("Please fill the Field");
                        return;
                    }
                    else {
                        thescl.setError(null);
                        if (value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Thursday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Thursday", thesdayop, thesdaycl));
                                }
                            }
                        }
                        else {
                            obj.add(new Model_shedule("Thursday", thesdayop, thesdaycl));
                        }
                    }
                }
                 else {
                    if (value.equals("0")) {
                        for (int i = 0; i < modelShedules.size(); i++) {
                            if (modelShedules.get(i).getDay().equals("Thursday")) {
                                obj.add(new Model_shedule(modelShedules.get(i).getId(), "Thursday", thesdayop, thesdaycl));
                            }
                        }
                    }
                    else {
                        obj.add(new Model_shedule("Thursday", thesdayop, thesdaycl));
                    }

                }
                if (fridayop.length()>0){
                    if( fridaycl.length()==0) {
                        // friop.setError("Please fill the Field");
                        fricl.setError("Please fill the Field");
                        return;
                    }else {
                        fricl.setError(null);
                        if(value.equals("0")){
                            for (int i=0;i<modelShedules.size();i++) {
                                if (modelShedules.get(i).getDay().equals("Friday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Friday", fridayop, fridaycl));
                                }
                            }
                        }else {
                            obj.add(new Model_shedule("Friday", fridayop, fridaycl));
                        }

                    }
                }
                else {
                    if(value.equals("0")){
                    for (int i=0;i<modelShedules.size();i++) {
                        if (modelShedules.get(i).getDay().equals("Friday")) {
                            obj.add(new Model_shedule(modelShedules.get(i).getId(), "Friday", fridayop, fridaycl));
                        }
                    }
                    }else {
                        obj.add(new Model_shedule("Friday", fridayop, fridaycl));
                    }
                }
                if (saturdayop.length()>0 ){
                    if(saturdaycl.length()==0) {
                        //satop.setError("Please fill the Field");
                        satcl.setError("Please fill the Field");
                        return;
                    }else {
                        satcl.setError(null);
                        if (value.equals("0")) {
                            for (int i = 0; i < modelShedules.size(); i++) {
                                if (modelShedules.get(i).getDay().equals("Saturday")) {
                                    obj.add(new Model_shedule(modelShedules.get(i).getId(), "Saturday", saturdayop, saturdaycl));
                                }
                            }
                        }else {
                            obj.add(new Model_shedule("Saturday", saturdayop, saturdaycl));
                        }
                    }
                }
              else {
                    if (value.equals("0")) {
                        for (int i = 0; i < modelShedules.size(); i++) {
                            if (modelShedules.get(i).getDay().equals("Saturday")) {
                                obj.add(new Model_shedule(modelShedules.get(i).getId(), "Saturday", saturdayop, saturdaycl));
                            }
                        }
                    }else {
                        obj.add(new Model_shedule("Saturday", saturdayop, saturdaycl));
                    }

                }


                if (value.equals("0")) {

                    HashMap<String, String> hashMap = new HashMap<>();
                    for (int i = 0; i < obj.size(); i++) {
                        String cz=DBTransactionFunctions.getConfigvalue("userid");
                        if((obj.get(i).getId()+"").equals("null")){
                            hashMap.put("shop_id", DBTransactionFunctions.getConfigvalue("userid"));
                            hashMap.put("day", obj.get(i).getDay());
                            hashMap.put("open_time", obj.get(i).getOptime());
                            hashMap.put("close_time", obj.get(i).getCltime());
                            hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                            flag = RetrofitInterface.EditShedule(hashMap, getActivity());
                        }else {
                            hashMap.put("id", obj.get(i).getId());
                            hashMap.put("shop_id", DBTransactionFunctions.getConfigvalue("userid"));
                            hashMap.put("day", obj.get(i).getDay());
                            hashMap.put("open_time", obj.get(i).getOptime());
                            hashMap.put("close_time", obj.get(i).getCltime());
                            hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                            flag = RetrofitInterface.InsertShedule(hashMap, getActivity());
                        }

                    }

                    if (flag) {
                        sunop.setText("");
                        suncl.setText("");
                        monop.setText("");
                        moncl.setText("");
                        thuop.setText("");
                        thucl.setText("");
                        wedop.setText("");
                        wedcl.setText("");
                        thesop.setText("");
                        thescl.setText("");
                        friop.setText("");
                        fricl.setText("");
                        satop.setText("");
                        satcl.setText("");

                    }
                    RetrofitInterface.SheduleData();

                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    for (int i = 0; i < obj.size(); i++) {

                        hashMap.put("shop_id", DBTransactionFunctions.getConfigvalue("userid"));
                        hashMap.put("day", obj.get(i).getDay());
                        hashMap.put("open_time", obj.get(i).getOptime());
                        hashMap.put("close_time", obj.get(i).getCltime());
                        hashMap.put("updated_time", String.valueOf(System.currentTimeMillis()));
                        flag = RetrofitInterface.EditShedule(hashMap, getActivity());
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    }
                    if (flag) {
                        RetrofitInterface.SheduleData();
                        sunop.setText("");
                        suncl.setText("");
                        monop.setText("");
                        moncl.setText("");
                        thuop.setText("");
                        thucl.setText("");
                        wedop.setText("");
                        wedcl.setText("");
                        thesop.setText("");
                        thescl.setText("");
                        friop.setText("");
                        fricl.setText("");
                        satop.setText("");
                        satcl.setText("");

                        Toast.makeText(getActivity(), "sucess", Toast.LENGTH_LONG).show();



                    }
                }



            }
        });

        return view;
    }

    private void setData(ArrayList<Model_shedule> modelShedules) {

        for (int i=0;i<modelShedules.size();i++){
            if (modelShedules.get(i).getDay().equals("Sunday")){
                sunop.setText(modelShedules.get(i).getOptime());
                suncl.setText(modelShedules.get(i).getCltime());
            }
            else if (modelShedules.get(i).getDay().equals("Monday")){
                monop.setText(modelShedules.get(i).getOptime());
                moncl.setText(modelShedules.get(i).getCltime());

            }
            else if (modelShedules.get(i).getDay().equals("Tuesday")){
                thuop.setText(modelShedules.get(i).getOptime());
                thucl.setText(modelShedules.get(i).getCltime());

            }
            else if (modelShedules.get(i).getDay().equals("Wednesday")){
                wedop.setText(modelShedules.get(i).getOptime());
                wedcl.setText(modelShedules.get(i).getCltime());

            }
            else if (modelShedules.get(i).getDay().equals("Thursday")){
                thesop.setText(modelShedules.get(i).getOptime());
                thescl.setText(modelShedules.get(i).getCltime());

            }
            else if (modelShedules.get(i).getDay().equals("Friday")){
                friop.setText(modelShedules.get(i).getOptime());
                fricl.setText(modelShedules.get(i).getCltime());

            }
            else{
                satop.setText(modelShedules.get(i).getOptime());
                satcl.setText(modelShedules.get(i).getCltime());

            }

        }
    }

    public void Settime(final TextView textView, Context context) {

        final Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                String myFormat = "hh:mm a"; // your own format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                String  formated_time = sdf.format(date.getTime());
                textView.setText(formated_time);
            }
        }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
    }



}







