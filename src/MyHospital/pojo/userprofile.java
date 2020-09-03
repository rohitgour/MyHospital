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
public class userprofile {

    public static String getUsertype() {
        return usertype;
    }

    public static void setUsertype(String usertype) {
        userprofile.usertype = usertype;
    }

    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        userprofile.userid = userid;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        userprofile.username = username;
    }
    private static String username;
    private static String userid;
    private static String usertype;
    
    
}
