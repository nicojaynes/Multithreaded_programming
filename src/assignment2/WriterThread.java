package assignment2;

import java.util.Random;

/**
 * Thread which contains a writer object that writes characters to the buffer
 * @author Nicolai Jaynes
 *
 */
public class WriterThread extends Thread {
	private Writer writer;

	public WriterThread (Writer writer) {
		this.writer = writer;
	}

	@Override
	public void run() {
		System.out.println("Writer thread started");
		writer.writeChar();
	}
}

