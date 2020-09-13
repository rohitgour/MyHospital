
package MyHospital.Dao;

import MyHospital.dbutil.DBConnection;
import MyHospital.pojo.EmpPojo;
import java.io.IOException;
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
public class EmpDao {
    public static String getNewId()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select max(empid) from employees");
        int id = 1;
        if(rs.next())
        {
            String empid = rs.getString(1);
            int eno = Integer.parseInt(empid.substring(1));
            id =id+ eno;
        }
        String sr = "E"+id;
        System.out.println(sr);
        return sr;
    }
    public static boolean AddEmp(EmpPojo p)throws SQLException ,IOException
    {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into Employees values(?,?,?,?,'Y',?,?,?,?,?,?,?,?,?,?)");
        
        ps.setString(1,p.getEmpid());
        ps.setString(2,p.getEmpname());
        ps.setString(3,p.getJob()); //.toUpperCase()
        ps.setInt(4,(int)p.getSal());
        ps.setString(5,p.getTag());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getContact());
        ps.setString(8, p.getAlter_contact());
        ps.setString(9, p.getAddress());
        ps.setString(10,p.getEmail());
        ps.setInt(11,p.getAccount_no());
        ps.setString(12,p.getIfsc_code());
        ps.setString(13,p.getBranch());
        ps.setBinaryStream(14,p.getPs(),p.getPs().available());
        int a = ps.executeUpdate();
        if(a==1)
            return true;
        return false;
    }
    public static ArrayList<EmpPojo> getAllEmp()throws SQLException
    {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from employees where active='Y'");
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
    public static ArrayList<EmpPojo> getAllId()throws SQLException
     {
         Statement st = DBConnection.getConnection().createStatement();
         ResultSet rs = st.executeQuery("select empid from employees where active='Y'");
          ArrayList<EmpPojo> EmpId = new ArrayList<EmpPojo>();
         while(rs.next())
         {
             
             EmpPojo e = new EmpPojo();
             e.setEmpid(rs.getString(1));//setEmpno(rs.getInt(1));
             EmpId.add(e);
             
         }
         return EmpId;
         
     }
    public static ArrayList<EmpPojo> getAllEmployeeDetails()throws SQLException
     {
         Statement st = DBConnection.getConnection().createStatement();
         ResultSet rs = st.executeQuery("select * from employees where active='N'");
          ArrayList<EmpPojo> EmpDetails = new ArrayList<EmpPojo>();
         while(rs.next())
         {
             
              EmpPojo p = new EmpPojo();
            p.setEmpid(rs.getString(1));
            p.setEmpname(rs.getString(2));
            p.setJob(rs.getString(3));
            p.setSal(rs.getInt(4));
             p.setActive(rs.getString(5));
             EmpDetails.add(p);
             
         }
         return EmpDetails;
         
     }
    
    
public static EmpPojo findEmpById(String eno)throws SQLException
    {
        EmpPojo e=null;
       PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from employees where empid=? and active='Y'");
       ps.setString(1,eno);
       ResultSet rs =ps.executeQuery();
       if(rs.next())
       {
         e=new EmpPojo();
         e.setEmpid(rs.getString(1));
         e.setEmpname(rs.getString(2));
         e.setJob(rs.getString(3));
         e.setSal(rs.getInt(4));
         e.setActive(rs.getString(5));
         e.setTag(rs.getString(6));
         e.setGender(rs.getString(7));
         e.setContact(rs.getString(8));
         e.setAlter_contact(rs.getString(9));
         e.setAddress(rs.getString(10));
         e.setEmail(rs.getString(11));
         e.setAccount_no(rs.getInt(12));
         e.setIfsc_code(rs.getString(13));
         e.setBranch(rs.getString(14));
  //       e.setPs(null);
       }
       return e; 
    }
public static boolean UpdateEmp(EmpPojo p)throws SQLException
{
    PreparedStatement ps = DBConnection.getConnection().prepareStatement("update employees set empname=?,role=?,sal=? where empid=?");
    ps.setString(1,p.getEmpname());
    ps.setString(2,p.getJob());
    ps.setInt(3,(int)p.getSal());
    ps.setString(4,p.getEmpid());
    
//    String rohit = p.getEmpid();
//    System.out.println(rohit);
    int result=ps.executeUpdate();
    if(result==1)
        return true;
    return false;
}
public static boolean empRemove(String empid)throws SQLException
{
    PreparedStatement ps = DBConnection.getConnection().prepareStatement("update employees set active='N' where empid=?");
    ps.setString(1, empid);
    int result =ps.executeUpdate();
    if(result==1)
        return true;
    return false;
}
public static HashMap<String,String> getNonRegisteredReceptionist() throws SQLException
{
    Connection conn = DBConnection.getConnection();
    String qry="Select empid,empname from employees where role='Receptionist' and empid not in(select empid from users where usertype='Receptionist')";
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery(qry);
    HashMap<String,String>receptionist = new HashMap();
    while(rs.next())
    {
        String id = rs.getString(1);
        String name = rs.getString(2);
        receptionist.put(id, name); 
    }
    return receptionist;
}

public static boolean deleteOtherEmployee(String empid)throws SQLException
{
    PreparedStatement ps = DBConnection.getConnection().prepareStatement("select empid from employees where empid=? and empid not in(select empid from users where empid=?)");
    ps.setString(1, empid);
    ps.setString(2, empid);
    int result  = ps.executeUpdate();
    return result>0;
}

public static String getEmpIdByName(String empname)throws SQLException
{
    PreparedStatement ps = DBConnection.getConnection().prepareStatement("select empid from employees where empname=?");
    ps.setString(1, empname);
    ResultSet rs = ps.executeQuery();
    String id =null;
    while(rs.next())
    {
        id = rs.getString(1);
    }
    return id;
}
}
