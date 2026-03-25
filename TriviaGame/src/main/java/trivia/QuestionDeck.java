package trivia;

import java.util.LinkedList;
import java.util.Queue;

public class QuestionDeck {
    private final Queue<String> popQuestions = new LinkedList<>();
    private final Queue<String> scienceQuestions = new LinkedList<>();
    private final Queue<String> sportsQuestions = new LinkedList<>();
    private final Queue<String> rockQuestions = new LinkedList<>();

    QuestionDeck() {
        for (int i = 0; i < 50; i++) {
            popQuestions.add("Pop Question " + i);
            scienceQuestions.add("Science Question " + i);
            sportsQuestions.add("Sports Question " + i);
            rockQuestions.add("Rock Question " + i);
        }
    }

    String nextQuestionFor(Category category) {
        switch (category) {
            case POP:
                return popQuestions.remove();
            case SCIENCE:
                return scienceQuestions.remove();
            case SPORTS:
                return sportsQuestions.remove();
            case ROCK:
                return rockQuestions.remove();
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }
    }

    enum Category {
        POP("Pop"),
        SCIENCE("Science"),
        SPORTS("Sports"),
        ROCK("Rock");

        private final String label;

        Category(String label) {
            this.label = label;
        }

        String label() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }
}

