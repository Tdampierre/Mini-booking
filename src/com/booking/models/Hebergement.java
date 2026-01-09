package com.booking.models;

import com.booking.interfaces.Reservable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Hebergement implements Reservable {
    private static int compteurId = 1;
    
    protected int id;
    protected String nom;
    protected String adresse;
    protected int capacite;
    protected double prixParNuit;
    protected String description;
    protected List<Double> notes;
    protected List<PeriodeDisponible> periodesDisponibles;

    public Hebergement(String nom, String adresse, int capacite, double prixParNuit, String description) {
        this.id = compteurId++;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.prixParNuit = prixParNuit;
        this.description = description;
        this.notes = new ArrayList<>();
        this.periodesDisponibles = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public int getCapacite() { return capacite; }
    public double getPrixParNuit() { return prixParNuit; }
    public String getDescription() { return description; }
    public List<Double> getNotes() { return notes; }

    public void setPrixParNuit(double prixParNuit) { this.prixParNuit = prixParNuit; }

    public void ajouterNote(double note) {
        if (note >= 0 && note <= 5) {
            notes.add(note);
        }
    }

    public double calculerMoyenneNotes() {
        if (notes.isEmpty()) return 0.0;
        return notes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    public void ajouterPeriodeDisponible(Date debut, Date fin) {
        periodesDisponibles.add(new PeriodeDisponible(debut, fin));
    }

    @Override
    public boolean estDisponible(Date debut, Date fin) {
        for (PeriodeDisponible periode : periodesDisponibles) {
            if (periode.chevauche(debut, fin)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double calculerPrix(Date debut, Date fin, int nombrePersonnes) {
        long diff = fin.getTime() - debut.getTime();
        int nuits = (int) (diff / (1000 * 60 * 60 * 24));
        return nuits * prixParNuit;
    }

    public abstract void afficherDetails();
}
