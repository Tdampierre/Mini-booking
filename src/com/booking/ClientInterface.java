package com.booking;

import com.booking.collections.CollectionHebergements;
import com.booking.models.Client;
import com.booking.models.Hebergement;
import com.booking.models.NouveauClient;
import com.booking.models.Personne;
import com.booking.models.Reservation;
import com.booking.services.AuthService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Gère le mode client interactif : inscription, connexion, recherche et réservation.
 */
public class ClientInterface {

    private final CollectionHebergements collection;
    private final AuthService authService;
    private final Scanner scanner;

    public ClientInterface(CollectionHebergements collection, AuthService authService, Scanner scanner) {
        this.collection = collection;
        this.authService = authService;
        this.scanner = scanner;
    }

    public void afficherMenuClient() {
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
    private void effectuerRechercheEtReservation(Client clientConnecte) {
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
}

