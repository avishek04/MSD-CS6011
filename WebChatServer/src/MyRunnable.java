import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyRunnable implements Runnable {
    InputStream inputStream = null;
    OutputStream outStream = null;
    private final Socket clientSocket;
    private boolean handshakeFinished = false;
    MyRunnable(Socket socket) {
        clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outStream = clientSocket.getOutputStream();
            while (true) {
                if (handshakeFinished) {
                    ChatMessage message = new ChatMessage(inputStream);
                    ChatResponse response = new ChatResponse(message);
                    System.out.println(response);
                    for (Socket client: Main.sockets) {
                        OutputStream out = client.getOutputStream();
                        out.write(response.responseFrame);
                        out.flush();
                    }
                } else {
                    HTTPRequest request = new HTTPRequest(inputStream);
                    HTTPResponse response = new HTTPResponse(request);
//            outStream.write(response.GetStatus());
//            outStream.write(response.GetContentType());
//            outStream.write(response.GetContent());
                    byte[] result = response.GetHandShakeResponse();
                    outStream.write(result);
                    outStream.flush();
                    handshakeFinished = true;
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
