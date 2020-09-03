/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyHospital.gui;

import java.text.SimpleDateFormat;

/**
 *
 * @author HP
 */
public class DateClass {
     private static java.util.Date d;
    public static String date()
    {
        d= new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        String src = sdf.format(d);
        return src;
        
    }
}
