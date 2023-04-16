package iterative;

import java.io.*;
import java.net.*;

class IterativeServer {
    public static void main(String argv[]) throws Exception {
        int n1;
        int n2;
        int res;

        // create a "listening socket" on the specified port
        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("waiting for requests...");

        while(true) {
            try {
			/*	accept is a blocking call
				once a new connection arrived, it creates
				a new "established socket"	*/
                Socket connectionSocket = welcomeSocket.accept();

                System.out.println("client connected");

                // input stream from the socket initialization
                BufferedReader inFromClient =
                        new BufferedReader(
                                new InputStreamReader(connectionSocket.getInputStream()));

                // output stream to the socket initialization
                DataOutputStream outToClient =
                        new DataOutputStream(connectionSocket.getOutputStream());

                // read a line (that terminates with \n) from the client
                String[] values = parseClientInput(inFromClient.readLine());

                System.out.println("serving request...");

                n1 = Integer.parseInt(values[0]);
                n2 = Integer.parseInt(values[1]);

                // wait for 10 seconds
                Thread.sleep(10000);

                res = n1 + n2;
                // send the response to the client
                outToClient.writeBytes(String.valueOf(res)+"\n");
                System.out.println(String.format("request served, result: %d", res));
            }catch (SocketException e){
                e.printStackTrace();
            }
        }
    }

    private static String[] parseClientInput(String input){
        String[] values = input.split(",");
        return values;
    }
}
