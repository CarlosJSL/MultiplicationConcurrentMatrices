import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Sequencial {

	public static void main(String[] args) {

		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();
		Scanner ler = new Scanner(System.in);
		int lin = 0, col = 0;
		int espacoPosicao;
		int matA[][];
		int matB[][];
		int matrizResultado[][];

		BufferedReader buffArq;
		String linhaBuff;
		int tam;

		String tamanho = ler.nextLine();

		try {
			tam = Utils.tryParse(tamanho);

			// Marcação de tempo
			Tempo tempo = new Tempo();
			tempo.tempoInicial = System.nanoTime();

			// Abertura do Arquivo MATRIZ A
			buffArq = mArquivos.buffArquivo("A", tamanho);

			linhaBuff = buffArq.readLine();
			espacoPosicao = linhaBuff.lastIndexOf(' ');
			
			MatrizesTamanhos matrizAsize = new MatrizesTamanhos();
			lin = Utils.tryParse(linhaBuff.substring(0, espacoPosicao));
			col = Utils.tryParse(linhaBuff.substring(espacoPosicao + 1));
			
			matrizAsize.setLinha(lin);
			matrizAsize.setColuna(col);

			matA = new int[matrizAsize.linha][matrizAsize.coluna];

			mMatrizes.preencheMatriz(matrizAsize.linha, matrizAsize.coluna, matA, buffArq);
			buffArq.close();
			//Fechamento Matriz A

			//ABRINDO ARQUIVO B
			buffArq = mArquivos.buffArquivo("B", tamanho);
			linhaBuff = buffArq.readLine();
			espacoPosicao = linhaBuff.lastIndexOf(' ');
			
			MatrizesTamanhos matrizBsize = new MatrizesTamanhos();
			lin = Utils.tryParse(linhaBuff.substring(0, espacoPosicao));
			col = Utils.tryParse(linhaBuff.substring(espacoPosicao + 1));

			matrizBsize.setLinha(lin);
			matrizBsize.setColuna(col);
	
			if(matrizAsize.coluna != matrizBsize.linha){
				System.err.println("Impossível realizar multiplicação onde o Tamanho da Coluna de A é diferente do tamanho da coluna de B");
				return;
			}
			
			matB = new int[matrizBsize.linha][matrizBsize.coluna];

			mMatrizes.preencheMatriz(matrizBsize.linha, matrizBsize.coluna, matB, buffArq);
			buffArq.close();
			//FECHANDO ARQUIVO B

			if (matrizAsize.coluna == matrizBsize.linha) {
				matrizResultado = new int[matrizAsize.linha][matrizBsize.coluna];
				matrizResultado = mMatrizes.multiplicar(matA, matB, matrizAsize.linha, matrizAsize.coluna, matrizBsize.linha, matrizBsize.coluna);
				mArquivos.escreverArquivo(matrizResultado,  matrizAsize.linha, matrizBsize.coluna, tempo);
				mMatrizes.imprimeMatriz(matrizResultado, matrizAsize.linha, matrizBsize.coluna);
			}		
			

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		} catch (NumberFormatException nfe) {
			System.err.printf("Erro na entrada " + nfe.getMessage());
			return;
		}

	}

}
