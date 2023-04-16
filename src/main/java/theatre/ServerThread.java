package theatre;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket connectionSocket;
    private BufferedReader inputFromClient;
    private DataOutputStream outputToClient;

    private BookingService bookingService;

    public ServerThread(Socket s, BookingService bookingService) {
        connectionSocket = s;
        try {
            inputFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            outputToClient = new DataOutputStream(
                    new DataOutputStream(connectionSocket.getOutputStream()));
            this.bookingService = bookingService;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        boolean exit = false;

        while (!exit) {
            Operations op;
            int n = 0;
            boolean res;
            try {
                System.out.println("waiting for requests from client..");
                String operazione = inputFromClient.readLine();
                System.out.println(operazione);
                op = Operations.valueOf(operazione);
                System.out.println(String.format("serving operation: %s", op.description));
                switch (op) {
                    case B://book
                        n = Integer.parseInt(inputFromClient.readLine());
                        System.out.println(String.format("trying to book %d seats", n));
                        res = this.bookingService.bookSeats(n, this);
                        System.out.println(String.format("operation %s", res ? "succeded" : "failed"));
                        break;
                    case F://free
                        n = Integer.parseInt(inputFromClient.readLine());
                        System.out.println(String.format("trying to free %d seats", n));
                        res = this.bookingService.freeSeats(n, this);
                        System.out.println(String.format("operation %s", res ? "succeded" : "failed"));
                        break;
                    case E://exit
                        connectionSocket.close();
                        exit = true;
                        break;
                    case A://check Availability
                    default:
                        break;
                }
                int avail = this.bookingService.getAvailableSeats(this);
                System.out.println(String.format("Are available %d seats", avail));

                // send the response to the client
                outputToClient.writeBytes(String.valueOf(avail) + "\n");
            } catch (Exception e) {
                exit = true;
                e.printStackTrace();
                try {
                    connectionSocket.close();
                } catch (IOException ex) {
                    e.printStackTrace();
                }
            }
        }
    }
}
