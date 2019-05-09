package com.venturetech.venture.butizon.Fragments.User;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.venturetech.venture.butizon.Adapters.User.AdapterHomeService;

import com.venturetech.venture.butizon.Model.Model_Club;
import com.venturetech.venture.butizon.Model.Services;
import com.venturetech.venture.butizon.Model.Shop_Service_Details;
import com.venturetech.venture.butizon.R;
import com.venturetech.venture.butizon.UserLogin.ActivityViewAllServices;
import com.venturetech.venture.butizon.UserLogin.SingleShop;
import com.venturetech.venture.butizon.UserLogin.ViewAllShops;
import com.venturetech.venture.butizon.databases.DBTransactionFunctions;

import java.util.ArrayList;
import java.util.HashMap;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Home extends Fragment {

    RecyclerView service,ratewise,shops;
    public static Fragment newInstance(){
        return new Home();
    }
    SliderLayout sliderLayout;
    LinearLayout layout1,layout2,layout3,layout4;
    ImageView ShopImage1,ShopImage2,ShopImage3,ShopImage4;
    TextView shopname1,address1,phone1,shopname2,address2,phone2,shopname3,address3,phone3,shopname4,address4,phone4;
    HashMap<String, Integer> Hash_file_maps ;
ArrayList<Shop_Service_Details> shop_service_details ,shop_service_details_rate;
ArrayList<Model_Club> shop_details ;
TextView serviceall,shopall,rateall;
    ArrayList<String>arrayList=new ArrayList<String>();
    Spinner service_id;
    String servicename;
    AdapterHomeService  adapterHomeService;
    ImageView imageView,imageView1,imageView2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =null;
        try {
            view = inflater.inflate(R.layout.activity_home, container, false);
           service = view.findViewById(R.id.Sevicelist);
           ratewise = view.findViewById(R.id.Ratebase);
           serviceall = view.findViewById(R.id.serviceall);
           shopall = view.findViewById(R.id.shopall);
           rateall = view.findViewById(R.id.rateall);
           sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
           service_id = view.findViewById(R.id.spinner);
           shopname1 = view.findViewById(R.id.shopname1);
           address1 = view.findViewById(R.id.serviceaddress1);
           phone1 = view.findViewById(R.id.phone1);
           imageView = view.findViewById(R.id.nodata);
           imageView1 = view.findViewById(R.id.nodata1);
           imageView2 = view.findViewById(R.id.nodata2);

           shopname2 = view.findViewById(R.id.shopname2);
           address2 = view.findViewById(R.id.serviceaddress2);
           phone2 = view.findViewById(R.id.phone2);

           shopname3 = view.findViewById(R.id.shopname3);
           address3 = view.findViewById(R.id.serviceaddress3);
           phone3 = view.findViewById(R.id.phone3);

           shopname4 = view.findViewById(R.id.shopname4);
           address4 = view.findViewById(R.id.serviceaddress4);
           phone4 = view.findViewById(R.id.phone4);

           layout1 = view.findViewById(R.id.shoplayout1);
           layout2 = view.findViewById(R.id.shoplayout2);
           layout3 = view.findViewById(R.id.shoplayout3);
           layout4 = view.findViewById(R.id.shoplayout4);

           shop_service_details = new ArrayList<Shop_Service_Details>();
           shop_service_details_rate = new ArrayList<Shop_Service_Details>();
           shop_service_details = DBTransactionFunctions.getShopServiceEmployeeData();
           Hash_file_maps = new HashMap<String, Integer>();
           Hash_file_maps.put("ButiZon", R.drawable.ad);
           Hash_file_maps.put("Hair Color", R.drawable.tc);
           Hash_file_maps.put("Hair Treatment", R.drawable.pf);
           Hash_file_maps.put("Face Massage", R.drawable.td);
           Hash_file_maps.put("Facial", R.drawable.ae);
           for (String name : Hash_file_maps.keySet()) {

               TextSliderView textSliderView = new TextSliderView(getActivity());
               textSliderView
                      // .description(name)
                       .image(Hash_file_maps.get(name))
                       .setScaleType(BaseSliderView.ScaleType.Fit);


               sliderLayout.addSlider(textSliderView);
           }
           sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
           sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
           sliderLayout.setCustomAnimation(new DescriptionAnimation());
           sliderLayout.setDuration(2000);

//service adapter used to show all services
           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
           service.setLayoutManager(linearLayoutManager);
           if (shop_service_details.size() == 0) {
               imageView.setVisibility(View.VISIBLE);

           } else {
               int resId = R.anim.layoutanim;
               LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
               service.setLayoutAnimation(animation);
               adapterHomeService = new AdapterHomeService(getActivity(), shop_service_details);

               service.setAdapter(adapterHomeService);

           }

           ArrayList<Services> services = new ArrayList<Services>();
           services = DBTransactionFunctions.getServicesList();
           for (int i = 0; i < services.size(); i++) {
               arrayList.add(services.get(i).getServicename());
           }
           ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, arrayList);
           arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           service_id.setAdapter(arrayAdapter);
           service_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   servicename = arrayList.get(i);
                   shop_service_details_rate = DBTransactionFunctions.getShopServiceEmployeeDataWithService(servicename);
                   if (shop_service_details_rate.size() == 0) {
                       imageView2.setVisibility(View.VISIBLE);
                       LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layoutanim);
                       ratewise.setLayoutAnimation(animation);
                       adapterHomeService = new AdapterHomeService(getActivity(), shop_service_details_rate);
                       ratewise.setAdapter(adapterHomeService);

                   } else {
                       LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layoutanim);
                       ratewise.setLayoutAnimation(animation);
                       adapterHomeService = new AdapterHomeService(getActivity(), shop_service_details_rate);
                       imageView2.setVisibility(View.GONE);
                       ratewise.setAdapter(adapterHomeService);
                   }

               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });
           LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
           ratewise.setLayoutManager(linearLayoutManager2);
           shop_service_details_rate = DBTransactionFunctions.getShopServiceEmployeeData();
            int resId = R.anim.layoutanim;

            //to display all shops
            // data adapter used to display the data
           if (shop_service_details_rate.size() == 0) {
               imageView2.setVisibility(View.VISIBLE);
               LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
               ratewise.setLayoutAnimation(animation);
               adapterHomeService = new AdapterHomeService(getActivity(), shop_service_details_rate);
               ratewise.setAdapter(adapterHomeService);

           } else {
               LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
               ratewise.setLayoutAnimation(animation);
               adapterHomeService = new AdapterHomeService(getActivity(), shop_service_details_rate);
               ratewise.setAdapter(adapterHomeService);
               imageView2.setVisibility(View.GONE);
           }

           shop_details = DBTransactionFunctions.getClubData();
           if (shop_details.size() == 0) {
               imageView1.setVisibility(View.VISIBLE);
               layout1.setVisibility(View.GONE);
               layout2.setVisibility(View.GONE);
               layout3.setVisibility(View.GONE);
               layout4.setVisibility(View.GONE);
           } else {
               SetShopData(shop_details);
           }
           //to see all  the shop details

           shopall.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), ViewAllShops.class);
                   startActivity(intent);
               }
           });
           //to see all  services

           serviceall.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), ActivityViewAllServices.class);
                   startActivity(intent);
               }
           });
           //to see all the data based on rate wise.

           rateall.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), ActivityViewAllServices.class);
                   startActivity(intent);
               }
           });

