import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Socket> sockets;
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket clientSocket = null;
        sockets = new ArrayList<>();

        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                clientSocket = server.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (clientSocket.isConnected()) {
                sockets.add(clientSocket);
                System.out.println("New Client");
                MyRunnable run = new MyRunnable(clientSocket);
                Thread th = new Thread(run);
                th.start();
            }
        }
    }
}
