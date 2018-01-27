package assignment3;

/**
 * Controller class which communicates between the different classes and the user interface
 * @author Nicolai Jaynes
 *
 */
public class Controller {
	private GUIFoodSupply gui;
	private Consumer ica;
	private Consumer coop;
	private Consumer cityGross;
	private Producer arla;
	private Producer axFood;
	private Producer scan;
	private Buffer buffer;


	/**
	 * Constructor which initializes gui, buffer and consumers
	 * @param gui
	 */
	public Controller() {
		gui = new GUIFoodSupply();
		gui.setController(this);
		buffer = new Buffer(this);
		initConsumers();
		gui.Start();
	}

	/**
	 * Method which initiates the consumers
	 */
	private void initConsumers() {
		ica = new Consumer("Ica", 15, 27, 12, buffer);
		ica.setController(this);
		cityGross = new Consumer("City Gross", 19, 22.30, 17, buffer);
		cityGross.setController(this);
		coop = new Consumer("Coop", 12, 31.50, 10, buffer);
		coop.setController(this);
	}

	public void incrementProgressBar() {
		gui.getBufferStatus().setValue(gui.getBufferStatus().getValue() + 1);
	}

	public void decrementtProgressBar() {
		gui.getBufferStatus().setValue(gui.getBufferStatus().getValue() - 1);
	}

	public void runScan() {
		scan = new Producer("Scan", buffer, this);
		scan.start();
	}

	public void runArla() {
		arla = new Producer("Arla", buffer, this);
		arla.start();
	}

	public void runAxFood() {
		axFood = new Producer("AxFood", buffer, this);
		axFood.start();
	}

	public void runIca() {
		gui.getBtnIcaStop().setEnabled(true);
		gui.getBtnIcaStart().setEnabled(false);

		//If current truck is full, change it and clear the textarea for this consumer
		if(ica.truckFull()) {
			ica = new Consumer("Ica", 15, 27, 12, buffer);
			ica.setController(this);
			clearIcaTextArea();
			ica.start();
		} else {
			ica.start();
		}
	}

	public void runCoop() {
		gui.getBtnCoopStop().setEnabled(true);
		gui.getBtnCoopStart().setEnabled(false);

		//If current truck is full, change it and clear the textarea for this consumer
		if (coop.truckFull()) {
			coop = new Consumer("Coop", 12, 31.50, 10, buffer);
			coop.setController(this);
			clearCoopTextArea();
			coop.start();
		} else {
			coop.start();
		}
	}

	public void runCityGross() {
		gui.getBtnCityGrossStop().setEnabled(true);
		gui.getBtnCityGrossStart().setEnabled(false);

		//If current truck is full, change it and clear the textarea for this consumer
		if (cityGross.truckFull()) {
			cityGross = new Consumer("City Gross", 19, 22.30, 17, buffer);
			cityGross.setController(this);
			clearCityGrossTextArea();
			cityGross.start();
		} else {
			cityGross.start();
		}
	}

	public void stopScan() {
		scan.setRunning(false);
		setStatusScan("Stopped");
	}

	public void stopArla() {
		arla.setRunning(false);
		setStatusArla("Stopped");
	}

	public void stopAxFood() {
		axFood.setRunning(false);
		setStatusAxFood("Stopped");
	}

	public void stopIca() {
		ica.setRunning(false);
		setStatusIca("Stopped");
		gui.getBtnIcaStop().setEnabled(false);
		gui.getBtnIcaStart().setEnabled(true);
	}

	public void stopCoop() {
		coop.setRunning(false);
		setStatusCoop("Stopped");
		gui.getBtnCoopStop().setEnabled(false);
		gui.getBtnCoopStart().setEnabled(true);
	}

	public void stopCityGross() {
		cityGross.setRunning(false);
		setStatusCityGross("Stopped");
		gui.getBtnCityGrossStop().setEnabled(false);
		gui.getBtnCityGrossStart().setEnabled(true);
	}

	public void setStatusScan(String status) {
		gui.getStatusScan().setText("Status: " + status);
	}

	public void setStatusArla(String status) {
		gui.getStatusArla().setText("Status: " + status);
	}

	public void setStatusAxFood(String status) {
		gui.getStatusAxFood().setText("Status: " + status);
	}

	public void setStatusIca(String status) {
		gui.getStatusIca().setText("Status: " + status);
	}

	public void setStatusCoop(String status) {
		gui.getStatusCoop().setText("Status: " + status);
	}

	public void setStatusCityGross(String status) {
		gui.getStatusCityGross().setText("Status: " + status);
	}

	public boolean continueLoadingIca() {
		boolean b;
		if(gui.getCheckBoxIca().isSelected()) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	public boolean continueLoadingCoop() {
		boolean b;
		if(gui.getCheckBoxCoop().isSelected()) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	public boolean continueLoadingCityGross() {
		boolean b;
		if(gui.getCheckBoxCityGross().isSelected()) {
			b = true;
		} else {
			b = false;
		}
		return b;
	}

	public void appendIcaTextArea(String item) {
		gui.getListIca().append(item + "\n");
	}

	public void appendListCoop(String item) {
		gui.getListCoop().append(item + "\n");
	}

	public void appendListCityGross(String item) {
		gui.getListCityGross().append(item + "\n");
	}

	public void clearIcaTextArea() {
		gui.getListIca().setText("");
	}

	public void clearCoopTextArea() {
		gui.getListCoop().setText("");
	}

	public void clearCityGrossTextArea() {
		gui.getListCityGross().setText("");
	}

	/**
	 * Sets the number of food items stored in buffer shown on interface
	 * @param size
	 */
	public void setCurrentAmtInBuffer(int size) {
		gui.getCurrentAmtInBuffer().setText("Current amount in buffer: " + size);
	}
}
