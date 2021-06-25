import java.io.*;

/*  Testing TCP server behaviour in Java. Implementation is very rough and rowdy. Server listens for all incoming connections,
    delegates each to a new thread and replies with three limited interactions:

    localhost:50505 on a browser gives you an index page;
    localhost:50505/image gives you an image of a polar bear saying hi.
    Anything else gives you a 404 not found message.

*/
public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        try {
            server.serverListen();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
