package test2022a.threading;

import java.util.LinkedList;

public class SyncAenderungsliste extends Aenderungsliste {
    private String[] messages;
    private int pointer;

    public SyncAenderungsliste() {
        this.messages = new String[100];
        this.pointer = 0;
    }

    @Override
    public synchronized void sendUpdate(String message) {
        while(pointer >= message.length()) {
            try {
                this.wait();
            } catch (InterruptedException ignored) { }
        }

        this.messages[this.pointer++] = message;
        this.notifyAll();
    }

    @Override
    public synchronized String getUpdate() {
        while(this.pointer == 0) {
            try {
                this.wait();
            } catch (InterruptedException ignored) { }
        }

        String message = this.messages[--this.pointer];
        this.notifyAll();
        return message;
    }
}
