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
        <h2>${param.cat}</h2>

        <div id="infoPage" >    
            <nav>
                <table cellspacing="0">
                    <c:forEach items="${product_map}" var="category">
                        <c:if test="${category.key == param.cat}">  
                            <c:set var="i" value="0" />

                            <c:forEach items="${category.value}" var="product">
                                <!--<td><a href="#"> ${product} </a></td>-->
                                <td><button type="button">${product}</button></td>
                                <tr></tr>

                            </c:forEach>
                        </c:if>
                    </c:forEach> 
                </table>
            </nav>
                
            <div id="infos" >
                je suis une ympho
            </div>
        </div>
    </body>
</html>
