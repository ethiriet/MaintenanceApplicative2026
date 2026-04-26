
import calendar.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void decritUnRendezVousPersonnel() {
        Event event = new Event(
                "RDV_PERSONNEL",
                "Dentiste",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                60,
                "",
                "",
                0
        );

        assertEquals(
                "RDV : Dentiste à 2026-04-20T10:30",
                event.description()
        );
    }

    @Test
    void decritUneReunion() {
        Event event = new Event(
                "REUNION",
                "Daily",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 9, 0),
                30,
                "Salle A",
                "Pierre, Paul",
                0
        );

        assertEquals(
                "Réunion : Daily à Salle A avec Pierre, Paul",
                event.description()
        );
    }

    @Test
    void decritUnEvenementPeriodique() {
        Event event = new Event(
                "PERIODIQUE",
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 18, 0),
                0,
                "",
                "",
                7
        );

        assertEquals(
                "Événement périodique : Sport tous les 7 jours",
                event.description()
        );
    }

    @Test
    void retourneUneDescriptionVidePourUnTypeInconnu() {
        Event event = new Event(
                "INCONNU",
                "Titre",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 18, 0),
                30,
                "",
                "",
                0
        );

        assertEquals("", event.description());
    }
}