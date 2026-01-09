package com.booking.models;

public class Hotel extends Hebergement {
    private int nombreEtoiles;

    public Hotel(String nom, String adresse, int capacite, double prixParNuit, 
                 String description, int nombreEtoiles) {
        super(nom, adresse, capacite, prixParNuit, description);
        this.nombreEtoiles = nombreEtoiles;
    }

    public int getNombreEtoiles() { return nombreEtoiles; }

    @Override
    public void afficherDetails() {
        System.out.println("🏨 Hotel: " + nom);
        System.out.println("   Adresse: " + adresse);
        System.out.println("   ⭐ " + nombreEtoiles + " étoiles");
        System.out.println("   Capacité: " + capacite + " personnes");
        System.out.printf("   Prix: %.2f€/nuit%n", prixParNuit);
        System.out.printf("   Note moyenne: %.1f/5%n", calculerMoyenneNotes());
    }
}
