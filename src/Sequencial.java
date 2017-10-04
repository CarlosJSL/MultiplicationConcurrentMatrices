import java.io.File;

public class Sequencial {

	public void executar(String[] args, Tempo tempo) {
		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();

		String nomeDoArquivoDaPrimeiraMatriz = diretorio + "/A" + args[0] + "x" + args[0] + ".txt";
		String nomeDoArquivoDaSegundaMatriz = diretorio + "/B" + args[0] + "x" + args[0] + ".txt";

		int[][] matrizA = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaPrimeiraMatriz);
		int[][] matrizB = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaSegundaMatriz);

		if (matrizA[0].length != matrizB.length) {
			throw new Error("Impossivel realizar multiplicacao");
		} else {
			int[][] matrizResultado = new int[matrizA.length][matrizB[0].length];
			matrizResultado = mMatrizes.multiplicar(matrizA, matrizB);
			mArquivos.escreverArquivo(matrizResultado, tempo);
		}
	}
}
