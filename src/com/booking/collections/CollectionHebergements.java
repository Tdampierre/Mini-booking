package com.booking.collections;

import com.booking.models.Hebergement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionHebergements {
    private List<Hebergement> hebergements;

    public CollectionHebergements() {
        this.hebergements = new ArrayList<>();
    }

    public void ajouter(Hebergement h) {
        hebergements.add(h);
    }

    public void supprimer(Hebergement h) {
        hebergements.remove(h);
    }

    public List<Hebergement> getHebergements() {
        return hebergements;
    }

    public List<Hebergement> rechercherParType(String type) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getType().equalsIgnoreCase(type)) {
                resultats.add(h);
            }
        }
        return resultats;
    }

    public List<Hebergement> rechercherParPrixMax(double prixMax) {
        List<Hebergement> resultats = new ArrayList<>();
        for (Hebergement h : hebergements) {
            if (h.getPrixParNuit() <= prixMax) {
                resultats.add(h);
            }
        }
        return resultats;
    }

    public void trierParPrix() {
        Collections.sort(hebergements, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                return Double.compare(h1.getPrixParNuit(), h2.getPrixParNuit());
            }
        });
    }

    public void trierParNote() {
        Collections.sort(hebergements, new Comparator<Hebergement>() {
            @Override
            public int compare(Hebergement h1, Hebergement h2) {
                return Double.compare(h2.getNoteMoyenne(), h1.getNoteMoyenne());
            }
        });
    }

    public void afficherTous() {
        for (Hebergement h : hebergements) {
            h.afficherDetails();
            System.out.println();
        }
    }
}
