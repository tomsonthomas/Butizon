package com.venturetech.venture.butizon.Adapters.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.venturetech.venture.butizon.Model.Employee;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.SingleItemView;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {
    Context context;
    ArrayList<Employee> arrayList;
    public AdapterSpinner(Context singleItemView, ArrayList<Employee> arrayList) {
        this.arrayList=arrayList;
        this.context=singleItemView;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(arrayList.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.spinnerlayout, viewGroup, false);
        }
        TextView textView  =view.findViewById(R.id.spinner_item);
        textView.setText(arrayList.get(i).getEmp_name());
        return view;
    }
}
