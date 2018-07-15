import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class main {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		int[] size = { 100, 200, 500, 1000 }; // test sizes

		int n = 7; // run 7 times for each size

		randomGenerator rand = new randomGenerator();

		double sekvRes[][] = new double[size.length][n];// holds runningtime og
														// sekv

		double parRes[][] = new double[size.length][n];// holds runningtime og
														// par

		sekv sekv = new sekv();

		par par = new par();

		Timer timer = new Timer();

		// first we test if the program calculates correctly
		double aSekv[][];

		double bSekv[][];

		double aPar[][] = new double[100][100];
		double bPar[][] = new double[100][100];

		int counter = 0;
		for (int i = 0; i < 100; i++) {
			for (int x = 0; x < 100; x++) {
				aPar[i][x] = counter;

				counter++;
			}
		}
		for (int i = 0; i < 100; i++) {
			bPar[i] = aPar[i].clone();
		}

		par.setMatrix(aPar, bPar,100);
		par.multiply();

		long sum = 0;
		for (int i = 0; i < aPar.length; i++) {
			for (int x = 0; x < aPar.length; x++) {
				sum += par.getResult()[i][x];
			}

		}
		System.out.println(sum);
		// test end , expected result is 25078325250000

		for (int i = 0; i < size.length; i++) { // init the random arrays

			for (int x = 0; x < n; x++) {

				aSekv = rand.getRandomMatrix(size[i]);//get 2 random matrixes
				bSekv = rand.getRandomMatrix(size[i]);
				aPar = new double[size[i]][size[i]];
				bPar = new double[size[i]][size[i]];

				for (int y = 0; y < aSekv.length; y++) { // make a copy of the
															// arrays
					aPar[y] = aSekv[y].clone();
					bPar[y] = bSekv[y].clone();
				}

				timer.start();// start timer
				sekv.multiply(aSekv, bSekv);// start sekv execution
				sekvRes[i][x] = timer.stop();// stop timer and save result
				timer.reset();// reset timer

				par.setMatrix(aPar, bPar, size[i]);// set the matrix
				timer.start();// start timer
				par.multiply();// start par execution
				parRes[i][x] = timer.stop();// stop timer and save result
				timer.reset();// reset timer

				test(sekv.getResult(), par.getResult(), size[i]);// test if the
																	// //results
																	// // are //
																	// identical
																	// }

			}
		}
		
		//print the result to console as well as a file called "Result.txt"
		PrintWriter writer = new PrintWriter(new File("Result.txt"));
		for (int x = 0; x < size.length; x++) {

			Arrays.sort(sekvRes[x]);
			Arrays.sort(parRes[x]);

			writer.println("-------------" + size[x] + "-------------");
			writer.println("Sekv = " + sekvRes[x][n / 2]);
			writer.println("Par = " + parRes[x][n / 2]);
			writer.println("Sekv/Par = " + (sekvRes[x][n / 2] / parRes[x][n / 2]) + "\n");
			writer.println("----------------------------\n");

			System.out.println("\n");
			System.out.println("-------------" + size[x] + "-------------");
			System.out.println("Sekv = " + sekvRes[x][n / 2]);
			System.out.println("Par = " + parRes[x][n / 2]);
			System.out.println("Sekv/Par = " + (sekvRes[x][n / 2] / parRes[x][n / 2]));
			System.out.println("----------------------------");

		}
		writer.close();

	}

	// used for testing the answers agains eachother
	static void test(double[][] a, double[][] b, int size) {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {

				if (a[x][y] != b[x][y]) {
					System.out.println("Error found, exitin");
					System.exit(1);
				}

			}
		}
		System.out.println("Test passed for " + size);
	}

}
