import java.io.IOException;
import java.net.*;


/*  Exercise showcasing how UDP works between a server and client in Java. Both classes have their own main thread,
    one plays the client trying to communicate with the server, the other is the server listening and replying.

    Client is supposed to send a string over UDP protocol to server, who replies with the same string in all-uppercase.
* */

public class Client {

    public static void main(String[] args) {

        String phrase = "One must imagine Sisyphus happy.";

        byte[] send = phrase.getBytes();
        byte[] rcvPacket = new byte[1024];

        try {

            DatagramSocket client = new DatagramSocket(50500);

            DatagramPacket phraseToSend = new DatagramPacket(send, send.length, InetAddress.getLocalHost(), 50505);
            client.send(phraseToSend);

            DatagramPacket phraseToReceive = new DatagramPacket(rcvPacket, rcvPacket.length);
            client.receive(phraseToReceive);

            System.out.println(new String (phraseToReceive.getData()));

            client.close();

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
