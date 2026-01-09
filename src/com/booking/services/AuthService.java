package com.booking.services;

import com.booking.models.Personne;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<Personne> utilisateurs;

    public AuthService() {
        this.utilisateurs = new ArrayList<>();
    }

    public void ajouterUtilisateur(Personne personne) {
        utilisateurs.add(personne);
        System.out.println("✅ Utilisateur ajouté : " + personne.getEmail());
    }

    public Personne seConnecter(String email, String motDePasse) {
        for (Personne p : utilisateurs) {
            if (p.getEmail().equals(email) && p.verifierMotDePasse(motDePasse)) {
                System.out.println("✅ Connexion réussie !");
                p.afficherRole();
                return p;
            }
        }
        System.out.println("❌ Email ou mot de passe incorrect");
        return null;
    }

    public List<Personne> getTousUtilisateurs() {
        return new ArrayList<>(utilisateurs);
    }
}
