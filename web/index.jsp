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
        String frame2;
        
        //class can be button, currentButton, or grayButton
        String viewClass = "currentButton";
        String editClass = "clickableButton";
        String compareClass = "clickableButton";
        String aboutClass = "clickableButton";
        
        if(request.getSession().getAttribute("login") == null) {
            request.getSession().setAttribute("login", "false");
        }
        if(request.getSession().getAttribute("mode") == null) {
            request.getSession().setAttribute("mode", "view");
        }
        if(request.getSession().getAttribute("mode").toString().equalsIgnoreCase("view")){
            viewClass = "currentButton";
            editClass = "clickableButton";
            compareClass = "clickableButton";
            aboutClass = "clickableButton";
        } else if(request.getSession().getAttribute("mode").toString().equalsIgnoreCase("edit")){
            viewClass = "clickableButton";
            editClass = "currentButton";
            compareClass = "clickableButton";
            aboutClass = "clickableButton";
        } else if(request.getSession().getAttribute("mode").toString().equalsIgnoreCase("compare")){
            viewClass = "clickableButton";
            editClass = "clickableButton";
            compareClass = "currentButton";
            aboutClass = "clickableButton";
        } else if (request.getSession().getAttribute("mode").toString().equalsIgnoreCase("about")){
            viewClass = "clickableButton";
            editClass = "clickableButton";
            compareClass = "clickableButton";
            aboutClass = "currentButton";
        }
        if(request.getAttribute("frame1") != null){
            frame1 = request.getAttribute("frame1").toString();
        } else {
            frame1 = "Data/Logo.html";
        }
        if(request.getAttribute("frame2") != null){
            frame2 = request.getAttribute("frame2").toString();
        } else {
            frame2 = "Data/Logo.html";
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
            <iframe id="teamListFrame" src="Data" seamless></iframe>
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
            <a onclick="setFrame1(URLCreate);" href="#">Create Account</a>
            <!--<input type="button" value="Better" onclick=" setFrame1(URLCreate); " /> -->
            <form method="POST" action="Login">
                <input type="text" placeholder="Username" name="user" /><br />
                <input type="password"  placeholder = "Password" name = "pass" />
                <input type="submit" value="Go"/>
            </form>  
            <%
                } %>
        </div>
        <div id="topBar">
            <ul>
                <li><a href="Server?mode=view" class="<jsp:expression>viewClass</jsp:expression>">View</a></li>
                <li><a href="Server?mode=edit" class="<jsp:expression>editClass</jsp:expression>">Edit</a></li>
                <li><a href="Server?mode=compare" class="<jsp:expression>compareClass</jsp:expression>">Compare</a></li>
                <li><a href="Server?mode=about&frame1=about.html" class="<jsp:expression>aboutClass</jsp:expression>">About</a></li>
            </ul>
        </div>
        <div id="info">
        <% if(request.getSession().getAttribute("mode").toString().equalsIgnoreCase("compare")){%>
        <iframe src="<%= frame1 %>" id="frame1" class="framesThin"></iframe>
        <iframe src="<%= frame2 %>" id="frame2" class="framesThin"></iframe>
        <%} else {%>
        <iframe src="<%= frame1 %>" id="frame1" class="framesWide"></iframe>
        <%} %>
            
        <table hidden>
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
