package com.venturetech.venture.butizon.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferenceData {
    public static String getSharedPrferencedata(Context applicationContext, String value) {
        String id=null;
        try {

            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("panchayath", MODE_PRIVATE);
            id = sharedPreferences.getString(value, String.valueOf(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }
    public static void setSharedpreference(Context context,String key,String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("panchayath",Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value);

    }
}