//Action  to view the details on click event
           layout1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), SingleShop.class);
                   intent.putExtra("id", shop_details.get(0).getId());
                   intent.putExtra("name", shop_details.get(0).getName());
                   intent.putExtra("address", shop_details.get(0).getStreet() + "," + shop_details.get(0).getCity() + "," + shop_details.get(0).getDistrict());
                   intent.putExtra("phone", shop_details.get(0).getMobile());
                   startActivity(intent);
               }
           });
           layout2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), SingleShop.class);
                   intent.putExtra("id", shop_details.get(1).getId());
                   intent.putExtra("name", shop_details.get(1).getName());
                   intent.putExtra("address", shop_details.get(1).getStreet() + "," + shop_details.get(1).getCity() + "," + shop_details.get(1).getDistrict());
                   intent.putExtra("phone", shop_details.get(1).getMobile());
                   startActivity(intent);
               }
           });
           layout3.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), SingleShop.class);
                   intent.putExtra("id", shop_details.get(2).getId());
                   intent.putExtra("name", shop_details.get(2).getName());
                   intent.putExtra("address", shop_details.get(2).getStreet() + "," + shop_details.get(2).getCity() + "," + shop_details.get(2).getDistrict());
                   intent.putExtra("phone", shop_details.get(2).getMobile());
                   startActivity(intent);
               }
           });

           layout4.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getActivity(), SingleShop.class);
                   intent.putExtra("id", shop_details.get(3).getId());
                   intent.putExtra("name", shop_details.get(3).getName());
                   intent.putExtra("address", shop_details.get(3).getStreet() + "," + shop_details.get(3).getCity() + "," + shop_details.get(3).getDistrict());
                   intent.putExtra("phone", shop_details.get(3).getMobile());
                   startActivity(intent);
               }
           });


       }catch (Exception e){
           e.printStackTrace();
       }
        return view;
    }
