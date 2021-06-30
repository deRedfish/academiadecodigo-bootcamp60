/*  The client side of this exercise can be replaced with a netcat connection to the server directly.
    Enter 'nc localhost 50505' while the server is running to connect.
    MainClient and Client simply emulate the same functionality as netcat.
* */

public class MainClient {

    public static void main(String[] args) {

        Client client = new Client();
        client.init();
    }
}
