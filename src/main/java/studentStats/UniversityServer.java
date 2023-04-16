package studentStats;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class UniversityServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(6789);
        while (true) {
            System.out.println("waiting for clients...");
            Socket connectedSocket = socket.accept();
            new UniThread(connectedSocket).start();
            System.out.println("client connected");
        }
    }

    static class UniThread extends Thread {
        Socket socket;
        private BufferedReader inputFromClient;

        public UniThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            //leggere dal client lo studente, salvarlo,
            // chiedere connessione con Client
            try {
                inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = inputFromClient.readLine();

                System.out.println(String.format("request: %s", request));
                //unmarshalling
                Gson gson = new Gson();
                Student studente = gson.fromJson(request, Student.class);

                System.out.println(String.format("oggetto studente: %s", studente));
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