//Home Screen data Shop details.

    private void SetShopData(ArrayList<Model_Club> shop_details) {
        try{if(shop_details.size()==1) {
            shopname1.setText(shop_details.get(0).getName());
            address1.setText(shop_details.get(0).getStreet() + "," + shop_details.get(0).getCity());
            phone1.setText(shop_details.get(0).getMobile());
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
        }else if(shop_details.size()==2){
            shopname1.setText(shop_details.get(0).getName());
            address1.setText(shop_details.get(0).getStreet() + "," + shop_details.get(0).getCity());
            phone1.setText(shop_details.get(0).getMobile());
            shopname2.setText(shop_details.get(1).getName());
            address2.setText(shop_details.get(1).getStreet() + "," + shop_details.get(1).getCity());
            phone2.setText(shop_details.get(1).getMobile());
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
        }else if(shop_details.size()==3){
            shopname1.setText(shop_details.get(0).getName());
            address1.setText(shop_details.get(0).getStreet() + "," + shop_details.get(0).getCity());
            phone1.setText(shop_details.get(0).getMobile());
            shopname2.setText(shop_details.get(1).getName());
            address2.setText(shop_details.get(1).getStreet() + "," + shop_details.get(1).getCity());
            phone2.setText(shop_details.get(1).getMobile());
            shopname3.setText(shop_details.get(2).getName());
            address3.setText(shop_details.get(2).getStreet() + "," + shop_details.get(2).getCity());
            phone3.setText(shop_details.get(2).getMobile());
            layout4.setVisibility(View.GONE);
        }else if(shop_details.size()>=4){
            shopname1.setText(shop_details.get(0).getName());
            address1.setText(shop_details.get(0).getStreet() + "," + shop_details.get(0).getCity());
            phone1.setText("Phone :"+shop_details.get(0).getMobile());
            shopname2.setText(shop_details.get(1).getName());
            address2.setText(shop_details.get(1).getStreet() + "," + shop_details.get(1).getCity());
            phone2.setText("Phone :"+shop_details.get(1).getMobile());
            shopname3.setText(shop_details.get(2).getName());
            address3.setText(shop_details.get(2).getStreet() + "," + shop_details.get(2).getCity());
            phone3.setText("Phone :"+shop_details.get(2).getMobile());
            shopname4.setText(shop_details.get(3).getName());
            address4.setText(shop_details.get(3).getStreet() + "," + shop_details.get(3).getCity());
            phone4.setText("Phone :"+shop_details.get(3).getMobile());
        }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
