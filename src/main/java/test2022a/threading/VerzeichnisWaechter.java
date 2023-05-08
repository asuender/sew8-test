package test2022a.threading;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class VerzeichnisWaechter implements Runnable {
    private String path;
    private int interval;
	private Aenderungsliste aenderungsliste;
	private boolean running;
	private CountDownLatch latch;

    public VerzeichnisWaechter(String path, int interval, Aenderungsliste aenderungsliste, CountDownLatch latch) {
        this.path = path;
        this.interval = interval;
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

		File f = new File(this.path);
		int bisher = f.listFiles().length;

		this.aenderungsliste.sendUpdate("Initialisiere Überwachung von " + f.getAbsolutePath() +
				": " + bisher + " Datei-/Verzeichnis-Einträge" );
		this.latch.countDown();

		while(this.running) {
			if(f.listFiles().length!=bisher) {
				bisher = f.listFiles().length;
				this.aenderungsliste.sendUpdate("Änderungen in " + f.getAbsolutePath() +
						": " + bisher + " Datei-/Verzeichnis-Einträge");
			}
			try {
				Thread.sleep(this.interval);
			} catch(InterruptedException ex) {}
		}

		this.aenderungsliste.sendUpdate("Beende Überwachung von " + f.getAbsolutePath());
    }
}
