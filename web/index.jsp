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
        <script>
            var URLCreate = "Login/CreateAccount.jsp";
            var URLTeamList = "Data?type=teamList&teamNumber=11";
            function setFrame1(url){
                document.getElementById("frame1").setAttribute("src", url);
            }
            function setTeamList(url){
                document.getElementById("teamListFrame").setAttribute("src", url)
            }
        </script>
        <%  DataBase data = ObjectRegestry.getDataBase(); 
        String frame1;
        if(request.getAttribute("frame1") != null){
            frame1 = request.getAttribute("frame1").toString();
        } else {
            frame1 = "logo.png";
        }
        if(request.getSession().getAttribute("login") == null) {
            request.getSession().setAttribute("login", "false");
        }
        %>
        <div id="logo">
            <img src="logo.png" id="logoimage" />

            <div id="logosub">
                <h3>Team 5163 <br /> Scouting</h3>
                <br />

            </div>
        </div>
        <div id="teamList">
            <form method="POST" action="processsearch.jsp">
                <input type="text" placeholder="Team Number" oninput="setTeamList(URLTeamList)"/>
                <input type="submit" value="Go" />
            </form>
            <!-- Generate list of team numbers here -->
            <iframe id="teamListFrame" src="Data" width="90%" seamless></iframe>
        </div>
        <div id="login">
            <% if (request.getSession().getAttribute("login").toString().equalsIgnoreCase("true")) {
            %> 
            <p>Welcome <%= request.getSession().getAttribute("name")%></p>
            <form method="POST" action="Server">
                <input type="hidden" name="keepin" value="false" />
                <input type="submit" value="Sign Out" />
            </form>
            <%
            } else {
            %> 
            <form method="POST" action="Server">
                <input type="hidden" name="frame1" value="Login/CreateAccount.jsp" />
                <input type="submit" value="Create Account" />
            </form>
            <input type="button" value="Better" onclick=" setFrame1(URLCreate); " />
            <form method="POST" action="Login">
                <input type="text" placeholder="Username" name="user" /><br />
                <input type="password"  placeholder = "Password" name = "pass" />
                <input type="submit" value="Go"/>
            </form>  
            <%
                } %>
        </div>
        <div id="info">
            <iframe src="<%= frame1 %>" id="frame1"></iframe>
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
