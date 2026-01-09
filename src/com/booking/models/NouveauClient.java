package com.booking.models;

public class NouveauClient extends Client {
    public NouveauClient(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
    }

    @Override
    public double getReduction() {
        return 0.0; // Pas de réduction
    }

    @Override
    public void afficherRole() {
        System.out.println("👤 Rôle: Nouveau Client (0% de réduction)");
    }
}
