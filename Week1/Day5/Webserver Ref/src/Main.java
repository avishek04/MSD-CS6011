import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket clientSocket = null;
        OutputStream outStream = null;
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        while (true) {
            try {
                clientSocket = server.accept();
                HTTPRequest request = new HTTPRequest(clientSocket);
                outStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outStream, false);
                HTTPResponse response = new HTTPResponse(request);
                out.println(response.GetResponse());
                out.flush();
                clientSocket.close();
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
