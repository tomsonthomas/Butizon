package com.venturetech.venture.butizon.Adapters.Club;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.venturetech.venture.butizon.Adapters.User.AdapterFeedback;
import com.venturetech.venture.butizon.Model.UserApp.List.FeedbackListt;
import com.venturetech.venture.butizon.Model.UserApp.List.ServiceFeedbackList;
import com.venturetech.venture.butizon.R;

import java.util.ArrayList;

public class AdapterFeedBack  extends  RecyclerView.Adapter<AdapterFeedBack.MyHolder>{

    Context context;
    ArrayList<FeedbackListt> feeback = new ArrayList<FeedbackListt>();
    ArrayList<ServiceFeedbackList>serviceFeedbackLists=new ArrayList<>();

    public AdapterFeedBack(Context applicationContext, ArrayList<FeedbackListt> feedbackListts) {
        this.context = applicationContext;
        this.feeback =feedbackListts;

    }

    @NonNull
    @Override
    public AdapterFeedBack.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapterclubfeedback,viewGroup,false);
        RecyclerView.ViewHolder holder = new AdapterFeedBack.MyHolder(view);
        return (AdapterFeedBack.MyHolder) holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFeedBack.MyHolder myHolder, int i) {


      //  myHolder.msg.setText(feeback.get(i).getMessage());
        myHolder.usname.setText(feeback.get(i).getUser_name());
        myHolder.servicename.setText(feeback.get(i).getServicename()+":: "+feeback.get(i).getMessage());






    }

    @Override
    public int getItemCount() {
            return feeback.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView msg,usname,servicename;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //msg=itemView.findViewById(R.id.message);
            usname=itemView.findViewById(R.id.Username);
            servicename=itemView.findViewById(R.id.srvicename);

        }
    }
}

