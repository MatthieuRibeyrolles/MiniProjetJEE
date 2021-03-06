<%-- 
    Document   : products_presentation_jsp
    Created on : 26 nov. 2019, 14:34:19
    Author     : Matthieu / Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Presentation</title>  
        <link href="https://fonts.googleapis.com/css?family=Baskervville|Lilita+One|Rokkitt&display=swap" rel="stylesheet">
        <link href="style/main.css" rel="stylesheet">
        <link href="style/table_css.css" rel="stylesheet">
    </head>

    <body>
        <div id="blocPage">
            <%@include file="accountButton.jsp" %>

            <c:if test="${sessionScope.admin == 'true'}" >
                <%@include file="adminButton.jsp" %>
            </c:if>


            <div id="menuBar">
                <c:forEach items="${product_map}" var="category">
                    <a href="?cat=${category.key}"> ${category.key} </a>
                </c:forEach>
            </div>

            <h2><c:if test="${not empty param.cat}" > ${param.cat} </c:if> </h2>

                <div id="infoPage" >    
                    <nav>
                        <table cellspacing="0">
                        <c:forEach items="${product_map}" var="category">
                            <c:if test="${category.key == param.cat}">  

                                <c:forEach items="${category.value}" var="product">
                                    <td><button class="product" type="button" id="${product}" >${product}</button></td>
                                    <!--/!\ ICI L'ID CONTIENT DES ESPACES ASKIP, SE DEMERDER POUR NE PLUS AVOIR D'ESPACES-->
                                    <tr></tr>

                                </c:forEach>
                            </c:if>
                        </c:forEach> 
                    </table>
                </nav>

                <c:if test="${not empty param.cat}" >
                    <div id="infos" >
                        <c:if test="${not empty param.product}" >
                            <c:set var="currentProduct" value="${product_information[param.product]}" />
                            <c:set var="reference" value="${currentProduct[0]}" />
                            <c:set var="fournisseur" value="${currentProduct[2]}" />
                            <c:set var="quantityBySell" value="${currentProduct[4]}" />
                            <c:set var="prix" value="${currentProduct[5]}" />
                            <c:set var="stock" value="${currentProduct[6]}" />
                            <c:set var="ordered" value="${currentProduct[7]}" />
                            <c:set var="refill" value="${currentProduct[8]}" />
                            <c:set var="sellable" value="${currentProduct[9]}" />

                            <h3>${param.product}</h3>
                            <c:if test="${sessionScope.admin != 'true'}" >
                                
                            </c:if>
                            
                            <c:choose>
                                <c:when test="${sessionScope.admin != 'true'}">
                                    <div>
                                        Référence du produit: ${reference} <br>
                                        <c:if test="${sessionScope.admin == 'true'}" > Référence fournisseur: ${fournisseur} <br> </c:if>
                                        Catégorie: ${param.cat} <br>
                                        Ce produit est vendu par ${quantityBySell} <br>
                                        Prix unitaire: ${prix} euros <br>
                                        <%--<c:if test="${sessionScope.admin == 'true'}" > --%> Unités restantes en stock: ${stock} <br> <%-- </c:if> --%>
                                        <c:if test="${sessionScope.admin == 'true'}" > Unités commandées: ${ordered} <br> </c:if>
                                        <c:if test="${sessionScope.admin == 'true'}" > Niveau de réaprovisionnement: ${refill} <br> </c:if>
                                            <label id="dispo">
                                                (${sellable == 'false' ? 'Disponible' : 'Indisponible'} à la vente)<br>
                                        </label>
                                        <c:if test="${sessionScope.client == 'true'}" >
                                            <form action="home" method="GET">
                                                Quantité: <input type="text" name="quantity" value="0">
                                                <input type="hidden" name="refProduit" value="${reference}" > <br>
                                                <input id="ajouterAuPanier" type="submit" value="Ajouter au panier">
                                            </form>
                                        </c:if>
                                    </div>
                                </c:when>
                                
                                <c:otherwise>
                                    <form id="modifsAdminForm" action="home">
                                        <span> Référence du produit <input name="modifRef" type="text" value="${reference}"</span> <br>
                                        <span> Référence du fournisseur <input name="modifFournisseur" type="text" value="${fournisseur}"</span> <br>
                                        <span> Catégorie <input name="modifCat" type="text" value="${param.cat}"</span> <br>
                                        <span> Vendu par lots de <input name="modifLots" type="text" value="${quantityBySell}"</span> <br>
                                        <span> Prix unitaire <input name="modifPrix" type="text" value="${prix}"</span> <br>
                                        <span> Unités restantes en stock <input name="modifStock" type="text" value="${stock}"</span> <br>
                                        <span> Unités commandées <input name="modifCommandees" type="text" value="${ordered}"</span> <br>
                                        <span> Niveau de réaprovisionnement <input name="modifReap" type="text" value="${refill}"</span> <br>
                                        <input type="submit" id="adminChangesButton" value="Sauvegarder" >
                                    </form>    
                                </c:otherwise>
                            </c:choose>
                            
                            
                        </c:if>
                    </div>
                </c:if>

                <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
                <script>
                    $(document).ready(function () {
                        $(".product").click(function () {
                            let params = "" + window.location.search;
                            let searchParams = new URLSearchParams(params);

                            if (searchParams.toString().includes("product")) {
                                searchParams.delete('product');
                            }

                            searchParams.append('product', $(this).attr('id'));
                            window.location.search = searchParams.toString();
                        });
                    });

                </script>
            </div>

        </div>
    </body>
</html>
