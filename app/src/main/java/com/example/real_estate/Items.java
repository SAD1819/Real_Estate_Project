package com.example.real_estate;

public class Items {
   String propertyname,area,address,contactno,price,imageurl;

    public Items() {
    }

    public Items(String propertyname, String area, String address, String contactno, String price, String imageurl) {
        this.propertyname = propertyname;
        this.area = area;
        this.address = address;
        this.contactno = contactno;
        this.price = price;
        this.imageurl = imageurl;
    }

    public String getPropertyname() {
        return propertyname;
    }

    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
