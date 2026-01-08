package com.booking;

import com.booking.models.*;
import com.booking.collections.CollectionHebergements;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainBooking {
    public static void main(String[] args) throws Exception {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║       MINI-BOOKING SYSTEM         ║");
        System.out.println("╚═══════════════════════════════════╝\n");

        // Créer des hébergements
        Hotel hotel = new Hotel("Grand Hotel Paris", "10 rue de Paris", 2, 150, 5);
        Appartement appart = new Appartement("Studio Marais", "25 rue du Marais", 4, 80, 2);
        Villa villa = new Villa("Villa Provence", "Route de Nice", 8, 300, true, 2000);

        // Ajouter des notes
        hotel.ajouterNote(5);
        hotel.ajouterNote(4);
        hotel.ajouterNote(5);
        appart.ajouterNote(4);
        appart.ajouterNote(3);
        villa.ajouterNote(5);
        villa.ajouterNote(5);

        // Collection
        CollectionHebergements collection = new CollectionHebergements();
        collection.ajouter(hotel);
        collection.ajouter(appart);
        collection.ajouter(villa);

        // Afficher tous les hébergements
        System.out.println("=== TOUS LES HÉBERGEMENTS ===\n");
        collection.afficherTous();

        // Créer un client fidèle (avec historique)
        Client client = new Client("Dupont", "Marie", "marie@email.com", "0612345678");
        
        // Simuler 5 réservations passées
        for (int i = 0; i < 5; i++) {
            client.ajouterReservation(null);
        }

        System.out.println("=== CLIENT FIDÈLE ===");
        System.out.println("Nom: " + client.getNomComplet());
        System.out.println("Réservations passées: " + client.getNombreReservations());

        // Créer un admin
        Administrateur admin = new Administrateur("Admin", "Super", "admin@booking.com", "admin123");
        
        // Connexion admin
        System.out.println("\n=== CONNEXION ADMIN ===");
        admin.seConnecter("admin123");

        // Calculer réduction
        double reduction = admin.calculerReduction(client);
        System.out.println("Réduction pour le client: " + (int)(reduction*100) + "%");

        // Créer une réservation
        System.out.println("\n=== NOUVELLE RÉSERVATION ===");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date debut = sdf.parse("15/07/2025");
        Date fin = sdf.parse("20/07/2025");

        Reservation res = admin.creerReservation(client, hotel, debut, fin);

        // Afficher facture
        if (res != null) {
            res.afficherFacture();
        }

        // Recherches
        System.out.println("\n=== RECHERCHE PAR TYPE (Hotel) ===");
        for (Hebergement h : collection.rechercherParType("Hotel")) {
            System.out.println("- " + h.getNom());
        }

        System.out.println("\n=== TRI PAR PRIX ===");
        collection.trierParPrix();
        for (Hebergement h : collection.getHebergements()) {
            System.out.println("- " + h.getNom() + " : " + h.getPrixParNuit() + "€/nuit");
        }

        // Déconnexion
        System.out.println();
        admin.seDeconnecter();

        System.out.println("\n=== FIN DU PROGRAMME ===");
    }
}
