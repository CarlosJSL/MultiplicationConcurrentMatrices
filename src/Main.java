import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[][] matrizOne = new int[1][2];
		int[][] matrizTwo = new int[2][3];

		alimentarMatriz(matrizOne);
		alimentarMatriz(matrizTwo);
		imprimirMatriz(matrizOne);
		System.out.println("");
		imprimirMatriz(matrizTwo);
		multiplicarMatrizes(matrizOne, matrizTwo);
		System.out.println("");
		imprimirMatriz(multiplicarMatrizes(matrizOne, matrizTwo));
	}

	public static void imprimirMatriz(int[][] matriz) {
		for (int linha = 0; linha < matriz.length; linha++) {
			for (int coluna = 0; coluna < matriz[0].length; coluna++) {
				System.out.println(linha + "x" + coluna + ":" + matriz[linha][coluna]);
			}
		}

	}

	public static void alimentarMatriz(int[][] matriz) {
		for (int linha = 0; linha < matriz.length; linha++) {
			for (int coluna = 0; coluna < matriz[0].length; coluna++) {
				Random random = new Random();
				matriz[linha][coluna] = random.nextInt(11); // números de 0 a 10
			}
		}
	}

	public static int[][] multiplicarMatrizes(int[][] matrizOne, int[][] matrizTwo) {
		int[][] matrizResultante = new int[matrizOne.length][matrizTwo[0].length];

		for (int linha = 0; linha < matrizResultante.length; linha++) {
			for (int coluna = 0; coluna < matrizResultante[0].length; coluna++) {
				int soma = 0;
				for (int k = 0; k < matrizOne[0].length; k++) {
					soma = soma + (matrizOne[linha][k] * matrizTwo[k][coluna]);

				}
				matrizResultante[linha][coluna] = soma;
			}
		}

		return matrizResultante;
	}
}
