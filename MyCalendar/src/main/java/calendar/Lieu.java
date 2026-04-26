package calendar;

public class Lieu {
    private final String valeur;

    public Lieu(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return valeur;
    }
}