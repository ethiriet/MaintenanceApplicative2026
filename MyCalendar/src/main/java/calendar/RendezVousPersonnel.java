package calendar;

import java.time.LocalDateTime;

public class RendezVousPersonnel extends Event {

    public RendezVousPersonnel(EventId id, TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        super(id,title, proprietaire, dateDebut, duree);
    }

    public RendezVousPersonnel(TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this(EventId.nouveau(), title, proprietaire, dateDebut, duree);
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