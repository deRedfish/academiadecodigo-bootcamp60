import java.net.Socket;
import java.util.ArrayList;

/*  Class used to represent an individual chat room. These are stored in a HashMap-type class called ChatRoomMap which
*   the server instantiates and maintains throughout the server's uptime.
*   Chatrooms are IDed by  name, and store an ArrayList containing users present within.
*   They serve to limit the broadcastRoom() method's reach.*/

public class ChatRoom {

    private String name;
    private ArrayList<Socket> roomMembers;

    public void setupRoom(String name) {
        this.name = name;
        this.roomMembers = new ArrayList<>();
    }

    public void addClient(Socket client) {
        roomMembers.add(client);
    }

    public void removeClient(Socket client) {
        roomMembers.remove(client);
    }

    public boolean checkClient(Socket client) {
        return roomMembers.contains(client);
    }

    public ArrayList<Socket> getRoomMembers() {
        return roomMembers;
    }

    public String getName() {
        return this.name;
    }
}
