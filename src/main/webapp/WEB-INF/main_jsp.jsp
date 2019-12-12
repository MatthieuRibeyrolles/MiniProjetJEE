<%-- 
    Document   : main_jsp
    Created on : 19 nov. 2019, 13:55:51
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/main.css" rel="stylesheet">
    </head>
    <body>        
        <div id="blocPage">
            <div id="connectDiv">
                <form action="home" method="POST">
                    Login: <input type="text" name="login"><br/>
                    Password: <input type="password" name="password">
                    <input id="loginButton" type="submit" value="Log in">
                </form>
            </div>
        </div>
    </body>
</html>
