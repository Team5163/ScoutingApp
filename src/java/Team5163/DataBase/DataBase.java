/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team5163.DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Yiwen Dong
 */
@WebServlet(name = "DataBase", urlPatterns = {"/DataBase"})
public class DataBase{
    private Connection connection;
    private PreparedStatement pstatement;
    private Statement statement;
    private ResultSet result;
    private int length;
    private String[] resultarr;
    private Context initialContext;
    private DataSource datasource;
    
    private List<String> listOfString = new ArrayList<>();

//    public List<String> getAllData(){
//        this.listOfString.add("Fuck you rish");
//        this.listOfString.add("Why?");
//        this.listOfString.add("Why not?? *said nobody ever*");
//        return listOfString;
//    }
    public void connect() {
        
        try {
            initialContext = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            datasource = (DataSource)initialContext.lookup("java:comp/env/jdbc/scoutdb");
        } catch (NamingException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            connection = datasource.getConnection();
            //ConnectionFactory cf = (ConnectionFactory) initialContext.lookup("java:comp/env/jdbc/ScoutDB")
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Test");
            rs.last();
            Team5163.Logger.Logger.log(String.valueOf(rs.getRow()));
            //Team5163.Logger.Logger.log(rs.);
            Team5163.Logger.Logger.log("HEHEHEHEHEHEHEHEHEEHEHEHEHEHEHHEHEHEHEHEHEHEHEHEHEHEHEHEHEHEE");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        Team5163.Logger.Logger.log("HOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOOHOHOHOOHOHOOHOHOHHOHOHOHOH");
        System.out.println("test");
        System.err.println("Test");
    }
    
    public String getData(int teamNumber, String field){
        return "";
    }
    
    public String[] findTeam(int number){
        String[] thing = new String[5];
        return thing;
    }
    
    public boolean haveTeam(){
        return false;
    }
    
    public void setData(int teamNumber, String field, String data){
        
    }
    
    public boolean haveData(int teamNumber, String field){
        return false;
    }
    
    public void createTeam(int number){
        
    }
}
