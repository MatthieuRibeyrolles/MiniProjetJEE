<%-- 
    Document   : admin_view
    Created on : 15 déc. 2019, 16:05:36
    Author     : Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin View</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://www.gstatic.com/charts/loader.js"></script>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.admin == 'true'}" >
                <form action="" method="GET">
                    <label for="startDate">Date de début:</label>
                    <input type="date" id="startDate" name="startDateForChart" value="1995-01-31" onchange="valide();" />
                    <label for="endDate">Date de fin:</label>
                    <input type="date" id="endDate" name="startDateForChart" value="1996-05-30" onchange="valide();" />
                </form>

                <div id="chartByCategory"></div>
                <div id="chartByCountry"></div>
                <div id="chartByClient"></div>

                <script src="scripts/drawCharts.js" ></script>
            </c:when>
            <c:otherwise>
                <c:redirect url="/home" />
            </c:otherwise>
        </c:choose>

    </body>
</html>
