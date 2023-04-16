package theatre;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TheatreServer {
    private static BookingService bookingService;

    public static void main(String argv[]) throws IOException {
        bookingService = new BookingService();
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("waiting for requests...");
        while (true) {
            try {
                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("new client connected");
                // thread creation passing the established socket as arg
                ServerThread serverThread =
                        new ServerThread(connectionSocket, bookingService);

                // start of the thread
                serverThread.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
