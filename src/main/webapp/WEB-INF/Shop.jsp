<%-- 
    Document   : Shop
    Created on : 15 déc. 2019, 23:06:02
    Author     : Matthieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon shop</title>
    </head>
    <body>
        <div id="blocPage">
            <c:choose>

            <c:when test="${sessionScope.client != 'true'}">
                <p id="errorMessage"> Vous devez être un client pours accéder à votre panier. </p>

                <div id="connectDiv">
                    <form action="shop" method="GET">
                        Login: <input type="text" name="login"><br/>
                        Password: <input type="password" name="password">
                        <input id="loginButton" type="submit" value="Log in">
                    </form>
                </div>
            </c:when>
                
            <c:otherwise>
                <div id="list">
                    <c:forEach items="${cart_list}" var="item">
                        <span>
                            ${item}
                            <button id="removeFromCart">Enlever du panier</button>
                        </span>
                    </c:forEach>
                
                </div>
                
                <button id="payer">Payer</button>
            </c:otherwise>
                
            </c:choose>
        </div>

    </body>
</html>
