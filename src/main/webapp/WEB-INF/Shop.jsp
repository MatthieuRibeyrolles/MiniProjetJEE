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
                            <label> Produit: ${item[0]} (reférence ${item[1]}) <br> Quantité commandée: ${item[2]} <br> Montant total :${item[3]} 
                                
                            </label>
                                    
                                <div id="buttons">

                                    <form action="shop" method="GET">
                                        <div> Quantité souhaitée: <input type="text" name="quantity" value="${item[1]}"> </div>
                                        <input type="hidden" id="refProduit" value="${item[1]}" />
                                        <input id="modifierPanier" type="submit" value="Modifier la quantité dans le panier">
                                    </form>
                                        
                                    <form action="shop" method="GET">
                                        <input type="hidden" id="supProduitRef" value="${item[1]}" />
                                        <button id="removeFromCart">Enlever du panier</button>
                                    </form>
                                </div>

                        </span>
                    </c:forEach>
                
                 </div>
                
                <form>
                    <input id="confirmerCommande" type="hidden" value="confirmerCommande" />
                    <input id="payer" type="submit" value="Payer"/>
                </form>
            </c:otherwise>
                
            </c:choose>
        </div>

    </body>
</html>
