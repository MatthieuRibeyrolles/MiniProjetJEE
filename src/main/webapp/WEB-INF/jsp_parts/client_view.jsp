<%-- 
    Document   : client_view
    Created on : 15 déc. 2019, 16:06:01
    Author     : Thibault/Bruno/Matthieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
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
                    <p>
                        <c:forEach items="${orderString[order_e]}" var="orderstring" >
                            ${orderstring}&nbsp;&nbsp;
                        </c:forEach>
                            
                            <br>
                        <c:forEach items="${line[order_e]}" var="ligne" >
                            Produit: ${ligne[0]}, Quantité commandée: ${ligne[1]} <br>
                        </c:forEach>
                    </p>
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
                    <p>
                        <c:forEach items="${orderString[order_e]}" var="orderstring" >
                            ${orderstring}&nbsp;&nbsp;
                        </c:forEach>
                            
                            <br>
                        <c:forEach items="${line[order_e]}" var="ligne" >
                            Produit: ${ligne[0]}, Quantité commandée: ${ligne[1]} <br>
                        </c:forEach>
                    </p>
                    <br>
                </c:forEach>
                    
                    <table>
                        <tr id="titleRow">
                            <td> Date d'envoi </td>
                            <td> Frais de port </td>
                            <td> Receveur </td>
                            <td> Adresse </td>
                            <td> Ville </td>
                            <td> Region </td>
                            <td> Code postal </td>
                            <td> Pays </td>
                            <td> Reduction </td>
                            <td> Prix total </td>
                        </tr>
                        
                        <c:forEach items="sessionScope.order"  var="order_e" >
                            <tr>
                                <c:forEach items="${orderString[order_e]}" var="orderstring" >
                                    ${orderstring}&nbsp;&nbsp;
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>


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