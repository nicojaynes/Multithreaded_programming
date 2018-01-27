package assignment3;

import java.util.Random;

/**
 * This is the producer class. Which contains an array of food items and will
 * randomly produce one of them and puts it in the storage/buffer.
 *
 * @author Nicolai Jaynes
 *
 */
public class Producer implements Runnable {
	private Random rand;
	private Buffer buffer;
	private FoodItem[] items;
	private Controller controller;
	private String name;
	private boolean isRunning;
	private Thread thread;

	public Producer(String name, Buffer buffer, Controller controller) {
		this.name = name;
		this.buffer = buffer;
		this.controller = controller;
		rand = new Random();
		items = new FoodItem[10]; // Initialize an array that can contain 10 food items
		initFoodItems();
	}

	public void initFoodItems() {
		items[0] = new FoodItem("Milk", 1.5, 1);
		items[1] = new FoodItem("Cream", 0.2, 3);
		items[2] = new FoodItem("Chicken", 1.0, 2);
		items[3] = new FoodItem("Sugar", 0.5, 1);
		items[4] = new FoodItem("Apple", 0.3, 5);
		items[5] = new FoodItem("Orange", 0.4, 2);
		items[6] = new FoodItem("Ham", 0.5, 2);
		items[7] = new FoodItem("Soda", 2.0, 1);
		items[8] = new FoodItem("Bread", 0.7, 1);
		items[9] = new FoodItem("Soap", 0.4, 3);
	}
	
	public void setStatusProducing() {
		if (name.equals("Scan")) {
			controller.setStatusScan("Producing");
		} else if (name.equals("Arla")) {
			controller.setStatusArla("Producing");
		} else if (name.equals("AxFood")) {
			controller.setStatusAxFood("Producing");
		}
	}
	
	public void setStatusIdle() {
		if (name.equals("Scan")) {
			controller.setStatusScan("Idle");
		} else if (name.equals("Arla")) {
			controller.setStatusArla("Idle");
		} else if (name.equals("AxFood")) {
			controller.setStatusAxFood("Idle");
		}
	}

	/**
	 * Produces a random food item and puts into the buffer/storage
	 */
	public void run() {
		while (isRunning) {
			if (!buffer.bufferFull()) {
				setStatusProducing();
				FoodItem randomItem = items[rand.nextInt(9)];
				try {
					buffer.put(randomItem);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				setStatusIdle();
			}
		}
		thread = null;
	}

	/**
	 * Starts the thread
	 */
	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			setRunning(true);
			thread.start();
		}
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
}
