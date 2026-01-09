package com.booking.models;

public class Appartement extends Hebergement {
    private boolean avecBalcon;

    public Appartement(String nom, String adresse, int capacite, double prixParNuit,
                       String description, boolean avecBalcon) {
        super(nom, adresse, capacite, prixParNuit, description);
        this.avecBalcon = avecBalcon;
    }

    public boolean isAvecBalcon() { return avecBalcon; }

    @Override
    public void afficherDetails() {
        System.out.println("🏠 Appartement: " + nom);
        System.out.println("   Adresse: " + adresse);
        System.out.println("   Balcon: " + (avecBalcon ? "Oui" : "Non"));
        System.out.println("   Capacité: " + capacite + " personnes");
        System.out.printf("   Prix: %.2f€/nuit%n", prixParNuit);
        System.out.printf("   Note moyenne: %.1f/5%n", calculerMoyenneNotes());
    }
}
