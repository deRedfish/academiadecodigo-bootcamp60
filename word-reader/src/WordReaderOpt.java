import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


/*  WordReaderOpt implements the same concept as WordReader but without pre-loading the entire file at any point.
    The constructor call initializes a BufferedReader to the file's start, and initializes a 'line' String to the first line.
    Its iterator then splits the first line into separate words stocked in a 'lineWords' array, keeps track of the 'currIndex'
    which is the position it was in previously in that array as it went through word by word. If the end of the array is reached,
    another line is fetched into 'line' and the 'lineWords' receives a new array with its split words. CurrIndex is then reset to reiterate
    the new 'listWords' obtained.

    Iteration is over when there are no more lines to fetch in the given file.
 */

public class WordReaderOpt implements Iterable<String>{

    private String line;
    BufferedReader bReader;

    public WordReaderOpt(String filePath) {

        try {
            FileReader fileReader = new FileReader(filePath);
            bReader = new BufferedReader(fileReader);
            line = bReader.readLine();

        } catch (FileNotFoundException e) {
            System.out.println("404");

        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {

            String[] lineWords = line.split(" +");
            int currIndex = -1;

            @Override
            public boolean hasNext() {

                if (currIndex == lineWords.length - 1) {

                    try {
                        if ((line = bReader.readLine()) != null) {
                            lineWords = line.split(" ");
                            currIndex = -1;
                            return true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return false;
                }
                return true;
            }

            @Override
            public String next() {

                currIndex++;
                return lineWords[currIndex];

            }
        };
    }
}
