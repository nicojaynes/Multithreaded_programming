package exercise1;

class Main {

	public static void main(String[] args) {
		TaskToDo task = new TaskToDo("Apu");
		Thread t1 = new Thread(task);
		t1.start();

		TaskToDo task2 = new TaskToDo("Homer");
		Thread t2 = new Thread(task2);
		t2.start();

		DoThingsOnMainThread();
	}

	static void DoThingsOnMainThread() {
		for (int i = 0; i < 10; i++) {
//			 Console.foregroundColor = ConsoleColor.Green;
			System.out.println("Greetings from Main thread!");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println("Something went wrong at the end!");
			}
		}
		System.out.println("Main thread terminated!");
		System.out.println("---------------------------------");
	}

}
