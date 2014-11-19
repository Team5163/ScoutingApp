<%-- 
    Document   : TeamData
    Created on : Aug 15, 2014, 3:06:33 PM
    Author     : Yiwen Dong
--%>

<%@page import="Team5163.ObjectRegistry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <!-- <link rel="stylesheet" type="text/css"  href="./style.css" /> -->
        <title>Team</title>
            <!--<style>
        input {
            padding-left: 1px;
            border-style: solid;
            border-color: black;
            border-width: 1px;
            font-family: helvetica, arial, sans serif;
            padding-left: 1px;
        }
    </style>-->
    </head>
    <% /*
         teamnum varchar(4), 
         teamname varchar(30), 
         drivetrain varchar(30), 
         speed varchar(10), 
         weight varchar(10), 
         stratcon varchar(100), 
         othercon varchar(100), 
         gencon varchar(100), 
         driver varchar(100), 
         mentor varchar(100), 
         sponsors varchar(300), 
         miscinfo varchar(500)
         */ %>
    <body>
        <%  String teamNumber = request.getAttribute("viewTeam").toString();
            String editReadOnly = "readonly";
            String editDisabled = "disabled";

            if (request.getSession().getAttribute("login") != null) {
                if (request.getSession().getAttribute("login").toString().equalsIgnoreCase("true") && request.getSession().getAttribute("mode").toString().equalsIgnoreCase("edit")) {
                    editReadOnly = "";
                    editDisabled = "";
                }
            }
        %>
        <form method="POST" action="Data">
            <input type="hidden" name="type" value="edit" />
            <input type="hidden" name="teamNumber" value="<%= teamNumber %>" />
            <div id="header">
                <h1>Info: Team <%= request.getAttribute("viewTeam").toString()%></h1>
            </div>
            <div id="scoring">
                Scores:
                <table cellpadding="5px">
                    <tr>
                        <td>Score</td>
                        <td>Alliance</td>
                        <td>Opponents</td>
                        <td>Match Number</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>0001, 0002</td>
                        <td>0003, 0004</td>
                        <td>45</td>
                    </tr>
                </table>
            </div>
            <div id="robot">
                Drive train: <input type="text" id="drivetrain" name="drivetrain" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "drivetrain")%>" <%= editReadOnly%> />
                <br /> 
                Speed: <input type="text" id="speed" name="speed" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "speed")%>" <%= editReadOnly%> />
                m/s <br /> 
                Weight: <input type="text" id="weight" name="weight" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "weight")%>" <%= editReadOnly%> />
                kg 
            </div>
            <div id="contacts">
                Strategy: <br /> 
                <input type="text" id="stratcon" name="stratcon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "stratcon")%>" <%= editReadOnly%> />
                , 
                <input type="text" id="othercon" name="othercon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "othercon")%>" <%= editReadOnly%> />
                <br /> General: <br /> 
                <input type="text" id="gencon" name="gencon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "gencon")%>" <%= editReadOnly%> /> 
            </div>
            <div id="wins">
                Wins: 1 <br /> Losses: 1
            </div>
            <div id="team">
                Team: 
                <input type="text" id="teamname" name="teamname" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "teamname")%>" <%= editReadOnly%> /> 
                <br /> Driver: 
                <input type="text" id="driver" name="driver" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "driver")%>" <%= editReadOnly%> /> 
                <br /> Mentor: 
                <input type="text" id="mentor" name="mentor" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "mentor")%>" <%= editReadOnly%> /> 
                <br /> Sponsors: 
                <input type="text" id="sponsors" name="sponsors" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "sponsors")%>" <%= editReadOnly%> /> 
                <br /> Contact Info: 
                <input type="text" id="miscinfo" name="miscinfo" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "miscinfo")%>" <%= editReadOnly%> /> 
            </div>
            <input type="submit" id="updateDB" value="Change" <%= editDisabled%> /> 
        </form>
    </body>
</html>
