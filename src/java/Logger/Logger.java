/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yiwen Dong
 */
@WebServlet(name = "Logger", urlPatterns = {"/Logger"})
public class Logger{
    
    public static void log(String message){
        System.err.println(parseTime() + message);
    }
    
    private static String parseTime(){
        Date date = new Date(System.currentTimeMillis());
        return "[" + date.toString() + "] : ";
    }
    
}
