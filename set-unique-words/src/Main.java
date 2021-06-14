import java.util.Iterator;


// Basic Set Iterator exercise. UniqueWord class implements Iterable, has a HashSet (setWords) and an add(String) method.
// add(String) receives a sentence, cuts it into words using spaces as cut offs, then adds each cut word into its setWords.
// Main then iterates through the UniqueWord object (setWords in main as well), and prints out the result of iterator.next().
// In this case, that means it prints out the next element in the set.
// Should print once for each unique word in the sentence attributed to 's'.

public class Main {

    public static void main(String[] args) {

        UniqueWord setWords = new UniqueWord();
        String s = "ABC ABC BBC BBC DNA DNA DNA";

        setWords.add(s);
        Iterator<String> iterator = setWords.iterator();

        while (iterator.hasNext()){

            System.out.println(iterator.next());

        }

    }
}
