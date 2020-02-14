<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
    	<c:import url="/inc/menu.jsp"/>
        <p class="info"><c:out value="${ message }" escapeXml="false"/></p>
        <c:if test="${!erreur}">
	        <p><c:out value="Client"/></p>
	        <p><c:out value="Nom : ${ commande.client.nom }"/></p>
	        <p><c:out value="Prénom : ${ commande.client.prenom }"/></p>
	        <p><c:out value="Adresse : ${ commande.client.adresse }"/></p>
	        <p><c:out value="Numéro de téléphone : ${ commande.client.telephone }"/></p>
	        <p><c:out value="Email : ${ commande.client.email }"/></p>
	        <p><c:out value="Commande"/></p>
	        <p><c:out value="Date  : ${ commande.date }"/></p> 
	        <p><c:out value="Montant  : ${ commande.montant }"/></p> 
	        <p><c:out value="Mode de paiement  : ${ commande.modePaiement }"/></p> 
	        <p><c:out value="Statut du paiement  : ${ commande.statutPaiement }"/></p> 
	        <p><c:out value="Mode de livraison  : ${ commande.modeLivraison }"/></p> 
	        <p><c:out value="Statut de la livraison  : ${ commande.statutLivraison }"/></p> 
        </c:if>
    </body>
</html>