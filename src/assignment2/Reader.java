package assignment2;

import java.util.Random;

import javax.swing.JLabel;

/**
 * Reader class which reads characters from the buffer, one character at a time
 * @author Nicolai Jaynes
 *
 */
public class Reader {
	private Controller controller;
	private CharacterBuffer buffer;
	private Random random;
	private int length;
	private boolean sync;

	public Reader (Controller controller, CharacterBuffer buffer) {
		this.controller = controller;
		this.buffer = buffer;
		random = new Random();
	}

	/**
	 * Sets how many characters to read. Which will be based on
	 * the length of the string that is fetched from the txtToTrans
	 * textfield in the gui.
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public void readChar() {
		for (int i = 0; i < length; i++) {
			if(sync == true) {
				buffer.syncGetChar();
			} else {
				buffer.getChar();
			}
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controller.setReaderLabel();
		controller.readerFinished();
	}

}
