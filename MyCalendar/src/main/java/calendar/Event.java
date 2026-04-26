package calendar;

import java.time.LocalDateTime;

public abstract class Event {
    public TitreEvenement title;
    public String proprietaire;
    public LocalDateTime dateDebut;
    public DureeEvenement duree;

    protected Event(TitreEvenement title, String proprietaire, LocalDateTime dateDebut, DureeEvenement duree) {
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
}