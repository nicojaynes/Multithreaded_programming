package pizza;

public class PizzaMain {
	public static void main(String[] args) {
		Buffer buffer = new Buffer(10);

		new Producer(buffer).start();
		new Consumer(buffer).start();
	}
}
