package producerconsumer;

public class Main {

	public static void main(String [] args) {
		char[] charsToWrite = {'H','e','l','l','o',' ', 'd','e','a','r','!'};
		Buffer buffer = new Buffer(10);
		Producer producer = new Producer(buffer, charsToWrite);
		Consumer consumer = new Consumer(buffer, charsToWrite.length);
		producer.start();
		consumer.start();
//		for(int i = 0; i < charsToWrite.length; i++) {
//			new Consumer(buffer).start();
//		}
	}

}
