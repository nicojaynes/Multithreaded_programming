package assignment2;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

/**
 * Writer class which writes characters to the buffer, one character at a time
 * @author Nicolai Jaynes
 *
 */
public class Writer {
	private Controller controller;
	private CharacterBuffer buffer;
	private Random random;
	private String textToTransfer;
	private boolean sync;

	public Writer (Controller controller, CharacterBuffer buffer) {
		this.buffer = buffer;
		this.controller = controller;
		random = new Random();
	}

	public void setTextToTransfer(String textToTransfer) {
		this.textToTransfer = textToTransfer;
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public void writeChar() {
		for (int i = 0; i < textToTransfer.length(); i++) {
			if (sync == true) {
				buffer.syncSetChar(textToTransfer.charAt(i));
			} else {
				buffer.setChar(textToTransfer.charAt(i));
			}
			try {
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controller.setWriterLabel();
		controller.writerFinished();
	}

}
