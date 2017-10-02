import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		// public long start = 0, end =0;
		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();
		

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();
		Scanner ler = new Scanner(System.in);
		int linA = 0, colA = 0;
		int linB = 0, colB = 0;
		int espacoPosicao;
		int matA[][];
		int matB[][];
		int resultado[][];

		BufferedReader buffArq;
		String linhaBuff;
		int tam;
		
		/*URL resource = Principal.class.getResource("abc");
		try {
			System.out.println(Paths.get(resource.toURI()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//System.out.println(directory.getAbsolutePath().toString());
		

		// System.out.println("Tamannho da Matriz para Multiplicação: ");
		
		//TODO: TROCAR PARA ARGS ANTES DE ENVIAR PARA EVERTON
		//String tamanho = args[0];
		String tamanho = ler.nextLine();
		
		System.out.println(directory.getAbsolutePath());
		//String teste =  directory.getAbsolutePath().toString()+ "\\A"+ tamanho + "x" + tamanho + ".txt";
		//System.out.println("Ai vem o caminho");
		//System.out.println(teste);
		//System.err.println("Testando");
		
		
		try {
			//do {
				Tempo tempo = new Tempo();
				tempo.tempoInicial = System.nanoTime();
				buffArq = mArquivos.buffArquivo("A", tamanho);
				System.out.println("Lendo arquivo");
				tam = Integer.parseInt(tamanho);
				linhaBuff = buffArq.readLine();
				espacoPosicao = linhaBuff.lastIndexOf(' ');

				linA = Integer.parseInt(linhaBuff.substring(0, espacoPosicao));
				colA = Integer.parseInt(linhaBuff.substring(espacoPosicao + 1));

				matA = new int[linA][colA];

				mMatrizes.preencheMatriz(linA, colA, matA, buffArq);
				buffArq.close();

				buffArq = mArquivos.buffArquivo("B", tamanho);
				linhaBuff = buffArq.readLine();
				espacoPosicao = linhaBuff.lastIndexOf(' ');

				linB = Integer.parseInt(linhaBuff.substring(0, espacoPosicao));
				colB = Integer.parseInt(linhaBuff.substring(espacoPosicao + 1));

				matB = new int[linB][colB];

				mMatrizes.preencheMatriz(linB, colB, matB, buffArq);
				buffArq.close();

				if (colA == linB) {

					resultado = new int[colA][linB];
					resultado = mMatrizes.multiplicar(matA, matB, linA, colA, linB, colB);

					/*ManipuladorMatrizesThread mManipuladorMatrizesThreadPar = new ManipuladorMatrizesThread(matA, matB,
							colA, linA, colB, linB, true);
					ManipuladorMatrizesThread mManipuladorMatrizesThreadImpar = new ManipuladorMatrizesThread(matA,
							matB, colA, linA, colB, linB, false);

					mManipuladorMatrizesThreadPar.start();
					mManipuladorMatrizesThreadImpar.start();

					try {
						mManipuladorMatrizesThreadPar.join();
						mManipuladorMatrizesThreadImpar.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					int[][] matrizPar = new int[colA][linB];
					matrizPar = mManipuladorMatrizesThreadPar.getMatResultado();

					int[][] matrizImpar = new int[colA][linB];
					matrizImpar = mManipuladorMatrizesThreadImpar.getMatResultado();

					resultado = mMatrizes.juntarMatrizes(matrizPar, matrizImpar, colA, linB);*/

					// mMatrizes.imprimeMatriz(resultado, colA, linB);
					//mArquivos.escreverArquivo(resultado, colA, linB, tempo);
					mMatrizes.imprimeMatriz(resultado, colA, linB);
				}
				tam = tam * 2;
				tamanho = Integer.toString(tam);
			//} while (tam < 5);

			/*
			 * System.out.println(); imprimeMatriz(matA, linA, colA);
			 * System.out.println(); imprimeMatriz(matB, linB, colB);
			 */

			System.out.println("Terminei Tudo");

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		

	}

}
