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
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            var URLCreate = "./Login/CreateAccount.jsp";
            var URLTeamList = "Data?type=teamList&teamNumber=11";
            //var url = [];
            var numbers = [];
            function setFrame1(url){
                parent.document.getElementById("frame1").setAttribute("src", url);
            }
            function setTeamList(url){
                parent.document.getElementById("teamListFrame").setAttribute("src", url)
            }
                    <% for(int a = 1; a < 6; a++){
                        String number = request.getAttribute(a + "").toString();
                        %>  numbers[<%= a %>] = "<%= number %>";<%
                    } %>
            function getTable(){
                var data = "";
                for(a = 1; a < 6; a++){
                    data = data + "<tr><td><a href=\"#\" onclick=\"setFrame1('Data?type=viewPage&amp;teamNumber=" + numbers[a] + "\');\" >" + numbers[a] + "</a></td></tr>";
                }
                return data;
            }
        </script>
        <table id="table">
            <tr></tr>
        </table>
        <script>var table = document.getElementById("table").innerHTML = getTable();</script>
    </body>
</html>
