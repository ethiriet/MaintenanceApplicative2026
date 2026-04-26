package calendar;

import java.time.LocalDateTime;

public class DateEvenement {
    private final LocalDateTime valeur;

    public DateEvenement(LocalDateTime valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime valeur() {
        return valeur;
    }

    public DateEvenement plusMinutes(int minutes) {
        return new DateEvenement(valeur.plusMinutes(minutes));
    }

    public DateEvenement plusDays(int jours) {
        return new DateEvenement(valeur.plusDays(jours));
    }

    public boolean isBefore(DateEvenement autre) {
        return valeur.isBefore(autre.valeur);
    }

    public boolean isAfter(DateEvenement autre) {
        return valeur.isAfter(autre.valeur);
    }

    @Override
    public String toString() {
        return valeur.toString();
    }
}