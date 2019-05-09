package com.venturetech.venture.butizon.Model;

public class Model_Club {
String Id,Name,Category,Mobile,Email,Website,Street,City,State,Country,District,P_image;

    public Model_Club(String id, String name,String category, String mobile, String email, String website, String street, String city, String state, String country, String district) {
        Id = id;
        Name = name;
        Category=category;
        Mobile = mobile;
        Email = email;
        Website = website;
        Street = street;
        City = city;
        State = state;
        Country = country;
        District = district;
    }

    public Model_Club(String id, String club_name, String category, String mobile, String email, String website, String street, String city, String state, String country, String district, String p_image)
    {
        Id = id;
        Name = club_name;
        Category=category;
        Mobile = mobile;
        Email = email;
        Website = website;
        Street = street;
        City = city;
        State = state;
        Country = country;
        District = district;
        P_image=p_image;
    }

    public String getP_image() {
        return P_image;
    }

    public void setP_image(String p_image) {
        this.P_image = p_image;
    }

    public String getId()
    {
        return Id;
    }
     public void setId(String id)
     {
        this.Id = id;
    }


    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String email)
    {
        this.Email = email;
    }


    public String getName()
    {
        return Name;
    }
    public void setName(String name)
    {
        this.Name = name;
    }

    public String getCategory()
    {
        return Category;
    }
    public void setCategory(String category)
    {
        this.Category = category;
    }


    public String getWebsite()
    {
        return Website;
    }
    public void setWebsite(String website)
    {
        this.Website = website;
    }


    public String getCity()
    {
        return City;
    }
    public void setCity(String city)
    {
        this.City = city;
    }


    public String getCountry()
    {
        return Country;
    }
    public void setCountry(String country)
    {
        this.Country = country;
    }


    public String getDistrict()
    {
        return District;
    }
    public void setDistrict(String district)
    {
        this.District = district;
    }



    public String getMobile()

    {
        return Mobile;
    }
    public void setMobile(String mobile)
    {
        this.Mobile = mobile;
    }


    public String getState()
    {
        return State;
    }
    public void setState(String state)
    {
        this.State = state;
    }


    public String getStreet()
    {
        return Street;
    }
    public void setStreet(String street) {
        this.Street = street;


    }


}
