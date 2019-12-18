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
                        <c:if test="${sessionScope.client == 'true'}" >
                            <button id="ordersButton"> Mes commandes passées </button>
                            <form action="cart">
                                <input id="cartButton" type="submit" value="Mon panier">
                            </form>
                        </c:if>

                        <c:if test="${sessionScope.admin == 'true'}" >
                            <button id="ordersButton"> les commandes des cliens </button>
                        </c:if>
                    </div>

                    <c:if test="${sessionScope.client == 'true'}" >
                        <h2>Modifer mes informations</h2>

                        <div id="changeInfos">
                            <form action="account" method="GET" id="infosForm">
                                <c:set var="infosClient" value="${sessionScope.infoClient}" />

                                Société <input type="text" name="societe" value="${infosClient[0]}">
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
                    </c:if>

                    <form action="">
                        <input type="hidden" name="deco" value="true">
                        <input type="submit" id="logOut" value="Se deconnecter" >
                    </form>

                </c:otherwise>
            </c:choose>

            <form action="home">
                <input type="submit" id="backToHome" value="Accueil" >
            </form>
        </div>

        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
        <script>
            $(document).ready(function () {
                var getUrl = window.location;
                $("#logoutButton").click(function () {
                    sessionStorage.clear();
                });

                $("#ordersButton").click(function () {
                    if (getUrl.host === "mysterious-brook-54628.herokuapp.com")
                        var baseUrl = getUrl.protocol + "//" + getUrl.host;
                    else
                        var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                    location.href = baseUrl + '/orders';
                });

                $("#cartButton").click(function () {
                    if (getUrl.host === "mysterious-brook-54628.herokuapp.com")
                        var baseUrl = getUrl.protocol + "//" + getUrl.host;
                    else
                        var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                    location.href = baseUrl + '/cart';
                });
            });
        </script>
    </body>
</html>
