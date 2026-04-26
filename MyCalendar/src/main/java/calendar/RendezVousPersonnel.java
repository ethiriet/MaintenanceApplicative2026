package calendar;

import java.time.LocalDateTime;

public class RendezVousPersonnel extends Event {

    public RendezVousPersonnel(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        super(title, proprietaire, dateDebut, dureeMinutes);
    }

    @Override
    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}