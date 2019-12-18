<%-- 
    Document   : client_view
    Created on : 15 déc. 2019, 16:06:01
    Author     : Thibault/Bruno/Matthieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="UTF-8">
        <title>Mes commandes</title>
        <link href="style/user_account_style.css" rel="stylesheet">
    </head>
    
    <body>
        <div id="blocPage">
            <c:choose>

                <c:when test="${sessionScope.log != 'true'}">
                    <p id="errorMessage"> Vous devez être connecté pour accéder à cette partie du site. </p>

                    <form action="account" >
                        <input type="submit" value="Continuer..">
                    </form>
                </c:when>


                <c:when test ="${sessionScope.admin == 'true'}" >

                        <h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>

                        <c:forEach items="${sessionScope.order}" var="order_e">
                            <div class="order">
                                <c:if test="${line != null}" >
                                     <c:forEach items="${orderString[order_e]}" var="orderstring" >
                                    ${orderstring}&nbsp;&nbsp;<br>
                                    </c:forEach>

                                        <br>
                                        <br>*

                                    <c:forEach items="${line[order_e]}" var="ligne" >
                                        Produit: ${ligne[0]}, Quantité commandée: ${ligne[1]} <br>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <br>
                        </c:forEach>


                        <form action="">
                                <input type="hidden" name="deco" value="true">
                                <input type="submit" id="logOut" value="Se deconnecter" >
                            </form>

                </c:when>

                <c:otherwise>

                        <h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>

                        <c:forEach items="${sessionScope.order}" var="order_e">
                            <div class="order">
                                <c:forEach items="${orderString[order_e]}" var="orderstring" >
                                    ${orderstring}&nbsp;&nbsp;<br>
                                </c:forEach>

                                    <br>
                                    <br>
                                <c:forEach items="${line[order_e]}" var="ligne" >
                                    Produit: ${ligne[0]}, Quantité commandée: ${ligne[1]} <br>
                                </c:forEach>
                            </div>
                            <br>
                        </c:forEach> 


                        <form action="account">
                            <input type="hidden" name="deco" value="true">
                            <input type="submit" id="logOut" value="Se deconnecter" >
                        </form>

                </c:otherwise>
            </c:choose>

            <form action="home">
                <input type="submit" id="backToHome" value="Accueil" >
            </form>
        </div>
    </body>
</html>