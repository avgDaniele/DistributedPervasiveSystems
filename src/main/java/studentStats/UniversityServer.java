package studentStats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class UniversityServer {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8087);
        while(true){
            Socket connectedSocket = socket.accept();
            new UniThread(connectedSocket).start();
        }
    }

    static class UniThread extends Thread{
        Socket socket;
        private BufferedReader inputFromClient;

        public UniThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            //leggere dal client lo studente, salvarlo,
            // chiedere connessione con Client
            try {
                inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //Gson gson = new Gson();
                String request = inputFromClient.readLine();
                //marshalling

                Student studente = new Student();
                System.out.println(studente);
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
