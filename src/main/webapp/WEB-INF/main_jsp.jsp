<%-- 
    Document   : main_jsp
    Created on : 19 nov. 2019, 13:55:51
    Author     : Matthieu, Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<button id="accountButton" type="button" >Mon compte</button>

<script type="text/javascript" >

    document.getElementById('accountButton').onclick = function (e) {
        var getUrl = window.location;
        if (getUrl.host === "mysterious-brook-54628.herokuapp.com")
            var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[0];
        else
            var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
        location.href = baseUrl + '/account';
    };

</script>
