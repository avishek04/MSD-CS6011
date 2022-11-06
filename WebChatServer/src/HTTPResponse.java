import java.security.MessageDigest;
import java.util.Base64;

public class HTTPResponse {
    public String upgrade;
    public String connection;
    public String webSocketAccept;
    public String status;
    public String contentType;
    public byte[] content;

    public String GetAcceptKey(String webSocketKey) {
        //if (webSocketKey != null && !webSocketKey.isEmpty()) {
            webSocketKey += "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

            try {
                // getInstance() method is called with algorithm SHA-1
                MessageDigest md = MessageDigest.getInstance("SHA-1");

                // digest() method is called
                // to calculate message digest of the input string
                // returned as array of byte
                byte[] messageDigest = md.digest(webSocketKey.getBytes("UTF-8"));
                String acceptKey = Base64.getEncoder().encodeToString(messageDigest);
                return acceptKey;

                // Convert byte array into signum representation
//                BigInteger no = new BigInteger(1, messageDigest);
//
//                // Convert message digest into hex value
//                String hashtext = no.toString(16);
//
//                // Add preceding 0s to make it 32 bit
//                while (hashtext.length() < 32) {
//                    hashtext = "0" + hashtext;
//                }
//
//                // return the HashText
//                return hashtext;
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        //}
        return null;
    }

    public void HandshakeResponse(String webSocketKey) {
        try {
            status = "HTTP/1.1 101 Switching Protocols";
            upgrade = "Upgrade: websocket";
            connection = "Connection: Upgrade";
            webSocketAccept = "Sec-WebSocket-Accept: " + GetAcceptKey(webSocketKey);
        }
        catch (Exception ex) {

        }
    }
    HTTPResponse(HTTPRequest request) {
        if (request.connection != null && request.webSocketKey != null) {
            HandshakeResponse(request.webSocketKey);
        }
        else {
            try {
                status = request.GetFile().length > 0 ? "HTTP/1.1 200 OK" : "HTTP/1.1 500 Internal Server Error";
                contentType = request.contentType.contains("html")? "Content-Type: text/html; charset=UTF-8" :
                        request.contentType.contains("css")? "Content-Type: text/css; charset=UTF-8" : "HTTP/1. 404 File not found";
                content = request.GetFile();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    public byte[] GetHandShakeResponse() {
        return (status + "\r\n" + upgrade + "\r\n" + connection + "\r\n" + webSocketAccept + "\r\n\r\n").getBytes();
    }
    public byte[] GetStatus() {
        return (status + "\n").getBytes();
    }

    public byte[] GetContentType() {
        return (contentType + "\n\n").getBytes();
    }

    public byte[] GetContent() {
        return content;
    }
}
