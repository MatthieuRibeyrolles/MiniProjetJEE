<%-- 
    Document   : Account
    Created on : 13 déc. 2019, 13:07:39
    Author     : bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mon compte</title>
        <link href="style/account.css" rel="stylesheet">
    </head>
    
    <body>
        <div id="blocPage">
            <c:choose>
                <c:when test="${sessionScope.log != 'true'}">
                    
                    <p id="errorMessage"> Vous devez être connecté pour accéder à cette partie du site. </p>
                    
                    <div id="connectDiv">
                        <form action="account" method="GET">
                            Login: <input type="text" name="login"><br/>
                            Password: <input type="password" name="password">
                            <input id="loginButton" type="submit" value="Log in">
                        </form>
                    </div>
                </c:when>

                <c:otherwise>
                    
                    <h1 id="name"> Bienvenue ${sessionScope.usrname} ! </h1>

                    <div id="topInfos" >

                        <if test="${sessionScope.client == 'true'}" >
                            <button id="ordersButton"> Cliquer pour voir mes commandes passées </button> 
                            <button id="cartButton"> Mon panier </button> 
                            <form>
                                <input type="hidden" value="goingToShop" >
                                <input id="cartButton" type="submit" value="cartButton">
                            </form>
                        </if>

                    </div>
                            
                            
                    <h2>Modifer mes informations</h2>
                    
                    <div id="changeInfos">
                        <form action="account" method="GET">
                            <c:set var="infosClient" value="${sessionScope.infoClient}" />
                            
                            Société <input type="text" name="societe" value="${infosClient[0]}"><br/>
                            Contact: <input type="text" name="contact" value="${infosClient[1]}">
                            Fonction: <input type="text" name="fonction" value="${infosClient[2]}">
                            Adresse: <input type="text" name="adresse" value="${infosClient[3]}">
                            Ville: <input type="text" name="ville" value="${infosClient[4]}">
                            Region: <input type="text" name="region" value="${infosClient[5]}">
                            Code postal:<input type="text" name="cp" value="${infosClient[6]}">
                            Pays: <input type="text" name="pays" value="${infosClient[7]}">
                            Telephone: <input type="text" name="tel" value="${infosClient[8]}">
                            Fax: <input type="text" name="fax" value="${infosClient[9]}">
                            <input id="enregistrer" type="submit" value="Enregistrer">
                        </form>
                    </div>
                            
                    <button id="logoutButton">Me deconnecter</button>

                </c:otherwise>
            </c:choose>
        </div>
        
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
        <script>
            $(document).ready(function() {
                $("#logoutButton").click(function() {
                    sessionStorage.clear();
                });
                
                $("#ordersButton").click(function() {
                    var getUrl = window.location;
                    var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                    location.href = baseUrl + '/orders';
                });
                
                 $("#cartButton").click(function() {
                    var getUrl = window.location;
                    var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                    location.href = baseUrl + '/cart';
                });
            });
        </script>
    </body>
</html>
