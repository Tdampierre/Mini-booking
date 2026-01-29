package com.booking;

import com.booking.collections.CollectionHebergements;
import com.booking.models.Administrateur;
import com.booking.models.Hebergement;
import com.booking.models.Personne;
import com.booking.services.AuthService;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Gère le mode administrateur interactif : connexion et gestion des hébergements.
 */
public class AdminInterface {

    private final CollectionHebergements collection;
    private final AuthService authService;
    private final Scanner scanner;

    public AdminInterface(CollectionHebergements collection, AuthService authService, Scanner scanner) {
        this.collection = collection;
        this.authService = authService;
        this.scanner = scanner;
    }

    public void afficherMenuAdmin() {
        System.out.println("═".repeat(60));
        System.out.println("👨‍💼 MODE ADMINISTRATEUR (INTERACTIF)");
        System.out.println("═".repeat(60));

        // Création d'un compte admin par défaut
        Administrateur admin = new Administrateur(
                "Admin",
                "Système",
                "admin@booking.com",
                "admin2024"
        );
        authService.ajouterUtilisateur(admin);
        System.out.println("ℹ️ Compte admin par défaut : admin@booking.com / admin2024");

        // Connexion
        System.out.println("\n🔐 Connexion administrateur");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        Personne utilisateur = authService.seConnecter(email, mdp);
        if (utilisateur == null || !(utilisateur instanceof Administrateur)) {
            System.out.println("❌ Connexion administrateur échouée.\n");
            return;
        }

        Administrateur adminConnecte = (Administrateur) utilisateur;

        boolean retour = false;
        while (!retour) {
            System.out.println("\n📋 MENU ADMINISTRATEUR");
            System.out.println("1️⃣  Lister tous les hébergements");
            System.out.println("2️⃣  Ajouter un nouvel hôtel simple");
            System.out.println("3️⃣  Modifier le prix d'un hébergement par ID");
            System.out.println("4️⃣  Supprimer un hébergement par ID");
            System.out.println("5️⃣  Afficher les statistiques");
            System.out.println("0️⃣  Retour au menu principal");
            System.out.print("\n👉 Votre choix : ");

            String saisie = scanner.nextLine();
            int choix;
            try {
                choix = Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un numéro valide.");
                continue;
            }

            switch (choix) {
                case 1:
                    collection.afficherTous();
                    break;
                case 2:
                    ajouterHotelSimple(adminConnecte);
                    break;
                case 3:
                    modifierPrixParId(adminConnecte);
                    break;
                case 4:
                    supprimerHebergementParId(adminConnecte);
                    break;
                case 5:
                    afficherStatistiques();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix inconnu.");
            }
        }
    }

    private void ajouterHotelSimple(Administrateur adminConnecte) {
        System.out.println("\n🏨 Ajout d'un nouvel hôtel");
        System.out.print("Nom de l'hôtel : ");
        String nom = scanner.nextLine();
        System.out.print("Ville : ");
        String ville = scanner.nextLine();
        System.out.print("Capacité (nombre de personnes) : ");
        int capacite;
        try {
            capacite = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }
        System.out.print("Prix par nuit : ");
        double prix;
        try {
            prix = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        String adresse = "Adresse inconnue, " + ville;
        Hebergement hotel = new HebergementHotelFactory().creerHotelSimple(nom, adresse, capacite, prix);

        // Disponibilité par défaut : 3 mois à partir d'aujourd'hui
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        Date fin = cal.getTime();
        hotel.ajouterPeriodeDisponible(debut, fin);

        adminConnecte.ajouterHebergement(collection, hotel);
    }

    private void modifierPrixParId(Administrateur adminConnecte) {
        System.out.print("\nID de l'hébergement : ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        Hebergement h = collection.rechercherParId(id);
        if (h == null) {
            System.out.println("❌ Hébergement introuvable.");
            return;
        }

        System.out.printf("Hébergement sélectionné : %s (%.2f€/nuit)%n", h.getNom(), h.getPrixParNuit());
        System.out.print("Nouveau prix : ");
        double prix;
        try {
            prix = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        adminConnecte.modifierPrix(h, prix);
    }

    private void supprimerHebergementParId(Administrateur adminConnecte) {
        System.out.print("\nID de l'hébergement à supprimer : ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        adminConnecte.supprimerHebergement(collection, id);
    }

    private void afficherStatistiques() {
        System.out.println("\n📊 Statistiques du système");
        System.out.println("─".repeat(40));
        System.out.println("📦 Nombre d'hébergements : " + collection.getTous().size());
        System.out.println("👥 Nombre d'utilisateurs : " + authService.getTousUtilisateurs().size());

        int totalReservations = 0;
        for (Personne p : authService.getTousUtilisateurs()) {
            if (p instanceof com.booking.models.Client) {
                totalReservations += ((com.booking.models.Client) p).getReservations().size();
            }
        }
        System.out.println("📅 Nombre total de réservations : " + totalReservations);
    }
}

