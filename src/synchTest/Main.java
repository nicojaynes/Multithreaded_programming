package synchTest;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Number number = new Number();
		ThreadDemo T1 = new ThreadDemo("Thread - 1 ", number);
		ThreadDemo T2 = new ThreadDemo("Thread - 2 ", number);

		T1.start();
		T2.start();
	}

}

class ThreadDemo extends Thread {
	private Thread t;
	private String threadName;
	Number number;
	private Random rand;

	ThreadDemo(String name, Number number) {
		this.threadName = name;
		this.number = number;
		rand = new Random();
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				number.incrementNumber(threadName);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
