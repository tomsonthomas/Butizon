package com.venturetech.venture.butizon.Fragments.Club;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.venturetech.venture.butizon.Adapters.Club.AdaterServices;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class EditService extends Fragment {
    static RecyclerView recyclerView;
    static ImageView nodata;
    static LinearLayout layout;
    static ArrayList<Services> list = new ArrayList<>();

    public static Fragment newInstance() {
        return new EditService();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listservice,container,false);
        list=  DBTransactionFunctions.getServices();
        recyclerView =view.findViewById(R.id.servicelist);
        nodata =view.findViewById(R.id.image);
        layout =view.findViewById(R.id.title);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if(list.size()>0) {
            nodata.setVisibility(View.GONE);

            AdaterServices adaterServices = new AdaterServices(getActivity(), list);
            recyclerView.setAdapter(adaterServices);
        }else {
            layout.setVisibility(View.GONE);
        }
        return (view);
    }
    public static void refresh(Context context) {
        try {
            list = DBTransactionFunctions.getServices();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            if (list.size() > 0) {
                nodata.setVisibility(View.GONE);
                AdaterServices adaterServices = new AdaterServices((FragmentActivity) context, list);
                recyclerView.setAdapter(adaterServices);
            } else {
                layout.setVisibility(View.GONE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
