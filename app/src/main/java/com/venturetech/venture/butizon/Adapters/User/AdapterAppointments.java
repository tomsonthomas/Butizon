package com.venturetech.venture.butizon.Adapters.User;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.venturetech.venture.butizon.Model.Model_Appointments;
import com.venturetech.venture.butizon.R;

import java.util.ArrayList;

public class AdapterAppointments extends RecyclerView.Adapter<AdapterAppointments.MyHolder> {
    Context context;
    ArrayList<Model_Appointments> appointments = new ArrayList<Model_Appointments>();
    public AdapterAppointments(FragmentActivity activity, ArrayList<Model_Appointments> appointments) {
        this.context = activity;
        this.appointments =appointments;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterappointments,viewGroup,false);
        RecyclerView.ViewHolder holder = new AdapterAppointments.MyHolder(view);
        return (AdapterAppointments.MyHolder) holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        if(appointments.size()==0) {
            myHolder.image.setImageResource(R.drawable.no_data_found_banner);
            myHolder.cancel.setVisibility(View.GONE);
            return;
        }
        if(i%2==0){
            Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
            Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
            Canvas canvas = new Canvas(imageRounded);
            Paint mpaint = new Paint();
            mpaint.setAntiAlias(true);
            mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
            myHolder.image.setImageBitmap(imageRounded);
        }
       myHolder.service.setText(appointments.get(i).getService_name());
        if(appointments.get(i).getStatus().equals("1")){
            myHolder.cancel.setText("Status: Appointment Approved");
        }else if(appointments.get(i).getStatus().equals("2")){
            myHolder.cancel.setText("Status: Appointment Cancelled");
        }else {
            myHolder.cancel.setText("Status: Waiting for Approval");
        }
       myHolder.name.setText(appointments.get(i).getClub_name());
       myHolder.address.setVisibility(View.GONE);
       myHolder.rate.setText("₹ "+appointments.get(i).getRate());
       myHolder.date.setText("Date:"+appointments.get(i).getAppoinmenttime());


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
        TextView service,name,address,rate,date,cancel;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            service = itemView.findViewById(R.id.servicename);
            name = itemView.findViewById(R.id.shopname);
            address = itemView.findViewById(R.id.address);
            rate = itemView.findViewById(R.id.rate);
            date = itemView.findViewById(R.id.appdate);
            cancel = itemView.findViewById(R.id.cancel);

        }
    }
}
