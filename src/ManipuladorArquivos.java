import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ManipuladorArquivos {
	
	String baseDir;
	
	public ManipuladorArquivos(String diretorio){
		this.baseDir = diretorio + "/";		
	}
	
	public BufferedReader buffArquivo(String lado, String tam) throws IOException {

		String nomeArq = baseDir + lado + tam + "x" + tam + ".txt";
		//System.out.println(nomeArq);
		FileReader arq = new FileReader(nomeArq);
		BufferedReader lerArq = new BufferedReader(arq);
		return lerArq;
	}
	
	public void escreverArquivo(int[][] mat, int lin, int col, Tempo tempoTotal) throws IOException {
		String nomeArquivo = "C" + lin + "x" + col + ".txt";

		File file = new File(baseDir + nomeArquivo);
		FileWriter fileWriter = new FileWriter(file);

		Writer writer = new BufferedWriter(fileWriter);
		writer.write(lin + " " + col);
		writer.write("\n");

		for (int i = 0; i < lin; i++) {
			for (int j = 0; j < col; j++) {
				writer.write(mat[i][j] + " ");
			}
			writer.write("\n");
		}
		writer.write("Tempo Inicial: " + tempoTotal.tempoInicial + "\n");
		tempoTotal.tempoFinal = System.nanoTime();
		writer.write("Tempo Final: " + tempoTotal.tempoFinal + "\n");
		writer.write("Tempo Total: " + tempoTotal.getTempTotal() + "\n");
		writer.close();
	}

}
