import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket clientSocket = null;
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        while (true) {
            try {
                clientSocket = server.accept();
                MyRunnable run = new MyRunnable(clientSocket);
                Thread th = new Thread(run);
                th.start();
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
