import java.sql.SQLOutput;


// This exercise served to experiment with Map structures and to reinforce the importance of contrasting composition and inheritance for the same purpose.
// In this situation inheritance is more concise, but it does not enforce encapsulation properly. Our Histogram is supposed to simply count occurrences of
// unique words, and both HistogramComp and HistogramInheritance do that, but HistogramInheritance also gives users access to all the other methods of a HashMap.
// HistogramComp limits interaction (important for safety purposes).
public class Main {

    public static final String STRING = "test word test words banana 1 10 1 10 1 1 1";

    public static void main(String[] args) {

        // Histogram made with composition. (Has a HashMap).
        HistogramComp wordHistogram = new HistogramComp(STRING);
        System.out.println("MAP has " + wordHistogram.size() + " distinct pairs.");

        for (String word : wordHistogram) {

            System.out.println(word + " : " + wordHistogram.get(word));

        }

        // Histogram made with inheritance. (Is a HashMap)
        HistogramInheritance wordHistogram2 = new HistogramInheritance(STRING);
        System.out.println("Inherited Map has " + wordHistogram2.size() + " distinct pairs.");

        for (String word : wordHistogram2.keySet()) {

            System.out.println(word + " : " + wordHistogram2.get(word));

        }
    }
}
