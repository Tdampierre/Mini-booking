package com.booking.models;

import java.util.ArrayList;
import java.util.List;

public class Client extends Personne {
    private String telephone;
    private List<Reservation> historique;

    public Client(String nom, String prenom, String email, String telephone) {
        super(nom, prenom, email);
        this.telephone = telephone;
        this.historique = new ArrayList<>();
    }

    public String getTelephone() { return telephone; }
    
    public int getNombreReservations() {
        return historique.size();
    }

    public void ajouterReservation(Reservation r) {
        historique.add(r);
    }

    public List<Reservation> getHistorique() {
        return historique;
    }
}
