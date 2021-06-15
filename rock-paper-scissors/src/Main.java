import java.util.Scanner;


/*  Implementation of a self-played rock-paper-scissors game, in which 2 players play RPS for a chosen number of rounds
    then tally up who won the most rounds to decide the victor.
 */
public class Main {

    public static void main(String[] args) {

        // Scanner is used to chose number of rounds, and to give names to players.
        Scanner input = new Scanner(System.in);

        // Always 2, does not support any other number of players.
        int numPlayers = 2;

        System.out.println("Choose number of rounds (int):");
        int numRounds = Integer.parseInt(input.nextLine());

        Game game = new Game(numPlayers);
        game.setPlayers(input);
        game.playRPS(numRounds);
    }
}
