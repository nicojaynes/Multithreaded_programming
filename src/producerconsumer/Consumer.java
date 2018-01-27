package producerconsumer;

public class Consumer extends Thread {
	private Buffer buffer;
	private int count;

	public Consumer(Buffer buffer, int count) {
		this.buffer = buffer;
		this.count = count;
	}

	public void run() {
		try {
			for (int i = 0; i < count; i++) {
				System.out.println("Reading: " + buffer.consume());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
