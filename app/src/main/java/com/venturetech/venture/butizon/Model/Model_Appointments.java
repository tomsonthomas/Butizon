package com.venturetech.venture.butizon.Model;

public class Model_Appointments {
    String Clubid,Club_name,Club_category,Mobile,Address,City,Street,State,Email,Service_id,Service_name,Rate,Empid,Emp_name,Phonenumber ,Appoinid,Userid,Status,Appoinmenttime ;
    public Model_Appointments(String clubid,String club_name,String category,String mobile,String city,String street,String state,String email,String service_id,String service_name,String rate,String empid,String emp_name,String phonenumber ,String appoinid,String userid,String status,String appoinmenttime) {
        this.Clubid=clubid;
        this.Club_name=club_name;
        this.Club_category=category;
        this.Mobile=mobile;
        this.City=city;
        this.Street=street;
        this.State=state;
        this.Email=email;
        this.Service_id=service_id;
        this.Service_name=service_name;
        this.Rate=rate;
        this.Empid=empid;
        this.Emp_name=emp_name;
        this.Phonenumber =phonenumber;
        this.Appoinid=appoinid;
        this.Userid=userid;
        this.Status=status;
        this.Appoinmenttime =appoinmenttime;
    }

    public Model_Appointments(String clubid,String club_name,String category,String mobile,String city,String street,String email,String service_id,String service_name,String rate,String empid,String emp_name,String phonenumber ,String appoinid,String userid,String status,String appoinmenttime) {
        this.Clubid=clubid;
        this.Club_name=club_name;
        this.Club_category=category;
        this.Mobile=mobile;
        this.Address=city;
        this.Street=street;
        this.Email=email;
        this.Service_id=service_id;
        this.Service_name=service_name;
        this.Rate=rate;
        this.Empid=empid;
        this.Emp_name=emp_name;
        this.Phonenumber =phonenumber;
        this.Appoinid=appoinid;
        this.Userid=userid;
        this.Status=status;
        this.Appoinmenttime =appoinmenttime;
    }

    public String getEmp_name() {
        return Emp_name;
    }

    public String getRate() {
        return Rate;
    }

    public String getService_name() {
        return Service_name;
    }

    public String getClub_name() {
        return Club_name;
    }

    public String getEmail() {
        return Email;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getStreet() {
        return Street;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getUserid() {
        return Userid;
    }

    public String getClubid() {
        return Clubid;
    }

    public String getAppoinid() {
        return Appoinid;
    }

    public String getAppoinmenttime() {
        return Appoinmenttime;
    }

    public String getEmpid() {
        return Empid;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public String getService_id() {
        return Service_id;
    }

    public String getStatus() {
        return Status;
    }
}
