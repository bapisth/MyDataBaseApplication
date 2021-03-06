package com.esspl.hemendra.mydaogenerator;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PERSON".
 */
public class Person {

    private Long id;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String address;
    private Long phone;

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String fname, String mname, String lname, String email, String address, Long phone) {
        this.id = id;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

}
