import java.util.Scanner;


/*  Game contains all of the information necessary to manage the game, but players choose their moves in their class.
    Game sets up its array of players, asking the user for both players' names and storing two player objects in its
    'players' property.

    Main then calls the playRPS() method (could be done at the end of setPlayers too, but this exercise was simple, so this was not considered).
    playRPS() represents the main game loop. It initializes a log of player victories with length 'numRounds' received as a parameter,
    then moves on into a for loop to play the game. Players choose their moves (randomly), then show their moves.

    compareMoves() compares the outcome of both hands and decides the round winner, storing it in the equivalent round position in the
    winnerOfRounds log.

    When numRounds is reached, countVictories tallies up number of rounds won on both sides and declares the ultimate victor.
 */
public class Game {

    // Array of players in game. Always has two positions in this implementation.
    private Player[] players;

    // Represents log of all rounds played, storing their winners.
    private String[] winnerOfRounds;

    public Game(int numPlayers) {
        this.players = new Player[numPlayers];
    }

    // Gets player names from terminal input.
    public void setPlayers(Scanner input) {

        System.out.println("Choose player 1's name: ");
        players[0] = new Player(input.nextLine());
        System.out.println("Choose player 2's name: ");
        players[1] = new Player(input.nextLine());
    }

    // Main game loop.
    public void playRPS(int numRounds) {

        // Log is initialized with a length of the number of rounds to be played.
        this.winnerOfRounds = new String[numRounds];

        // Until all rounds have been played, each player in 'players' chooses his move, then shows it.
        // The game then compares the given Moves and decides round winner (compareMoves call).
        for (int i = 0; i < numRounds; i++) {

            System.out.println("Round " + (i + 1));

            // Both players choose moves (equivalent of hiding hand behind back and gesturing the correct symbol).
            for (Player player : players) {
                System.out.println(player.getName() + " is choosing his move...");
                player.chooseMove();
            }

            // Both players show moves (equivalent of revealing your hand to the opponent from behind your back, both at the same time).
            for (Player player : players) {
                System.out.println(player.getName() + " chose: " + player.showMove());
            }

            // Stores the result of compareMoves in result (compareMoves decides winning player and returns it).
            String result = compareMoves(players);

            // TIED ROUNDS DO NOT COUNT. If a round is tied, it is repeated.
            if (result.equals("tie")) {
                System.out.println("It's a tie! Playing round again...");
                i--;        // Decrements index to repeat the round inside for loop.
                continue;
            }

            System.out.println(result + " won!");

            winnerOfRounds[i] = result;         // Logs victorious player.
        }
        countVictories(winnerOfRounds, players);            // Compares number of total victories on both sides, by tallying up victories in winnerOfRounds.
    }

    // Handles move comparison to decide which beats which, return the name of the player whose hand won the round.
    public String compareMoves(Player[] players) {

        switch (players[0].showMove()) {

            case ROCK:
                switch (players[1].showMove()) {
                    case ROCK:
                        return "tie";
                    case PAPER:
                        return players[1].getName();
                    case SCISSORS:
                        return players[0].getName();
                }

            case PAPER:
                switch (players[1].showMove()) {
                    case ROCK:
                        return players[0].getName();
                    case PAPER:
                        return "tie";
                    case SCISSORS:
                        return players[1].getName();
                }

            case SCISSORS:
                switch (players[1].showMove()) {
                    case ROCK:
                        return players[1].getName();
                    case PAPER:
                        return players[0].getName();
                    case SCISSORS:
                        return "tie";
                }
        }
        return null;
    }


    // Compares number of total victories on both sides, by tallying up victories in winnerOfRounds.
    public void countVictories(String[] winnerOfRounds, Player[] players) {

        int player1Wins = 0;
        int player2Wins = 0;


        for (int i = 0; i < winnerOfRounds.length; i++) {

            // Log stores the names of players as Strings, so getter and equals() is used to compare them.

            if (winnerOfRounds[i].equals(players[0].getName())) {
                player1Wins++;
                continue;

            }

            if (winnerOfRounds[i].equals(players[1].getName())) {
                player2Wins++;
                continue;
            }
        }

        //  Tallying up is done, decide who won the most times, or if both are tied.
        if (player1Wins < player2Wins) {
            System.out.println(players[1].getName() + " wins the game overall with " + player2Wins + " wins out of " + winnerOfRounds.length + "!");
            return;
        }

        if (player1Wins > player2Wins) {
            System.out.println(players[0].getName() + " wins the game overall with " + player1Wins + " wins out of " + winnerOfRounds.length + "!");

        } else {
            System.out.println("The game is tied, both players had the same number of wins: " + player1Wins + " and " + player2Wins);
        }
    }

}
