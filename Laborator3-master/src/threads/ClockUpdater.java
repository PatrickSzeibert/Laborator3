package threads;

import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockUpdater implements Runnable {
    private final JLabel clockLabel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ClockUpdater(JLabel clockLabel) {
        this.clockLabel = clockLabel;
    }

    @Override
    public void run() {
        while (true) {
            String currentTime = dateFormat.format(new Date());
            clockLabel.setText("Current Time: " + currentTime);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
