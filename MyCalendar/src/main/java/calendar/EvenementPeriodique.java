package calendar;

import java.time.LocalDateTime;

public class EvenementPeriodique extends Event {
    public int frequenceJours;

    public EvenementPeriodique(TitreEvenement title,
                               String proprietaire,
                               DateEvenement dateDebut,
                               int frequenceJours) {

        super(title, proprietaire, dateDebut, new DureeEvenement(0));
        this.frequenceJours = frequenceJours;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
    }

    @Override
    public boolean estPeriodique() {
        return true;
    }

    @Override
    public int frequenceJours() {
        return frequenceJours;
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        DateEvenement temp = dateDebut;

        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(frequenceJours);
        }

        return false;
    }
}