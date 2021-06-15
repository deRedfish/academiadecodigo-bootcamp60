import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


// HistogramComp represents the Histogram structure requested by the exercise with only three available methods.
public class HistogramComp implements Iterable<String>{

    private Map<String, Integer> histogram = new HashMap<>();


    // Constructor call also serves as String input method. The text whose words you want to count is passed through 'phrase'.
    public HistogramComp(String phrase) {

        for (String word : phrase.split(" ")) {

            if (histogram.containsKey(word)) {

                histogram.replace(word, histogram.get(word) + 1);

            } else {

                histogram.put(word, 1);
            }
        }
    }

    // Gets 'histogram' HashMap size.
    public int size(){

        return histogram.size();
    }

    // Gets number of occurrences of 'word' passed as parameter from 'histogram'.
    public int get(String word) {

        return histogram.get(word);

    }

    @Override
    public Iterator<String> iterator() {

        return histogram.keySet().iterator();

    }
}
