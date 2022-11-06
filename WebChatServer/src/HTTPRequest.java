import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HTTPRequest {
    public String method = null;
    public String filePath = null;
    public String host = null;
    public String upgrade = null;
    public String connection = null;
    public String webSocketKey = null;
    public String webSocketVer = null;
    public String contentType = null;
    public String encoding = null;
    public String body = null;
    HTTPRequest(InputStream inStream) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            SetRequestLine(in.readLine());
            String request = in.readLine();
            while (!request.isEmpty()) {
                System.out.println(request);
                SetRequest(request);
                request = in.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void SetClientMessage() {

    }
    public void SetRequest(String reqHeader) {
        String[] keyVal = reqHeader.split(": ", 2);
        if (keyVal[0].toLowerCase().contains("host")) {
            host = keyVal[1];
        } else if (keyVal[0].equalsIgnoreCase("upgrade")) {
            upgrade = keyVal[1];
        } else if (keyVal[0].equalsIgnoreCase("connection")) {
            connection = keyVal[1];
        } else if (keyVal[0].equalsIgnoreCase("sec-websocket-key")) {
            webSocketKey = keyVal[1];
        } else if (keyVal[0].equalsIgnoreCase("sec-websocket-version")) {
            webSocketVer = keyVal[1];
        } else if (keyVal[0].equalsIgnoreCase("accept")) {
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
    public byte[] GetFile() {
        Path path = null;
        byte[] file = null;
        try {
            if (contentType.toLowerCase().contains("html") && (filePath.equals("/") || filePath.toLowerCase().contains("index"))) {
                path = Paths.get("index.html");
            } else if (contentType.toLowerCase().contains("css") && filePath.toLowerCase().contains("css")) {
                path = Paths.get("index.css");
            }
            else {
                path = Paths.get("533108.jpg");
            }
            if (path != null) {
                file = Files.readAllBytes(path);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return file;
    }
}
