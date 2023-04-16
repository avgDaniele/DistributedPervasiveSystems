package multithread;

import java.io.*;
import java.net.*;

class MultiServer {
    public static void main(String argv[]) throws IOException {
        ServerSocket welcomeSocket = new ServerSocket(6789);

        System.out.println("waiting for requests...");
        while (true) {
            try {
                Socket connectionSocket = null;
                connectionSocket = welcomeSocket.accept();

                System.out.println("client connected");
                // thread creation passing the established socket as arg
                ServerThread theThread =
                        new ServerThread(connectionSocket);

                // start of the thread
                theThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}