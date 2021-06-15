import java.util.Iterator;


/*  Exercise contains two classes: WordReader and WordReaderOpt. Both receive a file path, and prepare iterators to
    go over its text. The iterators are called by main, and are expected to output to console each separate word of
    the file's text into successive lines. Both have been tested with the complete edition of War and Peace by Tolstoi.

    WordReader loads the entire file, splitting each line in sucession into separate words and adding them to an array list
    which is then iterated over with a default listIterator by main.

    WordReaderOpt simply loads the file beginning into a BufferedReader and initializes a 'line' property to the first
    line read. The iterator then splits the line into words in an array and goes over each position of that array,
    returning them one by one. If the array has been fully iterated, the next line is called and the process repeats.
    The file is never loaded in its entirety, just line by line, saving memory.

 */
public class Main {

    //Address for War and Peace in plain-text format in the project's root.
    public static final String FILE_PATH = "war-and-peace.txt";

    public static void main(String[] args) {


        // Commented out tests for WordReader.

        /*WordReader wordReader = new WordReader(FILE_PATH);

        for (String word : wordReader) {
            System.out.println(word);
        }

        Iterator<String> iterator = wordReader.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());*/


        // Tests for WordReaderOpt.

        WordReaderOpt wordReaderOpt = new WordReaderOpt(FILE_PATH);

        for (String word : wordReaderOpt) {
            System.out.println(word);
        }
    }
}
