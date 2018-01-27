package pizza;

public class Producer extends Thread {
	private Buffer buffer;
	private int pizzaNr = 0;

	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		while(true) {
			pizzaNr++;
			buffer.put(pizzaNr);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
