<%-- 
    Document   : Fail
    Created on : Aug 22, 2014, 10:25:34 PM
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
        <div><h1>Failed, <a href="Create">Try again?</a></h1></div>
        <div>
            <p><b>Error description:</b> <%= request.getAttribute("error").toString() %> </p>
        </div>
    </body>
</html>
