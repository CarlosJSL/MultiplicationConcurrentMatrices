import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ManipuladorArquivos {

	String baseDir;

	public ManipuladorArquivos(String diretorio) {
		this.baseDir = diretorio + "/";
	}

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
			//writer.write("Tempo Inicial: " + tempoTotal.tempoInicial + "\n");
			tempoTotal.tempoFinal = System.nanoTime();
			//writer.write("Tempo Final: " + tempoTotal.tempoFinal + "\n");
			writer.write("Tempo Total: " + tempoTotal.getTempTotal() + "\n");
			System.out.println(tempoTotal.getTempTotal()+"ms");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
