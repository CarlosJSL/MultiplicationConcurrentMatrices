import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[][] matriceOne = new int[1][2];
		int[][] matriceTwo = new int[2][3];

		feedMatrice(matriceOne);
		feedMatrice(matriceTwo);
		// printMatrice(matrizOne);
		// System.out.println("");
		// printMatrice(matrizTwo);

		long start = System.nanoTime();
		matricesMultiplication(matriceOne, matriceTwo);
		long elapsed = System.nanoTime() - start;

		System.out.println("Time spent in nanoseconds:" + elapsed);
		// printMatrice(matricesMultiplication(matrizOne, matrizTwo));
	}

	public static void printMatrice(int[][] matrice) {
		for (int row = 0; row < matrice.length; row++) {
			for (int column = 0; column < matrice[0].length; column++) {
				System.out.println(row + "x" + column + ":" + matrice[row][column]);
			}
		}

	}

	public static void feedMatrice(int[][] matrice) {
		for (int row = 0; row < matrice.length; row++) {
			for (int column = 0; column < matrice[0].length; column++) {
				Random random = new Random();
				matrice[row][column] = random.nextInt(11); // numbers from 0 to 10
			}
		}
	}

	public static int[][] matricesMultiplication(int[][] matriceOne, int[][] matriceTwo) {
		int[][] resultantMatrice = new int[matriceOne.length][matriceTwo[0].length];

		for (int row = 0; row < resultantMatrice.length; row++) {
			for (int column = 0; column < resultantMatrice[0].length; column++) {
				int sum = 0;
				for (int k = 0; k < matriceOne[0].length; k++) {
					sum = sum + (matriceOne[row][k] * matriceTwo[k][column]);

				}
				resultantMatrice[row][column] = sum;
			}
		}

		return resultantMatrice;
	}
}
