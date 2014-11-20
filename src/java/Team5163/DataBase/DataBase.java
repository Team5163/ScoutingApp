/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team5163.DataBase;

//import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
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
//    private final String username = "root";
//    private final String url = "jdbc:mysql://127.13.42.2:3306/";
//    private final String databaseName = "scoutdb";
//    private final String password = "5163";
    
//    private List<String> listOfString = new ArrayList<>();

//    public List<String> getAllData(){
//        this.listOfString.add("Fuck you rish");
//        this.listOfString.add("Why?");
//        this.listOfString.add("Why not?? *said nobody ever*");
//        return listOfString;
//    }
    public void connect() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/scoutdb", "root", "5163");
            //connection = DriverManager.getConnection(this.url + this.databaseName, this.username, this.password);
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
    
    public void checkConnection() {
        if (connection == null) {
            try {
                if (datasource == null) {
                    initialContext = new InitialContext();
                    datasource = (DataSource)initialContext.lookup("java:comp/env/jdbc/scoutdb");
                }
                connection = datasource.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getLength() {
        try {
            while(!connection.isValid(0)){
                this.connect();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            while(!connection.isValid(0)){
                this.connect();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            String[] result = new String[getLength("SELECT * FROM teamdata WHERE teamnum LIKE '" + number + "%' ORDER BY ABS(teamnum)")];
            ResultSet rs = null;
            try {
                statement = connection.createStatement();            
                rs = statement.executeQuery("SELECT * FROM teamdata WHERE teamnum LIKE '" + number + "%' ORDER BY ABS(teamnum)");
                //SELECT * FROM teamdata WHERE teamnum LIKE '%00%' ORDER BY ABS(teamnum);
                int i = 0;
                while (rs.next()) {
                    result[i] = rs.getString("teamnum");
                    i++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            return result;
    }
    
    public boolean haveTeam(String teamNumber){
        return getLength("SELECT * FROM teamdata WHERE teamnum='" + teamNumber + "'") != 0;
    }
    
    public void addTeam(String teamNumber) {
        PreparedStatement ps = null;
        if (!haveTeam(teamNumber)) {
            try {
                ps = connection.prepareStatement("INSERT INTO teamdata VALUES ('" + teamNumber + "',null,null,null,null,null,null,null,null,null,null,null)");
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        } else {
            Team5163.Logger.Logger.log("Team " + teamNumber + " Already Exists");
        }
    }
    public void setData(String teamNumber, String field, String data){
        PreparedStatement ps = null;
        if (getLength("SELECT * FROM teamdata WHERE teamnum = '" + teamNumber + "'") == 0)  {
            Team5163.Logger.Logger.log("Team " + teamNumber + " does not exist. Field " + field + " not updated to " + data + ".");
        } else {
            try {
                ps = connection.prepareStatement("UPDATE teamdata SET " + field + " = '" + data + "' WHERE teamnum = '" + teamNumber + "'");
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public boolean haveData(String teamNumber, String field){
        ResultSet rs;
        Boolean wasNull = null;
        // The irony... 
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM teamdata WHERE teamnum = '" + teamNumber + "'");
            rs.next();
            rs.getString(field);
            wasNull = rs.wasNull(); //What does wasNull do?
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return !wasNull;
    }
    
    public Map<String, Integer> getLogins() {
        checkConnection(); //add these to all methods?
        ResultSet rs;
        Map<String, Integer> logins = new HashMap<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM logins");
        
        
            while (rs.next()) {
                logins.put(rs.getString("username"), rs.getInt("passhash"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return logins;
    }
    
    public Map<String, Integer> addUser(String username, int passhash) {
        PreparedStatement ps;
        try {
            Team5163.Logger.Logger.log("INSERT INTO logins VALUES(\"" + username + "\"," + passhash + ");");
            ps = connection.prepareStatement("INSERT INTO logins VALUES(\"" + username + "\"," + passhash + ");");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return getLogins();
    }
    
    public Map<String, Integer> removeUser(String username) {
        try {
            statement.executeUpdate("DELETE FROM logins WHERE username=\"" + username + "\");");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getLogins();
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
