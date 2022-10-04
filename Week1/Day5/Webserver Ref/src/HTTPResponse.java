public class HTTPResponse {
    public String status;
    public String contentType;
    public String content;
    HTTPResponse(HTTPRequest request) {
        try {
            status = !request.GetFile().isEmpty() ? "HTTP/1.1 200 OK" : "HTTP/1.1 500 Internal Server Error";
            contentType = request.contentType.contains("html")? "Content-Type: text/html; charset=UTF-8" :
                    request.contentType.contains("css")? "Content-Type: text/css; charset=UTF-8" : "HTTP/1. 404 File not found";
            content = request.GetFile();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public String GetResponse() {
            return status + "\n" + contentType + "\n\n" + content;
    }
}
