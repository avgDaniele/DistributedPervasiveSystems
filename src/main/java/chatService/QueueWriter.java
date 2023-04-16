package chatService;

import java.io.BufferedReader;
import java.io.IOException;

public class QueueWriter extends Thread {
        BufferedReader reader;
        MessageQueue queue;

        public QueueWriter(BufferedReader in, MessageQueue queue) {
            this.reader = in;
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                    String mex = reader.readLine();
                    System.out.println("letto messaggio: " + mex);
                    queue.put(mex);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

}
