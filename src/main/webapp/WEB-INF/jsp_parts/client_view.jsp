<%-- 
    Document   : client_view
    Created on : 15 déc. 2019, 16:06:01
    Author     : Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

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
            <div id="topInfos" >
                <h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>


                <c:forEach items="${sessionScope.order}" var="order">
                    <p>${order}<br></p>

                </c:forEach>


                <button id="logoutButton">Log out</button>
            </div>
        </c:otherwise>
    </c:choose>
</div>