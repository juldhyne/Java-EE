package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sdzee.beans.Client;
import com.sdzee.beans.Commande;

@WebServlet( "/creationCommande" )
public class CreationCommande extends HttpServlet {

	public static final String ATT_COMMANDE = "commande";
	public static final String ATT_MESSAGE = "message";
	public static final String ATT_ERREUR = "erreur";
	public static final String PARAM_CLIENT_PRENOM = "prenomClient";
	public static final String PARAM_CLIENT_NOM = "nomClient";
	public static final String PARAM_CLIENT_ADRESSE = "adresseClient";
	public static final String PARAM_CLIENT_TELEPHONE = "telephoneClient";
	public static final String PARAM_CLIENT_EMAIL = "emailClient";
	public static final String PARAM_COMMANDE_MONTANT = "montantCommande";
	public static final String PARAM_COMMANDE_MODE_PAIEMENT = "modePaiementCommande";
	public static final String PARAM_COMMANDE_STATUT_PAIEMENT = "statutPaiementCommande";
	public static final String PARAM_COMMANDE_MODE_LIVRAISON = "modeLivraisonCommande";
	public static final String PARAM_COMMANDE_STATUT_LIVRAISON = "statutLivraisonCommande";
	public static final String PATTERN_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String VUE = "/WEB-INF/view/afficherCommande.jsp";
	
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /*
         * Récupération des données saisies, envoyées en tant que paramètres de
         * la requête GET générée à la validation du formulaire
         */
        String nom = request.getParameter( PARAM_CLIENT_NOM );
        String prenom = request.getParameter( PARAM_CLIENT_PRENOM );
        String adresse = request.getParameter( PARAM_CLIENT_ADRESSE );
        String telephone = request.getParameter( PARAM_CLIENT_TELEPHONE );
        String email = request.getParameter( PARAM_CLIENT_EMAIL );

        /* Récupération de la date courante */
        DateTime dt = new DateTime();
        /* Conversion de la date en String selon le format défini */
        DateTimeFormatter formatter = DateTimeFormat.forPattern( PATTERN_DATE_FORMAT );
        String date = dt.toString( formatter );
        double montant;
        try {
            /* Récupération du montant */
            montant = Double.parseDouble( request.getParameter( PARAM_COMMANDE_MONTANT ) );
        } catch ( NumberFormatException e ) {
            /* Initialisation à -1 si le montant n'est pas un nombre correct */
            montant = -1;
        }
        String modePaiement = request.getParameter( PARAM_COMMANDE_MODE_PAIEMENT );
        String statutPaiement = request.getParameter( PARAM_COMMANDE_STATUT_PAIEMENT );
        String modeLivraison = request.getParameter( PARAM_COMMANDE_MODE_LIVRAISON );
        String statutLivraison = request.getParameter( PARAM_COMMANDE_STATUT_LIVRAISON );

        String message;
        boolean erreur;
        /*
         * Initialisation du message à afficher : si un des champs obligatoires
         * du formulaire n'est pas renseigné, alors on affiche un message
         * d'erreur, sinon on affiche un message de succès
         */
        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || montant == -1
                || modePaiement.isEmpty() || modeLivraison.isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerCommande.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'une commande.";
            erreur = true;
        } else {
            message = "Commande créée avec succès !";
            erreur = false;
        }
        /*
         * Création des beans Client et Commande et initialisation avec les
         * données récupérées
         */
        Client client = new Client();
        client.setLastname( nom );
        client.setFirstname( prenom );
        client.setAddress( adresse );
        client.setPhone( telephone );
        client.setMail( email );

        Commande commande = new Commande();
        commande.setClient( client );
        commande.setDate( date );
        commande.setMontant( montant );
        commande.setModePaiement( modePaiement );
        commande.setStatutPaiement( statutPaiement );
        commande.setModeLivraison( modeLivraison );
        commande.setStatutLivraison( statutLivraison );

        /* Ajout du bean et du message à l'objet requête */
        request.setAttribute( ATT_COMMANDE, commande );
        request.setAttribute( ATT_MESSAGE, message );
        request.setAttribute( ATT_ERREUR, erreur);

        /* Transmission à la page JSP en charge de l'affichage des données */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}