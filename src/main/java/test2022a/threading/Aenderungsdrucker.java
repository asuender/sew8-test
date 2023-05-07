package test2022a.threading;

import java.util.concurrent.CountDownLatch;

public class Aenderungsdrucker implements Runnable {
    private Aenderungsliste aenderungsliste;
    private boolean running;
    private CountDownLatch latch;

    public Aenderungsdrucker(Aenderungsliste aenderungsliste, CountDownLatch latch) {
        this.aenderungsliste = aenderungsliste;
        this.latch = latch;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        this.running = true;

        try {
            latch.await();

            while(running) {
                System.out.println(this.aenderungsliste.getUpdate());
            }

        } catch (InterruptedException ignored) { }
    }
}
