import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
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
			System.err.println("Entrada inválida, é necessário dois valores numéricos");
			return;
		} else {
			valores = tamanho.split(" ");
			if (valores.length != 2) {
				System.err.println("Entrada inválida, é necessário dois valores numéricos");
				return;
			}
		}

		try {
			tam = Utils.tryParse(valores[0]);
			qtdThreads = Utils.tryParse(valores[1]);
			if (qtdThreads > tam) {
				System.err.println("A quantidade de Threads não pode ser maior que quantidade de linhas");
				return;
			}

			// Marcação de tempo
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
						"Impossível realizar multiplicação onde o Tamanho da Coluna de A é diferente do tamanho da coluna de B");
				return;
			}

			matB = new int[matrizBsize.linha][matrizBsize.coluna];

			mMatrizes.preencheMatriz(matrizBsize.linha, matrizBsize.coluna, matB, buffArq);
			buffArq.close();
			// FECHANDO ARQUIVO B
			
			
			
			if (matrizAsize.coluna == matrizBsize.linha) {

				int qtdLinhasPorThreads = matrizAsize.linha / qtdThreads;
				int resto = matrizAsize.linha % qtdThreads; 
				ArrayList<PosicaoPorThread> listaPosicao = new ArrayList<PosicaoPorThread>();
				
				int contadorLinhas = 0;
				int ultimaPosicao = 0;
				//divisor de linhas por thread, considerando que cada a última threa ficará com o sua parte e o resto da divisão
				for(int i = 0; i< qtdThreads;i++){
					contadorLinhas++;
					if((contadorLinhas == qtdLinhasPorThreads) ){
						PosicaoPorThread add = new PosicaoPorThread();
						add.setStart(ultimaPosicao);
						add.setEnd(i);
						if(resto > 0 && i == qtdThreads-1){
							add.setEnd(i+resto);
						}
						ultimaPosicao = i+1;
						listaPosicao.add(add);
						contadorLinhas = 0;
					}
				}
				
				//matrizThreads = new int[qtdThreads][matrizAsize.linha][matrizBsize.coluna];
				//ArrayList<ManipuladorMatrizesThread> matrizesThreads = new ArrayList<ManipuladorMatrizesThread>();

				
				
				
				
			}

		} catch (Exception e) {
			System.err.println("Erro " + e.getMessage());
		}

	}

}


