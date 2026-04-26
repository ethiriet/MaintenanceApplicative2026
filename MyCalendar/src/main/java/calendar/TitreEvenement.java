package calendar;

import java.util.Objects;

public class TitreEvenement {
    private final String valeur;

    public TitreEvenement(String valeur) {
        this.valeur = Objects.requireNonNull(valeur);
    }

    public String valeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}