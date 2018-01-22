package com.example.user.expenses2;

/**
 * Created by user on 20-07-2017.
 */

public class UserBean {
    String item;
    String cost;
    String date;
    String column_id;

    public UserBean(String column_id,String item, String cost, String date) {
        this.column_id = column_id;
        this.item = item;
        this.cost = cost;
        this.date = date;

    }

    public String getColumn_id() {
        return column_id;
    }

    public String getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public String getItem() {
        return item;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
