package com.example.e_clinicapp;

public class Caregiver {
    public Caregiver(){

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

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public Caregiver(String contact, String names, String age, String idno) {
        this.contact = contact;
        this.names = names;
        this.age = age;
        this.idno = idno;
    }

    String contact, names,age, idno;

}
