package threads;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class OrderFileWatcher implements Runnable {
    private final File fileToWatch;
    private long lastModified;

    public OrderFileWatcher(String filePath) {
        this.fileToWatch = new File(filePath);
        this.lastModified = fileToWatch.lastModified();
    }

    @Override
    public void run() {
        while (true) {
            if (fileToWatch.lastModified() != lastModified) {
                lastModified = fileToWatch.lastModified();
                System.out.println("The order file has been updated!");
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
