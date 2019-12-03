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
        <title>BMT website</title>
        <link href="style/main.css" rel="stylesheet">
    </head>
    <body>        
        <div id="blocPage">
            <h1>Welcome on our website</h1>
            
            <div id="connectDiv">
                <form action="login" method="GET">
                    
                    Login: <input type="text" name="login"><br/>
                    Password: <input type="password" name="password">
                    <input id="loginButton" type="submit" value="Log in">
                </form>
            </div>
            
            <!--<p>${categories_list}</p>
            
            <div id="menuBar">
                <c:forEach items = "${categories_list}" var = "cat">
                    <p> "${cat}" </p>
                </c:forEach>
            
            -->
            </div>
            </div>
        </div>
    </body>
</html>
