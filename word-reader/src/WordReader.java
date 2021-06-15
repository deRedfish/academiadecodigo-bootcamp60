import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/*  Class that stocks the entire contents of a given text file into an ArrayList 'words', separated by blank spaces.
    Its iterator is a default ArrayList iterator which simply goes over the contents of 'words'.
 */
public class WordReader implements Iterable<String> {

    private ArrayList words = new ArrayList<String>();

    public WordReader(String filePath) {

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bReader.readLine()) != null) {

                String[] lineWords = line.split(" ");

                for (String word : lineWords) {
                    words.add(word);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @Override
    public Iterator iterator() {
        return words.iterator();
    }
}
