package assignment4;

/**
 * Writer class.
 * This class starts a thread which calls the method "writeData" in the buffer the
 * amount of times that there is strings to write.
 * @author Nicolai Jaynes
 *
 */
public class Writer implements Runnable {
	private BoundedBuffer buffer;
	private String[] textIn;
	private Thread thread;

	/**
	 * Constructor which takes a shared buffer and an
	 * array of strings to write to the buffer
	 * @param buffer
	 * @param textIn
	 */
	public Writer(BoundedBuffer buffer, String[] textIn) {
		this.buffer = buffer;
		this.textIn = textIn;
	}

	public void writeLoop() throws InterruptedException {
		for (int i = 0; i < textIn.length; i++) {
			buffer.writeData(textIn[i]);
		}
	}

	public void run() {
		try {
			writeLoop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread = null;
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
}
