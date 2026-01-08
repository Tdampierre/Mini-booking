package com.booking.models;

public class Hotel extends Hebergement {
    private int etoiles;

    public Hotel(String nom, String adresse, int capacite, double prixParNuit, int etoiles) {
        super(nom, adresse, capacite, prixParNuit);
        this.etoiles = etoiles;
    }

    public int getEtoiles() { return etoiles; }

    @Override
    public String getType() {
        return "Hotel";
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== HOTEL ===");
        System.out.println("Nom: " + nom);
        System.out.println("Adresse: " + adresse);
        System.out.println("Etoiles: " + etoiles + " ★");
        System.out.println("Capacité: " + capacite + " personnes");
        System.out.println("Prix: " + prixParNuit + "€/nuit");
        System.out.println("Note: " + String.format("%.1f", getNoteMoyenne()) + "/5");
    }
}
