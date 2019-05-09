package com.venturetech.venture.butizon.Adapters.User;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.SingleItemView;

import java.util.ArrayList;

public class AdapterHomeService extends RecyclerView.Adapter<AdapterHomeService.MyHolder> {
    ArrayList<Shop_Service_Details> data = new ArrayList<Shop_Service_Details>();
    Context context;
    public AdapterHomeService(FragmentActivity activity, ArrayList<Shop_Service_Details> shop_service_details) {
    this.data =shop_service_details;
        this.context =activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        try {
             view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterhomeservice, viewGroup, false);
             holder = new AdapterHomeService.MyHolder(view);

        }catch (Exception e){
            e.printStackTrace();
        }
        return (AdapterHomeService.MyHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int position) {
        try {

                Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
                Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
                Canvas canvas = new Canvas(imageRounded);
                Paint mpaint = new Paint();
                mpaint.setAntiAlias(true);
                mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
                myHolder.image.setImageBitmap(imageRounded);


            myHolder.name.setText(data.get(position).getService_name());
            myHolder.address.setText(data.get(position).getClub_name() + ","+data.get(position).getStreet() + "," + data.get(position).getCity() + "," + data.get(position).getState());
            myHolder.rate.setText("₹ " + data.get(position).getRate());
            myHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context,SingleItemView.class);
                    intent.putExtra("serid",data.get(position).getId_Service() );
                    intent.putExtra("shopid",data.get(position).getId());
                    context.startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView image;
        TextView name,address,rate;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.serviceimage);
            name =itemView.findViewById(R.id.serviceshopname);
            address =itemView.findViewById(R.id.serviceaddress);
            rate =itemView.findViewById(R.id.servicerate);
            layout=itemView.findViewById(R.id.servicelayout);
        }
    }
}
