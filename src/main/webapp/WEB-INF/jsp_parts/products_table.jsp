<%-- 
    Document   : productsPresentation
    Created on : 27 nov. 2019, 14:10:42
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/table_css.css" rel="stylesheet">
    </head>
    
    <body>
        
        <h2><c:if test="${not empty param.cat}" > ${param.cat} </c:if> </h2>

        <div id="infoPage" >    
            <nav>
                <table cellspacing="0">
                    <c:forEach items="${product_map}" var="category">
                        <c:if test="${category.key == param.cat}">  
                            <c:set var="i" value="0" />

                            <c:forEach items="${category.value}" var="product">
                                <td><button class="product" type="button" id="${product}" >${product}</button></td>
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
                            
                            <p>
                                Référence du produit: ${reference} <br>
                                Référence fournisseur: ${fournisseur} <br>
                                Catégorie: ${param.cat} <br>
                                Ce produit est vendu par ${quantityBySell} <br>
                                Prix unitaire: ${prix} euros <br>
                                Unités restantes en stock: ${stock} <br>
                                Unités commandées: ${ordered} <br>
                                Niveau de réaprovisionnement: ${refill} <br>
                               ${sellable == 'false' ? 'Disponible' : 'Indisponible'} à la vente<br>
                            </p>
                    </c:if>
                </div>
            </c:if>

            <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.4.1.min.js"></script>
            <script>
                $(document).ready(function() {
                    $(".product").click(function() {
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
    </body>
</html>
