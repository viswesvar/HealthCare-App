package com.assignment.diabetesrecords.entity;

/**
 * Created by lokendra on 12/4/16.
 */
public class Doctor {

    private int id;
    private String name;
    private String phone;
    private String emailid;

    public Doctor() {
    }

    public Doctor(int id, String name, String phone, String emailid) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.emailid = emailid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }
}
