import java.io.IOException;
import java.net.Socket;

/*  Dedicated thread that listens for incoming client connection attempts. When a new connection is made, the
*   new client's socket is given to the Server to store.*/

public class ServerConnectionHandler implements Runnable{

    private Server server;

    public ServerConnectionHandler(Server server ) {

        this.server = server;

    }

    @Override
    public void run() {

        System.out.println("Connection handler ready.");
        while (server.getServerSocket().isBound()) {

            try {
                Socket newClient = server.getServerSocket().accept();
                System.out.println("Connection accepted!");
                server.connectClient(newClient);
                System.out.println(newClient.getInetAddress().getHostName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
