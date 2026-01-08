package com.booking.models;

public class Villa extends Hebergement {
    private boolean piscine;
    private int surfaceTerrain;

    public Villa(String nom, String adresse, int capacite, double prixParNuit, boolean piscine, int surfaceTerrain) {
        super(nom, adresse, capacite, prixParNuit);
        this.piscine = piscine;
        this.surfaceTerrain = surfaceTerrain;
    }

    public boolean hasPiscine() { return piscine; }
    public int getSurfaceTerrain() { return surfaceTerrain; }

    @Override
    public String getType() {
        return "Villa";
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== VILLA ===");
        System.out.println("Nom: " + nom);
        System.out.println("Adresse: " + adresse);
        System.out.println("Piscine: " + (piscine ? "Oui" : "Non"));
        System.out.println("Terrain: " + surfaceTerrain + " m²");
        System.out.println("Capacité: " + capacite + " personnes");
        System.out.println("Prix: " + prixParNuit + "€/nuit");
        System.out.println("Note: " + String.format("%.1f", getNoteMoyenne()) + "/5");
    }
}
