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
                                <td><button class="product" type="button" id="${product}">${product}</button></td>
                                <tr></tr>

                            </c:forEach>
                        </c:if>
                    </c:forEach> 
                </table>
            </nav>
            
            <c:if test="${not empty param.cat}" >
                <c:out value="${productBean}" />
                <div id="infos" >
                    
                    
                    <label id="info_name" />
                    <label id="info_ref" /> 
<!--                        
                        vendu par $(qt package), au prix de $(price)
                        vendu par $(provider)
                        en stock: $(qt stock) (disponible à la vente: $(available)

                        si (admin)
                            alors 
                        quantité commandée: qt ordered-->


                </div>
            </c:if>

        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="productsInformations.js"></script>
        
    </body>
</html>
