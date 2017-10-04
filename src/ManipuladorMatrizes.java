import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManipuladorMatrizes {

	int[][] matrizResultado;

	public int[][] multiplicar(int[][] matA, int[][] matB) {

		int[][] matC = new int[matA.length][matB[0].length];
		int soma = 0;
		for (int i = 0; i < matA.length; i++) {
			for (int j = 0; j < matB[0].length; j++) {
				for (int x = 0; x < matA[0].length; x++) {
					soma += matA[i][x] * matB[x][j];
				}
				matC[i][j] = soma;
				soma = 0;
			}
		}
		return matC;
	}

	public void imprimeMatriz(int[][] matrizImprimir) {
		for (int i = 0; i < matrizImprimir.length; i++) {
			for (int j = 0; j < matrizImprimir[0].length; j++) {
				System.out.print(matrizImprimir[i][j] + " ");
			}
		}
	}

	public int[][] CriarEPreencherMatriz(String nomeDoArquivoASerLido) {
		String[] valores;
		try {
			FileReader arquivo = new FileReader(nomeDoArquivoASerLido);
			BufferedReader arquivoSendoLido = new BufferedReader(arquivo);

			String linhaDoArquivo = arquivoSendoLido.readLine();

			int linhaMatrizA = Integer.parseInt(linhaDoArquivo.split(" ")[0]);
			int colunaMatrizA = Integer.parseInt(linhaDoArquivo.split(" ")[1]);
			int[][] matrizA = new int[linhaMatrizA][colunaMatrizA];

			for (int i = 0; i < matrizA.length; i++) {
				valores = arquivoSendoLido.readLine().split(" ");
				for (int j = 0; j < matrizA[0].length; j++) {
					matrizA[i][j] = Integer.parseInt(valores[j]);
				}
			}
			arquivoSendoLido.close();
			return matrizA;
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return null;
	}

	public void juntarMatrizesPorPosicao(int[][] matA, PosicaoPorThread posicao, int[][] matriz) {
		if (this.matrizResultado == null) {
			matrizResultado = new int[matriz.length][matriz[0].length];
		}

		for (int i = posicao.start; i <= posicao.end; i++) {
			//if (i >= posicao.start && i <= posicao.end) {
				for (int j = 0; j < matriz[0].length; j++) {
					this.matrizResultado[i][j] = matA[i][j];
				}
			//}
		}
	}

	public int[][] getMatrizResultado() {
		return matrizResultado;
	}

}
