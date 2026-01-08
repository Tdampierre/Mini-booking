package com.booking.models;

import java.util.Date;
import java.util.List;

public class Administrateur extends Personne {
    private String motDePasse;
    private boolean estConnecte;

    public Administrateur(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email);
        this.motDePasse = motDePasse;
        this.estConnecte = false;
    }

    public boolean seConnecter(String mdp) {
        if (mdp.equals(this.motDePasse)) {
            estConnecte = true;
            System.out.println("Connexion réussie !");
            return true;
        }
        System.out.println("Mot de passe incorrect !");
        return false;
    }

    public void seDeconnecter() {
        estConnecte = false;
        System.out.println("Déconnexion effectuée.");
    }

    public boolean estConnecte() {
        return estConnecte;
    }

    public double calculerReduction(Client client) {
        int nbRes = client.getNombreReservations();
        if (nbRes >= 10) return 0.15;
        if (nbRes >= 5) return 0.10;
        if (nbRes >= 3) return 0.05;
        return 0.0;
    }

    public Reservation creerReservation(Client client, Hebergement h, Date debut, Date fin) {
        if (!estConnecte) {
            System.out.println("Vous devez être connecté !");
            return null;
        }
        
        Reservation res = new Reservation(client, h, debut, fin);
        double reduction = calculerReduction(client);
        
        if (reduction > 0) {
            res.appliquerReduction(reduction);
            System.out.println("Réduction de " + (int)(reduction*100) + "% appliquée !");
        }
        
        client.ajouterReservation(res);
        System.out.println("Réservation " + res.getId() + " créée !");
        return res;
    }
}
