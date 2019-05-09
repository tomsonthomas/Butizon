package com.venturetech.venture.butizon.Adapters.User;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.SingleShop;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class AdapterViewAllShops extends RecyclerView.Adapter<AdapterViewAllShops.MyHolder> {
    Context context;
    ArrayList<Model_Club> shop_details;
    public AdapterViewAllShops(Context con, ArrayList<Model_Club> shop_details) {
        this.context =con;
        this.shop_details =shop_details;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterviewallshops,viewGroup,false);
        RecyclerView.ViewHolder holder = new AdapterViewAllShops.MyHolder(view);

        return (MyHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        if(shop_details.size()==0) {
            myHolder.imageView.setImageResource(R.drawable.no_data_found_banner);
            return;
        }
        String image=DBTransactionFunctions.getClubImage(shop_details.get(i).getId())+"";
        if(!image.equals("null")) {
            byte[] imageBytes = Base64.decode(image, Base64.DEFAULT);
            Bitmap mbitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            //  Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
            Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
            Canvas canvas = new Canvas(imageRounded);
            Paint mpaint = new Paint();
            mpaint.setAntiAlias(true);
            mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
            myHolder.imageView.setImageBitmap(imageRounded);
        }else {
            Bitmap mbitmap = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.tc)).getBitmap();
            Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
            Canvas canvas = new Canvas(imageRounded);
            Paint mpaint = new Paint();
            mpaint.setAntiAlias(true);
            mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 100, 100, mpaint);// Round Image Corner 100 100 100 100
            myHolder.imageView.setImageBitmap(imageRounded);        }


myHolder.name.setText(shop_details.get(i).getName());
myHolder.address.setText(shop_details.get(i).getStreet()+","+shop_details.get(i).getCity()+","+shop_details.get(i).getDistrict());
myHolder.phone.setText("Phone :"+shop_details.get(i).getMobile());
myHolder.layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context,SingleShop.class);
        intent.putExtra("id",shop_details.get(i).getId());
        intent.putExtra("name",shop_details.get(i).getName());
        intent.putExtra("address",shop_details.get(i).getStreet()+","+shop_details.get(i).getCity()+","+shop_details.get(i).getDistrict());
        intent.putExtra("phone",shop_details.get(i).getMobile());
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        if(shop_details.size()==0)
            return 1;
        else
        return shop_details.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout layout;
        AppCompatTextView name,address,phone;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.serviceimage1);
            layout =itemView.findViewById(R.id.shoplayout1);
            name = itemView.findViewById(R.id.shopname1);
            address = itemView.findViewById(R.id.serviceaddress1);
            phone = itemView.findViewById(R.id.phone1);
        }
    }

}
