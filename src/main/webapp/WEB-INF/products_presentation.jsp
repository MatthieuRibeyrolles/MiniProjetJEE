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
            <%@include file="main_jsp.jsp" %>
            
            <div id="menuBar">
                <c:forEach items="${product_map}" var="category">
                    <a href="?cat=${category.key}"> ${category.key} </a>
                </c:forEach>
            </div>

            
            <c:choose>
                <c:when test="${empty param.cat}">
                    <c:import url="/WEB-INF/jsp_parts/products_table.jsp" >
                        <c:param name="cat" value="7" ></c:param>
                    </c:import>
                </c:when>
                
                <c:otherwise>
                    <c:import url="/WEB-INF/jsp_parts/products_table.jsp" ></c:import>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>
