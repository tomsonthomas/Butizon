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

import com.venturetech.venture.butizon.Adapters.Club.AdapterEmployee;
import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class EditEmployee extends Fragment {
    static RecyclerView recyclerView;
    static ArrayList<Employee> list = new ArrayList<>();
static ImageView imageView;
    public static Fragment newInstance() {
        return new EditEmployee();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.editemployee,container,false);
        list=DBTransactionFunctions.getEmployee();
        recyclerView =view.findViewById(R.id.employeelist);
        imageView =view.findViewById(R.id.image);
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);








        if(list.size()==0){
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            AdapterEmployee adaterEmployee = new AdapterEmployee(getActivity(), list);
            recyclerView.setAdapter(adaterEmployee);
        }
        return (view);

    }
    public static void refresh(Context context) {
        try{
            list=DBTransactionFunctions.getEmployee();
            RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            if(list.size()==0){
                imageView.setVisibility(View.VISIBLE);
            }
            else {
                AdapterEmployee adaterEmployee = new AdapterEmployee((FragmentActivity) context, list);
                recyclerView.setAdapter(adaterEmployee);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
