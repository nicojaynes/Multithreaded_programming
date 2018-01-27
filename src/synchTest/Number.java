package synchTest;

import java.util.concurrent.Semaphore;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;



public class Number {
	private int number = 0;
	private Semaphore sem = new Semaphore(1);
	private Mutex mutex = new Mutex();
	
	//Unsyncronized
//	public void incrementNumber(String threadName) {
//		number++;
//		System.out.println(threadName + " incremented the number to " + number);
//	}
	
	//Monitor
//	public synchronized void incrementNumber(String threadName) {
//		number++;
//		System.out.println(threadName + " incremented the number to " + number);
//	}
	
	// Semaphore
	public void incrementNumber(String threadName) throws InterruptedException {
		sem.acquire();
		System.out.println(threadName + " acquired the semaphore");
		number++;
		System.out.println(threadName + " incremented the number to " + number);
		System.out.println(threadName + " will now release the semaphore");
		sem.release();
	}
	
	//Mutex
//	public void incrementNumber(String threadName) throws InterruptedException {
//		mutex.acquire();
//		System.out.println(threadName + " acquired the mutex");
//		number++;
//		System.out.println(threadName + " incremented the number to " + number);
//		System.out.println(threadName + " will now release the mutex");
//		mutex.release();
//	}
	
	

}
