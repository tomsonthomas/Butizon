package com.venturetech.venture.butizon.Model;

public class Model_Employee_Service {

String ServiceId,ServiceRate,ServiceName,EmpId,EmpName;

    public Model_Employee_Service(String serviceid, String rate, String name, String empid, String empname) {
    this.ServiceId = serviceid;
    this.ServiceName = name;
    this.ServiceRate = rate;
    this.EmpId =empid;
    this.EmpName =empname;
    }

    public String getEmpId() {
        return EmpId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public String getServiceRate() {
        return ServiceRate;
    }
}
