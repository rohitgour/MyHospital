/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.Dao;

import MyHospital.dbutil.DBConnection;
import MyHospital.pojo.EmpPojo;
import MyHospital.pojo.UserPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ReceptionistDao {
    public static boolean addReceptionist(UserPojo p) throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
        ps.setString(1,p.getUserid());
        ps.setString(2,p.getUserName());
        ps.setString(3,p.getEmpId());
        ps.setString(4,p.getPassword());
        ps.setString(5,p.getUsertype());
        int x = ps.executeUpdate();
        return x>0; 
    }

   public static ArrayList<EmpPojo> getAllReceptionist()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from employees where role='Receptionist' and active ='Y'");
        ArrayList<EmpPojo>emplist = new ArrayList<>();
        while(rs.next())
        {
            EmpPojo p = new EmpPojo();
            p.setEmpid(rs.getString(1));
            p.setEmpname(rs.getString(2));
            p.setJob(rs.getString(3));
            p.setSal(rs.getInt(4));
            emplist.add(p);
        }
    return emplist;
    }
   public static boolean removeReceptionist(String empid)throws SQLException
   {
      PreparedStatement ps =DBConnection.getConnection().prepareStatement("update users set active='N' where userid =?");
      ps.setString(1, empid);
      int result = ps.executeUpdate();
      return result>0;
   }
   public static UserPojo getReceptionist(String empid)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from users where empid=? and active='Y'");
        ps.setString(1, empid);
        ResultSet rs = ps.executeQuery();
       UserPojo p = new UserPojo();
        if(rs.next())
        {
            
            p.setUserid(rs.getString(1));
            p.setUserName(rs.getString(2));
            p.setEmpId(rs.getString(3));
            p.setPassword(rs.getString(4));
            p.setUsertype(rs.getString(5));
        }
    return p;
    }
   
    public static ArrayList<UserPojo> getAllId()throws SQLException
     {
         Statement st = DBConnection.getConnection().createStatement();
         ResultSet rs = st.executeQuery("select empid from users where usertype='Receptionist' and active='Y'");
          ArrayList<UserPojo> EmpId = new ArrayList<>();
         while(rs.next())
         {
             
             UserPojo e = new UserPojo();
             e.setEmpId(rs.getString(1));
             EmpId.add(e);
             
         }
         return EmpId;
         
     }
}