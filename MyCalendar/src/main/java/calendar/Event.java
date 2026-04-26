package calendar;

import java.time.LocalDateTime;

public abstract class Event {
    public String title;
    public String proprietaire;
    public LocalDateTime dateDebut;
    public int dureeMinutes;

    protected Event(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract String description();

    public boolean estPeriodique() {
        return false;
    }

    public int frequenceJours() {
        return 0;
    }
}