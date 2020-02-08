package com.example.user.bookers;

/**
 * Created by User on 24/06/2019.
 */

public class Rent {
    public Rent(String submit_date, String price, String title) {
        this.submit_date = submit_date;
        this.price = price;
        this.title = title;
    }
    Rent(){

    }

    String submit_date,price,title;

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
