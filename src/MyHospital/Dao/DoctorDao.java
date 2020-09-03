/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.Dao;

import MyHospital.dbutil.DBConnection;
import MyHospital.pojo.DoctorPojo;
import MyHospital.pojo.PatientPojo;
import MyHospital.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class DoctorDao {
     public static String getNewId()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select max(doctorid) from doctors");
        int id = 1;
        if(rs.next())
        {
            String docid = rs.getString(1);
            int eno = Integer.parseInt(docid.substring(3));
            id =id+ eno;
        }
        String sr = "DOC"+id;
        System.out.println(sr);
        return sr;
    }
    
    public static ArrayList<String> getDoctorId() throws SQLException
    {
        ArrayList<String>docId = new ArrayList<>();
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery("select doctorid from doctors where active='Y'");
        while(rs.next())
        {
            docId.add(rs.getString(1));
        }
        return docId;
    }
    public static boolean addDoctorToUser(UserPojo p)throws SQLException
    {
        PreparedStatement ps =DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
        ps.setString(3,p.getEmpId());
        ps.setString(2,p.getUserName());
        ps.setString(1,p.getUserid());
        ps.setString(4,p.getPassword());
        ps.setString(5,p.getUsertype());
        int result = ps.executeUpdate();
        return result>0;
        
    }
    public static boolean addDoctorToDoctors(DoctorPojo p)throws SQLException
    {
        PreparedStatement ps =DBConnection.getConnection().prepareStatement("insert into doctors values(?,?,?,?,'Y')");
        ps.setString(1,p.getUserid());
        ps.setString(2,p.getDoctorid());
        ps.setString(3,p.getQualification());
        ps.setString(4,p.getSpecialist());
       
        int result = ps.executeUpdate();
        return result>0;
        
    }
    
    
    public static HashMap<String,String> getNonRegisteredDoctor() throws SQLException
    {
    Connection conn = DBConnection.getConnection();
    String qry="Select empid,empname from employees where role='Doctor' and empid not in(select empid from users where usertype='Doctor')";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(qry);
    HashMap<String,String>doctor = new HashMap();
    while(rs.next())
    {
        String id = rs.getString(1);
        String name = rs.getString(2);
        doctor.put(id, name); 
    }
        return doctor;
    }
    
    public static ArrayList<DoctorPojo> getAllDoctor()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from doctors where active='Y'");
        ArrayList<DoctorPojo>doctorlist = new ArrayList<>();
        while(rs.next())
        {
            DoctorPojo p = new DoctorPojo();
            p.setUserid(rs.getString(1));
            p.setDoctorid(rs.getString(2));
            p.setQualification(rs.getString(3));
            p.setSpecialist(rs.getString(4));
            
            doctorlist.add(p);
        }
        return doctorlist;
    }
    
     public static ArrayList<DoctorPojo> getAllDoctors()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from doctors where active='Y'");
        ArrayList<DoctorPojo>doctorslist = new ArrayList<>();
        while(rs.next())
        {
            DoctorPojo p = new DoctorPojo();
            p.setUserid(rs.getString(1));
            p.setDoctorid(rs.getString(2));
            p.setQualification(rs.getString(3));
            p.setSpecialist(rs.getString(4));
            p.setActive(rs.getString(5));
            
            doctorslist.add(p);
        }
        return doctorslist;
    }
      public static boolean deleteDoctor(String id)throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps,ps1 ;
        ps=conn.prepareStatement("update doctors set active='N' where userid=?");
        ps.setString(1, id);
        ps1 =conn.prepareStatement("update users set active='N' where userid=?");
        ps1.setString(1, id);
        int Result = ps.executeUpdate();
        int Result1=ps1.executeUpdate();
        if(Result>0)
            return Result1>0;
        return false;
    }
      public static HashMap<String,String>getAllDocId()throws SQLException
      {
          Statement st = DBConnection.getConnection().createStatement();
          ResultSet rs =st.executeQuery("select doctorid,userid from doctors where active='Y'");
          HashMap<String,String>Idlist = new HashMap();
          while(rs.next())
          {
              String doctorid = rs.getString(1);
              String id = rs.getString(2);
              
              Idlist.put(doctorid,id); 
          }
        return Idlist;
      }
      public static ArrayList<PatientPojo> getPatientDetailByDocId(PatientPojo p,java.util.Date todayDate)throws SQLException
      {
          PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from patient where doctor_id=? and p_date=?");
          long ms1 = todayDate.getTime();
          java.sql.Date sdate = new java.sql.Date(ms1);
          ps.setString(1, p.getDoctor_Id());
          ps.setDate(2,sdate);
          ResultSet rs = ps.executeQuery();
          ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo pp = new PatientPojo();
            pp.setP_Id(rs.getString(1));
            pp.setF_Name(rs.getString(2));
            pp.setS_Name(rs.getString(3));
            pp.setAge(rs.getInt(4));
            pp.setOPD(rs.getString(5));
            pp.setGender(rs.getString(6));
            pp.setM_Status(rs.getString(7));
            pp.setP_Date(rs.getDate(8));
            pp.setAddress(rs.getString(9));
            pp.setPhone_No(rs.getString(10));
            pp.setDoctor_Id(rs.getString(11));
            pp.setWardNo(rs.getString(12));
            pp.setBedNo(rs.getString(13));
         //   p.setActive(rs.getString(13));
            patientlist.add(pp);
            System.out.println(p);
        }
        System.out.println("............p.");
        return patientlist;
      }
      
      
      public static ArrayList<PatientPojo> getAllPatientDetailByDocId(String p)throws SQLException
      {
          PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from patient where doctor_id=? and active='Y'");
          
          ps.setString(1, p);
          ResultSet rs = ps.executeQuery();
          ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo pp = new PatientPojo();
            pp.setP_Id(rs.getString(1));
            pp.setF_Name(rs.getString(2));
            pp.setS_Name(rs.getString(3));
            pp.setAge(rs.getInt(4));
            pp.setOPD(rs.getString(5));
            pp.setGender(rs.getString(6));
            pp.setM_Status(rs.getString(7));
            pp.setP_Date(rs.getDate(8));
            pp.setAddress(rs.getString(9));
            pp.setPhone_No(rs.getString(10));
            pp.setDoctor_Id(rs.getString(11));
         //   p.setActive(rs.getString(13));
            patientlist.add(pp);
            System.out.println(p);
        }
        System.out.println("............p.");
        return patientlist;
      }
      
       public static ArrayList<DoctorPojo> getAllDetails(String id)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from doctors where userid=? and active='Y'");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<DoctorPojo>doctorDetail= new ArrayList<>();
        while(rs.next())
        {
            DoctorPojo p = new DoctorPojo();
            p.setUserid(rs.getString(1));
            p.setDoctorid(rs.getString(2));
            p.setQualification(rs.getString(3));
            p.setSpecialist(rs.getString(4));
            p.setActive(rs.getString(5));
            
            doctorDetail.add(p);
        }
        return doctorDetail;
    }
       
         public static String getUserId(String id)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select userid from doctors where doctorid=? and active='Y'");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        String did=null;
        while(rs.next())
            did=rs.getString(1);
        return did;
        
    }
         
         public static boolean DoctorUpdate(DoctorPojo p)throws SQLException
    {
        PreparedStatement ps =DBConnection.getConnection().prepareStatement("update doctors set QUALIFICATION=?,SPECIALIST=? where doctorsid=?");
        
        ps.setString(3,p.getDoctorid());
        ps.setString(1,p.getQualification());
        ps.setString(2,p.getSpecialist());
       
        int result = ps.executeUpdate();
        return result>0;
        
    }
      
}