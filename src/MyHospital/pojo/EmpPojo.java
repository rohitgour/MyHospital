/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.pojo;

/**
 *
 * @author HP
 */
public class EmpPojo {

    public EmpPojo() {
    }

    public EmpPojo(String empid, String empname, String job, double sal, String active, byte[] picture) {
        this.empid = empid;
        this.empname = empname;
        this.job = job;
        this.sal = sal;
        this.active = active;
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
    
    private String empid;
    private String empname;
    private String job;
    private double sal;
    private String active;
    private byte[] picture;
    private String tag;
    private String gender;
    private int contact;
    private int alter_contact;
    private String address;
    private String email;
    private int account_no;
    private String ifsc_code;
    private String branch;
    
}
