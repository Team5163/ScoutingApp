/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5163.Login;

import Team5163.Logger.Logger;
import Team5163.ObjectRegistry;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yiwen Dong
 */
public class Login extends HttpServlet { //This servlet throws a NullPointerException when you try to directly access it from the web.

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) //pass should not be stored as a string- Its hash code should be the only representation of it in memory.
            throws ServletException, IOException {
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        //Logger.log("User: \"" + name + "\" Tried to login using pass: \"" + pass + "\"");
        //Logger.log(ObjectRegestry.getWorkingDir());
        if (ObjectRegistry.getLoginData().checkUser(name, pass)) {
            Logger.log("User: \"" + name + "\" Successfuly logined using pass: \"" + pass.hashCode() + "\"");
            request.getSession().setAttribute("login", "true");
            request.getSession().setAttribute("name", name);
            request.getRequestDispatcher("Server").forward(request, response);
        } else {
            //Logger.log(System.getProperty("user.dir"));
            Logger.log("User: \"" + name + "\" Fail to login using pass: \"" + pass.hashCode() + "\"");
            request.getSession().setAttribute("login", "false");
            request.getRequestDispatcher("Server").forward(request, response);
        }
    }
    
    @Override
    public void init(){
        //Logger.log("Start");
        ObjectRegistry.getLoginData().start();
    }
    
    @Override
    public void destroy(){
        //Logger.log("destory");
        ObjectRegistry.getLoginData().stop();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet manages login and user verification.";
    }// </editor-fold>

}
