package assignment1;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

/**
 * This thread starts when the button in the triangle thread is clicked
 * @author Nicolai Jaynes
 *
 */
public class RotateThread implements Runnable {
	private JPanel oval;
	private boolean isRunning;

	public RotateThread(JPanel oval) {
		this.oval = oval;
	}

	@Override
	public void run() {
		oval.setVisible(true);
		while(isRunning) {
			Random rand = new Random();
			oval.setBounds(rand.nextInt(130), rand.nextInt(130), 35, 35);
			oval.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		oval.setVisible(false);
	}

	public void setRunning(boolean running) {
		this.isRunning = running;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
