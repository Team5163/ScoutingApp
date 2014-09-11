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
public class Create extends HttpServlet {
    
    String adminpass = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getRequestDispatcher("Create.jsp").forward(request, response);
        if(request.getParameter("user") == null){
            request.getRequestDispatcher("Login/CreateAccount.jsp").forward(request, response);
            return;
        }
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("re-pass");
        String adminpass = request.getParameter("adminPass");
        if(!pass.equals(repass)){
            request.setAttribute("error", "Password did not match, Plese try again.");
            request.getRequestDispatcher("Login/Fail.jsp").forward(request, response);
            return;
        }
        if(!this.adminpass.equals(adminpass)){
            request.setAttribute("error", "Plese contact your Adminstrator for the correct Admin Password");
            Logger.log("Attempted to regester account from: \"" + request.getRemoteAddr() + "\" but Failed - Wrong Admin Pass");
            request.getRequestDispatcher("Login/Fail.jsp").forward(request, response);
            return;
        }
        if(pass.equals(repass) && this.adminpass.equals(adminpass)){
            ObjectRegistry.getLoginData().addUser(name, pass);
            Logger.log("Account added with name: \"" + name + "\" with pass \"" + pass.hashCode() + "\"");
            ObjectRegistry.getLoginData().listUser();
            request.getRequestDispatcher("Login/Success.html").forward(request, response);
            return;
        }
        
    }
    
    @Override
    public void init(){
        //Logger.log("Start");
        adminpass = String.valueOf((int)Math.floor(Math.random() * 10000));
        Logger.log("To create account, use admin password: " + adminpass);
    }
    
    @Override
    public void destroy(){
        //Logger.log("destory");
        
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
        return "Short description";
    }// </editor-fold>

}
