import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTTPRequest {
    public String method;
    public String filePath;
    public String host;
    public String contentType;
    public String encoding;
    public String body;
    HTTPRequest(Socket socket) {
        InputStream inStream = null;
        try {
            inStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            SetRequestLine(in.readLine());
            String request = in.readLine();
            while (!request.isEmpty()) {
                System.out.println(request);
                SetRequest(request);
                request = in.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public void SetRequest(String reqHeader) {
        //HTTPRequest httpRequest = new HTTPRequest();
        String[] keyVal = reqHeader.split(": ", 2);
        if (keyVal[0].toLowerCase().contains("host")) {
            host = keyVal[1];
        }
        else if (keyVal[0].toLowerCase().equals("accept")) {
           contentType = keyVal[1];
        }
        else if (keyVal[0].toLowerCase().contains("encoding")) {
            encoding = keyVal[1];
        }
        else if (keyVal[0].toLowerCase().contains("body")) {
            body = keyVal[1];
        }
    }
    public void SetRequestLine(String reqLine) {
        if (!reqLine.isEmpty()) {
            String[] reqArr = reqLine.split(" ");
            method = reqArr[0];
            filePath = reqArr[1];
        }
    }
    public String GetFile() {
        Path path = null;
        String file = null;
        try {
            if (contentType.toLowerCase().contains("html") && (filePath.equals("/") || filePath.toLowerCase().contains("index"))) {
                path = Paths.get("index.html");
            } else if (contentType.toLowerCase().contains("css") && filePath.toLowerCase().contains("css")) {
                path = Paths.get("index.css");
            }
            if (path != null) file = Files.readString(path);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return file;
    }
}
