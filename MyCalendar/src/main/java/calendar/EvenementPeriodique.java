package calendar;

public class EvenementPeriodique extends Event {
    public int frequenceJours;

    public EvenementPeriodique(EventId id,
                               TitreEvenement title,
                               String proprietaire,
                               DateEvenement dateDebut,
                               int frequenceJours) {

        super(id, title, proprietaire, dateDebut, new DureeEvenement(0));
        this.frequenceJours = frequenceJours;
    }

    public EvenementPeriodique(TitreEvenement title,
                               String proprietaire,
                               DateEvenement dateDebut,
                               int frequenceJours) {

        this(EventId.nouveau(), title, proprietaire, dateDebut, frequenceJours);
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
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

    @Override
    public boolean estEnConflitAvec(Event autre) {
        return false;
    }
}