package calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(String type, String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes,
                             String lieu, String participants, int frequenceJours) {
        Event e;

        switch (type) {
            case "RDV_PERSONNEL":
                e = new RendezVousPersonnel(title, proprietaire, dateDebut, dureeMinutes);
                break;
            case "REUNION":
                e = new Reunion(title, proprietaire, dateDebut, dureeMinutes, lieu, participants);
                break;
            case "PERIODIQUE":
                e = new EvenementPeriodique(title, proprietaire, dateDebut, frequenceJours);
                break;
            default:
                throw new IllegalArgumentException("Type d'événement inconnu : " + type);
        }

        ajouter(e);
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();
        for (Event e : events) {
            if (e.estPeriodique()) {
                LocalDateTime temp = e.dateDebut;
                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    temp = temp.plusDays(e.frequenceJours());
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes);
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes);

        if (e1.estPeriodique() || e2.estPeriodique()) {
            return false; // Simplification abusive
        }

        if (e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut)) {
            return true;
        }
        return false;
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public void ajouter(Event event) {
        events.add(event);
    }
}