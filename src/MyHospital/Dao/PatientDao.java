/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.Dao;

import MyHospital.dbutil.DBConnection;
import MyHospital.pojo.PatientPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class PatientDao {
    public static boolean addPatient(PatientPojo p)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1,p.getP_Id());
        ps.setString(2,p.getF_Name());
        ps.setString(3,p.getS_Name());
        ps.setInt(4,p.getAge());
        ps.setString(5,p.getOPD());
        ps.setString(6,p.getGender());
        ps.setString(7,p.getM_Status());
        ps.setDate(8,p.getP_Date());
        ps.setString(9,p.getAddress());
        ps.setString(10,p.getPhone_No());
        ps.setString(11,p.getDoctor_Id());
        ps.setString(12,"Y");
        ps.setString(13,p.getBedNo());
        ps.setString(14,p.getWardNo());
        ps.setString(15,p.getRelation());
        ps.setString(16,p.getTag());
        ps.setString(17,p.getSYW());
        return (ps.executeUpdate()!=0);
    }
    
     public static boolean updatePatient(PatientPojo p)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update patient set f_name=?,s_name=?,age=?,OPD=?,gender=?,m_status=?,p_date=?,Address=?,phone_no=?,doctor_id=?,Bed_no=?,Ward_no=?,active=? where p_id=?");
     //   ps.setString(1,p.getP_Id());
        ps.setString(1,p.getF_Name());
        ps.setString(2,p.getS_Name());
        ps.setInt(3,p.getAge());
        ps.setString(4,p.getOPD());
        ps.setString(5,p.getGender());
        ps.setString(6,p.getM_Status());
        ps.setDate(7,p.getP_Date());
        ps.setString(8,p.getAddress());
        ps.setString(9,p.getPhone_No());
        ps.setString(10,p.getDoctor_Id());
        ps.setString(13,"Y");
        ps.setString(12,p.getWardNo());
        ps.setString(11,p.getBedNo());
        ps.setString(14,p.getP_Id());
        ps.setString(15, null);
        int result= ps.executeUpdate();
        return result>0;
    }
    
    public static String getNewId()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select count(*) from patient");
        int id = 1;
        if(rs.next()==true)
        {
            String patientid = rs.getString(1);
            // int eno = Integer.parseInt(empid.substring(1));
            System.out.println(patientid);
    //        int eno = Integer.parseInt(patientid.substring(1));
            id =id+ Integer.parseInt(patientid);
            String sr = "P"+id;
        return sr;
        }
        else
            return "P101";
        
    }
    public static HashMap<String,String> getPatientid() throws SQLException
    {
    Connection conn = DBConnection.getConnection();
    String qry="Select p_id,f_name from patient where active='Y'";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(qry);
    HashMap<String,String>patient = new HashMap();
    while(rs.next())
    {
        String id = rs.getString(1);
        String name = rs.getString(2);
        patient.put(id, name); 
    }
        return patient;
    }
 
    public static boolean deletePatient(String id)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update patient set active='N' where p_id=?");
        ps.setString(1, id);
        int Result = ps.executeUpdate();
        return Result>0;
    }
    public static ArrayList<PatientPojo> getAllPatient()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from patient where active='Y'");
        ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo p = new PatientPojo();
            p.setP_Id(rs.getString(1));
            p.setF_Name(rs.getString(2));
            p.setS_Name(rs.getString(3));
            p.setAge(rs.getInt(4));
            p.setOPD(rs.getString(5));
            p.setGender(rs.getString(6));
            p.setM_Status(rs.getString(7));
            p.setP_Date(rs.getDate(8));
            p.setAddress(rs.getString(9));
            p.setPhone_No(rs.getString(11));
            p.setDoctor_Id(rs.getString(12));
            p.setRelation(rs.getString(13));
            p.setSYW(rs.getString(14));
         //   p.setActive(rs.getString(13));
            patientlist.add(p);
            System.out.println(p);
        }
      //  System.out.println("............p.");
        return patientlist;
        
    }
    
     public static ArrayList<PatientPojo> getAllPatientDetailById(String pid)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from patient where p_id=? and active='Y'");
        ps.setString(1, pid);
        ResultSet rs = ps.executeQuery();
        ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo p = new PatientPojo();
            p.setP_Id(rs.getString(1));
            p.setF_Name(rs.getString(2));
            p.setS_Name(rs.getString(3));
            p.setAge(rs.getInt(4));
            p.setOPD(rs.getString(5));
            p.setGender(rs.getString(6));
            p.setM_Status(rs.getString(7));
            p.setP_Date(rs.getDate(8));
            p.setAddress(rs.getString(9));
            p.setPhone_No(rs.getString(10));
            p.setDoctor_Id(rs.getString(11));
            p.setActive(rs.getString(12));
            p.setBedNo(rs.getString(13));
            p.setWardNo(rs.getString(14));
            p.setRelation(rs.getString(15));
            p.setTag(rs.getString(16));
            p.setSYW(rs.getString(17));
         //   p.setActive(rs.getString(13));
            patientlist.add(p);
            System.out.println("Your details are  - "+p);
        }
       
        return patientlist;
        
    }
    
     public static ArrayList<PatientPojo> getAllPatients()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from patient");
        ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo p = new PatientPojo();
            p.setP_Id(rs.getString(1));
            p.setF_Name(rs.getString(2));
            p.setS_Name(rs.getString(3));
            p.setAge(rs.getInt(4));
            p.setOPD(rs.getString(5));
            p.setGender(rs.getString(6));
            p.setM_Status(rs.getString(7));
            p.setP_Date(rs.getDate(8));
            p.setAddress(rs.getString(9));
            p.setPhone_No(rs.getString(10));
            p.setDoctor_Id(rs.getString(11));
            p.setActive(rs.getString(12));
            p.setBedNo(rs.getString(13));
            p.setWardNo(rs.getString(14));
            p.setTag(rs.getString(15));
            p.setSYW(rs.getString(16));
            p.setRelation(rs.getString(17));
            
            patientlist.add(p);
        }
        return patientlist;
    }
     
       public static ArrayList<PatientPojo> getAllPatientByName(PatientPojo r)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select P_ID from patient where F_NAME=? and S_Name=?");
        ps.setString(1,r.getF_Name());
        ps.setString(2, r.getS_Name());
        ResultSet rs = ps.executeQuery();
        ArrayList<PatientPojo>patientlist = new ArrayList<>();
        while(rs.next())
        {
            PatientPojo p = new PatientPojo();
            p.setP_Id(rs.getString(1));
            patientlist.add(p);
            System.out.println("getAllPatientByName");
        }
        return patientlist;
    }
       public static ArrayList<String> getWardNo()throws SQLException
       {
           Statement st = DBConnection.getConnection().createStatement();
           ResultSet rs = st.executeQuery("select ward_no from ward");
           ArrayList<String>wardList = new ArrayList<>();
           while(rs.next())
           {
               String wardId = rs.getString(1);
               wardList.add(wardId);
           }
           return wardList;
       }
        public static ArrayList<String> getBedNo(String ward)throws SQLException
       {
           PreparedStatement ps = DBConnection.getConnection().prepareStatement("select bed_no from bed where bed_no not in(select bed_no from patient where ward_no=? and active='Y')");
           ps.setString(1, ward);
           ResultSet rs=ps.executeQuery();
           ArrayList<String>BedList = new ArrayList<>();
           while(rs.next())
           {
               String bedId = rs.getString(1);
               BedList.add(bedId);
           }
           return BedList;
       }
        public static String getP_id(String phone)throws SQLException
        {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("select p_id from patient where phone_no=?");
            ps.setString(1, phone);
            String p = null;
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                p = rs.getString(1);
            }
            return p;
        }
}
