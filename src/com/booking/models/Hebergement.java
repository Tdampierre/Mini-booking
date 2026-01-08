package com.booking.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Hebergement {
    protected String id;
    protected String nom;
    protected String adresse;
    protected int capacite;
    protected double prixParNuit;
    protected List<Integer> notes;
    private static int compteur = 0;

    public Hebergement(String nom, String adresse, int capacite, double prixParNuit) {
        this.id = "HEB" + (++compteur);
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.prixParNuit = prixParNuit;
        this.notes = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public int getCapacite() { return capacite; }
    public double getPrixParNuit() { return prixParNuit; }

    public void ajouterNote(int note) {
        if (note >= 1 && note <= 5) {
            notes.add(note);
        }
    }

    public double getNoteMoyenne() {
        if (notes.isEmpty()) return 0;
        double somme = 0;
        for (int n : notes) somme += n;
        return somme / notes.size();
    }

    public abstract String getType();
    public abstract void afficherDetails();
}
