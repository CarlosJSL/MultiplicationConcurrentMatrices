import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsavel por executar metodos relativos a matriz como por exemplo
 * multiplicação,impressao e preenchimento
 *
 */
public class ManipuladorMatrizes {

	int[][] matrizResultado;

	/**
	 * Realiza a multiplicação de duas matrizes
	 * 
	 * @param matA
	 *            - primeira matriz da multiplicação
	 * @param matB
	 *            - segunda matriz da multiplicação
	 * 
	 * @return matriz resultante da multiplicação
	 */
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

	/**
	 * Realiza a impressao de uma matriz qualquer
	 * 
	 * @param matrizImprimir
	 *            - matriz a ter seus valores impressos
	 * 
	 */
	public void imprimeMatriz(int[][] matrizImprimir) {
		for (int i = 0; i < matrizImprimir.length; i++) {
			for (int j = 0; j < matrizImprimir[0].length; j++) {
				System.out.print(matrizImprimir[i][j] + " ");
			}
		}
	}

	/**
	 * Cria uma matriz e associa a ela valores existens em um arquivo txt
	 * 
	 * @param nomeDoArquivoASerLido
	 *            - nome do arquivo que conterá os valores a serem alocados em
	 *            uma determinada matriz
	 * 
	 * @return retona uma matriz preenchida com os valores que estavam em um
	 *         arquivo txt
	 */
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

	/**
	 * Juntar as matrizes resultados da multiplicacao, uma vez que cada Thread escreve apenas
	 * em uma parte da matriz, aqui ela est� tendo seus resultados unidos.
	 * @param matA
	 *             - Matriz resultado da multiplicacao
	 * @param posicao
	 *             - Range das posicao que tem os resultados validos
	 * @param linha
	 *             - Quantidade de Linhas da matriz resultado
	 * @param coluna
	 *             - Quantidade de Colunas da matriz resutlado
	 */
	public void juntarMatrizesPorIntervalo(int[][] matA, IntervaloLinhas posicao, int linha, int coluna) {
		if (this.matrizResultado == null) {
			
			matrizResultado = new int[linha][coluna];
		}

		for (int i = posicao.inicio; i <= posicao.fim; i++) {
			for (int j = 0; j < matrizResultado[0].length; j++) {
				this.matrizResultado[i][j] = matA[i][j];
			}
		}
	}
	
	/**
	 * Retorna a matriz resultante de uma multiplicação
	 * 
	 * @return retona uma matriz resultante de uma multiplicação
	 */
	public int[][] getMatrizResultado() {
		return matrizResultado;
	}
}
