package assignment1;

import java.util.Random;

import javax.swing.JLabel;

/**
 * This thread starts when the button in the display thread is clicked
 * @author Nicolai Jaynes
 *
 */
public class RandomTextThread implements Runnable {
	private JLabel displayTextLabel;
	private boolean isRunning;

	public RandomTextThread(JLabel displayTextLabel) {
		this.displayTextLabel = displayTextLabel;
	}

	@Override
	public void run() {
		displayTextLabel.setVisible(true);
		while(isRunning){
			Random rand = new Random();
			displayTextLabel.setBounds(rand .nextInt(180), rand.nextInt(180), 80, 50);
			displayTextLabel.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		displayTextLabel.setVisible(false);
	}

	public void setRunning(boolean running) {
		this.isRunning = running;
	}

	public boolean isRunning() {
		return isRunning;
	}

}
