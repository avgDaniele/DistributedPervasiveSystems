package theatre;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BookingService extends Thread {
    private Socket connectionSocket;
    private BufferedReader inputFromClient;
    private DataOutputStream outputToClient;

    private static int availableSeats;

    public BookingService(Socket s) {
        connectionSocket = s;
        try {
            inputFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            outputToClient = new DataOutputStream(
                    new DataOutputStream(connectionSocket.getOutputStream()));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
