package pizza;

import java.util.Random;

public class Consumer extends Thread {
	private Buffer buffer;

	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}

	public void run() {
		while(true) {
			int number = buffer.get();
			if(number != 0) {
			System.out.println("Customer with ticket number " + number + " is being served");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
