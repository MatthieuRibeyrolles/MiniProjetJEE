<%-- 
    Document   : admin_view
    Created on : 15 déc. 2019, 16:05:36
    Author     : Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin View</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <link href="style/admin.css" rel="stylesheet">
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.admin == 'true'}" >
                <form action="" method="GET">
                    <span>
                        <label for="startDate">Date de début:</label>
                        <input type="date" id="startDate" name="startDateForChart" value="1990-01-01" onchange="valide();" />
                        <label for="endDate">Date de fin:</label>
                        <input type="date" id="endDate" name="startDateForChart" value="2019-12-31" onchange="valide();" />

                        <button id="backToHome" >Accueil</button>
                    </span>
                </form>

                <div id="chartByCategory"></div>
                <div id="chartByCountry"></div>
                <div id="chartByClient"></div>

                <script src="scripts/drawCharts.js" ></script>

                <script type="text/javascript" >
                    document.getElementById('backToHome').onclick = function (e) {
                        e.preventDefault();
                        var getUrl = window.location;
                        if (getUrl.host === "mysterious-brook-54628.herokuapp.com")
                            var baseUrl = getUrl.protocol + "//" + getUrl.host;
                        else
                            var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
                        location.href = baseUrl + '/home';
                    };
                </script>
            </c:when>
            <c:otherwise>
                <c:redirect url="/home" />
            </c:otherwise>
        </c:choose>

    </body>
</html>
