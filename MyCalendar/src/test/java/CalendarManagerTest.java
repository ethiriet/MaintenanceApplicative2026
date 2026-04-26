import calendar.CalendarManager;
import calendar.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalendarManagerTest {

    @Test
    void unNouveauCalendrierNeContientAucunEvenement() {
        CalendarManager calendar = new CalendarManager();

        assertTrue(calendar.getEvents().isEmpty());
    }

    @Test
    void ajouteUnRendezVousPersonnel() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "RDV_PERSONNEL",
                "Dentiste",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                60,
                "",
                "",
                0
        );

        assertEquals(1, calendar.getEvents().size());
        assertEquals("Dentiste", calendar.getEvents().get(0).title);
    }

    @Test
    void ajouteUneReunion() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "REUNION",
                "Daily",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 9, 0),
                30,
                "Salle A",
                "Pierre, Paul",
                0
        );

        Event event = calendar.getEvents().get(0);

        assertEquals("REUNION", event.type);
        assertEquals("Daily", event.title);
        assertEquals("Salle A", event.lieu);
        assertEquals("Pierre, Paul", event.participants);
    }

    @Test
    void ajouteUnEvenementPeriodique() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "PERIODIQUE",
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 18, 0),
                0,
                "",
                "",
                7
        );

        Event event = calendar.getEvents().get(0);

        assertEquals("PERIODIQUE", event.type);
        assertEquals(7, event.frequenceJours);
    }

    @Test
    void retourneLesEvenementsDansUnePeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "RDV_PERSONNEL",
                "Dans période",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                60,
                "",
                "",
                0
        );

        calendar.ajouterEvent(
                "RDV_PERSONNEL",
                "Hors période",
                "Pierre",
                LocalDateTime.of(2026, 5, 20, 10, 0),
                60,
                "",
                "",
                0
        );

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
        assertEquals("Dans période", result.get(0).title);
    }

    @Test
    void inclutUnEvenementSitueExactementAuDebutDeLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "RDV_PERSONNEL",
                "Début période",
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 0, 0),
                60,
                "",
                "",
                0
        );

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
    }

    @Test
    void inclutUnEvenementSitueExactementALaFinDeLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "RDV_PERSONNEL",
                "Fin période",
                "Pierre",
                LocalDateTime.of(2026, 4, 30, 23, 59),
                60,
                "",
                "",
                0
        );

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
    }

    @Test
    void retourneUnEvenementPeriodiqueSiUneOccurrenceEstDansLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "PERIODIQUE",
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 18, 0),
                0,
                "",
                "",
                7
        );

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 8, 0, 0),
                LocalDateTime.of(2026, 4, 8, 23, 59)
        );

        assertEquals(1, result.size());
        assertEquals("Sport", result.get(0).title);
    }

    @Test
    void neRetournePasUnEvenementPeriodiqueSiAucuneOccurrenceNestDansLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouterEvent(
                "PERIODIQUE",
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 18, 0),
                0,
                "",
                "",
                7
        );

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 9, 0, 0),
                LocalDateTime.of(2026, 4, 9, 23, 59)
        );

        assertTrue(result.isEmpty());
    }

    @Test
    void detecteUnConflitEntreDeuxEvenementsSimplesQuiSeChevauchent() {
        Event e1 = new Event(
                "RDV_PERSONNEL",
                "RDV 1",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                60,
                "",
                "",
                0
        );

        Event e2 = new Event(
                "RDV_PERSONNEL",
                "RDV 2",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                60,
                "",
                "",
                0
        );

        CalendarManager calendar = new CalendarManager();

        assertTrue(calendar.conflit(e1, e2));
    }

    @Test
    void neDetectePasDeConflitEntreDeuxEvenementsSimplesQuiNeSeChevauchentPas() {
        Event e1 = new Event(
                "RDV_PERSONNEL",
                "RDV 1",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                60,
                "",
                "",
                0
        );

        Event e2 = new Event(
                "RDV_PERSONNEL",
                "RDV 2",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 11, 0),
                60,
                "",
                "",
                0
        );

        CalendarManager calendar = new CalendarManager();

        assertFalse(calendar.conflit(e1, e2));
    }

    @Test
    void neDetectePasLesConflitsAvecUnEvenementPeriodiqueComportementActuel() {
        Event e1 = new Event(
                "PERIODIQUE",
                "Sport",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                60,
                "",
                "",
                7
        );

        Event e2 = new Event(
                "RDV_PERSONNEL",
                "RDV",
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                60,
                "",
                "",
                0
        );

        CalendarManager calendar = new CalendarManager();

        assertFalse(calendar.conflit(e1, e2));
    }
}