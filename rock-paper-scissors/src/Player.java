public class Player {

    // Stores current move. Is changed each round.
    private Moves hand;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    // Chooses move randomly, stores it in hand property.
    public void chooseMove() {
        this.hand = Moves.values()[RandGen.randInt(3)];
    }

    // Getter for hand.
    public Moves showMove() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
