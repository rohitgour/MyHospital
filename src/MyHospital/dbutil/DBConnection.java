/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class DBConnection {
    private static Connection conn;
    static 
    {
        try 
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-K1G36F7:1521/XE","Rupesh1","abc1");
            System.out.println("Connection Open Successfully");
            // JOptionPane.showMessageDialog(null,"Connection Open successfully!");
        }
        catch(SQLException e)
        {
           JOptionPane.showMessageDialog(null,"Cannot Load Driver!"); 
           e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
          JOptionPane.showMessageDialog(null,"Problem in DB!"+e);    
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            if(conn!=null)
            {
            conn.close();
                System.out.println("Connection Close Successfully");
           // JOptionPane.showMessageDialog(null,"Connection Close Successfully!"); 
            }
        }
         catch(SQLException e)
        {
           JOptionPane.showMessageDialog(null,"Cannot close the Connection!"); 
           e.printStackTrace();
        }
    }
}
