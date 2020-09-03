/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.pojo;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class PatientPojo {

    @Override
    public String toString() {
        return "PatientPojo{" + "P_Id=" + P_Id + ", F_Name=" + F_Name + ", S_Name=" + S_Name + ", Age=" + Age + ", BedNo=" + BedNo + ", WardNo=" + WardNo + ", OPD=" + OPD + ", Gender=" + Gender + ", M_Status=" + M_Status + ", P_Date=" + P_Date + ", Address=" + Address + ", Phone_No=" + Phone_No + ", Doctor_Id=" + Doctor_Id + ", Active=" + Active + ", tag=" + tag + ", relation=" + relation + ", SYW=" + SYW + '}';
    }

    public PatientPojo(String P_Id, String F_Name, String S_Name, int Age, String BedNo, String WardNo, String OPD, String Gender, String M_Status, Date P_Date, String Address, String Phone_No, String Doctor_Id, String Active, String tag, String relation, String SYW) {
        this.P_Id = P_Id;
        this.F_Name = F_Name;
        this.S_Name = S_Name;
        this.Age = Age;
        this.BedNo = BedNo;
        this.WardNo = WardNo;
        this.OPD = OPD;
        this.Gender = Gender;
        this.M_Status = M_Status;
        this.P_Date = P_Date;
        this.Address = Address;
        this.Phone_No = Phone_No;
        this.Doctor_Id = Doctor_Id;
        this.Active = Active;
        this.tag = tag;
        this.relation = relation;
        this.SYW = SYW;
    }

    public String getSYW() {
        return SYW;
    }

    public void setSYW(String SYW) {
        this.SYW = SYW;
    }



    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public PatientPojo() {
    }

    public String getP_Id() {
        return P_Id;
    }

    public void setP_Id(String P_Id) {
        this.P_Id = P_Id;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    public String getS_Name() {
        return S_Name;
    }

    public void setS_Name(String S_Name) {
        this.S_Name = S_Name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getBedNo() {
        return BedNo;
    }

    public void setBedNo(String BedNo) {
        this.BedNo = BedNo;
    }

    public String getWardNo() {
        return WardNo;
    }

    public void setWardNo(String WardNo) {
        this.WardNo = WardNo;
    }

    public String getOPD() {
        return OPD;
    }

    public void setOPD(String OPD) {
        this.OPD = OPD;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getM_Status() {
        return M_Status;
    }

    public void setM_Status(String M_Status) {
        this.M_Status = M_Status;
    }

    public Date getP_Date() {
        return P_Date;
    }

    public void setP_Date(Date P_Date) {
        this.P_Date = P_Date;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String Phone_No) {
        this.Phone_No = Phone_No;
    }

    public String getDoctor_Id() {
        return Doctor_Id;
    }

    public void setDoctor_Id(String Doctor_Id) {
        this.Doctor_Id = Doctor_Id;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }

  
private String P_Id;
    private String F_Name;
    private String S_Name;
    private int Age;
    private String BedNo;
    private String WardNo;
    private String OPD;
    private String Gender;
    private String M_Status;
    private Date P_Date;
    private String Address;
    private String Phone_No;
    private String Doctor_Id;  
    private String Active;
    private String tag;
    private String relation;
    private String SYW;
}
