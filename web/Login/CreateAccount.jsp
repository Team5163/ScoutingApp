<%-- 
    Document   : CreateAccount
    Created on : Aug 2, 2014, 6:53:11 PM
    Author     : Yiwen Dong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div>
            <form method="POST" action="../Create">
                User Name: <input type="text" placeholder="User Name Here" name="user" class="input" /><br />
                Password: <input type="password" placeholder="Password Here" name="pass" class="input" /><br />
                Re-type Password: <input type="password" placeholder="Re-type Password Here" name="re-pass" class="input" /><br />
                <input type="submit" value="Submit" /><input type="reset" value="Reset" /> 
            </form>
        </div>
    </body>
</html>
