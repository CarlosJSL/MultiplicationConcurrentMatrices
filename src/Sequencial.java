import java.io.File;
import java.util.Scanner;

public class Sequencial {

	public static void main(String[] args) {
		Tempo tempo = new Tempo();
		tempo.tempoInicial = System.nanoTime();

		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();

		Scanner ler = new Scanner(System.in);
		String tamanho = ler.nextLine();

		String nomeDoArquivoDaPrimeiraMatriz = diretorio + "/A" + tamanho + "x" + tamanho + ".txt";
		String nomeDoArquivoDaSegundaMatriz = diretorio + "/B" + tamanho + "x" + tamanho + ".txt";

		int[][] matrizA = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaPrimeiraMatriz);
		int[][] matrizB = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaSegundaMatriz);

		if (matrizA[0].length != matrizB.length) {
			throw new Error("Impossivel realizar multiplicacao");
		} else {
			int[][] matrizResultado = new int[matrizA.length][matrizB[0].length];
			matrizResultado = mMatrizes.multiplicar(matrizA, matrizB);
			mArquivos.escreverArquivo(matrizResultado, tempo);
			mMatrizes.imprimeMatriz(matrizResultado);
		}
	}
}
