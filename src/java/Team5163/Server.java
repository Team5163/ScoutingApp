package Team5163;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Team5163.DataBase.DataBase;
import Team5163.Logger.Logger;
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
public class Server extends HttpServlet {
    
    DataBase dataBase = new DataBase();

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Server</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Server at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        if(request.getSession().isNew()){
            Team5163.Logger.Logger.log("New client with ip: " + request.getRemoteAddr() + " With: " + request.getHeader("User-Agent"));
        }
        //request.setAttribute("data", new DataBase());
        if(request.getSession().getAttribute("login") == null){
            request.getSession().setAttribute("login", "false");
        }
        
        if(request.getParameter("keepin") != null){
            if(request.getParameter("keepin").equalsIgnoreCase("false")){
                request.getSession().setAttribute("login", "false");
            }
        }
        
        if(request.getParameter("frame1") != null){
            request.setAttribute("frame1", request.getParameter("frame1"));
            //Logger.log("Thing: " + request.getAttribute("frame1").toString());
        }
        
        if(request.getParameter("mode") != null){
            request.getSession().setAttribute("mode", request.getParameter("mode"));
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
