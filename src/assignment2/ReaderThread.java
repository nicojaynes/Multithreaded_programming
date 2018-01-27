package assignment2;

import java.util.Random;

/**
 * Thread which contains a reader object that reads characters from the buffer
 * @author Nicolai Jaynes
 *
 */
public class ReaderThread extends Thread {
	private Reader reader;

	public ReaderThread(Reader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		System.out.println("Reader thread started");
		reader.readChar();
	}
}


