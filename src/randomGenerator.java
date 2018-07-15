import java.util.Random;

public class randomGenerator {

	Random rand = new Random();

	public int[] getRandomArray(int size) {

		int[] out = new int[size];

		for (int i = 0; i < out.length; i++) {
			out[i] = rand.nextInt(size);
		}

		return out;

	}

	public double[][] getRandomMatrix(int size) {

		double[][] out = new double[size][size];

		for (int i = 0; i < size; i++) {

			for (int x = 0; x < size; x++) {
				out[i][x] = rand.nextDouble();
			}
		}
		return out;
	}

}
