
public class Timer {

	private double timer = 0;

	public void start() {

		timer = System.nanoTime();

	}

	public double stop() {

		return ((System.nanoTime() - timer) / 100000);

	}

	public void reset() {
		timer = 0;
	}
}
