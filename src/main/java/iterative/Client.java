package iterative;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.*;
import java.net.*;

class Client {
    public static void main(String argv[]) throws Exception {
        int n1;
        int n2;
        int result;

        // input stream initialization (from user keyboard)
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

		/* client socket initialization
			localhost: server address
			6789: server service port number */
        Socket clientSocket = new Socket("localhost", 6789);

        // output stream towards socket initialization
        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        // input stream from socket initialization
        BufferedReader inFromServer =
                new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

        // read a line from the user
        System.out.println("inserisci il primo numero");
        n1 = Integer.parseInt(inFromUser.readLine());
        System.out.println("inserisci il secondo numero");
        n2 = Integer.parseInt(inFromUser.readLine());

        // send the line to the server
        String request = String.valueOf(n1 + "," + n2 + '\n');
        outToServer.writeBytes(String.valueOf(request));
        System.out.println(String.format("richiesta inviata", request));
        // read the response from the server
        result = Integer.parseInt(inFromServer.readLine());
        System.out.println("FROM SERVER: " + result);
        clientSocket.close();
    }
}
