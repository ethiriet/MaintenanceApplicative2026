package calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CalendarManager {
    private List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }


    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }

    public List<Event> eventsDansPeriode(DateEvenement debut, DateEvenement fin) {
        List<Event> result = new ArrayList<>();

        for (Event e : events) {
            if (e.estDansPeriode(debut, fin)) {
                result.add(e);
            }
        }

        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        return e1.estEnConflitAvec(e2);
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }

    public void supprimer(EventId id) {
        events.removeIf(event -> event.aPourId(id));
    }

    public void ajouter(Event event) {
        events.add(event);
    }
}