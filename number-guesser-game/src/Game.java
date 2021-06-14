import java.util.Scanner;

public class Game {

    // Uses a console scanner for player input.
    private Scanner scanner;

    // Logs previous numbers chosen to invalidate repetitions in future guesses.
    private int[] log;
    private int logIndex;

    // Stores the range chosen through the game's constructor call;
    private int range;

    // Stores the randomly chosen number between 0 and range generated in the game's constructor call;
    private int winNum;

    // Stores the game's players. Player is an inner class of Game (see below).
    private Player[] players;


    // Constructor
    public Game() {

        // Provides scanner for user use.
        this.scanner = new Scanner(System.in);

        // Prompts and waits for the user to provide the maximum bound of the game's range. Minimum is always 0.
        System.out.println("Choose a range: (Format = (int)'range')");
        this.range = Integer.parseInt(scanner.nextLine());

        // Creates an array of length 'range', because since we do not allow repetitions, we can be sure that worst case
        // the game will take 'range' turns. No array out of bounds occurs.
        this.log = new int[this.range];
        this.logIndex = 0;

        // Prompts and waits for the user to provide a number of players that will play the game.
        System.out.println("Choose a number of players: (Format = (int)'numPlayers')");
        this.players = playerFiller(Integer.parseInt(scanner.nextLine()));

        // Generates a random winning number to guess between 0 and 'range'.
        this.winNum = (int) Math.ceil(Math.random() * this.range);
    }

    // Method called to start the game after game instantiation. Loops through the players' array, asking for guesses,
    // until of them provides the winning number. Breaks out of remaining players' array iteration, raises 'win' flag to true
    // and goes to playAgain() to ask player if he wants to play once more or not.
    public boolean play() {

        boolean win = false;

        while (!win) {

            for (Player player : this.players) {

                System.out.println(player.getName() + " 's turn to chose a number! Between 1 and " + this.range + ".");

                int guess = player.guess();

                if (guess == winNum) {

                    // Prints player's name if he guesses the right number.
                    System.out.println("Congratulations! " + player.getName() + " won!");
                    // Raises 'win' flag
                    win = true;
                    // Exits for loop, no need to continue iterating through players asking for guesses: game is won.
                    break;

                }

                // If player failed to win this turn, logs his guess, increments the logs' index and loops to next player.
                log[logIndex] = guess;
                logIndex++;
            }
        }
        return playAgain();
    }


    // Asks player if he wants to play again, returns 'true' if he answers 'y', false if anything else is provided.
    // Start class (main caller) checks for this method's return to reinstatiate a game and call play again on this new game.
    public boolean playAgain() {

        System.out.println("Play again? ('y' to play again / any other key to exit)");

        if (scanner.nextLine().equals("y")) {

            System.out.println("Starting over...");

        } else {

            System.out.println("Goodbye.");
            return false;

        }

        return true;
    }


    // Used by constructor to get player names and fill players array with the requested number of players.
    public Player[] playerFiller(int numPlayers) {

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {

            System.out.println("Input player " + (i + 1) + "'s name:");
            players[i] = new Player(scanner.nextLine());

        }

        return players;
    }

    // Player class serves no purpose in this exercise outside of the Game class, and is simple (one property, one method besides constructor)
    // Seems apt to include it as an inner class. Allows us to fetch the scanner and log without passing them as arguments to guess.
    public class Player {

        private String name;

        // Player's name, provided by input from the player during game constructor call, through playerFiller().
        public Player(String name) {
            this.name = name;
        }


        // Raises incorrectGuess, initializes guess, then loops asking for a new guess through scanner input until it is lowered (meaning guess is valid).
        // A valid guess is one not already present in the game's log, or within the game's upper and lower bounds ('range' and 0).
        // Returns guess for game.play() to handle.
        public int guess() {

            boolean incorrectGuess = true;
            int guess = 0;

            while (incorrectGuess) {

                guess = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < log.length; i++) {

                    if (log[i] == guess) {

                        System.out.println("Number was previously picked, choose again.");
                        break;

                    }

                    if (guess < 1 || guess > range) {

                        System.out.println("Number not within range, choose again");
                        break;

                    }

                    incorrectGuess = false;
                }
            }

            return guess;
        }

        // Returns player's name property. Used to indicate whose turn it is and to print out the winner.
        public String getName() {
            return this.name;
        }
    }
}
