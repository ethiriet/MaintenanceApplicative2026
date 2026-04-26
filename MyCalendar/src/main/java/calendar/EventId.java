package calendar;

import java.util.Objects;
import java.util.UUID;

public class EventId {
    private final String valeur;

    public EventId(String valeur) {
        this.valeur = Objects.requireNonNull(valeur);
    }

    public static EventId nouveau() {
        return new EventId(UUID.randomUUID().toString());
    }

    public String valeur() {
        return valeur;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventId autre)) {
            return false;
        }
        return valeur.equals(autre.valeur);
    }

    @Override
    public int hashCode() {
        return valeur.hashCode();
    }

    @Override
    public String toString() {
        return valeur;
    }
}