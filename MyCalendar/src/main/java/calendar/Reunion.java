package calendar;

import java.time.LocalDateTime;

public class Reunion extends Event {
    public String lieu;
    public String participants;

    public Reunion(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = lieu;
        this.participants = participants;
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}