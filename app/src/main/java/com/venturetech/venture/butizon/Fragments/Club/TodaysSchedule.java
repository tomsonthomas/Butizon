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
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.venturetech.venture.butizon.Adapters.Club.AdapterBookings;
import com.venturetech.venture.butizon.Adapters.Club.AdapterTodaysSchedule;
import com.venturetech.venture.butizon.Model.ModelClubAppointments;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class TodaysSchedule  extends Fragment {
    static RecyclerView recyclerView;
    EditText editText;
    static ArrayList<ModelClubAppointments> appointments;

    public static Fragment newInstance() {
        return new TodaysSchedule();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_viewa_ll_services,container,false);
        recyclerView = view.findViewById(R.id.Sevicelist);
        editText = view.findViewById(R.id.search_service);
        appointments =new ArrayList<ModelClubAppointments>();
        appointments = DBTransactionFunctions.getTodaysAppoinment();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        AdapterTodaysSchedule adapterAppointments =new AdapterTodaysSchedule(getActivity(),appointments);
        recyclerView.setAdapter(adapterAppointments);
        return view;
    }
    public static  void refresh(Context context){
        appointments = DBTransactionFunctions.getClubAppoinment();
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        AdapterBookings adapterAppointments =new AdapterBookings((FragmentActivity) context,appointments);
        recyclerView.setAdapter(adapterAppointments);
    }
}