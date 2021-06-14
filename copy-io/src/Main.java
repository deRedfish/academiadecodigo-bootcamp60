import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


// Testing Input / Output calls.
// Copies linus.jpg to copiedbyte.jpg, byte per byte, then copies copiedbyte.jpg to copiedbuffer, buffer (1024 bytes long) per buffer.
// As tested, all three files have the same md5 hash.

public class Main {

    public static void main(String[] args) {

        try {

            FileInputStream input = new FileInputStream("src/linus.jpg");
            FileOutputStream output = new FileOutputStream("src/copiedbyte.jpg");

            int b;

            while ((b = input.read()) != -1) {

                output.write(b);

            }

            input.close();
            output.close();

            input = new FileInputStream("src/copiedbyte.jpg");
            output = new FileOutputStream("src/copiedbuffer.jpg");

            byte[] buffer = new byte[1024];
            int bb;

            while ((bb = input.read(buffer)) != -1) {

                output.write(buffer, 0, bb);
            }

            System.out.println("Done copying!");
        } catch (FileNotFoundException e) {

            System.out.println("Caught: File not found.");

        } catch (IOException e) {

            System.out.println("Caught: IO Exception.");
        }
    }

}
