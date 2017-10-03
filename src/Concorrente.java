import java.io.BufferedReader;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

public class Concorrente {

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
		int matrizThreads[][][];
		int matrizResultado[][];
		String[] valores;

		BufferedReader buffArq;
		String linhaBuff;

		int tam;
		int qtdThreads;

		String tamanho = ler.nextLine();
		if (!tamanho.contains(" ")) {
			System.err.println("Entrada inv�lida, � necess�rio dois valores num�ricos");
			return;
		} else {
			valores = tamanho.split(" ");
			if (valores.length != 2) {
				System.err.println("Entrada inv�lida, � necess�rio dois valores num�ricos");
				return;
			}
		}

		try {
			tam = Utils.tryParse(valores[0]);
			qtdThreads = Utils.tryParse(valores[1]);
			if (qtdThreads > tam) {
				System.err.println("A quantidade de Threads n�o pode ser maior que quantidade de linhas");
				return;
			}

			// Marca��o de tempo
			Tempo tempo = new Tempo();
			tempo.tempoInicial = System.nanoTime();

			// Abertura do Arquivo MATRIZ A
			buffArq = mArquivos.buffArquivo("A", valores[0]);

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
			// Fechamento Matriz A

			// ABRINDO ARQUIVO B
			buffArq = mArquivos.buffArquivo("B", valores[0]);
			linhaBuff = buffArq.readLine();
			espacoPosicao = linhaBuff.lastIndexOf(' ');

			MatrizesTamanhos matrizBsize = new MatrizesTamanhos();
			lin = Utils.tryParse(linhaBuff.substring(0, espacoPosicao));
			col = Utils.tryParse(linhaBuff.substring(espacoPosicao + 1));

			matrizBsize.setLinha(lin);
			matrizBsize.setColuna(col);

			if (matrizAsize.coluna != matrizBsize.linha) {
				System.err.println(
						"Imposs�vel realizar multiplica��o onde o Tamanho da Coluna de A � diferente do tamanho da coluna de B");
				return;
			}

			matB = new int[matrizBsize.linha][matrizBsize.coluna];

			mMatrizes.preencheMatriz(matrizBsize.linha, matrizBsize.coluna, matB, buffArq);
			buffArq.close();
			// FECHANDO ARQUIVO B

			if (matrizAsize.coluna == matrizBsize.linha) {

				int qtdLinhasPorThreads = matrizAsize.linha / qtdThreads;
				int resto = matrizAsize.linha % qtdThreads;
				Hashtable<PosicaoPorThread, ManipuladorMatrizesThread> hashPosicaoThread = new Hashtable<PosicaoPorThread, ManipuladorMatrizesThread>();

				int contadorLinhas = 0;
				int ultimaPosicao = 0;
				// divisor de linhas por thread, considerando que cada a �ltima
				// threa ficar� com o sua parte e o resto da divis�o
				int contadorThreads = 0;
				int finalPosical = 0;
				while(contadorThreads != qtdThreads){
					
					contadorLinhas++;
					if ((contadorLinhas == qtdLinhasPorThreads)) {
						contadorThreads++;
						PosicaoPorThread addPosicao = new PosicaoPorThread();
						addPosicao.setStart(ultimaPosicao);
						addPosicao.setEnd(finalPosical);
						if (resto > 0 && contadorThreads == qtdThreads) {
							addPosicao.setEnd(finalPosical + resto);
						}

						ManipuladorMatrizesThread addThread = new ManipuladorMatrizesThread();
						addThread.setColA(matrizAsize.coluna);
						addThread.setColB(matrizBsize.coluna);
						addThread.setLinA(matrizAsize.linha);
						addThread.setLinB(matrizBsize.linha);
						addThread.setMatA(matA);
						addThread.setMatB(matB);
						addThread.setPosicao(addPosicao);
						

						hashPosicaoThread.put(addPosicao, addThread);
						System.out.println(
								"Thread para posicao " + addPosicao.start + " At� posicao " + addPosicao.end);

						ultimaPosicao = finalPosical + 1;
						contadorLinhas = 0;
					}
					finalPosical++;
				}
				
				
				
				
				
				
				
				/*

					for (int i = 0; i < qtdThreads; i++) {
						contadorLinhas++;
						if ((contadorLinhas == qtdLinhasPorThreads)) {
							PosicaoPorThread addPosicao = new PosicaoPorThread();
							addPosicao.setStart(ultimaPosicao);
							addPosicao.setEnd(i);
							if (resto > 0 && i == qtdThreads - 1) {
								addPosicao.setEnd(i + resto);
							}

							ManipuladorMatrizesThread addThread = new ManipuladorMatrizesThread();
							addThread.setColA(matrizAsize.coluna);
							addThread.setColB(matrizBsize.coluna);
							addThread.setLinA(matrizAsize.linha);
							addThread.setLinB(matrizBsize.linha);
							addThread.setMatA(matA);
							addThread.setMatB(matB);
							addThread.setPosicao(addPosicao);

							hashPosicaoThread.put(addPosicao, addThread);
							System.out.println(
									"Thread para posicao " + addPosicao.start + " At� posicao " + addPosicao.end);

							ultimaPosicao = i + 1;
							contadorLinhas = 0;
						}
					}
				*/

				for (PosicaoPorThread key : hashPosicaoThread.keySet()) {
					hashPosicaoThread.get(key).run();
					hashPosicaoThread.get(key).join();
				}

				System.out.println("Terminei de processar");

				for (PosicaoPorThread key : hashPosicaoThread.keySet()) {
					int[][] mat = hashPosicaoThread.get(key).getMatResultado();
					mMatrizes.juntarMatrizesPorPosicao(mat, key, matrizAsize.linha, matrizBsize.coluna);
				}

				System.out.println("Terminei a jun��o");

				mMatrizes.imprimeMatriz(mMatrizes.getMatrizResultado(), matrizAsize.linha, matrizBsize.coluna);
				mArquivos.escreverArquivo(mMatrizes.getMatrizResultado(),  matrizAsize.linha, matrizBsize.coluna, tempo);
				//tempo.tempoFinal = System.nanoTime();
				
				System.out.println("O Tempo total de execu�ao foi de: " + tempo.getTempTotal());

				
				
				/*
				 * ArrayList<PosicaoPorThread> listaPosicao = new
				 * ArrayList<PosicaoPorThread>();
				 * 
				 * int contadorLinhas = 0; int ultimaPosicao = 0; //divisor de
				 * linhas por thread, considerando que cada a �ltima threa
				 * ficar� com o sua parte e o resto da divis�o for(int i = 0; i<
				 * qtdThreads;i++){ contadorLinhas++; if((contadorLinhas ==
				 * qtdLinhasPorThreads) ){ PosicaoPorThread add = new
				 * PosicaoPorThread(); add.setStart(ultimaPosicao);
				 * add.setEnd(i); if(resto > 0 && i == qtdThreads-1){
				 * add.setEnd(i+resto); } ultimaPosicao = i+1;
				 * listaPosicao.add(add); contadorLinhas = 0; } }
				 * 
				 * ArrayList<ManipuladorMatrizesThread> matrizesThreads = new
				 * ArrayList<ManipuladorMatrizesThread>(); for (PosicaoPorThread
				 * posicaoPorThread : listaPosicao) { ManipuladorMatrizesThread
				 * add = new ManipuladorMatrizesThread();
				 * add.setColA(matrizAsize.coluna);
				 * add.setColB(matrizBsize.coluna);
				 * add.setLinA(matrizAsize.linha);
				 * add.setLinB(matrizBsize.linha); add.setMatA(matA);
				 * add.setMatB(matB); add.setPosicao(posicaoPorThread);
				 * matrizesThreads.add(add); }
				 * 
				 * for (ManipuladorMatrizesThread manipuladorMatrizesThread :
				 * matrizesThreads) { manipuladorMatrizesThread.start();
				 * manipuladorMatrizesThread.join(); } System.out.println(
				 * "multiplica��o terminada");
				 * 
				 * 
				 * 
				 * 
				 * for(int i = 0; i<listaPosicao.size(); i++){
				 * mMatrizes.juntarMatrizesPorPosicao(manipuladorMatrizesThread[
				 * i]., posicao, contadorLinhas, col); }
				 * 
				 * 
				 * for (ManipuladorMatrizesThread manipuladorMatrizesThread :
				 * matrizesThreads) { mMatrizes.juntarMatrizesPorPosicao(matA,
				 * posicao, contadorLinhas, col);
				 * 
				 * }
				 */

				// matrizThreads = new
				// int[qtdThreads][matrizAsize.linha][matrizBsize.coluna];
				// ArrayList<ManipuladorMatrizesThread> matrizesThreads = new
				// ArrayList<ManipuladorMatrizesThread>();

			}

		} catch (Exception e) {
			System.err.println("Erro " + e.getMessage());
		}

	}

}
