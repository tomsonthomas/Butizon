package com.venturetech.venture.butizon.Fragments.User;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.venturetech.venture.butizon.Adapters.User.AdapterAppointments;
import com.venturetech.venture.butizon.Model.Model_Appointments;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class BookingHistory extends Fragment {
    RecyclerView recyclerView;
    EditText editText;
    ArrayList<Model_Appointments> appointments;

    public static Fragment newInstance() {
        return new BookingHistory();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_viewa_ll_services,container,false);
        recyclerView = view.findViewById(R.id.Sevicelist);
        editText = view.findViewById(R.id.search_service);
        appointments =new ArrayList<Model_Appointments>();
        appointments = DBTransactionFunctions.getAppoinments();

        //adapter used to show the appoinments or history

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        AdapterAppointments adapterAppointments =new AdapterAppointments(getActivity(),appointments);
        recyclerView.setAdapter(adapterAppointments);
        return view;
    }
}
