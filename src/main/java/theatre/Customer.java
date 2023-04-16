package theatre;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Customer {

    public static void main(String argv[]) throws Exception {

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

        boolean exit = false;
        int postiPrenotati = 0;
        while (!exit){
            Operations op;
            String result;
            int n;

            System.out.println("che operazione vuoi fare?\nbook seats: B\nfree seats: F\nCheck available seats: A\nExit the process: E");
            String operazione = inFromUser.readLine();
            op = Operations.valueOf(operazione);
            outToServer.writeBytes(String.valueOf(op)+"\n");
            switch (op){
                case B:
                    System.out.println("quanti posti prenotare?");
                    n = Integer.parseInt(inFromUser.readLine());
                    outToServer.writeBytes(String.valueOf(n)+"\n");
                    result = inFromServer.readLine();
                    System.out.println(result + "\n");
                    break;
                case F:
                    System.out.println("quanti posti liberare?");
                    n = Integer.parseInt(inFromUser.readLine());
                    outToServer.writeBytes(String.valueOf(n)+"\n");
                    result = inFromServer.readLine();
                    System.out.println(result + "\n");
                    break;
                case E:
                    System.out.println("exiting..." + "\n");
                    clientSocket.close();
                    exit = true;
                    break;
                case A:
                    result = inFromServer.readLine();
                    System.out.println(result + "\n");
                    break;
                default:
                    break;
            }
        }
    }
}
