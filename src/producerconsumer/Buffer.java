package producerconsumer;

import java.util.concurrent.Semaphore;

import sun.awt.Mutex;

public class Buffer {
	private int size;
	private char[] buffer;
	private int inPos = 0;
	private int outPos = 0;
	private Semaphore sem1;
	private Semaphore sem2;
	private Mutex mutex;

	public Buffer(int size) {
		this.size = size;
		buffer = new char[size];
		sem1 = new Semaphore(0);
		sem2 = new Semaphore(size);
		mutex = new Mutex();
	}

	public void produce(char c) throws InterruptedException {
		sem2.acquire();
		buffer[inPos] = c;
		inPos = (inPos+1) % size;
		sem1.release();
	}

	public char consume() throws InterruptedException {
		try {
			sem1.acquire();
			return buffer[outPos];
		} finally {
			mutex.lock();
			outPos = (outPos+1) % size;
			mutex.unlock();
			sem2.release();
		}
	}
}
