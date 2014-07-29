<%-- 
    Document   : Display
    Created on : Jul 27, 2014, 4:04:55 PM
    Author     : Yiwen Dong
--%>

<%@page import="Team5163.ObjectRegestry"%>
<%@page import="java.util.List"%>
<%@page import="Team5163.DataBase.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 5163 Scouting </title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <% DataBase data = ObjectRegestry.getDataBase(); %>
        <div id="logo">
            <img src="logo.png" id="logoimage" />

            <div id="logosub">
                <h3>Team 5163 <br /> Scouting</h3>
                <br />

            </div>
        </div>
        <div id="teamList">
            <form method="POST" action="processsearch.jsp">
                <input type="text" placeholder="Team Number" />
                <input type="submit" value="Go" />
            </form>
            <!-- Generate list of team numbers here -->
        </div>
        <div id="login">
            <% if (request.getSession().getAttribute("login").toString().equalsIgnoreCase("true")) {
            %> 
            <p>Welcome <%= request.getSession().getAttribute("name")%></p>
            <form method="POST" action="Server">
                <input type="submit" value="Sign Out" />
            </form>
            <%
            } else {
            %> 
            <form method="POST" action="Login">
                <input type="text" placeholder="Username" name="user" /><br />
                <input type="password"  placeholder = "Password" name = "pass" />
                <input type="submit" value="Go"/>
            </form>  
            <%
                } %>
        </div>
        <div id="info">
            <table>
                <% List<String> strings = data.getAllData();
                    for (int a = 0; a < strings.size(); a++) {
                %>
                <tr>
                    <td><%= a%></td>
                    <td><%= strings.get(a)%></td>
                    <% }%>
            </table>
        </div>
    </body>
</html>
