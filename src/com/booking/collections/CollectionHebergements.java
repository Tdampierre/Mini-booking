package com.booking.collections;

import com.booking.models.Hebergement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionHebergements {
    private List<Hebergement> hebergements;

    public CollectionHebergements() {
        this.hebergements = new ArrayList<>();
    }

    public void ajouter(Hebergement hebergement) {
        hebergements.add(hebergement);
    }

    public boolean supprimer(int id) {
        return hebergements.removeIf(h -> h.getId() == id);
    }

    public Hebergement rechercherParId(int id) {
        return hebergements.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Hebergement> rechercherParVille(String ville) {
        return hebergements.stream()
                .filter(h -> h.getAdresse().toLowerCase().contains(ville.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Hebergement> rechercherParPrixMax(double prixMax) {
        return hebergements.stream()
                .filter(h -> h.getPrixParNuit() <= prixMax)
                .collect(Collectors.toList());
    }

    public List<Hebergement> rechercherParCapacite(int capaciteMin) {
        return hebergements.stream()
                .filter(h -> h.getCapacite() >= capaciteMin)
                .collect(Collectors.toList());
    }

    public void afficherTous() {
        if (hebergements.isEmpty()) {
            System.out.println("📭 Aucun hébergement disponible");
            return;
        }

        System.out.println("\n🏠 Liste des hébergements (" + hebergements.size() + ")");
        System.out.println("═".repeat(60));
        for (Hebergement h : hebergements) {
            h.afficherDetails();
            System.out.println();
        }
    }

    public List<Hebergement> getTous() {
        return new ArrayList<>(hebergements);
    }
}
