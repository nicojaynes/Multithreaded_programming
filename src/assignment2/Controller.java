package assignment2;

import java.awt.Color;
import java.util.Random;

import javax.swing.JTextArea;

/**
 * Controller class which handles communication between classes and has
 * methods that get called when the different buttons are clicked in the interface
 * @author Nicolai Jaynes
 *
 */
public class Controller {
	private boolean sync;
	private boolean writerFinished;
	private boolean readerFinished;
	private String transmitted;
	private String received;
	private Writer writer;
	private Reader reader;
	private WriterThread writerThread;
	private ReaderThread readerThread;
	private CharacterBuffer buffer;
	private GUIMutex gui;

	/**
	 * Constructor which initializes some variables and starts the interface
	 * @param gui
	 */
	public Controller(GUIMutex gui) {
		this.gui = gui;
		gui.setController(this);
		writerFinished = false;
        readerFinished = false;
        buffer = new CharacterBuffer(this);
        reader = new Reader(this,buffer);
        writer = new Writer(this,buffer);
        gui.start();
	}

	/**
	 * Method which gets called when the run button is clicked
	 */
	public void run() {
		String textToTransfer = gui.getTxtTrans().getText();
		writer.setTextToTransfer(textToTransfer);
		reader.setLength(textToTransfer.length());
		writer.setSync(sync);
		reader.setSync(sync);
		gui.getBtnRun().setEnabled(false);
		writerThread = new WriterThread(writer);
		readerThread = new ReaderThread(reader);
		writerThread.start();
		readerThread.start();
	}

	/**
	 * Method which gets called when the clear button is clicked
	 */
	public void clear() {
		transmitted = "";
		received = "";
		buffer.clearBuffer();
		gui.clearGUI();
	}

	/**
	 * Method which gets called when the writer is finished
	 */
	public void writerFinished() {
		writerFinished = true;
		if(readerFinished == true) {
			compare();
		}
	}

	/**
	 * Method which gets called when the reader is finished
	 */
	public void readerFinished() {
		readerFinished = true;
		if(writerFinished == true) {
			compare();
		}
	}

	public void setSync(boolean sync) {
		this.sync = sync;
	}

	public void addCharToTransmitted(String character) {
		transmitted += character;
	}

	public void addCharToReceived(String character) {
		received += character;
	}

	public void displayWriterLog(String string) {
		gui.getListW().append(string);
	}

	public void displayReaderLog(String string) {
		gui.getListR().append(string);
	}

	public void setWriterLabel() {
		gui.getLblTrans().setText(transmitted);
	}

	public void setReaderLabel() {
		gui.getLblRec().setText(received);
	}

	/**
	 * Method which compares the transmitted and received strings.
	 * If they match the result panel will become green and the status label will say "MATCH".
	 * If not the result panel will become red and the status label will say "NO MATCH"
	 */
	public void compare() {
		writerFinished = false;
		readerFinished = false;
		gui.getBtnClear().setEnabled(true);

		if(received.equals(transmitted)) {
			gui.getLblStatus().setText("MATCH");
			gui.getPnlRes().setBackground(Color.GREEN);
		} else {
			gui.getLblStatus().setText("NO MATCH");
			gui.getPnlRes().setBackground(Color.RED);
		}
	}
}
