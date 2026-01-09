package com.booking.models;

public abstract class Personne {
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;

    public Personne(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }

    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }

    public abstract void afficherRole();
}
