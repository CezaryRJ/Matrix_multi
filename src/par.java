
public class par {

	/*
	 * 
	 * 
	 * N MUST BE LARGER OR EQUAL TO THE AMMOUNT OF CORES USED FOR THIS TO WORK
	 * 
	 * 
	 * 
	 */
	private double c[][];
	double a[][];
	double b[][];

	int n;

	int coreCount = 0;

	Thread threads[];

	// constructor
	par() {
		coreCount = Runtime.getRuntime().availableProcessors();

		threads = new Thread[coreCount];

	}

	void print() {//print the result array
		for (int i = 0; i < n; i++) {
			for (int x = 0; x < n; x++) {
				System.out.print(c[i][x] + " ");
			}
			System.out.println();
		}
	}

	void setMatrix(double a[][], double b[][], int n) {
		this.a = a;
		this.b = b;
		this.n = n;

	}

	double[][] getResult() {
		return c;
	}

	public void transpose(double[][] A) {

		double temp;
		int aRows = A.length;
		int aColumns = A[0].length;

		for (int i = 0; i < aRows; i++) {
			for (int j = i + 1; j < aColumns; j++) {
				temp = A[i][j];
				A[i][j] = A[j][i];
				A[j][i] = temp;
			}
		} // tatt fra kode brukt i uke 3

	}

	void multiply() throws InterruptedException {

		c = new double[n][n];

		transpose(b);

		for (int i = 0; i < coreCount; i++) {
			threads[i] = new Thread(new worker(i));
		}

		for (int i = 0; i < coreCount; i++) {
			threads[i].start();
		}
		for (int i = 0; i < coreCount; i++) {
			threads[i].join();
		}

	}

	class worker implements Runnable {

		int index; // indicates the threads number

		worker(int index) {
			this.index = index;
		}

		@Override
		public void run() {//each thred calculates the n-th position in c

			double elem = 0;

			int i = index; // cell index

			int rad = 0; //x coordinate

			int rad2 = index; //y coordinate
			

			while (i < n * n) {//we have n*n cells, therefore loop unitill all cells are processed

				for (int x = 0; x < n; x++) {

					elem += a[rad][x] * b[rad2][x]; // for each cell, do the calculaton

				}

				c[rad][rad2] = elem; // save result

				i += coreCount; //advance to the threads next cell

				rad = i / n; //caclulate new x coordinate

				rad2 = i % n;// calculate new y coordinate

				elem = 0;// reset 

			}

		}

	}

}
