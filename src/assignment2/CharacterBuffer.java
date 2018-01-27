package assignment2;

import java.util.ArrayList;

import javax.swing.JTextArea;

/**
 * Characterbuffer class which can contain at most one character at a time.
 * The writer can set a character and the reader can read the character.
 * The class contains both unsynchronized and synchronized methods to do this.
 * @author Nicolai Jaynes
 *
 */
public class CharacterBuffer {
	private Controller controller;
	private Character character;
	private boolean filled;

	/**
	 * Constructor which initializes the buffer and sets the filled boolean to false.
	 * @param controller
	 */
	public CharacterBuffer(Controller controller) {
		this.controller = controller;
		filled = false;
	}

	/**
	 * Unsynchronized set character method.
	 * It sets the character to the character passed as a parameter.
	 * It displays the character on the writer log and finally adds
	 * the character to the end of the transmitted string.
	 * @param character character to set in the buffer
	 */
	public void setChar(Character character) {
		this.character = character;
		controller.displayWriterLog("Writing " + character + "\n");
		controller.addCharToTransmitted(String.valueOf(character));
	}

	/**
	 * Unsynchronized get character method.
	 * It displays the character on the reader log and finally adds
	 * the character to the end of the received string.
	 */
	public void getChar() {
		controller.displayReaderLog("Reading " + character + "\n");
		controller.addCharToReceived(String.valueOf(character));
	}

	/**
	 * Synchronized set character method.
	 * It checks if the buffer is already filled. If so it waits for notifyAll() to be called.
	 * It  then sets the character to the character passed as a parameter.
	 * It displays the character on the writer log and finally adds
	 * the character to the end of the transmitted string.
	 * The filled boolean will be set to true, since there is now a new
	 * character to be read.
	 * At the end it notifyAll is called which means the the other thread may
	 * proceed to use the buffer object.
	 * @param character character to set in the buffer
	 */
	public synchronized void syncSetChar(Character character) {
		while(filled == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.character = character;
		controller.displayWriterLog("Writing " + character + "\n");
		controller.addCharToTransmitted(String.valueOf(character));
		filled = true;
		notifyAll();
	}

	/**
	 * Synchronized get character method.
	 * It checks if the buffer is not filled. If so it waits for notifyAll() to be called.
	 * It then displays the character on the reader log and finally adds
	 * the character to the end of the received string.
	 * The filled boolean will be set to false, since there is now no new character to be read.
	 * At the end it notifyAll is called which means the the other thread may
	 * proceed to use the buffer object.
	 */
	public synchronized void syncGetChar() {
		while (filled == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controller.displayReaderLog("Reading " + character + "\n");
		controller.addCharToReceived(String.valueOf(character));
		filled = false;
		notifyAll();
	}

	/**
	 * Method which clears the buffer
	 */
	public void clearBuffer() {
		filled = false;
		this.character = null;
	}

}
