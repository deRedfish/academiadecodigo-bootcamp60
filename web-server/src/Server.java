import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    private int port = 50505;
    private ServerSocket serverSocket;


    public void serverListen() throws IOException {

        serverSocket = new ServerSocket(port);

        while (true) {

            ClientDispatcher clientDispatcher = new ClientDispatcher(serverSocket.accept());

            Thread clientThread = new Thread(clientDispatcher);
            clientThread.start();
        }
    }
}

