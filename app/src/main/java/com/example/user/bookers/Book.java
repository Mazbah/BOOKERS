package com.example.user.bookers;

/**
 * Created by User on 17/06/2019.
 */

public class Book {

    // define four String variables
    private String title;
    private String description;
    private String submit_date;
    private String imageUrl;
    private String author;
    private String price;
    private String uid;
    private String location;
    private String number;
    private String RentId;

    public String getRentId() {
        return RentId;
    }

    public void setRentId(String rentId) {
        RentId = rentId;
    }

    // generate their respective constructors

    public Book(String title, String con, String imageUrl, String author, String price,String location,String number,String submit_date,String RentId) {
        this.title = title;
        this.submit_date =submit_date;
        this.uid = uid;
        this.RentId =RentId;
        this.imageUrl=imageUrl;
        this.author = author;
        this.price = price;
        this.location = location;
        this.number = number;
    }
    // create an empty constructor
    public Book() {
    }


    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public void setuid(String uid){this.uid=uid;}
    public void setPrice(String Price){
        this.price = Price;
    }
    public void setauthor(String author){
        this.author = author;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDesc(String description) {
        this.description = description;
    }

    public String getuid() {
        return uid;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getTitle() {
        return title;
    }
    public String getPrice(){
        return  price;
    }
    public String getauthor() {
        return author;
    }
    public String getNumber(){ return number;}
    public String getLocation(){ return  location;}

}