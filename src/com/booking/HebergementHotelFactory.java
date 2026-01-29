package com.booking;

import com.booking.models.Hotel;
import com.booking.models.Hebergement;

/**
 * Petite fabrique pour centraliser la création d'un hôtel simple
 * (évite de dupliquer le code et garde AdminInterface plus lisible).
 */
public class HebergementHotelFactory {

    public Hebergement creerHotelSimple(String nom, String adresse, int capacite, double prix) {
        return new Hotel(
                nom,
                adresse,
                capacite,
                prix,
                "Hôtel ajouté par l'administrateur",
                3
        );
    }
}

