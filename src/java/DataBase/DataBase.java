/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yiwen Dong
 */
@WebServlet(name = "DataBase", urlPatterns = {"/DataBase"})
public class DataBase{
    
    private List<String> listOfString = new ArrayList<>();

    public List<String> getAllData(){
        this.listOfString.add("Fuck you rish");
        this.listOfString.add("Why?");
        this.listOfString.add("Why not?? *said nobody ever*");
        return listOfString;
    }
}
