/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Team5163.DataBase;

import Team5163.Logger.Logger;
import Team5163.ObjectRegistry;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yiwen Dong
 */
public class Data extends HttpServlet {

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
        String requestType = request.getParameter("type");
            if(requestType != null){
                if(requestType.equalsIgnoreCase("teamList")){
                    //return list of teams.
                    int teamNumber = Integer.valueOf(request.getParameter("teamNumber"));
                    request.setAttribute("1", (int)Math.floor(Math.random() * 10000));
                    request.setAttribute("2", (int)Math.floor(Math.random() * 10000));
                    request.setAttribute("3", (int)Math.floor(Math.random() * 10000));
                    request.setAttribute("4", (int)Math.floor(Math.random() * 10000));
                    request.setAttribute("5", (int)Math.floor(Math.random() * 10000));
                    request.getRequestDispatcher("Data/TeamList.jsp").forward(request, response);
                    //search database from a partial complete number 
                }
                if(requestType.equalsIgnoreCase("viewPage")){
                    Logger.log("Getting page for team: " + request.getParameter("teamNumber"));
                    request.getRequestDispatcher("Data/TeamData.jsp").forward(request, response);
                }
            }
    }
    @Override
    public void init(){
        //Logger.log("Start");
        ObjectRegistry.getDataBase().connect();
    }
    
    @Override
    public void destroy(){
        //Logger.log("destory");
        ObjectRegistry.getDataBase().close();
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
