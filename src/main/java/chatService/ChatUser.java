package chatService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class ChatUser {
    private String userId;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 6789);
        BufferedReader fromTerminale = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
        BufferedReader fromServer =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

        Thread w = new Writer(fromTerminale, toServer);
        Thread r = new Reader(fromServer);
        w.start();
        r.start();
    }

    static class Writer extends Thread {
        BufferedReader reader;
        DataOutputStream writer;

        public Writer(BufferedReader in, DataOutputStream out) {
            this.reader = in;
            this.writer = out;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("inserisci un messaggio da inviare a tutti");
                    String mex = reader.readLine();
                    writer.writeBytes(mex+"\n");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Reader extends Thread {
        BufferedReader reader;

        public Reader(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            try {
                while(true){
                    String result = reader.readLine();
                    System.out.println("FROM SERVER: " + result);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
