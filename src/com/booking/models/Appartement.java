package com.booking.models;

public class Appartement extends Hebergement {
    private int nombrePieces;

    public Appartement(String nom, String adresse, int capacite, double prixParNuit, int nombrePieces) {
        super(nom, adresse, capacite, prixParNuit);
        this.nombrePieces = nombrePieces;
    }

    public int getNombrePieces() { return nombrePieces; }

    @Override
    public String getType() {
        return "Appartement";
    }

    @Override
    public void afficherDetails() {
        System.out.println("=== APPARTEMENT ===");
        System.out.println("Nom: " + nom);
        System.out.println("Adresse: " + adresse);
        System.out.println("Pièces: " + nombrePieces);
        System.out.println("Capacité: " + capacite + " personnes");
        System.out.println("Prix: " + prixParNuit + "€/nuit");
        System.out.println("Note: " + String.format("%.1f", getNoteMoyenne()) + "/5");
    }
}
