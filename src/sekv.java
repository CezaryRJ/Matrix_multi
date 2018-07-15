
public class sekv {

	int n;

	private double c[][];

	void multiply(double[][] a, double[][] b) {

		transpose(b);

		double sum;

		n = a.length;

		c = new double[n][n];// init result array

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				sum = 0;
				for (int k = 0; k < n; k++) {

					sum += a[y][k] * b[x][k];// sum
				}
				c[y][x] = sum;

			}
		}
	}

	public static void transpose(double[][] A) {
		double temp;
		int aRows = A.length;
		int aColumns = A[0].length;

		for (int i = 0; i < aRows; i++) {
			for (int j = i + 1; j < aColumns; j++) {
				temp = A[i][j];
				A[i][j] = A[j][i];
				A[j][i] = temp;
			}
		}
	}

	void print() {
		for (int i = 0; i < n; i++) {
			for (int x = 0; x < n; x++) {
				System.out.print(c[i][x] + " ");
			}
			System.out.println();
		}
	}

	double[][] getResult() {
		return c;
	}

}
