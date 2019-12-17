<%-- 
    Document   : adminButton
    Created on : 17 déc. 2019, 15:52:18
    Author     : Thibault
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<button id="adminButton" type="button" >Statistiques</button>

<script type="text/javascript" >
    document.getElementById('adminButton').onclick = function (e) {
        var getUrl = window.location;
        if (getUrl.host === "mysterious-brook-54628.herokuapp.com")
            var baseUrl = getUrl.protocol + "//" + getUrl.host;
        else
            var baseUrl = getUrl.protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
        location.href = baseUrl + '/admin';
    };
</script>