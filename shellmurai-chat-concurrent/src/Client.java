import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*  Client has two threads, one for receiving messages continuously from the server, represented by the ClientListener inner class,
*   and one for sending messages to the server, represented by the sender method.*/

public class Client {

    private Socket client;

    public void init() {

        try {

            client = new Socket(InetAddress.getLocalHost(), 50505);

            ExecutorService cachedPool = Executors.newCachedThreadPool();

            ClientListener clientListener = new ClientListener();
            cachedPool.submit(clientListener);

            sender();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Uses a BufferedReader to read keyboard input line by line. Passes each line to a BufferedWriter which sends the
    // string upstream to the server.

    public void sender() {

        try {
            BufferedWriter msgSender = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ready to send");

            while (!client.isClosed()) {

                msgSender.write(input.readLine() + "\n");
                msgSender.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ClientListener implements Runnable {

        // Dedicated thread for message reception. Is responsible for terminating the process on the client's side on
        // client's departure from the server (through the 'Q:' command).

        @Override
        public void run() {

            try {
                BufferedReader msgReceiver = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (!client.isClosed()) {

                    String msgReceived = msgReceiver.readLine();
                    if (msgReceived == null) {
                        client.close();
                        System.exit(0);
                    }
                    System.out.println(msgReceived);

                }
                msgReceiver.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
