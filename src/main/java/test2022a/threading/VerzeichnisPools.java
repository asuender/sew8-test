package test2022a.threading;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VerzeichnisPools {
    public static void main(String[] args) throws IOException {
        CountDownLatch latch = new CountDownLatch(3);
		Aenderungsliste aenderungsliste = new Aenderungsliste();

		ExecutorService executor = Executors.newFixedThreadPool(3);

		VerzeichnisWaechter w1 = new VerzeichnisWaechter("/Users/asuender/Documents", 1000, aenderungsliste, latch);
		VerzeichnisWaechter w2 = new VerzeichnisWaechter("/Users/asuender/Desktop", 1000, aenderungsliste, latch);
		VerzeichnisWaechter w3 = new VerzeichnisWaechter("/Users/asuender/Downloads", 1000, aenderungsliste, latch);
		Aenderungsdrucker drucker = new Aenderungsdrucker(aenderungsliste, latch);

		executor.execute(w1);
		executor.execute(w2);
		executor.execute(w3);
		new Thread(drucker).start();

		System.out.println("Starting all threads. Press Enter to quit.");

		System.in.read();

		drucker.setRunning(false);
		w1.setRunning(false);
		w2.setRunning(false);
		w3.setRunning(false);
    }
}
