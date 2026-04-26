package calendar;

import java.time.LocalDateTime;

public class RendezVousPersonnel extends Event {

    public RendezVousPersonnel(TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(title, proprietaire, dateDebut, duree);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut;
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}