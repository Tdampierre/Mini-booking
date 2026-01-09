package com.booking.models;

public class AncienClient extends Client {
    private double tauxReduction;

    public AncienClient(String nom, String prenom, String email, String motDePasse, double tauxReduction) {
        super(nom, prenom, email, motDePasse);
        this.tauxReduction = tauxReduction;
    }

    @Override
    public double getReduction() {
        return tauxReduction;
    }

    @Override
    public void afficherRole() {
        System.out.printf("👤 Rôle: Ancien Client (%.0f%% de réduction)%n", tauxReduction * 100);
    }
}
