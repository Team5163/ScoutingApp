<%-- 
    Document   : TeamData
    Created on : Aug 15, 2014, 3:06:33 PM
    Author     : Yiwen Dong, Rish Shadra
--%>

<%@page import="Team5163.ObjectRegistry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" />
        <title>Team</title>
    </head>
    <body>
        <%  String teamNumber = request.getAttribute("viewTeam").toString();
            String editReadOnly = "readonly";
            String editDisabled = "disabled";
            String inputClass = "displayValues";

            if (request.getSession().getAttribute("login") != null) {
                if (request.getSession().getAttribute("login").toString().equalsIgnoreCase("true") && request.getSession().getAttribute("mode").toString().equalsIgnoreCase("edit")) {
                    editReadOnly = "";
                    editDisabled = "";
                    inputClass = "editValues";
                }
            }
        %>
        <form method="POST" action="Data">
            <input type="hidden" name="type" value="edit" />
            <input type="hidden" name="teamNumber" value="<%= teamNumber%>" />
            <div id="header">
                <h1>Info: Team <%= request.getAttribute("viewTeam").toString()%></h1>
            </div>
            <table>
                <tr><td colspan="2" class="tableheader"><h2>Robot Info</h2></td></tr>
                <tr>
                    <td>Drive train:</td>
                    <td><input class="<%= inputClass%>" type="text" id="drivetrain" name="drivetrain" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "drivetrain")%>" <%= editReadOnly%> /></td>
                </tr>

                <tr><td>Speed:</td><td> <input class="<%= inputClass%>" type="text" id="speed" name="speed" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "speed") + " m/s"%>" <%= editReadOnly%> /></td></tr>

                <tr><td>Weight:</td><td> <input class="<%= inputClass%>" type="text" id="weight" name="weight" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "weight") + " kg"%>" <%= editReadOnly%> /></td></tr>
                <tr><td colspan="2" class="tableheader"><h2>Contacts</h2></td></tr>        <!--<h2>Contacts</h2>-->
                <tr><td>Strategy:</td>
                    <td><input class="<%= inputClass%>" type="text" id="stratcon" name="stratcon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "stratcon")%>" <%= editReadOnly%> /></td> 
                </tr>
                <tr><td> </td><td><input class="<%= inputClass%>" type="text" id="othercon" name="othercon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "othercon")%>" <%= editReadOnly%> /></td>
                </tr>
                <tr><td>General:</td>
                    <td><input class="<%= inputClass%>" type="text" id="gencon" name="gencon" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "gencon")%>" <%= editReadOnly%> /> </td>
                </tr>
                <br />
                <tr><td colspan="2" class="tableheader"><h2>Team Info</h2></td></tr>
                <tr><td>Team:</td>
                    <td><input class="<%= inputClass%>" type="text" id="teamname" name="teamname" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "teamname")%>" <%= editReadOnly%> /> </td>
                </tr>
                <tr><td>Driver:</td>
                    <td><input class="<%= inputClass%>" type="text" id="driver" name="driver" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "driver")%>" <%= editReadOnly%> /> </td>
                </tr>
                <tr><td>Mentor:</td>
                    <td><input class="<%= inputClass%>" type="text" id="mentor" name="mentor" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "mentor")%>" <%= editReadOnly%> /> </td>
                </tr>
                <tr><td>Sponsors:</td>
                    <td><input class="<%= inputClass%>" type="text" id="sponsors" name="sponsors" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "sponsors")%>" <%= editReadOnly%> /> </td>
                </tr>
                <tr><td colspan="2" class="tableheader"><h2>&nbsp;</h2></td></tr>
                <tr><td>Miscellaneous Info:&nbsp;&nbsp;</td>
                    <td><input class="<%= inputClass%>" type="text" id="miscinfo" name="miscinfo" placeholder="<%= ObjectRegistry.getDataBase().getData(teamNumber, "miscinfo")%>" <%= editReadOnly%> /> </td>
                </tr>
            </table>

            <%
                if (!editReadOnly.equalsIgnoreCase("readonly")) {
            %>
            <br />
            <input type="submit" id="updateDB" value="Update Team Information" /> 
            <%
                }
            %>
        </form>
    </body>
</html>
