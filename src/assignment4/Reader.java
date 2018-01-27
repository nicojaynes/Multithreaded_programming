package assignment4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

/**
 * Reader class.
 * This class starts a thread which calls the method "readData" in the buffer the
 * amount of times that there is strings to read.
 * @author Nicolai Jaynes
 *
 */
public class Reader implements Runnable {
	private BoundedBuffer buffer;
	private int count;
	private List<String> stringList;
	private Thread thread;
	private JTextPane destPane;
	private JMenuItem saveItem;
	private JLabel lblChanges;

	/**
	 * Constructor which takes the shared buffer, the number of strings to read as
	 * parameters.
	 * It also takes the destination pane and the saveItem Menu Item from the
	 * gui as parameters. These are needed when the reader thread is done and
	 * is going to write the strings to the destination pane and enable the
	 * save item MenuItem
	 * @param buffer
	 * @param nbrOfStrings
	 * @param gui
	 */
	public Reader(BoundedBuffer buffer, int nbrOfStrings, JTextPane destPane, JMenuItem saveItem, JLabel lblChanges) {
		this.buffer = buffer;
		this.count = nbrOfStrings;
		this.destPane = destPane;
		this.saveItem = saveItem;
		this.lblChanges = lblChanges;
		stringList = new ArrayList<String>();
	}

	public void readLoop() throws InterruptedException {
		for (int i = 0; i < count; i++) {
			stringList.add(buffer.readData());
		}
		writeToDestPane();
	}

	/**
	 * Writes the list of strings to the destination pane in the gui
	 */
	private void writeToDestPane() {
		StringBuilder text = new StringBuilder();
		for (int i = 0; i < stringList.size(); i++) {
			text.append(stringList.get(i));
		}
		destPane.setText(text.toString());
		lblChanges.setText("No. of Replacements: " + buffer.getNbrReplacements());
		saveItem.setEnabled(true);

	}

	public List<String> getStrings() {
		return stringList;
	}

	public void run() {
		try {
			readLoop();
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
