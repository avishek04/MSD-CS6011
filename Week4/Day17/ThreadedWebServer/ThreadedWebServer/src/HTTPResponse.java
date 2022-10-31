import java.util.stream.Stream;

public class HTTPResponse {
    public String status;
    public String contentType;
    public byte[] content;
    HTTPResponse(HTTPRequest request) {
        try {
            status = request.GetFile().length > 0 ? "HTTP/1.1 200 OK" : "HTTP/1.1 500 Internal Server Error";
            contentType = request.contentType.contains("html")? "Content-Type: text/html; charset=UTF-8" :
                    request.contentType.contains("css")? "Content-Type: text/css; charset=UTF-8" : "HTTP/1. 404 File not found";
            content = request.GetFile();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
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
