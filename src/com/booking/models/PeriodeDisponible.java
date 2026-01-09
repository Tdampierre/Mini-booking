package com.booking.models;

import java.util.Date;

public class PeriodeDisponible {
    private Date dateDebut;
    private Date dateFin;

    public PeriodeDisponible(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public boolean chevauche(Date debut, Date fin) {
        return !(fin.before(dateDebut) || debut.after(dateFin));
    }

    public Date getDateDebut() { return dateDebut; }
    public Date getDateFin() { return dateFin; }
}
