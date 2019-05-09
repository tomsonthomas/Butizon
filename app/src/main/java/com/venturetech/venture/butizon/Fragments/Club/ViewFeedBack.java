package com.venturetech.venture.butizon.Fragments.Club;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.venturetech.venture.butizon.Adapters.Club.AdapterBookings;
import com.venturetech.venture.butizon.Adapters.Club.AdapterFeedBack;
import com.venturetech.venture.butizon.Model.UserApp.List.FeedbackListt;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;

public class ViewFeedBack extends Fragment {
    ArrayList<FeedbackListt> feeback = new ArrayList<FeedbackListt>();
    RecyclerView recyclerView;

    public  static  Fragment newInstance(){
        return new ViewFeedBack();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.activity_viewa_ll_services,container,false);
        recyclerView =view.findViewById(R.id.Sevicelist);
        feeback = DBTransactionFunctions.getFeedbackForClub();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layoutanim);
        recyclerView.setLayoutAnimation(animation);
        AdapterFeedBack adapterFeedBack =new AdapterFeedBack(getActivity(),feeback);
        recyclerView.setAdapter(adapterFeedBack);
        return view;
    }

}
