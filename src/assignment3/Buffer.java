package assignment3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * This class is the buffer or storage where food items are stored. It can hold
 * a maximum of 50 food items. The producers and consumers are stopped from
 * using the buffer at the same time by using semaphores
 *
 * @author Nicolai Jaynes
 *
 */
public class Buffer {
	private Queue<FoodItem> queue;
	private boolean filled;
	private Controller controller;
	private static Semaphore semaphore;

	public Buffer(Controller controller) {
		this.controller = controller;
		queue = new LinkedList<FoodItem>();
		filled = false;
		semaphore = new Semaphore(1); // Semaphore which controlls that one
									  //thread at a time can access the put and get methods
	}

	/**
	 * Puts a food item in the buffer
	 *
	 * @param item Item to be put in buffer
	 * @throws InterruptedException
	 */
	public void put(FoodItem item) throws InterruptedException {
		semaphore.acquire(); // Acquires permit
		if (!filled) {
			queue.add(item);
			controller.setCurrentAmtInBuffer(size());
			controller.incrementProgressBar();
			if (queue.size() == 50) {
				filled = true;
			} else if (queue.size() < 50) {
				filled = false;
			}
		}
		semaphore.release(); // Releases permit once item has been added to queue
	}

	/**
	 * Gets a food item from the buffer
	 *
	 * @return FoodItem from the queue
	 * @throws InterruptedException
	 */
	public FoodItem get() throws InterruptedException {
		semaphore.acquire(); // Acquires permit
		if (!queue.isEmpty()) {
			try {
				return queue.remove();
			} finally {
				controller.decrementtProgressBar();
				controller.setCurrentAmtInBuffer(size());
				if (queue.size() < 50) {
					filled = false;
				}
				semaphore.release();
			}
		}
		semaphore.release();
		return null; // If the queue is empty return null
	}

	public boolean bufferFull() {
		return filled;
	}

	public int size() {
		return queue.size();
	}

}
