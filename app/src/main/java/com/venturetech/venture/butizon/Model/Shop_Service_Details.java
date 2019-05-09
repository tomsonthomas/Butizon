package com.venturetech.venture.butizon.Model;

import android.os.Parcelable;

import java.util.ArrayList;

public class Shop_Service_Details{
    String Id,  Club_name, Category, Shop_Mobile,  City,  Street,  Email,  State,  Id_Service,  Service_name,  Rate,  Id_Emp,  Emp_name,  Emp_mobile;

    public Shop_Service_Details(String id, String club_name,String category, String mobile, String city, String street, String email, String state, String id1, String service_name, String rate, String id2, String emp_name, String phonenumber) {
        this.Id =id;
        this.Club_name = club_name;
        this.Category=category;
        this.Shop_Mobile =mobile;
        this.City = city;
        this.Street =street;
        this.Email =email;
        this.State = state;
        this.Id_Service = id1;
        this.Service_name = service_name;
        this.Rate = rate;
        this.Id_Emp =id2;
        this.Emp_name = emp_name;
        this.Emp_mobile = phonenumber;
    }

    public String getId() {
        return Id;
    }

    public String getCategory()
    {
        return Category;
    }

    public String getStreet() {
        return Street;
    }

    public String getState() {
        return State;
    }

    public String getCity() {
        return City;
    }

    public String getEmail() {
        return Email;
    }

    public String getClub_name() {
        return Club_name;
    }

    public String getId_Service() {
        return Id_Service;
    }

    public String getService_name() {
        return Service_name;
    }

    public String getShop_Mobile() {
        return Shop_Mobile;
    }

    public String getRate() {
        return Rate;
    }

    public String getEmp_mobile() {
        return Emp_mobile;
    }

    public String getEmp_name() {
        return Emp_name;
    }

    public String getId_Emp() {
        return Id_Emp;
    }
}
