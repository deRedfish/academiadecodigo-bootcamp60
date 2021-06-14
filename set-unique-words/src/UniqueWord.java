import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueWord implements Iterable<String>{

    private Set<String> setWords = new HashSet<>();

    public void add(String s) {

        for (String word: s.split(" ")) {

            setWords.add(word);

        }
    }

    @Override
    public Iterator iterator() {
        return setWords.iterator();
    }
}
