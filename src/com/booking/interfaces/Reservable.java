package com.booking.interfaces;

import java.util.Date;

public interface Reservable {
    boolean estDisponible(Date dateDebut, Date dateFin);
    double calculerPrix(Date dateDebut, Date dateFin, int nombrePersonnes);
}
