package com.venturetech.venture.butizon.Model;

public class Employee {
    String Id,shopid,service_id,emp_name,emp_phone;

    public Employee(String id, String shopid, String service_id, String emp_name, String emp_phone) {
        Id = id;
        this.shopid = shopid;
        this.service_id = service_id;
        this.emp_name = emp_name;
        this.emp_phone = emp_phone;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }
}
