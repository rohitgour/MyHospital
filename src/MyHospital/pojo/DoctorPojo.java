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
public class DoctorPojo {

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public DoctorPojo() {
    }

    public DoctorPojo(String userid, String doctorid, String qualification, String specialist) {
        this.userid = userid;
        this.doctorid = doctorid;
        this.qualification = qualification;
        this.specialist = specialist;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
    private String userid;
    private String doctorid;
    private String qualification;
    private String specialist;
    private String active; 
}
