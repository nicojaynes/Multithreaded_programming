package assignment4;

/**
 * This class is a bounded buffer which holds a specified amount of strings.
 * @author Nicolai Jaynes
 *
 */
public class BoundedBuffer {
	private String[] stringArray;   //The actual string buffer array
	private Status[] status;        //An array of Status objects, one for each element in buffer
	private int elements; 			//Elements in buffer
	private int writePos;			//Position pointers for each thread
	private int readPos;
	private int findPos;
	private String findString;		//The string to search for, if any
	private String replaceString;	//The replace string, if any
	private int nbrReplacements;    //Replacement counter

	/**
	 * Constructor which takes the amount of elements, findString and replaceString.
	 * The findString is the string to find and replace in the text.
	 * The replaceString is the string to replace findString with in the text.
	 * @param elements
	 * @param findString
	 * @param replaceString
	 */
	public BoundedBuffer (int elements , String findString, String replaceString) {
		this.elements = elements;
		this.findString = findString;
		this.replaceString = replaceString;
		stringArray = new String[elements];  //Initiate the array of string
 		status = new Status[elements];       //Initiate array of Status enums which indicate
 												//the status of the strings in the array
 		//Inititate all statuses to "EMPTY"
		for (int i = 0; i < status.length; i++) {
			status[i] = Status.EMPTY;
		}
		writePos = 0;
		readPos = 0;
		findPos = 0;
		nbrReplacements = 0;
	}

	public enum Status {
		EMPTY,
		CHECKED,
		NEW
	}

	public int getNbrReplacements() {
		return nbrReplacements;
	}

	/**
	 * Modify method which modifies a string if it matches
	 * the string entered by the user to replace.
	 * @throws InterruptedException
	 */
	public synchronized void modify() throws InterruptedException {
		while(status[findPos] != Status.NEW) {
			wait();
		}

		String stringToCheck = stringArray[findPos];
		if(stringToCheck.equals(findString)) {
			stringArray[findPos] = replaceString + " ";         //Replace string if it matches
			nbrReplacements++;
		} else {
			stringArray[findPos] = stringArray[findPos] + " ";  //Add an empty space between words if not
		}
		status[findPos] = Status.CHECKED;
		findPos = (findPos+1) % stringArray.length;

		notifyAll();
	}

	/**
	 * Reads a word from the string array if
	 * the status of the position is "CHECKED"
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized String readData() throws InterruptedException {
		while (status[readPos] != Status.CHECKED) {
			wait();
		}
		try {
			return stringArray[readPos];
		} finally {
			status[readPos] = Status.EMPTY;
			readPos = (readPos+1) % stringArray.length;

			notifyAll();
		}
	}

	/**
	 * Writes a word/string to the buffer
	 * @param string
	 * @throws InterruptedException
	 */
	public synchronized void writeData(String string) throws InterruptedException {
		while(status[writePos] != Status.EMPTY) {
			wait();
		}

		stringArray[writePos] = string;
		status[writePos] = Status.NEW;
		writePos = (writePos+1) % stringArray.length;

		notifyAll();
	}

}
