<%-- 
    Document   : TeamList
    Created on : Aug 9, 2014, 7:34:05 PM
    Author     : Yiwen Dong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            var URLCreate = "./Login/CreateAccount.jsp";
            var URLTeamList = "Data?type=teamList&teamNumber=11";
            //var url = [];
            var numbers = [];
            function setFrame1(url){
                parent.document.getElementById("teamviewframe").setAttribute("src", url);
            }
            function setFrame2(url){
                parent.document.getElementById("teamcompareframe").setAttribute("src", url);
            }
            function setTeamList(url){
                parent.document.getElementById("teamlistframe").setAttribute("src", url)
            }
                    <% int a = 0;
                        while(request.getAttribute("Result" + a) != null){
                    %> numbers[<%= a %>] = "<%= request.getAttribute("Result" + a) %>";<%
                            a = a+ 1;
                        } %>
            function getTable(){
                <% if(request.getSession().getAttribute("mode").toString().equalsIgnoreCase("compare")){ %>
                var data = "";
                for(a = 0; a < numbers.length; a++){
                    data = data + "<tr><td><a href=\"#\" onclick=\"setFrame1('Data?type=viewPage&teamNumber=" + numbers[a] + "\');\" >" + numbers[a]
                            + "</a></td><td><a href=\"#\" onclick=\"setFrame2('Data?type=viewPage&teamNumber=" + numbers[a] + "\');\" >" + numbers[a] + "</a></td></tr>";
                }
                return data;
                }
               <%} else {%> 
               var data = "";
                for(a = 0; a < numbers.length; a++){
                    data = data + "<tr><td><a href=\"#\" onclick=\"setFrame1('Data?type=viewPage&teamNumber=" + numbers[a] + "\');\" >" + numbers[a] + "</a></td></tr>";
                }
                return data;
                }
               <%} %>
        </script>
        <table id="table">
            <tr></tr>
        </table>
        <% if (request.getSession().getAttribute("login").toString().equalsIgnoreCase("true")){ %>
        <p>----------- <br>
            <a href="#" onclick="setFrame1('Data/CreateTeam.html');">Create Team</a><br>
            -----------
        </p>
        <% } %>
        <script>var table = document.getElementById("table").innerHTML = getTable();</script>
    </body>
</html>
