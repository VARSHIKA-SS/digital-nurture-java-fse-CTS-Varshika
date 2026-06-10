import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClientServerChat {
    
    static class Server {
        public void start() {
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                System.out.println("Server started on port 5000");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in));
                
                out.println("Server: Hello!");
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Client: " + msg);
                    System.out.print("Server: ");
                    out.println("Server: " + sysIn.readLine());
                }
                
                socket.close();
                serverSocket.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    static class Client {
        public void connect() {
            try {
                Socket socket = new Socket("localhost", 5000);
                System.out.println("Connected to server");
                
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
                
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                    System.out.print("Client: ");
                    out.println(userIn.readLine());
                }
                
                socket.close();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equals("server"))
                new Server().start();
            else if (args[0].equals("client"))
                new Client().connect();
        } else {
            System.out.println("Usage: java TCPClientServerChat server");
            System.out.println("       java TCPClientServerChat client");
        }
    }
}
