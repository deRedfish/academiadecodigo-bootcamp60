import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*  Thread dedicated to interpreting client input and interfacing with the Server's methods and commands.
    Translates String requests into properly formatted messages or command requests for the Server to receive.
    Each client has his own associated thread.
* */

public class ServerClientHandler implements Runnable {

    private Server server;
    private Socket client;
    private String name;
    private String roomName;

    public ServerClientHandler(Server server, Socket client) {
        this.server = server;
        this.client = client;
        this.name = client.getInetAddress().getHostName();
        this.roomName = "main";
    }

    @Override
    public void run() {

        try {
            String incomingMessage;

            while (client.isBound()) {

                BufferedReader clientListener = new BufferedReader(new InputStreamReader(client.getInputStream()));
                incomingMessage = clientListener.readLine();
                System.out.println(incomingMessage);

                String[] decoder = incomingMessage.split(" ", 3);

                switch (decoder[0]) {

                    case "N:":

                        if (decoder.length != 2) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        System.out.println(decoder[1]);
                        this.name = decoder[1];
                        server.setName(client, name);
                        server.broadcast(client.getInetAddress().getHostName(), "Name set to " + this.name);
                        break;

                    case "whois:":

                        if (decoder.length != 1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        server.broadcast(client.getInetAddress().getHostName(), "MEMBERS IN CHAT: ");

                        for (String id : server.whois()) {
                            server.broadcast(client.getInetAddress().getHostName(), id);
                        }
                        break;

                    case "W:":

                        if (decoder.length != 3) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        if (server.broadcast(decoder[1], "WHISPER FROM " + this.name + ": " + decoder[2]) == -1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Name not found!");
                        }
                        break;

                    case "R:":

                        if (decoder.length != 2) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        if (server.moveClientToRoom(client, roomName, decoder[1]) == -1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Room not found!");
                            break;
                        }
                        roomName = decoder[1];
                        server.broadcast(client.getInetAddress().getHostName(), "SERVER: Entered room " + decoder[1] + ".");
                        break;

                    case "CR:":

                        if (decoder.length != 2) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        System.out.println("Creating new room");
                        if (server.createRoom(client, decoder[1]) == -1) {
                            System.out.println("Room already present");
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Room already present!");
                            break;
                        }
                        server.moveClientToRoom(client, roomName, decoder[1]);
                        roomName = decoder[1];
                        System.out.println(roomName);
                        server.broadcast(client.getInetAddress().getHostName(), "SERVER: Room " + decoder[1] + " created, you were moved there.");
                        break;

                    case "GR:":

                        if (decoder.length != 1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        server.broadcast(client.getInetAddress().getHostName(), "The following chat rooms are available (connect using 'R: <roomName>'): \n" + server.getRooms());
                        break;

                    case "A:":

                        if (decoder.length < 2 ) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }
                        String outMessageALL = (this.name + " is shouting: " + decoder[1]);

                        if (decoder.length > 2) {
                            outMessageALL = outMessageALL + " " + decoder[2];
                        }

                        server.broadcastAll(outMessageALL);
                        break;

                    case "H:":

                        if (decoder.length != 1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }
                        server.showCommands(client);
                        break;

                    case "Q:":

                        if (decoder.length != 1) {
                            server.broadcast(client.getInetAddress().getHostName(), "SERVER: Incorrect command usage.");
                            break;
                        }

                        quit();
                        clientListener.close();
                        break;

                    default:
                        server.broadcastRoom(roomName, this.name + " : " + incomingMessage);
                        break;
                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        server.removeClient(client, roomName);
        try {

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
