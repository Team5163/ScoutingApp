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
    
    public String getData(int teamNumber, String field){
        String data;
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
                if (Integer.parseInt(rs.getString("teamnum")) == teamNumber) {
                    return rs.getString(field);
                } else {
                    rs.next();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }
    
    public String[] findTeam(int number){
        //TO DO! Make this work with SQL queries and less for loops. Fsck for loops.
        int removed = 0;
        ArrayList<String> teams = new ArrayList();
        String numstring = String.valueOf(number);
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM teamdata");
            rs.next();
            for (int i = 0; i < getLength(); i++) {
                if (String.valueOf(rs.getString("teamnum").charAt(0)).equals(String.valueOf(numstring.charAt(0)))) {
                    teams.add(rs.getString("teamnum"));
                }
            }
            
            if (numstring.length() > 1) {
                for (int i = 0; i < teams.size(); i++) {
                    if (!String.valueOf(numstring.charAt(1)).equals(String.valueOf(teams.get(i - removed)).charAt(1))) {
                        removed++;
                        teams.remove(i - removed);
                    }
                }
            }
            removed = 0;
            if (numstring.length() > 2) {
                for (int i = 0; i < teams.size(); i++) {
                    if (!String.valueOf(numstring.charAt(2)).equals(String.valueOf(teams.get(i - removed)).charAt(2))) {
                        removed++;
                        teams.remove(i - removed);
                    }
                }
            }
            removed = 0;
            if (numstring.length() > 3) {
                for (int i = 0; i < teams.size(); i++) {
                    if (!String.valueOf(numstring.charAt(3)).equals(String.valueOf(teams.get(i - removed)).charAt(3))) {
                        removed++;
                        teams.remove(i - removed);
                    }
                }
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] match = (String[]) teams.toArray();
        return match;
    }
    
    public boolean haveTeam(int teamNumber){
    
    return getLength("SELECT * FROM teamdata WHERE teamnum=" + teamNumber) != 0;
    
    }
    
    public void setData(int teamNumber, String field, String data){
        
    }
    
    public boolean haveData(int teamNumber, String field){
        return false;
    }
    
    public void createTeam(int number){
        
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
