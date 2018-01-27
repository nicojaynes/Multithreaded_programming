package producerconsumer;

public class Producer extends Thread {
	private Buffer buffer;
	private char[] charsToWrite;

	public Producer(Buffer buffer, char[] charsToWrite) {
		this.buffer = buffer;
		this.charsToWrite = charsToWrite;
	}

	public void run() {
		for (int i = 0; i < charsToWrite.length; i++) {
			try {
				System.out.println("Writing: " + charsToWrite[i]);
				buffer.produce(charsToWrite[i]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
