public class MainServer {

    public static void main(String[] args) {

        Server server = new Server();
        server.setupServer(50505);

        while(server.getServerSocket().isBound()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
