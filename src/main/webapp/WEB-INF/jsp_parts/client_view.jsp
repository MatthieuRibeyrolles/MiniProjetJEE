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
            
                <!--<h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>-->
                
                <div id="orders">
                    <c:forEach items="${order}" var="order_element">                   
                        <p>
                        <c:forEach items="${clientline[order_element]}" var="ligne">
                             ${ligne} 
                        </c:forEach>
                        </p>
                        <br>
                    </c:forEach>
                        
<!--                        <c:forEach items="${product_map}" var="category">
                        <c:if test="${category.key == param.cat}">  

                            <c:forEach items="${category.value}" var="product">
                                <td><button class="product" type="button" id="${product}" >${product}</button></td>
                                <tr></tr>

                            </c:forEach>
                        </c:if>
                    </c:forEach> -->
                    
                </div>
            
                <button id="logoutButton">Log out</button>
            
        </c:otherwise>
    </c:choose>
</div>