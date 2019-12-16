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
        <link href="style/shop.css" rel="stylesheet">
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
                    <c:forEach items="${sessionScope.cart_list}" var="item">
                        <span>
                            coucou
                            <label> Produit: ${item[0]} (reférence ${item[1]}). Quantité commandée: ${item[2]}, pour un montant total de ${item[3]} 
                                <form action="shop" method="GET">
                                    Quantité: <input type="text" name="quantity" value="item[1]">
                                    <input type="hidden" id="refProduit" value="${item[1]}" />
                                    <input id="modifierPanier" type="submit" value="Modifier la quantité dans le panier">
                                </form>
                            </label>
                            <form action="shop" method="GET">
                                <input type="hidden" id="refProduit" value="${item[1]}" />
                                <button id="removeFromCart">Enlever du panier</button>
                            </form>
                            
                            
                        </span>
                    </c:forEach>
                
                </div>
                
                <button id="payer">Payer</button>
            </c:otherwise>
                
            </c:choose>
        </div>

    </body>
</html>
