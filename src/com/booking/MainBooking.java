package com.booking;

import com.booking.collections.CollectionHebergements;
import com.booking.models.*;
import com.booking.services.AuthService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainBooking {
    private static CollectionHebergements collection = new CollectionHebergements();
    private static AuthService authService = new AuthService();
    private static Scanner scanner = new Scanner(System.in);

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
                    menuClientInteractif();
                    break;
                case 2:
                    menuAdminInteractif();
                    break;
                case 3:
                    scenario1_NouveauClient();
                    break;
                case 4:
                    scenario2_AncienClient();
                    break;
                case 5:
                    scenario3_Administrateur();
                    break;
                case 6:
                    demonstrationPolymorphisme();
                    break;
                case 7:
                    demonstrationCollections();
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

    /**
     * SCÉNARIO 1 : Nouveau client (sans réduction)
     * - Création et inscription
     * - Connexion au système
     * - Recherche par ville
     * - Réservation d'un hébergement
     * - Consultation de l'historique
     */
    private static void scenario1_NouveauClient() {
        System.out.println("═".repeat(60));
        System.out.println("📌 SCÉNARIO 1 : NOUVEAU CLIENT (0% de réduction)");
        System.out.println("═".repeat(60));

        // Étape 1 : Inscription
        System.out.println("\n1️⃣  Inscription d'un nouveau client");
        System.out.println("─".repeat(40));
        NouveauClient client = new NouveauClient(
            "Dupont", 
            "Jean", 
            "jean.dupont@email.com", 
            "password123"
        );
        authService.ajouterUtilisateur(client);

        // Étape 2 : Connexion
        System.out.println("\n2️⃣  Tentative de connexion");
        System.out.println("─".repeat(40));
        Personne utilisateur = authService.seConnecter("jean.dupont@email.com", "password123");
        
        if (utilisateur == null) {
            System.out.println("❌ Échec du scénario 1");
            return;
        }

        Client clientConnecte = (Client) utilisateur;

        // Étape 3 : Recherche d'hébergements
        System.out.println("\n3️⃣  Recherche d'hébergements à Paris");
        System.out.println("─".repeat(40));
        List<Hebergement> resultats = collection.rechercherParVille("Paris");
        System.out.println("📍 " + resultats.size() + " hébergement(s) trouvé(s) à Paris\n");
        
        for (Hebergement h : resultats) {
            h.afficherDetails();
            System.out.println();
        }

        // Étape 4 : Réservation
        if (!resultats.isEmpty()) {
            System.out.println("4️⃣  Réservation du premier hébergement");
            System.out.println("─".repeat(40));
            
            Hebergement choix = resultats.get(0);
            
            // Dates : dans 10 jours pour 3 nuits
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 10);
            Date debut = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 3);
            Date fin = cal.getTime();

            Reservation reservation = clientConnecte.reserver(choix, debut, fin, 2);
            
            if (reservation != null) {
                System.out.println("\n✅ Réservation créée avec succès !");
                reservation.afficherDetails();
            }
        }

        // Étape 5 : Historique
        System.out.println("\n5️⃣  Consultation de l'historique");
        System.out.println("─".repeat(40));
        clientConnecte.afficherHistorique();
        
        System.out.println("\n✅ Scénario 1 terminé\n");
    }

    /**
     * SCÉNARIO 2 : Ancien client (avec réduction de 15%)
     * - Inscription avec taux de réduction
     * - Connexion
     * - Réservation d'une villa
     * - Vérification de l'application de la réduction
     */
    private static void scenario2_AncienClient() {
        System.out.println("═".repeat(60));
        System.out.println("📌 SCÉNARIO 2 : ANCIEN CLIENT (15% de réduction)");
        System.out.println("═".repeat(60));

        // Étape 1 : Inscription avec réduction
        System.out.println("\n1️⃣  Inscription d'un ancien client avec réduction");
        System.out.println("─".repeat(40));
        AncienClient client = new AncienClient(
            "Martin", 
            "Sophie", 
            "sophie.martin@email.com", 
            "password456", 
            0.15  // 15% de réduction
        );
        authService.ajouterUtilisateur(client);

        // Étape 2 : Connexion
        System.out.println("\n2️⃣  Connexion de l'ancien client");
        System.out.println("─".repeat(40));
        Personne utilisateur = authService.seConnecter("sophie.martin@email.com", "password456");
        
        if (utilisateur == null) {
            System.out.println("❌ Échec du scénario 2");
            return;
        }

        Client clientConnecte = (Client) utilisateur;

        // Étape 3 : Recherche d'une villa
        System.out.println("\n3️⃣  Recherche de villas");
        System.out.println("─".repeat(40));
        List<Hebergement> resultats = collection.rechercherParVille("Cannes");
        
        if (!resultats.isEmpty()) {
            Hebergement villa = resultats.get(0);
            System.out.println("🏡 Villa trouvée :");
            villa.afficherDetails();

            // Étape 4 : Réservation avec réduction
            System.out.println("\n4️⃣  Réservation de la villa (avec réduction)");
            System.out.println("─".repeat(40));
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 20);
            Date debut = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 7);  // 7 nuits
            Date fin = cal.getTime();

            Reservation reservation = clientConnecte.reserver(villa, debut, fin, 6);
            
            if (reservation != null) {
                System.out.println("\n✅ Réservation avec réduction appliquée !");
                reservation.afficherDetails();
            }
        }

        System.out.println("\n✅ Scénario 2 terminé\n");
    }

    /**
     * SCÉNARIO 3 : Administrateur
     * - Connexion admin
     * - Ajout d'un nouvel hébergement
     * - Modification du prix d'un hébergement
     * - Suppression d'un hébergement
     * - Affichage des statistiques
     */
    private static void scenario3_Administrateur() {
        System.out.println("═".repeat(60));
        System.out.println("📌 SCÉNARIO 3 : ADMINISTRATEUR");
        System.out.println("═".repeat(60));

        // Étape 1 : Création compte admin
        System.out.println("\n1️⃣  Création du compte administrateur");
        System.out.println("─".repeat(40));
        Administrateur admin = new Administrateur(
            "Admin", 
            "Système", 
            "admin@booking.com", 
            "admin2024"
        );
        authService.ajouterUtilisateur(admin);

        // Étape 2 : Connexion admin
        System.out.println("\n2️⃣  Connexion administrateur");
        System.out.println("─".repeat(40));
        Personne utilisateur = authService.seConnecter("admin@booking.com", "admin2024");
        
        if (utilisateur == null || !(utilisateur instanceof Administrateur)) {
            System.out.println("❌ Échec du scénario 3");
            return;
        }

        Administrateur adminConnecte = (Administrateur) utilisateur;

        // Étape 3 : Ajout d'un nouvel hébergement
        System.out.println("\n3️⃣  Ajout d'un nouvel hébergement");
        System.out.println("─".repeat(40));
        Hotel nouveauHotel = new Hotel(
            "Hôtel Concorde", 
            "Place de la Concorde, 75008 Paris", 
            2, 
            200.0, 
            "Hôtel de prestige 5 étoiles", 
            5
        );
        
        // Ajouter disponibilité
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        Date fin = cal.getTime();
        nouveauHotel.ajouterPeriodeDisponible(debut, fin);
        
        adminConnecte.ajouterHebergement(collection, nouveauHotel);
        nouveauHotel.afficherDetails();

        // Étape 4 : Modification de prix
        System.out.println("\n4️⃣  Modification du prix d'un hébergement");
        System.out.println("─".repeat(40));
        Hebergement hotel1 = collection.rechercherParId(1);
        
        if (hotel1 != null) {
            System.out.println("Hébergement sélectionné : " + hotel1.getNom());
            System.out.printf("Ancien prix : %.2f€/nuit%n", hotel1.getPrixParNuit());
            adminConnecte.modifierPrix(hotel1, 195.0);
            System.out.printf("Nouveau prix : %.2f€/nuit%n", hotel1.getPrixParNuit());
        }

        // Étape 5 : Affichage des statistiques
        System.out.println("\n5️⃣  Statistiques du système");
        System.out.println("─".repeat(40));
        System.out.println("📊 Nombre d'hébergements : " + collection.getTous().size());
        System.out.println("👥 Nombre d'utilisateurs : " + authService.getTousUtilisateurs().size());
        
        // Compter les réservations totales
        int totalReservations = 0;
        for (Personne p : authService.getTousUtilisateurs()) {
            if (p instanceof Client) {
                totalReservations += ((Client) p).getReservations().size();
            }
        }
        System.out.println("📅 Nombre de réservations : " + totalReservations);

        System.out.println("\n✅ Scénario 3 terminé\n");
    }

    /**
     * Démonstration du polymorphisme
     */
    private static void demonstrationPolymorphisme() {
        System.out.println("═".repeat(60));
        System.out.println("📌 DÉMONSTRATION DU POLYMORPHISME");
        System.out.println("═".repeat(60));

        // Polymorphisme sur les hébergements
        System.out.println("\n1️⃣  Polymorphisme : méthode afficherDetails()");
        System.out.println("─".repeat(40));
        System.out.println("Tous les types d'hébergements utilisent la même méthode :\n");
        
        List<Hebergement> hebergements = collection.getTous();
        for (int i = 0; i < Math.min(3, hebergements.size()); i++) {
            hebergements.get(i).afficherDetails();
            System.out.println();
        }

        // Polymorphisme sur les personnes
        System.out.println("\n2️⃣  Polymorphisme : méthode afficherRole()");
        System.out.println("─".repeat(40));
        System.out.println("Tous les utilisateurs utilisent la même méthode :\n");
        
        List<Personne> utilisateurs = authService.getTousUtilisateurs();
        for (Personne p : utilisateurs) {
            System.out.println("👤 " + p.getPrenom() + " " + p.getNom());
            p.afficherRole();
            System.out.println();
        }

        // Polymorphisme sur les réductions
        System.out.println("\n3️⃣  Polymorphisme : méthode getReduction()");
        System.out.println("─".repeat(40));
        for (Personne p : utilisateurs) {
            if (p instanceof Client) {
                Client c = (Client) p;
                System.out.printf("%s %s : %.0f%% de réduction%n", 
                    c.getPrenom(), c.getNom(), c.getReduction() * 100);
            }
        }

        System.out.println("\n✅ Démonstration du polymorphisme terminée\n");
    }

    /**
     * Démonstration de l'utilisation des collections
     */
    private static void demonstrationCollections() {
        System.out.println("═".repeat(60));
        System.out.println("📌 DÉMONSTRATION DES COLLECTIONS");
        System.out.println("═".repeat(60));

        // Collection d'hébergements
        System.out.println("\n1️⃣  Collection List<Hebergement>");
        System.out.println("─".repeat(40));
        List<Hebergement> hebergements = collection.getTous();
        System.out.println("📦 Taille de la collection : " + hebergements.size());
        System.out.println("Type : ArrayList<Hebergement>");

        // Collection de personnes
        System.out.println("\n2️⃣  Collection List<Personne>");
        System.out.println("─".repeat(40));
        List<Personne> personnes = authService.getTousUtilisateurs();
        System.out.println("📦 Taille de la collection : " + personnes.size());
        System.out.println("Type : ArrayList<Personne>");

        // Collection de réservations
        System.out.println("\n3️⃣  Collections List<Reservation> par client");
        System.out.println("─".repeat(40));
        for (Personne p : personnes) {
            if (p instanceof Client) {
                Client c = (Client) p;
                System.out.printf("%s %s : %d réservation(s)%n", 
                    c.getPrenom(), c.getNom(), c.getReservations().size());
            }
        }

        // Collection de notes
        System.out.println("\n4️⃣  Collections List<Double> pour les notes");
        System.out.println("─".repeat(40));
        Hebergement premier = hebergements.get(0);
        System.out.println("Hébergement : " + premier.getNom());
        System.out.println("Notes : " + premier.getNotes());
        System.out.printf("Moyenne : %.2f/5%n", premier.calculerMoyenneNotes());

        // Recherche avec collections
        System.out.println("\n5️⃣  Recherche dans les collections");
        System.out.println("─".repeat(40));
        System.out.println("🔍 Hébergements à Paris : " + 
            collection.rechercherParVille("Paris").size());
        System.out.println("🔍 Hébergements ≤ 200€ : " + 
            collection.rechercherParPrixMax(200.0).size());
        System.out.println("🔍 Hébergements capacité ≥ 4 : " + 
            collection.rechercherParCapacite(4).size());

        System.out.println("\n✅ Démonstration des collections terminée\n");
    }

    /**
     * Mode client interactif : inscription, connexion, recherche et réservation.
     */
    private static void menuClientInteractif() {
        System.out.println("═".repeat(60));
        System.out.println("👤 MODE CLIENT (INTERACTIF)");
        System.out.println("═".repeat(60));

        // Inscription
        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        NouveauClient client = new NouveauClient(nom, prenom, email, motDePasse);
        authService.ajouterUtilisateur(client);

        // Connexion
        System.out.println("\n🔐 Connexion");
        System.out.print("Email : ");
        String emailConnexion = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdpConnexion = scanner.nextLine();

        Personne utilisateur = authService.seConnecter(emailConnexion, mdpConnexion);
        if (utilisateur == null || !(utilisateur instanceof Client)) {
            System.out.println("❌ Impossible de se connecter en tant que client.\n");
            return;
        }

        Client clientConnecte = (Client) utilisateur;

        boolean retour = false;
        while (!retour) {
            System.out.println("\n📋 MENU CLIENT");
            System.out.println("1️⃣  Rechercher un hébergement par ville");
            System.out.println("2️⃣  Afficher l'historique des réservations");
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
                    effectuerRechercheEtReservation(clientConnecte);
                    break;
                case 2:
                    clientConnecte.afficherHistorique();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    System.out.println("❌ Choix inconnu.");
            }
        }
    }

    /**
     * Recherche d'hébergements et création éventuelle d'une réservation.
     */
    private static void effectuerRechercheEtReservation(Client clientConnecte) {
        System.out.print("\n🔍 Ville recherchée : ");
        String ville = scanner.nextLine();

        List<Hebergement> resultats = collection.rechercherParVille(ville);
        if (resultats.isEmpty()) {
            System.out.println("📭 Aucun hébergement trouvé pour cette ville.");
            return;
        }

        System.out.println("\n🏠 Hébergements trouvés :");
        for (int i = 0; i < resultats.size(); i++) {
            Hebergement h = resultats.get(i);
            System.out.printf("%d) [ID %d] %s - %.2f€/nuit, capacité %d%n",
                    i + 1, h.getId(), h.getNom(), h.getPrixParNuit(), h.getCapacite());
        }

        System.out.print("\nSouhaitez-vous faire une réservation ? (o/n) : ");
        String reponse = scanner.nextLine().trim().toLowerCase();
        if (!reponse.equals("o") && !reponse.equals("oui")) {
            return;
        }

        System.out.print("Numéro de l'hébergement choisi : ");
        String choixStr = scanner.nextLine();
        int indexChoix;
        try {
            indexChoix = Integer.parseInt(choixStr) - 1;
        } catch (NumberFormatException e) {
            System.out.println("❌ Numéro invalide.");
            return;
        }

        if (indexChoix < 0 || indexChoix >= resultats.size()) {
            System.out.println("❌ Numéro hors limite.");
            return;
        }

        Hebergement choix = resultats.get(indexChoix);

        System.out.print("Nombre de personnes : ");
        int nbPersonnes;
        try {
            nbPersonnes = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        System.out.print("Nombre de nuits : ");
        int nbNuits;
        try {
            nbNuits = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        System.out.print("Dans combien de jours commence le séjour ? ");
        int joursAvantDebut;
        try {
            joursAvantDebut = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Valeur invalide.");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, joursAvantDebut);
        Date debut = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, nbNuits);
        Date fin = cal.getTime();

        Reservation reservation = clientConnecte.reserver(choix, debut, fin, nbPersonnes);
        if (reservation != null) {
            System.out.println("\n✅ Réservation créée avec succès !");
            reservation.afficherDetails();
        }
    }

    /**
     * Mode administrateur interactif : connexion et gestion des hébergements.
     */
    private static void menuAdminInteractif() {
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

    private static void ajouterHotelSimple(Administrateur adminConnecte) {
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
        Hotel hotel = new Hotel(
                nom,
                adresse,
                capacite,
                prix,
                "Hôtel ajouté par l'administrateur",
                3
        );

        // Disponibilité par défaut : 3 mois à partir d'aujourd'hui
        Calendar cal = Calendar.getInstance();
        Date debut = cal.getTime();
        cal.add(Calendar.MONTH, 3);
        Date fin = cal.getTime();
        hotel.ajouterPeriodeDisponible(debut, fin);

        adminConnecte.ajouterHebergement(collection, hotel);
    }

    private static void modifierPrixParId(Administrateur adminConnecte) {
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

    private static void supprimerHebergementParId(Administrateur adminConnecte) {
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

    private static void afficherStatistiques() {
        System.out.println("\n📊 Statistiques du système");
        System.out.println("─".repeat(40));
        System.out.println("📦 Nombre d'hébergements : " + collection.getTous().size());
        System.out.println("👥 Nombre d'utilisateurs : " + authService.getTousUtilisateurs().size());

        int totalReservations = 0;
        for (Personne p : authService.getTousUtilisateurs()) {
            if (p instanceof Client) {
                totalReservations += ((Client) p).getReservations().size();
            }
        }
        System.out.println("📅 Nombre total de réservations : " + totalReservations);
    }
}
