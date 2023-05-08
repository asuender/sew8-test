package test2022a.threading;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class SyncVerzeichnisCheck {
    public static void main(String[] args) throws IOException {
        CountDownLatch latch = new CountDownLatch(3);
		SyncAenderungsliste aenderungsliste = new SyncAenderungsliste();

		VerzeichnisWaechter w1 = new VerzeichnisWaechter("/Users/asuender/Documents", 1000, aenderungsliste, latch);
		VerzeichnisWaechter w2 = new VerzeichnisWaechter("/Users/asuender/Desktop", 1000, aenderungsliste, latch);
		VerzeichnisWaechter w3 = new VerzeichnisWaechter("/Users/asuender/Downloads", 1000, aenderungsliste, latch);
		Aenderungsdrucker drucker = new Aenderungsdrucker(aenderungsliste, latch);

		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		Thread t3 = new Thread(w3);
		Thread t4 = new Thread(drucker);

		System.out.println("Starting all threads. Press Enter to quit.");
		t4.start(); t1.start(); t2.start(); t3.start();

		System.in.read();

		w1.setRunning(false);
		w2.setRunning(false);
		w3.setRunning(false);
		drucker.setRunning(false);
    }
}
