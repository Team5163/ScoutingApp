package Team5163;


import Team5163.DataBase.DataBase;
import Team5163.Logger.Logger;
import Team5163.Login.LoginData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yiwen Dong
 */
public class ObjectRegistry {
    
    private static DataBase dataBase = new DataBase();
    private static Logger logger = new Logger();
//    private static Map<String, Integer> users = new HashMap(){{
//        this.put("Yiwen", new String("1234").hashCode());
//    }};
    //private static String workingDir = System.getenv("APPDATA") + "\\ScoutingApp";
    private static String workingDir =  "/var/lib/openshift/542b12975973ca77b00000f3/app-root/data/";
    private static LoginData loginData = new LoginData();
    
    public static DataBase getDataBase(){
        return dataBase;
    }
    
    public static Logger getLogger(){
        return logger;
    }
    
//    public static Map<String, Integer> getAllUsers(){
//            loginData.getUsers();
//        return users;
//    }
    
    public static String getWorkingDir(){
        return workingDir;
    }
    
    public static LoginData getLoginData(){
        return loginData;
    }
}
