import java.util.Scanner;

public class Start {

    // Simple game that asks for a range of numbers, from 0 to max, and a number of players, then iterates through
    // players asking for their guess. Games continues, looping through players until one of them guesses the right number.
    public static void main(String[] args) {


        Game game = new Game();
        while (game.play()) {
            game = new Game();
        }
    }
}
