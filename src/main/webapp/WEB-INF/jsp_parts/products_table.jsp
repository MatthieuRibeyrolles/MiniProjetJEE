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
            <table cellspacing="0">
                <caption><h1>products for category ${param.cat}</h1></caption>

                <c:forEach var = "i" begin="0" end="15">
                    <tr>
                        <c:forEach var = "j" begin="0" end="5">
                            <td> <a href="#"> Produit ${i} ${j} </a></td>
                        </c:forEach>
                    </tr>
                </c:forEach>
        </table>

        <c:out value="${sessionScope.coucou}"></c:out>
    </body>
</html>