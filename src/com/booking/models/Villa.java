package com.booking.models;

public class Villa extends Hebergement {
    private boolean avecPiscine;
    private double superficieTerrain;

    public Villa(String nom, String adresse, int capacite, double prixParNuit,
                 String description, boolean avecPiscine, double superficieTerrain) {
        super(nom, adresse, capacite, prixParNuit, description);
        this.avecPiscine = avecPiscine;
        this.superficieTerrain = superficieTerrain;
    }

    public boolean isAvecPiscine() { return avecPiscine; }
    public double getSuperficieTerrain() { return superficieTerrain; }

    @Override
    public void afficherDetails() {
        System.out.println("🏡 Villa: " + nom);
        System.out.println("   Adresse: " + adresse);
        System.out.println("   Piscine: " + (avecPiscine ? "Oui" : "Non"));
        System.out.printf("   Terrain: %.0f m²%n", superficieTerrain);
        System.out.println("   Capacité: " + capacite + " personnes");
        System.out.printf("   Prix: %.2f€/nuit%n", prixParNuit);
        System.out.printf("   Note moyenne: %.1f/5%n", calculerMoyenneNotes());
    }
}
