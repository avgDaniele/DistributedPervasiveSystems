package chatService;

import java.util.ArrayList;
import java.util.List;

public class MessageQueue {
    private List<String> messages;

    public MessageQueue() {
        messages = new ArrayList<>();
    }

    public synchronized void put(String message) {
        System.out.println("aggiunto messaggio: " + message);
        messages.add(message);
        notifyAll();
    }

    public synchronized String take(int mexLetti) throws InterruptedException {
        if (messages.size() <= mexLetti) {
            wait();
        }
        return messages.get(mexLetti);
    }

    public int getNumberOfMessagesSent() {
        return this.messages.size();
    }
}
