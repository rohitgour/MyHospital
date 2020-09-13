/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.pojo;

import java.io.FileInputStream;

/**
 *
 * @author HP
 */
public class EmpPojo {

    @Override
    public String toString() {
        return "EmpPojo{" + "empid=" + empid + ", empname=" + empname + ", job=" + job + ", sal=" + sal + ", active=" + active + ", ps=" + ps + ", tag=" + tag + ", gender=" + gender + ", contact=" + contact + ", alter_contact=" + alter_contact + ", address=" + address + ", email=" + email + ", account_no=" + account_no + ", ifsc_code=" + ifsc_code + ", branch=" + branch + '}';
    }

    public EmpPojo(String empid, String empname, String job, int sal, String active, byte[] picture, FileInputStream ps, String tag, String gender, String contact, String alter_contact, String address, String email, int account_no, String ifsc_code, String branch) {
        this.empid = empid;
        this.empname = empname;
        this.job = job;
        this.sal = sal;
        this.active = active;
     //   this.picture = picture;
        this.ps = ps;
        this.tag = tag;
        this.gender = gender;
        this.contact = contact;
        this.alter_contact = alter_contact;
        this.address = address;
        this.email = email;
        this.account_no = account_no;
        this.ifsc_code = ifsc_code;
        this.branch = branch;
    }

    public EmpPojo() {
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(byte[] picture) {
//        this.picture = picture;
//    }

    public FileInputStream getPs() {
        return ps;
    }

    public void setPs(FileInputStream ps) {
        this.ps = ps;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAlter_contact() {
        return alter_contact;
    }

    public void setAlter_contact(String alter_contact) {
        this.alter_contact = alter_contact;
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

    public int getAccount_no() {
        return account_no;
    }

    public void setAccount_no(int account_no) {
        this.account_no = account_no;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    
    
    private String empid;
    private String empname;
    private String job;
    private int sal;
    private String active;
   // private byte[] picture;
    private FileInputStream ps;
    private String tag;
    private String gender;
    private String contact;
    private String alter_contact;
    private String address;
    private String email;
    private int account_no;
    private String ifsc_code;
    private String branch;
    
}
