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

    public void addTeam(String teamnum) {
        checkConnection();
        if (!haveTeam(teamnum)) {
            try {
                ps = connection.prepareStatement("INSERT INTO teamdata VALUES (?,null,null,null,null,null,null,null,null,null,null,null)");
                ps.setString(1, teamnum);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            Team5163.Logger.Logger.log("Team " + teamnum + " Already Exists");
        }
    }

    public void setData(String teamnum, String field, String data) {
        checkConnection();
        if (getLength("SELECT * FROM teamdata WHERE teamnum = '" + teamnum + "'") == 0) {
            Team5163.Logger.Logger.log("Team " + teamnum + " does not exist. Field " + field + " not updated to " + data + ".");
        } else {
            try {
                ps = connection.prepareStatement("UPDATE teamdata SET " + field + " = ? WHERE teamnum = ?"); //field, data, teamnum
                ps.setString(1, data);
                ps.setString(2, teamnum);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean haveData(String teamnum, String field) {
        checkConnection();
        ResultSet rs;
        Boolean wasNull = null; //Maybe change to true?
        // The irony... 
        try {
            ps = connection.prepareStatement("SELECT " + field + " FROM teamdata WHERE teamnum = '" + teamnum + "';");
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

    public Map<String, Integer> addUser(String username, int passhash, int teamnum) { //decide on string or int for teamnum and make a database with it- right now it's a string in one place and an int in another.
        checkConnection();
        try {
//            Team5163.Logger.Logger.log("INSERT INTO logins VALUES(\"" + username + "\"," + passhash + ");");
            ps = connection.prepareStatement("INSERT INTO logins VALUES(?, ?, ?);");
            ps.setString(1, username);
            ps.setInt(2, passhash);
            ps.setInt(3, teamnum);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getLogins();
    }

    public Map<String, Integer> removeUser(String username) {
        checkConnection();
        try {
            ps = connection.prepareCall("DELETE FROM logins WHERE (username=?);"); //username
            ps.setString(1, username);
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

    public int[] getTeamMatches(String teamnum) { //TODO: Write getLength method with resultset as input. Change teamnum to an int instead of a string in the db and in this method.
        checkConnection();
        ResultSet rs;
        int[] matchnums = null;
        try {
            ps = connection.prepareStatement("SELECT matchnum FROM matchdata WHERE (red1= " + teamnum + ") OR (red2=" + teamnum + ") OR (red3= " + teamnum + ") OR (blue1= " + teamnum + ") OR (blue2= " + teamnum + ") OR (blue3= " + teamnum + ");");
            rs = ps.executeQuery(); //Use preparedstatement arguments here instead of string concat
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

    public boolean getWinningAlliance(int matchnum) {
        //Returns true for blue, false for red. Why? Because true and blue rhyme. On a more serious note, how will this handle ties? Null?
        checkConnection();
        ResultSet rs;
        int totalred = 0;
        int totalblue = 0;
        try {
            ps = connection.prepareStatement("SELECT red1score,red2score,red3score,blue1score,blue2score,blue3score FROM matchdata WHERE matchnum = ?");
            ps.setInt(1, matchnum);
            rs = ps.executeQuery();

            totalred = rs.getInt("red1score") + rs.getInt("red2score") + rs.getInt("red3score");
            totalblue = rs.getInt("blue1score") + rs.getInt("blue2score") + rs.getInt("blue3score");

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return totalblue > totalred;

    }

    public Map<Integer, Integer> getScores(int matchnum) { //TODO: Add a duplicate team resolution/merge tool
        //<TeamNum,Score>
        checkConnection();
        Map<Integer, Integer> scores = new HashMap();
        ResultSet rs;

        try {
            ps = connection.prepareStatement("SELECT red1,red2,red3,blue1,blue2,blue3,red1score,red2score,red3score,blue1score,blue2score,blue3score FROM matchdata WHERE matchnum = ?");
            rs = ps.executeQuery();
            rs.next();

            scores.put(rs.getInt("red1"), rs.getInt("red1score"));
            scores.put(rs.getInt("red2"), rs.getInt("red2score"));
            scores.put(rs.getInt("red3"), rs.getInt("red3score"));
            scores.put(rs.getInt("blue1"), rs.getInt("blue1score"));
            scores.put(rs.getInt("blue2"), rs.getInt("blue2score"));
            scores.put(rs.getInt("blue3"), rs.getInt("blue3score"));

            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeStatement();
        return scores;
    }

    public String[][] summarizeDatabase(String tablename) {
        checkConnection();
        ResultSet rs;
        ResultSetMetaData rsmd;
        int numCols, length;
        String[][] summary;
        try {
            length = getLength("SELECT * FROM " + tablename + ";");
            ps = connection.prepareStatement("SELECT * FROM " + tablename + ";"); //Is this line needed?
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            numCols = rsmd.getColumnCount();
            summary = new String[length][numCols];
            for (int i = 0; i < length; i++) {
                rs.next();
                for (int j = 0; j < numCols; j++) {
                    summary[i][j] = rs.getObject(j).toString();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return new String[1][1]; //TO BE IMPLEMENTED, for use with planned debug page
    }

    private void closeStatement() {
        //To be run after every function call in this class.
    }

    public void close() {
        try {
            statement.close();
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
