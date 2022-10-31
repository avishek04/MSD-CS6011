import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MyRunnable implements Runnable {

    OutputStream outStream = null;
    private final Socket clientSocket;
    MyRunnable(Socket socket) {
        clientSocket = socket;
    }
    @Override
    public void run() {
        try {
            HTTPRequest request = new HTTPRequest(clientSocket);
            outStream = clientSocket.getOutputStream();
            HTTPResponse response = new HTTPResponse(request);
            outStream.write(response.GetStatus());
            outStream.write(response.GetContentType());
            outStream.write(response.GetContent());
            outStream.flush();
            clientSocket.close();
        }
        catch (Exception ex) {

        }
    }
}
