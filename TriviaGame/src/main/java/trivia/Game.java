package trivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static jdk.vm.ci.code.CodeUtil.isOdd;


// TODO refactor me
public class Game implements IGame {
   private final List<Player> players = new ArrayList<>();
   private final QuestionDeck questions = new QuestionDeck();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];
   private int currentPlayerIndex = 0;

   LinkedList popQuestions = new LinkedList();
   LinkedList scienceQuestions = new LinkedList();
   LinkedList sportsQuestions = new LinkedList();
   LinkedList rockQuestions = new LinkedList();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public Game() {
      for (int i = 0; i < 50; i++) {
         popQuestions.addLast("Pop Question " + i);
         scienceQuestions.addLast(("Science Question " + i));
         sportsQuestions.addLast(("Sports Question " + i));
         rockQuestions.addLast(createRockQuestion(i));
      }
   }

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   @Override
   public boolean add(String playerName) {
      if (players.size() >= 4) {
         throw new IllegalStateException("Maximum number of players is " + 4);
      }

      players.add(new Player(playerName));

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player currentPlayer = currentPlayer();

      System.out.println(currentPlayer.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer.isInPenaltyBox()) {
         handleRollInPenaltyBox(currentPlayer, roll);
         return;
      }

      movePlayerAndAskQuestion(currentPlayer, roll);

   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   private void handleRollInPenaltyBox(Player player, int roll) {
      if (isOdd(roll)) {
         isGettingOutOfPenaltyBox = true;
         System.out.println(player.getName() + " is getting out of the penalty box");
         movePlayerAndAskQuestion(player, roll);
      } else {
         isGettingOutOfPenaltyBox = false;
         System.out.println(player.getName() + " is not getting out of the penalty box");
      }
   }

   private void movePlayerAndAskQuestion(Player player, int roll) {
      player.move(roll);

      System.out.println(player.getName() + "'s new location is " + player.getPlace());
      System.out.println("The category is " + currentCategory());

      askQuestion();
   }


   private void askQuestion() {
      System.out.println(questions.nextQuestionFor(currentCategory()));
   }

   private QuestionDeck.Category currentCategory() {
      int place = currentPlayer().getPlace() - 1;

      switch (place % 4) {
         case 0:
            return Category.POP;
         case 1:
            return Category.SCIENCE;
         case 2:
            return Category.SPORTS;
         default:
            return Category.ROCK;
      }
   }

   public boolean handleCorrectAnswer() {
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }

      } else {

         System.out.println("Answer was correct!!!!");
         purses[currentPlayer]++;
         System.out.println(players.get(currentPlayer)
                 + " now has "
                 + purses[currentPlayer]
                 + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }


}
