/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Team5163.Login;

import Team5163.Logger.Logger;
import static Team5163.Logger.Logger.log;
import Team5163.ObjectRegestry;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yiwen Dong
 */
public class Login extends HttpServlet {

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
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        Logger.log("User: \"" + name + "\" Tried to login using pass: \"" + pass + "\"");
        Logger.log(ObjectRegestry.getWorkingDir());
        if (checkPass(name, pass)) {
            Logger.log("Welcome user");
            Logger.log(System.getProperty("user.dir"));
            request.getSession().setAttribute("login", "true");
            request.getSession().setAttribute("name", name);
            request.getRequestDispatcher("Display.jsp").forward(request, response);
        } else {
            Logger.log(System.getProperty("user.dir"));
            Logger.log("you idieot");
        }
    }

    private boolean checkPass(String username, String password) {
        if (ObjectRegestry.getAllUsers().containsKey(username)) {
            if (ObjectRegestry.getAllUsers().get(username).hashCode() == password.hashCode()) {
                return true;
            }
        }
        return false;
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
