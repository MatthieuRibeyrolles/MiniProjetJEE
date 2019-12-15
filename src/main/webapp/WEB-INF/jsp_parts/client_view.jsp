<%-- 
    Document   : client_view
    Created on : 15 déc. 2019, 16:06:01
    Author     : Thibault/Bruno/Matthieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<%@include file="other_view.jsp" %>


<div id="blocPage">
    <c:choose>

        <c:when test="${sessionScope.client != 'true'}">
            <p id="errorMessage"> Vous devez être connecté pour accéder à cette partie du site. </p>

            <div id="connectDiv">
                <form action="home" method="GET">
                    Login: <input type="text" name="login"><br/>
                    Password: <input type="password" name="password">
                    <input id="loginButton" type="submit" value="Log in">
                </form>
            </div>
        </c:when>

        <c:otherwise>
            
                <h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>


                <c:forEach items="${sessionScope.order}" var="order">                   
                    <p>
                    <c:forEach items="${order}" var="ligne">
                         ${ligne} 
                    </c:forEach>
                    </p>
                    <br>
                </c:forEach>


                <button id="logoutButton">Log out</button>
            
        </c:otherwise>
    </c:choose>
</div>