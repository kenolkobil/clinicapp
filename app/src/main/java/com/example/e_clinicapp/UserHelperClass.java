package com.example.e_clinicapp;

public class UserHelperClass {
    String contact, names,age,status, id;

    public UserHelperClass(String s, String s1, String married, String uid) {



    }

    public UserHelperClass(String contact, String names, String age, String status, String id) {
        this.contact = contact;
        this.names = names;
        this.age = age;
        this.status = status;
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}