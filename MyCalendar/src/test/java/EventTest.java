import calendar.EvenementPeriodique;
import calendar.Event;
import calendar.RendezVousPersonnel;
import calendar.Reunion;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void decritUnRendezVousPersonnel() {
        Event event = new RendezVousPersonnel(
                "Dentiste",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                60
        );

        assertEquals(
                "RDV : Dentiste à 2026-04-20T10:30",
                event.description()
        );
    }

    @Test
    void decritUneReunion() {
        Event event = new Reunion(
                "Daily",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 9, 0),
                30,
                "Salle A",
                "Pierre, Paul"
        );

        assertEquals(
                "Réunion : Daily à Salle A avec Pierre, Paul",
                event.description()
        );
    }

    @Test
    void decritUnEvenementPeriodique() {
        Event event = new EvenementPeriodique(
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 18, 0),
                7
        );

        assertEquals(
                "Événement périodique : Sport tous les 7 jours",
                event.description()
        );
    }
}