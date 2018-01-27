package assignment4;

/**
 * Modifier class.
 * This class starts a thread which calls the method "modify" in the buffer the
 * amount of times that there is strings to check and modify.
 * @author Nicolai Jaynes
 *
 */
public class Modifier implements Runnable {
	private BoundedBuffer buffer;
	private int count;
	private Thread thread;

	/**
	 * Constructor which takes a shared buffer and the number of strings
	 * to be checked.
	 * @param buffer
	 * @param nbrOfStrings
	 */
	public Modifier(BoundedBuffer buffer, int nbrOfStrings) {
		this.buffer = buffer;
		this.count = nbrOfStrings;
	}

	public void modifierLoop() throws InterruptedException {
		for (int i = 0; i < count; i++) {
			buffer.modify();
		}
	}

	public void run() {
		try {
			modifierLoop();
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
