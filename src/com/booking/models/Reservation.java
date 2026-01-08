package com.booking.models;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {
    private String id;
    private Client client;
    private Hebergement hebergement;
    private Date dateDebut;
    private Date dateFin;
    private double prixTotal;
    private double reduction;
    private static int compteur = 0;

    public Reservation(Client client, Hebergement hebergement, Date dateDebut, Date dateFin) {
        this.id = "RES" + (++compteur);
        this.client = client;
        this.hebergement = hebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.reduction = 0;
        calculerPrix();
    }

    private void calculerPrix() {
        long diff = dateFin.getTime() - dateDebut.getTime();
        int nuits = (int) (diff / (1000 * 60 * 60 * 24));
        this.prixTotal = nuits * hebergement.getPrixParNuit();
    }

    public void appliquerReduction(double pourcentage) {
        this.reduction = pourcentage;
        this.prixTotal = prixTotal * (1 - pourcentage);
    }

    public String getId() { return id; }
    public Client getClient() { return client; }
    public Hebergement getHebergement() { return hebergement; }
    public Date getDateDebut() { return dateDebut; }
    public Date getDateFin() { return dateFin; }
    public double getPrixTotal() { return prixTotal; }
    public double getReduction() { return reduction; }

    public int getNombreNuits() {
        long diff = dateFin.getTime() - dateDebut.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public void afficherFacture() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║           FACTURE                  ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ Réservation: " + id);
        System.out.println("║ Client: " + client.getNomComplet());
        System.out.println("║ Email: " + client.getEmail());
        System.out.println("║ Hébergement: " + hebergement.getNom());
        System.out.println("║ Du: " + sdf.format(dateDebut));
        System.out.println("║ Au: " + sdf.format(dateFin));
        System.out.println("║ Nuits: " + getNombreNuits());
        System.out.println("║ Prix/nuit: " + hebergement.getPrixParNuit() + "€");
        if (reduction > 0) {
            System.out.println("║ Réduction: " + (int)(reduction*100) + "%");
        }
        System.out.println("║ TOTAL: " + String.format("%.2f", prixTotal) + "€");
        System.out.println("╚════════════════════════════════════╝");
    }
}
