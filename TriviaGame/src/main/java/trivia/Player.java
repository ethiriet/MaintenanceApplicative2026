package trivia;

public class Player {
    private final String name;
    private int place = 1;
    private int purse = 0;
    private boolean inPenaltyBox = false;

    Player(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    int getPlace() {
        return place;
    }

    int getPurse() {
        return purse;
    }

    boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    void move(int roll) {
        place += roll;
        if (place > 12) {
            place -= 12;
        }
    }

    void addCoin() {
        purse++;
    }

    void sendToPenaltyBox() {
        inPenaltyBox = true;
    }
}

