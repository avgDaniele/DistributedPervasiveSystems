package multithread;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private Socket connectionSocket = null;
    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    // the constructor argument is an established socket
    public ServerThread(Socket s) {
        connectionSocket = s;
        try {
            inFromClient =
                    new BufferedReader(
                            new InputStreamReader(connectionSocket.getInputStream()));
            outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int n1, n2, res;
        try {
            String[] values = parseClientInput(inFromClient.readLine());
            n1 = Integer.parseInt(values[0]);
            n2 = Integer.parseInt(values[1]);

            System.out.println(String.format("serving request: %d + %d", n1, n2));
            res = n1 + n2;

            this.sleep(10000);

            // send the response to the client
            outToClient.writeBytes(String.valueOf(res) + "\n");
            System.out.println(String.format("request served, result: %d", res));

            connectionSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] parseClientInput(String input) {
        String[] values = input.split(",");
        return values;
    }
}
