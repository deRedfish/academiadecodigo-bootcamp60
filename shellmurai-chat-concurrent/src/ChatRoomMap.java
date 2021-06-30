import java.util.HashMap;

    /*  This class's purpose is to allow for multiple chat rooms to exist at the same time. It is a type of HashMap
    *   with limited functionality, where the keys are the chat rooms' names and the values are the ChatRoom objects
    *   themselves.*/

public class ChatRoomMap {

    private HashMap<String, ChatRoom> chatRoomMap;

    public ChatRoomMap() {
        this.chatRoomMap = new HashMap<>();
    }

    public void addRoom(ChatRoom chatRoom) {
        chatRoomMap.put(chatRoom.getName(), chatRoom);
    }

    public void removeRoom(String name) {
        chatRoomMap.remove(name);
    }

    public ChatRoom getRoom(String name) {
        return chatRoomMap.get(name);
    }

    public String getRooms() {

        String roomList = "";

        for (String roomName: chatRoomMap.keySet()) {
            roomList = roomList + roomName + "\n";
        }

        return roomList;
    }
}
