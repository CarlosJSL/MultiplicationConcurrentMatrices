import java.io.File;
/**
 * Classe responsavel por conter o metodo
 * que realiza a multiplicação de matrizes quadradas	
 * no formato sequencial
 * 
 */
public class Sequencial {
	
	/**
     * Metodo principal que se encarregará
     * de prover todos os recursos necessarios
     * para a multiplicação de matrizes no formato
     * sequencial 
     *
     * @param args parametro passado pelo usuario no terminal
     * @param tempo variavel que ficará encarregada de calcular o tempo
     * 				total da execução
     */
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
