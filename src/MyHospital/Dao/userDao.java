/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.Dao;

import MyHospital.dbutil.DBConnection;
import MyHospital.pojo.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class userDao {
    
    public static String getvalidateId(String id)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select empid from users where userid=? and active='Y'");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        String empid=null;
        if(rs.next())
           empid=rs.getString(1);
        return empid;
    }
    
    
    
    public static String validateUser(User u)throws SQLException
    {
      
        PreparedStatement ps =DBConnection.getConnection().prepareStatement("Select username from Users where userid=? and password=? and usertype=? and active='Y'");
        ps.setString(1,u.getUserid());
        ps.setString(2,u.getPassword());
        ps.setString(3,u.getUsertype());
        ResultSet rs = ps.executeQuery();
        
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
            
        }
        return username;
        
    }
    public static String getUsertype(String userid) throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select usertype from users where userid=?");
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        String usertype=null;
        if(rs.next())
            usertype=rs.getString(1);
        return usertype;
    }
    public static String matchPassword(String userid)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select password from users where userid=?");
        ps.setString(1,userid);
        ResultSet rs = ps.executeQuery();
        String userpassword=null;
        if(rs.next())
        {
            userpassword=rs.getString(1);
            System.out.println("userpassord old password is  :--"+userpassword);
            
        }
        return userpassword;
        
    }
    public static boolean changePassword(User u)throws SQLException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update users set password=? where userid=?");
        ps.setString(1,u.getPassword());
        ps.setString(2,u.getUserid());
        int result =ps.executeUpdate();
        return result>0;   
    }
    public static String getDoctorId(String id)throws SQLException 
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select doctorid from doctors where userid=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        String Did=null;
        if(rs.next())
        {
            Did=rs.getString(1);
            System.out.println("id is :--"+Did);
            
        }
        return Did;
    }
    public static boolean removeUser(String id)throws SQLException
    {
      PreparedStatement ps = DBConnection.getConnection().prepareStatement("update users set active='N' where userid=?");
      ps.setString(1, id);
      int result = ps.executeUpdate();
      return result>0;
    }
    
}
