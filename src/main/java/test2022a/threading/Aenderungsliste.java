package test2022a.threading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Aenderungsliste {
    private BlockingQueue<String> messages;

    public Aenderungsliste() {
        this.messages = new LinkedBlockingQueue<>();
    }

    public void sendUpdate(String message) {
        try {
            this.messages.put(message);
        } catch (InterruptedException ignored) { }
    }

    public String getUpdate() {
        String update = "";

        try {
            update = this.messages.take();
        } catch(InterruptedException ignored) { }

        return update;
    }
}