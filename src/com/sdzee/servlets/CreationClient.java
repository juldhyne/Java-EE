package com.sdzee.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.beans.Client;

@WebServlet( "/creationClient" )
public class CreationClient extends HttpServlet {
	
	public static final String ATT_CLIENT = "client";
	public static final String ATT_MESSAGE = "message";
	public static final String ATT_ERREUR = "erreur";
	public static final String PARAM_CLIENT_PRENOM = "prenomClient";
	public static final String PARAM_CLIENT_NOM = "nomClient";
	public static final String PARAM_CLIENT_ADRESSE = "adresseClient";
	public static final String PARAM_CLIENT_TELEPHONE = "telephoneClient";
	public static final String PARAM_CLIENT_EMAIL = "emailClient";
	public static final String VUE = "/WEB-INF/view/afficherClient.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String firstname = request.getParameter(PARAM_CLIENT_PRENOM);
		String lastname = request.getParameter(PARAM_CLIENT_NOM);
		String address = request.getParameter(PARAM_CLIENT_ADRESSE);
		String phone = request.getParameter(PARAM_CLIENT_TELEPHONE);
		String mail = request.getParameter(PARAM_CLIENT_EMAIL);
		String message;
		boolean erreur;
        /*
         * Initialisation du message à afficher : si un des champs obligatoires
         * du formulaire n'est pas renseigné, alors on affiche un message
         * d'erreur, sinon on affiche un message de succès
         */
        if ( lastname.trim().isEmpty() || address.trim().isEmpty() || phone.trim().isEmpty() ) {
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires. <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client.";
            erreur = true;
        } else {
            message = "Client créé avec succès !";
            erreur = false;
        }
		Client client = new Client();
		client.setFirstname(firstname);
		client.setLastname(lastname);
		client.setAddress(address);
		client.setPhone(phone);
		client.setMail(mail);
		
		request.setAttribute(ATT_CLIENT, client);
		request.setAttribute(ATT_MESSAGE, message);
		request.setAttribute(ATT_ERREUR, erreur);
		
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	}
}
