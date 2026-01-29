package com.booking;

import com.booking.collections.CollectionHebergements;
import com.booking.models.*;
import com.booking.services.AuthService;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainBooking {
    private static CollectionHebergements collection = new CollectionHebergements();
    private static AuthService authService = new AuthService();
    private static Scanner scanner = new Scanner(System.in);

    // Interfaces dédiées (client, admin, scénarios/démos)
    private static ClientInterface clientInterface = new ClientInterface(collection, authService, scanner);
    private static AdminInterface adminInterface = new AdminInterface(collection, authService, scanner);
    private static DemoScenarios demoScenarios = new DemoScenarios(collection, authService);

    public static void main(String[] args) {
        afficherBanniere();
        initialiserDonnees();

        afficherMenuPrincipal();

        scanner.close();
        System.out.println("\n✅ Programme terminé avec succès !");
    }

    /**
     * Affiche le menu principal et permet à l'utilisateur de choisir quoi exécuter.
     */
    private static void afficherMenuPrincipal() {
        boolean quitter = false;

        while (!quitter) {
            System.out.println("═".repeat(60));
            System.out.println("📋 MENU PRINCIPAL");
            System.out.println("═".repeat(60));
            System.out.println("1️⃣  Mode client interactif");
            System.out.println("2️⃣  Mode administrateur interactif");
            System.out.println("3️⃣  Scénario 1 (démo auto : nouveau client)");
            System.out.println("4️⃣  Scénario 2 (démo auto : ancien client)");
            System.out.println("5️⃣  Scénario 3 (démo auto : administrateur)");
            System.out.println("6️⃣  Démonstration du polymorphisme");
            System.out.println("7️⃣  Démonstration des collections");
            System.out.println("0️⃣  Quitter");
            System.out.print("\n👉 Votre choix : ");

            String saisie = scanner.nextLine();
            int choix;

            try {
                choix = Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un numéro valide.\n");
                continue;
            }

            System.out.println();

            switch (choix) {
                case 1:
                    clientInterface.afficherMenuClient();
                    break;
                case 2:
                    adminInterface.afficherMenuAdmin();
                    break;
                case 3:
                    demoScenarios.scenario1_NouveauClient();
                    break;
                case 4:
                    demoScenarios.scenario2_AncienClient();
                    break;
                case 5:
                    demoScenarios.scenario3_Administrateur();
                    break;
                case 6:
                    demoScenarios.demonstrationPolymorphisme();
                    break;
                case 7:
                    demoScenarios.demonstrationCollections();
                    break;
                case 0:
                    quitter = true;
                    break;
                default:
                    System.out.println("❌ Choix inconnu, merci de réessayer.\n");
            }
        }
    }

    private static void afficherBanniere() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         SYSTÈME DE RÉSERVATION D'HÉBERGEMENTS             ║");
        System.out.println("║                    PROJET POO - JAVA                       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Initialisation de la base de données avec 5 hébergements minimum
     */
    private static void initialiserDonnees() {
        System.out.println("═".repeat(60));
        System.out.println("🔄 INITIALISATION DES DONNÉES");
        System.out.println("═".repeat(60));

        // 2 Hôtels
        Hotel hotel1 = new Hotel(
            "Hôtel Royal Paris", 
            "10 Avenue des Champs-Élysées, 75008 Paris", 
            2, 
            180.0, 
            "Hôtel 4 étoiles au coeur de Paris", 
            4
        );
        
        Hotel hotel2 = new Hotel(
            "Hôtel Riviera", 
            "20 Promenade des Anglais, 06000 Nice", 
            4, 
            220.0, 
            "Hôtel 5 étoiles avec vue mer", 
            5
        );

        // 2 Appartements
        Appartement appart1 = new Appartement(
            "Studio Le Marais", 
            "5 Rue Vieille du Temple, 75004 Paris", 
            2, 
            95.0, 
            "Studio moderne avec balcon", 
            true
        );
        
        Appartement appart2 = new Appartement(
            "Appartement Lyon Centre", 
            "15 Rue de la République, 69002 Lyon", 
            4, 
            130.0, 
            "Appartement familial spacieux", 
            false
        );

        // 1 Villa
        Villa villa1 = new Villa(
            "Villa Côte d'Azur", 
            "30 Boulevard de la Croisette, 06400 Cannes", 
            8, 
            550.0, 
            "Villa de luxe avec piscine privée", 
            true, 
            250.0
        );

        // Ajout à la collection
        collection.ajouter(hotel1);
        collection.ajouter(hotel2);
        collection.ajouter(appart1);
        collection.ajouter(appart2);
        collection.ajouter(villa1);

        // Définir les périodes de disponibilité (3 mois à partir d'aujourd'hui)
        Calendar cal = Calendar.getInstance();
        Date dateDebut = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        Date dateFin = cal.getTime();

        for (Hebergement h : collection.getTous()) {
            h.ajouterPeriodeDisponible(dateDebut, dateFin);
            
            // Ajouter des notes aléatoires
            h.ajouterNote(4.5);
            h.ajouterNote(4.2);
            h.ajouterNote(4.8);
        }

        System.out.println("✅ " + collection.getTous().size() + " hébergements initialisés");
        System.out.println("✅ Périodes de disponibilité configurées");
        System.out.println();
    }

    // Les méthodes détaillées (scénarios, menus interactifs, démos)
    // ont été déplacées dans les classes :
    // - ClientInterface
    // - AdminInterface
    // - DemoScenarios
    // pour alléger au maximum ce fichier MainBooking.
}
