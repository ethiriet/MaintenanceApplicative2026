package calendar;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Event {
    public final EventId id;
    public TitreEvenement title;
    public String proprietaire;
    public DateEvenement dateDebut;
    public DureeEvenement duree;

    protected Event(EventId id, TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.id = id;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public abstract String description();
    public boolean aPourId(EventId id) {
        return this.id.equals(id);
    }
    public boolean estPeriodique() {
        return false;
    }

    public int frequenceJours() {
        return 0;
    }

    static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }

    public abstract boolean estDansPeriode(DateEvenement debut, DateEvenement fin);

    public boolean estEnConflitAvec(Event autre) {
        DateEvenement fin = dateDebut.plusMinutes(duree.enMinutes());
        DateEvenement finAutre = autre.dateDebut.plusMinutes(autre.duree.enMinutes());

        return dateDebut.isBefore(finAutre) && fin.isAfter(autre.dateDebut);
    }
}