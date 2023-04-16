package chatService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatService extends Thread {
    MessageQueue queue;
    Socket socket;
    BufferedReader fromClient;
    DataOutputStream toClient;
    Writer writer;
    Reader reader;

    public ChatService(Socket s, MessageQueue q) throws IOException {
        this.socket = s;
        this.queue = q;
        fromClient = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        toClient = new DataOutputStream(socket.getOutputStream());

        writer = new Writer(fromClient, queue);
        reader = new Reader(toClient, queue);
    }

    @Override
    public void run() {
        writer.start();
        reader.start();

    }

    //SCRIVE NEL BUFFER
    class Writer extends Thread {
        BufferedReader reader;
        MessageQueue queue;

        public Writer(BufferedReader in, MessageQueue queue) {
            this.reader = in;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String mex = reader.readLine();
                    System.out.println("letto messaggio: " + mex);
                    queue.put(mex);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //LEGGE DAL CLIENT
    class Reader extends Thread {
        DataOutputStream writer;
        MessageQueue queue;
        int messagesRead;

        public Reader(DataOutputStream writer, MessageQueue queue) {
            this.writer = writer;
            this.queue = queue;
            this.messagesRead = queue.getNumberOfMessagesSent();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = queue.take(messagesRead);
                    messagesRead ++;
                    System.out.println("invio messaggio: " + message);
                    writer.writeBytes("ricevuto messaggio:" + message + "\n");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

