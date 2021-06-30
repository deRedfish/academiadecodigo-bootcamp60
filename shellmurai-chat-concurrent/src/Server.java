import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*  Server is the backbone of the chat application. It interacts with the clients through the ServerClientHandler class
*   threads and the ServerConnectionHandler thread. These two translate user requests into server method calls executed here.
*   Server stocks client sockets in a HashMap clientlist where sockets are keys and names are values, and within a ChatRoom's
*   array list of members, accessible through the ChatRoomMap HashMap-style object which the Server maintains.*/

public class Server {

    private ServerSocket serverSocket;
    private int port;
    private HashMap<Socket, String> clientList;
    private ExecutorService cachedPool;
    private BufferedWriter broadcaster;
    private ChatRoomMap chatRooms;

    public void setupServer(int port) {

        try {

            serverSocket = new ServerSocket(port);
            this.port = port;
            clientList = new HashMap<>();

            this.chatRooms = new ChatRoomMap();
            ChatRoom main = new ChatRoom();
            main.setupRoom("main");

            this.chatRooms.addRoom(main);

            cachedPool = Executors.newCachedThreadPool();

            ServerConnectionHandler serverConnectionHandler = new ServerConnectionHandler(this);
            cachedPool.submit(serverConnectionHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectClient(Socket newClient) {

        clientList.put(newClient, "Awaiting name.");
        chatRooms.getRoom("main").addClient(newClient);
        ServerClientHandler clientHandler = new ServerClientHandler(this, newClient);
        cachedPool.submit(clientHandler);
        broadcast(newClient.getInetAddress().getHostName(), "Welcome to the Shellmurai-Chat Server! \n" +
                "You are now in the 'main' chat room. \n" +
                "To get a list of available commands, type 'H:'. \n");

    }

    public void removeClient(Socket client, String roomName) {
        clientList.remove(client);
        chatRooms.getRoom(roomName).removeClient(client);
        broadcastAll("SERVER: " + client.getInetAddress().getHostName() + " has left the server.");
    }

    public void broadcastAll(String message) {

        for (Socket client : clientList.keySet()) {

            try {
                broadcaster = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                broadcaster.write(message + "\n");
                broadcaster.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcastRoom(String roomName, String message) {


        for (Socket client : chatRooms.getRoom(roomName).getRoomMembers()) {

            try {
                broadcaster = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                broadcaster.write(message + "\n");
                broadcaster.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int broadcast(String name, String message) {

        for (Socket client : clientList.keySet()) {

            if (client.getInetAddress().getHostName().equals(name)) {

                try {
                    broadcaster = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    broadcaster.write(message + "\n");
                    broadcaster.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return 0;
            }


        }
        return -1;
    }

    public int createRoom(Socket client, String name) {

        if (chatRooms.getRoom(name) != null) {
            return -1;
        }
        ChatRoom newRoom = new ChatRoom();
        newRoom.setupRoom(name);

        chatRooms.addRoom(newRoom);
        return 0;
    }

    public int moveClientToRoom(Socket client, String currentRoom, String goToRoom) {

        if (chatRooms.getRoom(goToRoom) == null) {
            return -1;
        }

        chatRooms.getRoom(goToRoom).addClient(client);
        chatRooms.getRoom(currentRoom).removeClient(client);

        return 0;
    }

    public String getRooms() {
        return chatRooms.getRooms();
    }

    public LinkedList<String> whois() {

        LinkedList<String> ids = new LinkedList<>();

        for (Socket client : clientList.keySet()) {

            ids.add(client.getInetAddress().getHostName() + " : " + clientList.get(client));
        }

        return ids;
    }

    public void showCommands(Socket client) {
        broadcast(client.getInetAddress().getHostName(), "'N: <String name>' sets your name to the given word (NOTE: Only one word allowed, no spaces). \n" +
                "'A: <String message>' broadcasts the given message to every user regardless of chat room. \n" +
                "'whois:' requests a list of every user connected to the server, by name and IP address. \n" +
                "'GR:' requests the list of every available chat room on the server. \n" +
                "'CR: <String roomName>' creates a room with the given name, as long as it does not already exist. \n" +
                "'R: <String roomName>' moves the user to the given chat room if it exists. \n" +
                "'W: <String IP address> <String message>' Sends the message to the user given by IP address if he is connected to the server.\n" +
                "'H:' shows chat commands.\n" +
                "'Q:' quits chat.\n" );
    }

    public void setName(Socket client, String name) {

        clientList.replace(client, clientList.get(client), name);
    }

    /* GETTERS */

    public ServerSocket getServerSocket() {

        return serverSocket;
    }

}
