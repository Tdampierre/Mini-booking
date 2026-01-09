package com.booking.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Client extends Personne {
    protected List<Reservation> reservations;

    public Client(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse);
        this.reservations = new ArrayList<>();
    }

    public abstract double getReduction();

    public Reservation reserver(Hebergement hebergement, Date debut, Date fin, int nombrePersonnes) {
        if (!hebergement.estDisponible(debut, fin)) {
            System.out.println("❌ Hébergement non disponible pour ces dates");
            return null;
        }

        if (nombrePersonnes > hebergement.getCapacite()) {
            System.out.println("❌ Nombre de personnes supérieur à la capacité");
            return null;
        }

        Reservation reservation = new Reservation(hebergement, this, debut, fin, nombrePersonnes);
        reservations.add(reservation);
        System.out.println("✅ Réservation créée avec succès !");
        return reservation;
    }

    public void annulerReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            System.out.println("✅ Réservation annulée");
        } else {
            System.out.println("❌ Réservation introuvable");
        }
    }

    public void afficherHistorique() {
        if (reservations.isEmpty()) {
            System.out.println("📭 Aucune réservation");
            return;
        }

        System.out.println("\n📜 Historique des réservations de " + prenom + " " + nom);
        System.out.println("━".repeat(60));
        for (Reservation r : reservations) {
            r.afficherDetails();
        }
    }

    public List<Reservation> getReservations() { return reservations; }

    @Override
    public void afficherRole() {
        System.out.println("👤 Rôle: Client");
    }
}
