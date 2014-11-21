/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5163.DataBase;

import java.sql.*;
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
//TODO: Rewrite to use PareparedStatement
public class DataBase {

    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private int length;
    private Context initialContext;
    private DataSource datasource;

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
            datasource = (DataSource) initialContext.lookup("java:comp/env/jdbc/scoutdb");
        } catch (NamingException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void checkConnection() {
        try {
            while (connection == null) {
                try {
                    if (datasource == null) {
                        initialContext = new InitialContext();
                        datasource = (DataSource) initialContext.lookup("java:comp/env/jdbc/scoutdb");
                    }
                    connection = datasource.getConnection();
                } catch (SQLException | NamingException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            while (!connection.isValid(0)) {
                this.connect();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getLength() {
        checkConnection();
        try {
            ps = connection.prepareStatement("SELECT teamnum FROM teamdata");
            ResultSet rs = ps.executeQuery();
            rs.last();
            length = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return length;
    }

    private int getLength(String query) {
        checkConnection();
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.last();
            length = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return length;
    }

    public String getData(String teamNumber, String field) {
        checkConnection();
        String data = null;
        try {
            ps = connection.prepareStatement("SELECT teamnum," + field + " FROM teamdata;");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
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
        closeStatement();
        return data;
    }

    public String[] findTeam(String number) {
        checkConnection();
        String[] result = new String[getLength("SELECT teamnum FROM teamdata WHERE teamnum LIKE '" + number + "%' ORDER BY ABS(teamnum)")];
        ResultSet rs;
        try {
            ps = connection.prepareStatement("SELECT teamnum FROM teamdata WHERE teamnum LIKE '" + number + "%' ORDER BY ABS(teamnum)");
            rs = ps.executeQuery();
            //SELECT * FROM teamdata WHERE teamnum LIKE '%00%' ORDER BY ABS(teamnum);
            int i = 0;
            while (rs.next()) {
                result[i] = rs.getString("teamnum");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return result;
    }

    public boolean haveTeam(String teamNumber) {
        checkConnection();
        return getLength("SELECT * FROM teamdata WHERE teamnum='" + teamNumber + "'") != 0;
    }

    public void addTeam(String teamNumber) {
        checkConnection();
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

    public void setData(String teamNumber, String field, String data) {
        checkConnection();
        if (getLength("SELECT * FROM teamdata WHERE teamnum = '" + teamNumber + "'") == 0) {
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

    public boolean haveData(String teamNumber, String field) {
        checkConnection();
        ResultSet rs;
        Boolean wasNull = null; //Maybe change to true?
        // The irony... 
        try {
            ps = connection.prepareStatement("SELECT " + field + " FROM teamdata WHERE teamnum = '" + teamNumber + "';");
            rs = ps.executeQuery();
            rs.next();
            rs.getString(field);
            wasNull = rs.wasNull();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return !wasNull;
    }

    public Map<String, Integer> getLogins() {
        checkConnection();
        ResultSet rs;
        Map<String, Integer> logins = new HashMap<>();
        try {
            ps = connection.prepareStatement("SELECT * FROM logins;");
            rs = ps.executeQuery();

            while (rs.next()) {
                logins.put(rs.getString("username"), rs.getInt("passhash"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return logins;
    }

    public Map<String, Integer> addUser(String username, int passhash, int teamnum) {
        checkConnection();
        try {
//            Team5163.Logger.Logger.log("INSERT INTO logins VALUES(\"" + username + "\"," + passhash + ");");
            ps = connection.prepareStatement("INSERT INTO logins VALUES(\"" + username + "\"," + passhash + "," + teamnum + ");");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getLogins();
    }

    public Map<String, Integer> removeUser(String username) {
        checkConnection();
        try {
            ps = connection.prepareCall("DELETE FROM logins WHERE username=\"" + username + "\");");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return getLogins();
    }

    public int getTeamNumber(String username) {
        checkConnection();
        ResultSet rs;
        int teamnum = 0;
        try {
            ps = connection.prepareStatement("SELECT teamnum FROM logins WHERE (username=\"" + username + "\");");
            rs = ps.executeQuery();
            rs.next();
            teamnum = rs.getInt("teamnum");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return teamnum;
    }

    public void setTeamNumber(String username, int number) {
        checkConnection();
        //NOT YET IMPLEMENTED, use this to test preparedStatement
        closeStatement();
    }

    public int[] getTeamMatches(String teamnum) { //TODO: Write getLength method with resultset as input
        checkConnection();
        ResultSet rs;
        int[] matchnums = null;
        try {
            ps = connection.prepareStatement("SELECT matchnum FROM matchdata WHERE (red1= " + teamnum + ") OR (red2=" + teamnum + ") OR (red3= " + teamnum + ") OR (blue1= " + teamnum + ") OR (blue2= " + teamnum + ") OR (blue3= " + teamnum + ");");
            rs = ps.executeQuery();
            matchnums = new int[getLength("SELECT matchnum FROM matchdata WHERE (red1= " + teamnum + ") OR (red2=" + teamnum + ") OR (red3= " + teamnum + ") OR (blue1= " + teamnum + ") OR (blue2= " + teamnum + ") OR (blue3= " + teamnum + ");")];
            int i = 0;
            while (rs.next()) {
                matchnums[i] = rs.getInt("teamnum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return matchnums;
    }
    
    private void closeStatement() {
        //To be run after every function call in this class.
    }
    
    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}