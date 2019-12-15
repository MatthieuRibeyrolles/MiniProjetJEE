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

                            <if test="${sessionScope.client == 'true'}" >
                                <button id="ordersButton"> Mes commandes passées </button> 
                            </if>

                            <button id="logoutButton">Me deconnecter</button>
                        </div>
                            
                    <div id="changeInfos">
                        <form action="account" method="GET">
                            Société <input type="text" name="societe"><br/>
                            Contact: <input type="text" name="contact">
                            Fonction: <input type="text" name="contact">
                            Adresse: <input type="text" name="contact">
                            Ville: <input type="text" name="contact">
                            Region: <input type="text" name="contact">
                            Code postal:<input type="text" name="contact">
                            Pays: <input type="text" name="contact">
                            Telephone: <input type="text" name="contact">
                            Fax: <input type="text" name="contact">
                            <input id="Enregistrer" type="submit" value="Enregistrer">
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
        <script>
            $(document).ready(function() {
                $("#logoutButton").click(function() {
                    sessionStorage.clear();
                });
            });
            
            $("#ordersButton").click(function() {
                var getUrl = window.location;
                var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                location.href = baseUrl + '/orders';
            });

        </script>
    </body>
</html>
