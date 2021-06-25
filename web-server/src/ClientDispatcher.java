import java.io.*;
import java.net.Socket;

public class ClientDispatcher implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String header;

    private String doc = "HTTP/1.0 200 Document Follows\r\n" +
            "Content-Type: text/html; charset=UTF-8\r\n" +
            "Content-Length: <file_byte_size> \r\n" +
            "\r\n" +
            "<!DOCTYPE html>\n";

    private String img = "HTTP/1.0 200 Document Follows\r\n" +
            "Content-Type: image/png\r\n" +
            "Content-Length: <file_byte_size>\r\n" +
            "\r\n";

    private String notFound = "HTTP/1.0 404 Not Found\r\n" +
            "Content-Type: text/html; charset=UTF-8\r\n" +
            "Content-Length: <file_byte_size> \r\n" +
            "\r\n" +
            "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>404 Not Found</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "Not Found. 404.\n" +
            "</body>\n" +
            "\n" +
            "</html>";

    private String homepage = "<html>\n" +
            "<head>\n" +
            "<title>Welcome brethren</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "Let us praise the Sun!\n" +
            "</body>\n" +
            "\n" +
            "</html>";


    public ClientDispatcher(Socket client) {
        this.client = client;
    }

    public void sendPath(String path) throws IOException {

        switch (path) {

            case "/":
                out.write(doc);
                out.flush();
                out.write(homepage);
                out.flush();
                break;

            case "/image":
                out.write(img);
                out.flush();

                FileInputStream file = new FileInputStream("resources/bear.png");
                byte[] buffer = new byte[1024];
                int offset;

                while ((offset = file.read(buffer)) != -1) {
                    System.out.println(offset);
                    client.getOutputStream().write(buffer, 0, offset);
                }

                file.close();
                break;

            default:
                out.write(notFound);
                out.flush();
                break;
        }
    }

    public String readHeader() throws IOException {

        header = in.readLine();
        return header.split(" ")[1];
    }

    @Override
    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            sendPath(readHeader());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
