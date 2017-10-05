import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Classe responsavel por fazer a manipulação dos arquivos txt
 * 
 */
public class ManipuladorArquivos {

	String baseDir;

	public ManipuladorArquivos(String diretorio) {
		this.baseDir = diretorio + "/";
	}

	/**
	 * Metodo responsavel por gravar informações de uma matriz em um arquivo txt
	 * 
	 * @param mat  matriz que será gravada no arquivo txt
	 * @param tempoTotal tempo necessario para realizar a multiplicação de matrizes e
	 *                   gravar em um arquivo txt
	 */
	public void escreverArquivo(int[][] mat, Tempo tempoTotal) {
		String nomeArquivo = "C" + mat.length + "x" + mat[0].length + ".txt";

		File file = new File(baseDir + nomeArquivo);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			Writer writer = new BufferedWriter(fileWriter);
			writer.write(mat.length + " " + mat[0].length);
			writer.write("\n");

			for (int i = 0; i < mat.length; i++) {
				for (int j = 0; j < mat[0].length; j++) {
					writer.write(mat[i][j] + " ");
				}
				writer.write("\n");
			}
			tempoTotal.tempoFinal = System.nanoTime();
			writer.write("Tempo Total: " + tempoTotal.getTempTotal() + "\n");
			System.out.println(tempoTotal.getTempTotal() + "ms");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
