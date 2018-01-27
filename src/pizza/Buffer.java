package pizza;

public class Buffer {
	private int[] buffer;
	private int count = 0, inPos = 0, outPos = 0;
	private Status[] status;

	public Buffer(int size) {
		buffer = new int[size];
		status = new Status[size];
		for (int i = 0; i < status.length; i++) {
			status[i] = Status.EMPTY;
		}
	}

	public enum Status {
		NEW, EMPTY;
	}

	public synchronized void put(int pizzaNr) {
		if (status[inPos] == Status.EMPTY) {
			buffer[inPos] = pizzaNr;
			status[inPos] = Status.NEW;
			System.out.println("Pizza produced!");
			inPos = (inPos + 1) % buffer.length;
		}
	}

	public synchronized int get() {
		if (status[outPos] == Status.NEW) {
			try {
				return buffer[outPos];
			} finally {
				status[outPos] = Status.EMPTY;
				outPos = (outPos + 1) % buffer.length;
			}
		}
		return 0;
	}


}
