<%-- 
    Document   : products_presentation_jsp
    Created on : 26 nov. 2019, 14:34:19
    Author     : Matthieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Presentation</title>  
        <link href="style/main.css" rel="stylesheet">
    </head>
    
    <body>
        <div id="blocPage">
            <div id="menuBar">
                <c:forEach var = "j" begin="0" end="5">
                    <a href="?cat=${j}"> Category ${j} </a>
                </c:forEach>
            </div>
            <%@include file="jsp_parts/products_table.jsp" %>
        </div>
    </body>
</html>
