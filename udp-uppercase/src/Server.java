import java.io.IOException;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {

            DatagramSocket server = new DatagramSocket(50505);

            while (true) {

                byte[] buffer = new byte[1024];

                DatagramPacket rcvPacket = new DatagramPacket(buffer, buffer.length);
                server.receive(rcvPacket);

                String toUpper = new String(rcvPacket.getData()).toUpperCase();
                System.out.println(toUpper);

                byte[] sendPhrase = toUpper.getBytes();

                DatagramPacket sndPacket = new DatagramPacket(sendPhrase, sendPhrase.length, rcvPacket.getAddress(), rcvPacket.getPort());
                server.send(sndPacket);

            }

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
