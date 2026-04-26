package calendar;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Event {
    public TitreEvenement title;
    public String proprietaire;
    public DateEvenement dateDebut;
    public DureeEvenement duree;

    protected Event(TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.duree = duree;
    }

    public abstract String description();

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
}