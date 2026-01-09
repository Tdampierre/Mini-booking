package com.booking.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private static int compteurId = 1;
    
    private int id;
    private Hebergement hebergement;
    private Client client;
    private Date dateDebut;
    private Date dateFin;
    private int nombrePersonnes;
    private double prixTotal;

    public Reservation(Hebergement hebergement, Client client, Date dateDebut, Date dateFin, int nombrePersonnes) {
        this.id = compteurId++;
        this.hebergement = hebergement;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePersonnes = nombrePersonnes;
        this.prixTotal = calculerPrixTotal();
    }

    private double calculerPrixTotal() {
        double prix = hebergement.calculerPrix(dateDebut, dateFin, nombrePersonnes);
        double reduction = client.getReduction();
        return prix * (1 - reduction);
    }

    public void afficherDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📄 Réservation #" + id);
        System.out.println("👤 Client : " + client.getPrenom() + " " + client.getNom());
        System.out.println("🏠 Hébergement : " + hebergement.getNom());
        System.out.println("📅 Du " + sdf.format(dateDebut) + " au " + sdf.format(dateFin));
        System.out.println("👥 Nombre de personnes : " + nombrePersonnes);
        System.out.printf("💰 Prix total : %.2f€%n", prixTotal);
        if (client.getReduction() > 0) {
            System.out.printf("   (Réduction de %.0f%% appliquée)%n", client.getReduction() * 100);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    public Hebergement getHebergement() { return hebergement; }
    public Date getDateDebut() { return dateDebut; }
    public Date getDateFin() { return dateFin; }
}
