package com.venturetech.venture.butizon.Model;

public class ModelClubAppointments {

    String Shopid, Appointid,Userid,Status,AppTime,ServiceId,ServiceName,ServiceRate,EmployeeId,Employyename,EmployeePhone,Name,Mobile,Address,Email,Gender;

    public ModelClubAppointments( String appointid,String shopid, String userid, String status, String appTime, String serviceId, String serviceName, String serviceRate, String employeeId, String employyename, String employeePhone, String name, String mobile, String address, String email, String gender) {
        Shopid = shopid;
        Appointid = appointid;
        Userid = userid;
        Status = status;
        AppTime = appTime;
        ServiceId = serviceId;
        ServiceName = serviceName;
        ServiceRate = serviceRate;
        EmployeeId = employeeId;
        Employyename = employyename;
        EmployeePhone = employeePhone;
        Name = name;
        Mobile = mobile;
        Address = address;
        Email = email;
        Gender = gender;
    }

    public String getServiceRate() {
        return ServiceRate;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public String getStatus() {
        return Status;
    }

    public String getUserid() {
        return Userid;
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getShopid() {
        return Shopid;
    }

    public String getAddress() {
        return Address;
    }

    public String getEmail() {
        return Email;
    }

    public String getAppointid() {
        return Appointid;
    }

    public String getAppTime() {
        return AppTime;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getEmployeePhone() {
        return EmployeePhone;
    }

    public String getEmployyename() {
        return Employyename;
    }

    public String getGender() {
        return Gender;
    }
}
