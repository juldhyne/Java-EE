<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage du client</title>
    </head>
    <body>
    	<c:import url="/inc/menu.jsp"/>
    	<p class="info"><c:out value="${ message }" escapeXml="false"/></p>
    	<c:if test="${!erreur}">
	    	<p><c:out value="Nom: ${client.firstname} ${client.lastname}"/></p>
	    	<p><c:out value="Adresse: ${client.address}"/></p>
	    	<p><c:out value="Numéro de téléphone: ${client.phone}"/></p>
	    	<p><c:out value="Email: ${client.mail}"/></p>
    	</c:if>
    </body>
</html>