import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, String> headerMap = new HashMap<>();
    public static HashMap<String, String> reqLine = new HashMap<>();

    public static void CreateHeaderMap(String header) {
        String[] keyVal = header.split(": ", 2);
        headerMap.put(keyVal[0], keyVal[1]);
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = null;
            Socket clientSocket = null;
            OutputStream outStream = null;
            InputStream inStream = null;
            server = new ServerSocket(8080);
            while (true) {
                //Scanner sc = new Scanner(in);
                clientSocket = server.accept();
                inStream = clientSocket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
                outStream = clientSocket.getOutputStream();
                PrintWriter out = new PrintWriter(outStream, false);
                String requestLine = "";
                requestLine = in.readLine();
                if (!requestLine.isEmpty()) {
                    String[] reqArr = requestLine.split(" ");
                    reqLine.put("Method", reqArr[0]);
                    reqLine.put("FileName", reqArr[1]);
                }
                String header = in.readLine();
                while (header.length() > 0) {
                    CreateHeaderMap(header);
                    header = in.readLine();
                }

//                FileInputStream file = new FileInputStream("index.html");
//                Scanner sc = new Scanner(file);
//                String htmlFile = sc.nextLine();
//                while (sc.hasNext()) {
//                    htmlFile += sc.nextLine();
//                }
//                file.close();
//                FileReader file = new FileReader("index.html");
//                file.transferTo(out);
                Path path = Paths.get("index.html");
                String html = Files.readString(path);
                String response = "HTTP/1.1 200 OK\nContent-Type: text/html; charset=UTF-8\n\n" + html;
                out.println(response);
                out.flush();
                clientSocket.close();
            }
        }
        catch (IOException ex) {
            System.err.println(ex.toString());
            System.exit(1);
        }
    }
}
