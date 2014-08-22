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
 * @author Rish Shadra
 */
@WebServlet(name = "DataBase", urlPatterns = {"/DataBase"})
public class DataBase{
    private Connection connection;
    //private PreparedStatement pstatement;
    private Statement statement;
    //private ResultSet result;
    private int length;
    //private String[] resultarr;
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
        
//        try {
//            
//            //statement = connection.createStatement();
//            //ResultSet rs = statement.executeQuery("SELECT * FROM Test");
//            //Team5163.Logger.Logger.log(String.valueOf(rs.getRow()));
//            //Team5163.Logger.Logger.log(rs.);
//            //Team5163.Logger.Logger.log("HEHEHEHEHEHEHEHEHEEHEHEHEHEHEHHEHEHEHEHEHEHEHEHEHEHEHEHEHEHEE");
//        } catch (SQLException ex) {
//            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //Team5163.Logger.Logger.log("HOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOHOOHOHOHOOHOHOOHOHOHHOHOHOHOH");
        //System.out.println("test");
        //System.err.println("Test");
    }
    
    public int getLength() {
    
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teamdata");
            resultSet.last();    
            length = resultSet.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }
    
    
    public int getLength(String query) {
    
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();    
            length = resultSet.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }
    
    public String getData(String teamNumber, String field){
        String data = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery("SELECT * FROM teamdata");
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < getLength(); i++) {
            try {
                if (rs.getString("teamnum").equals(teamNumber)) {
                    data = rs.getString(field);
                } else {
                    rs.next();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return data;
    }
    
    public String[] findTeam(String number){
            String[] result = new String[getLength("SELECT * FROM teamdata WHERE teamnum LIKE '" + number + "%'")];
            ResultSet rs = null;
        try {
            //DONE! Make this work with SQL queries and less for loops. Fsck for loops.

            //if (number.length() > 4) {
            //    return null;
            //}
            statement = connection.createStatement();
            
                rs = statement.executeQuery("SELECT * FROM teamdata WHERE teamnum LIKE '" + number + "%'");
                //rs.next();
            int i = 0;
            while (rs.next()) {
                //if (rs.getString("teamnum") != null) {
                result[i] = rs.getString("teamnum");
                i++;
                //}
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public boolean haveTeam(String teamNumber){
    
    return getLength("SELECT * FROM teamdata WHERE teamnum=" + teamNumber) != 0;
    //Does this thing need quotes or what?
    }
    
    public void setData(int teamNumber, String field, String data){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO teamdata (" + field + ") VALUES ('" + data + "')");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public boolean haveData(String teamNumber, String field){
        ResultSet rs;
        Boolean wasNull = null;
        // The irony... 
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM teamdata WHERE teamnum=" + teamNumber);
            rs.next();
            //Do I need quotes on that statement? Stay tuned for the next episode of TESTING!
            rs.getString(field);
            wasNull = rs.wasNull();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !wasNull;
    }
    
    /*public void createTeam(int number){
        DEPRICATED. Use setData with field teamnum instead.
    }*/
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
