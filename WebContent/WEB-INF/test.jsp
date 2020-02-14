<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
        <p>Ceci est une page générée depuis une JSP.</p>
        <p>
            ${test}
            ${param.auteur}
        </p>
        <p>
            Récupération du bean :
            ${coyote.prenom}
            ${coyote.nom}
        </p>
        <p>
            Récupération de la liste :
            <c:forEach var="item" items="${liste}" >
				<c:out value="${item}" />
			</c:forEach>
        </p>
        <p>
            Récupération du jour du mois :
            <c:choose>
		        <c:when test="${ jour % 2 == 0 }">Jour pair : ${jour}</c:when>
		        <c:otherwise>Jour impair : ${jour}</c:otherwise>
		    </c:choose>
        </p>
    </body>
</html>