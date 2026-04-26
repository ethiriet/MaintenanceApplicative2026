package calendar;

public class Reunion extends Event {
    public Lieu lieu;
    public String participants;

    public Reunion(EventId id, TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree, Lieu lieu, String participants) {
        super(id, title, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    public Reunion(TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree, Lieu lieu, String participants) {
        this(EventId.nouveau(), title, proprietaire, dateDebut, duree, lieu, participants);
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }

    @Override
    public boolean estDansPeriode(DateEvenement debut, DateEvenement fin) {
        return !dateDebut.isBefore(debut) && !dateDebut.isAfter(fin);
    }
}