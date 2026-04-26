package calendar;

import java.time.LocalDateTime;

public class Reunion extends Event {
    public Lieu lieu;
    public String participants;

    public Reunion(TitreEvenement title, String proprietaire, DateEvenement dateDebut, DureeEvenement duree, Lieu lieu, String participants) {
        super(title, proprietaire, dateDebut, duree);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}