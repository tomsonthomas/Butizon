package com.venturetech.venture.butizon.Model;

public class UserModel {
    String id,name,mobile,gender,age,address,email,street,city,district,state,country,p_image;

    public UserModel(String id11, String name1, String id1, String s, String id, String name, String mobile, String gender, String age)
    {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.email = email;
        this.street=street;
        this.city=city;
        this.state=state;
        this.district=district;
        this.country=country;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public UserModel(String id, String name, String address, String name1, String id2, String name2, String mobile, String gender, String p_image, String age) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.email = email;
        this.street=street;
        this.city=city;
        this.state=state;
        this.district=district;
        this.country=country;
        this.p_image=p_image;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getId()
    {
        return id;
    }

             public void setId(String id)
             {
              this.id = id;
             }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    }

