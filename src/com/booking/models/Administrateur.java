package com.booking.models;

import com.booking.collections.CollectionHebergements;

public class Administrateur extends Personne {
    public Administrateur(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
    }

    public void ajouterHebergement(CollectionHebergements collection, Hebergement hebergement) {
        collection.ajouter(hebergement);
        System.out.println("✅ Hébergement ajouté: " + hebergement.getNom());
    }

    public void supprimerHebergement(CollectionHebergements collection, int id) {
        if (collection.supprimer(id)) {
            System.out.println("✅ Hébergement supprimé");
        } else {
            System.out.println("❌ Hébergement introuvable");
        }
    }

    public void modifierPrix(Hebergement hebergement, double nouveauPrix) {
        hebergement.setPrixParNuit(nouveauPrix);
        System.out.printf("✅ Prix modifié: %.2f€/nuit%n", nouveauPrix);
    }

    @Override
    public void afficherRole() {
        System.out.println("👨‍💼 Rôle: Administrateur");
    }
}
