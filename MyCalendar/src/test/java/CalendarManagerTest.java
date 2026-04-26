import calendar.CalendarManager;
import calendar.DureeEvenement;
import calendar.Event;
import calendar.EvenementPeriodique;
import calendar.Lieu;
import calendar.RendezVousPersonnel;
import calendar.Reunion;
import calendar.TitreEvenement;
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
    void ajouteUnEvenementDirectement() {
        CalendarManager calendar = new CalendarManager();

        Event event = new RendezVousPersonnel(
                new TitreEvenement("Dentiste"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                new DureeEvenement(60)
        );

        calendar.ajouter(event);

        assertEquals(1, calendar.getEvents().size());
        assertSame(event, calendar.getEvents().get(0));
    }

    @Test
    void ajouteUnRendezVousPersonnel() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new RendezVousPersonnel(
                new TitreEvenement("Dentiste"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                new DureeEvenement(60)
        ));

        Event event = calendar.getEvents().get(0);

        assertEquals(1, calendar.getEvents().size());
        assertInstanceOf(RendezVousPersonnel.class, event);
        assertEquals("Dentiste", event.title.valeur());
    }

    @Test
    void ajouteUneReunion() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new Reunion(
                new TitreEvenement("Daily"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 9, 0),
                new DureeEvenement(30),
                new Lieu("Salle A"),
                "Pierre, Paul"
        ));

        Event event = calendar.getEvents().get(0);

        assertInstanceOf(Reunion.class, event);

        Reunion reunion = (Reunion) event;

        assertEquals("Daily", reunion.title.valeur());
        assertEquals("Salle A", reunion.lieu.toString());
        assertEquals("Pierre, Paul", reunion.participants);
    }

    @Test
    void ajouteUnEvenementPeriodique() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new EvenementPeriodique(
                new TitreEvenement("Sport"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 18, 0),
                7
        ));

        Event event = calendar.getEvents().get(0);

        assertInstanceOf(EvenementPeriodique.class, event);

        EvenementPeriodique evenementPeriodique = (EvenementPeriodique) event;

        assertEquals("Sport", evenementPeriodique.title.valeur());
        assertEquals(7, evenementPeriodique.frequenceJours);
    }

    @Test
    void retourneLesEvenementsDansUnePeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new RendezVousPersonnel(
                new TitreEvenement("Dans période"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                new DureeEvenement(60)
        ));

        calendar.ajouter(new RendezVousPersonnel(
                new TitreEvenement("Hors période"),
                "Pierre",
                LocalDateTime.of(2026, 5, 20, 10, 0),
                new DureeEvenement(60)
        ));

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
        assertEquals("Dans période", result.get(0).title.valeur());
    }

    @Test
    void inclutUnEvenementSitueExactementAuDebutDeLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new RendezVousPersonnel(
                new TitreEvenement("Début période"),
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 0, 0),
                new DureeEvenement(60)
        ));

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
    }

    @Test
    void inclutUnEvenementSitueExactementALaFinDeLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new RendezVousPersonnel(
                new TitreEvenement("Fin période"),
                "Pierre",
                LocalDateTime.of(2026, 4, 30, 23, 59),
                new DureeEvenement(60)
        ));

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 1, 0, 0),
                LocalDateTime.of(2026, 4, 30, 23, 59)
        );

        assertEquals(1, result.size());
    }

    @Test
    void retourneUnEvenementPeriodiqueSiUneOccurrenceEstDansLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new EvenementPeriodique(
                new TitreEvenement("Sport"),
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 18, 0),
                7
        ));

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 8, 0, 0),
                LocalDateTime.of(2026, 4, 8, 23, 59)
        );

        assertEquals(1, result.size());
        assertEquals("Sport", result.get(0).title.valeur());
    }

    @Test
    void neRetournePasUnEvenementPeriodiqueSiAucuneOccurrenceNestDansLaPeriode() {
        CalendarManager calendar = new CalendarManager();

        calendar.ajouter(new EvenementPeriodique(
                new TitreEvenement("Sport"),
                "Pierre",
                LocalDateTime.of(2026, 4, 1, 18, 0),
                7
        ));

        List<Event> result = calendar.eventsDansPeriode(
                LocalDateTime.of(2026, 4, 9, 0, 0),
                LocalDateTime.of(2026, 4, 9, 23, 59)
        );

        assertTrue(result.isEmpty());
    }

    @Test
    void detecteUnConflitEntreDeuxEvenementsSimplesQuiSeChevauchent() {
        Event e1 = new RendezVousPersonnel(
                new TitreEvenement("RDV 1"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                new DureeEvenement(60)
        );

        Event e2 = new RendezVousPersonnel(
                new TitreEvenement("RDV 2"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                new DureeEvenement(60)
        );

        CalendarManager calendar = new CalendarManager();

        assertTrue(calendar.conflit(e1, e2));
    }

    @Test
    void neDetectePasDeConflitEntreDeuxEvenementsSimplesQuiNeSeChevauchentPas() {
        Event e1 = new RendezVousPersonnel(
                new TitreEvenement("RDV 1"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                new DureeEvenement(60)
        );

        Event e2 = new RendezVousPersonnel(
                new TitreEvenement("RDV 2"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 11, 0),
                new DureeEvenement(60)
        );

        CalendarManager calendar = new CalendarManager();

        assertFalse(calendar.conflit(e1, e2));
    }

    @Test
    void neDetectePasLesConflitsAvecUnEvenementPeriodiqueComportementActuel() {
        Event e1 = new EvenementPeriodique(
                new TitreEvenement("Sport"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 0),
                7
        );

        Event e2 = new RendezVousPersonnel(
                new TitreEvenement("RDV"),
                "Pierre",
                LocalDateTime.of(2026, 4, 20, 10, 30),
                new DureeEvenement(60)
        );

        CalendarManager calendar = new CalendarManager();

        assertFalse(calendar.conflit(e1, e2));
    }
}