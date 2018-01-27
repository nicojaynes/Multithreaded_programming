package assignment3;

/**
 * This class represents a consumer truck. It has values for what maximum amount of items, volume, and weight.
 * It also has current values that will increment as the truck loads food items.
 * @author Nicolai Jaynes
 *
 */
public class Consumer implements Runnable {
	private int maxItems;
	private int maxVolume;
	private int currentItemAmount;
	private int currentVolume;
	private double currentWeight;
	private double maxWeight;
	private boolean continueLoading;
	private boolean isRunning;
	private boolean coopRunning;
	private boolean cityGrossRunning;
	private boolean truckFull;
	private FoodItem item;
	private String name;
	private Controller controller;
	private Buffer buffer;
	private Thread thread;

	public Consumer(String name, int maxItems, double maxWeight, int maxVolume, Buffer buffer) {
		this.name = name;
		this.maxItems = maxItems;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.buffer = buffer;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public boolean truckFull() {
		return truckFull;
	}

	public void resetTruck() {
		currentItemAmount = 0;
		currentWeight = 0;
		currentVolume = 0;
		truckFull = false;
	}

	private void setStatusLoading() {
		if (name.equals("Ica")) {
			controller.setStatusIca("Loading");
		} else if (name.equals("Coop")) {
			controller.setStatusCoop("Loading");
		} else if (name.equals("City Gross")) {
			controller.setStatusCityGross("Loading");
		}
	}

	private void addItemToTextArea() {
		if (name.equals("Ica")) {
			controller.appendIcaTextArea(item.getName());
		} else if (name.equals("Coop")) {
			controller.appendListCoop(item.getName());
		} else if (name.equals("City Gross")) {
			controller.appendListCityGross(item.getName());
		}
	}

	private void setStatusFull() {
		if (name.equals("Ica")) {
			controller.setStatusIca("Full");
		} else if (name.equals("Coop")) {
			controller.setStatusCoop("Full");
		} else if (name.equals("City Gross")) {
			controller.setStatusCityGross("Full");
		}
	}

	private void checkContinueLoad() {
		if (name.equals("Ica")) {
			continueLoading = controller.continueLoadingIca();
		} else if (name.equals("Coop")) {
			continueLoading = controller.continueLoadingCoop();
		} else if (name.equals("City Gross")) {
			continueLoading = controller.continueLoadingCityGross();
		}
	}

	/**
	 * Gets a food item from the buffer
	 */
	public void run() {
		while (isRunning) {
			//Check to see if the truck is not full and if the start loading button is pressed
			if (currentItemAmount <= maxItems && currentWeight <= maxWeight && currentVolume <= maxVolume) {
				setStatusLoading();

				try {
					item = buffer.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//Check to see if the item was not null
				if (item != null) {
					// Increment the amount of items, weight and volume of the truck
					currentItemAmount += 1;
					currentWeight += item.getWeight();
					currentVolume += item.getVolume();

					// Add the name of the item loaded to the textarea
					addItemToTextArea();
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				truckFull = true; //Boolean to indicate that the truck is full

				//Sets the status to full on the current truck
				setStatusFull();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//Check if the continueload button is checked
				checkContinueLoad();

				//If The continueload checkbox is checked change to another truck and start loading it
				//If not stop loading onto the current truck
				if (continueLoading) {
					if (name.equals("Ica")) {
						controller.setStatusIca("Changing truck");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.stopIca();
						controller.runIca();
					} else if (name.equals("Coop")) {
						controller.setStatusCoop("Changing truck");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.stopCoop();
						controller.runCoop();
					} else if (name.equals("City Gross")) {
						controller.setStatusCityGross("Changing truck");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						controller.stopCityGross();
						controller.runCityGross();
					}
				} else {
					if (name.equals("Ica")) {
						controller.stopIca();
					} else if (name.equals("Coop")) {
						controller.stopCoop();
					} else if (name.equals("City Gross")) {
						controller.stopCityGross();
					}
				}
			}
		}
		thread = null;
	}


	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			setRunning(true);
			thread.start();
		}
	}

}
