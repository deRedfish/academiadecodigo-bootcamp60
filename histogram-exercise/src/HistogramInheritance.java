import java.util.HashMap;

public class HistogramInheritance extends HashMap<String, Integer> {

    public HistogramInheritance(String phrase) {

        for (String word : phrase.split(" ")) {

            if (containsKey(word)) {

                replace(word, get(word) + 1);

            } else {

                put(word, 1);

            }
        }
    }
}
