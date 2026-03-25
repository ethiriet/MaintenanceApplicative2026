import calendar.CalendarManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarManagerTest {

    @Test
    void shouldCreateCalendarManager() {
        CalendarManager calendarManager = new CalendarManager();
        assertTrue(calendarManager.events.isEmpty());
    }
}