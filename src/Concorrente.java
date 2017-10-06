import java.io.File;
import java.util.Hashtable;

/**
 * Classe responsavel por conter o metodo
 * que realiza a multiplicação de matrizes quadradas	
 * no formato concorrente
 * 
 */
public class Concorrente {
	
	/**
     * Metodo principal que se encarregará
     * de prover todos os recursos necessarios
     * para a multiplicação de matrizes no formato
     * concorrente 
     *
     * @param args Parametro passado pelo usuario no terminal
     * @param tempo variavel que ficará encarregada de calcular o tempo total da execução
     */
	public void executar(String[] args, Tempo tempo) {

		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();

		int tamanho = Integer.parseInt(args[0]);
		int qtdThreads = Integer.parseInt(args[1]);

		if (qtdThreads > tamanho) {
			throw new Error("A quantidade de Threads n�o pode ser maior que quantidade de linhas");
		}

		String nomeDoArquivoDaPrimeiraMatriz = diretorio + "/A" + tamanho + "x" + tamanho + ".txt";
		String nomeDoArquivoDaSegundaMatriz = diretorio + "/B" + tamanho + "x" + tamanho + ".txt";

		int[][] matrizA = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaPrimeiraMatriz);
		int[][] matrizB = mMatrizes.CriarEPreencherMatriz(nomeDoArquivoDaSegundaMatriz);

		if (matrizA[0].length == matrizB.length) {

			int qtdLinhasPorThreads = matrizA.length / qtdThreads;
			int resto = matrizA.length % qtdThreads;
			Hashtable<IntervaloLinhas, ManipuladorMatrizesThread> hashIntervaloThread = criarHasthTableThread(
					qtdThreads, matrizA, matrizB, qtdLinhasPorThreads, resto);

			try {
				for (IntervaloLinhas key : hashIntervaloThread.keySet()) {
					hashIntervaloThread.get(key).run();
					hashIntervaloThread.get(key).join();
				}
			} catch (InterruptedException e) {
				System.err.println("Erro na sincroniza��o: " + e.getMessage());
			}

			for (IntervaloLinhas key : hashIntervaloThread.keySet()) {
				int[][] mat = hashIntervaloThread.get(key).getMatResultado();
				mMatrizes.juntarMatrizesPorIntervalo(mat, key, matrizA.length, matrizB[0].length);
			}

			mArquivos.escreverArquivo(mMatrizes.getMatrizResultado(), tempo);

		} else {
			throw new Error("Não é possivel multiplicar essas matrizes!");
		}
	}

	/**
	 * Cria��o da HashTable tendo sua chave o intervalo de posi��es, e seu valor a Thread
	 * 
	 * @param qtdThreads
	 * 				- Quantidade de Threads solicitado pelo usu�rio que n�o poder� ser maior que a Quantidade de Linhas
	 * @param matrizA
	 * 				- Matriz A para multiplica��o
	 * @param matrizB
	 * 				- Matriz B para multiplica��o
	 * @param qtdLinhasPorThreads
	 * 				- Quantidade de linhas por Thread, valor entre Linhas/qtdThreads
	 * @param resto
	 * 				- Resto da divis�o quando ela n�o � exata 
	 * @return table hash contendo as threads e respectivas linhas
	 */
	private static Hashtable<IntervaloLinhas, ManipuladorMatrizesThread> criarHasthTableThread(int qtdThreads,
			int[][] matrizA, int[][] matrizB, int qtdLinhasPorThreads, int resto) {
		Hashtable<IntervaloLinhas, ManipuladorMatrizesThread> hashIntervaloThread = new Hashtable<IntervaloLinhas, ManipuladorMatrizesThread>();

		int contadorLinhas = 0;
		int ultimaPosicaoIntervalo = 0;
		int contadorThreads = 0;
		int finalPosicaoIntervalo = 0;

		while (contadorThreads != qtdThreads) {

			contadorLinhas++;
			if (contadorLinhas == qtdLinhasPorThreads) {
				contadorThreads++;
				IntervaloLinhas addIntervalo = criarIntervaloLinhas(qtdThreads, resto, ultimaPosicaoIntervalo,
						contadorThreads, finalPosicaoIntervalo);

				ManipuladorMatrizesThread addThread = new ManipuladorMatrizesThread(matrizA, matrizB, addIntervalo);

				hashIntervaloThread.put(addIntervalo, addThread);

				ultimaPosicaoIntervalo = finalPosicaoIntervalo + 1;
				contadorLinhas = 0;
			}
			finalPosicaoIntervalo++;
		}
		return hashIntervaloThread;
	}

	/**
	 * M�todo respons�vel por criar os intervalos de posi��o que a thread poder� trabalhar
	 * atentando que a �ltima Thread sempre ter� mais trabalho pois ficar� com o resto da divis�o 
	 * quando n�o for poss�vel dividir igualmente
	 * 
	 * @param qtdThreads
	 * 				- Quantidade de Threads solicitada pelo usu�rios
	 * @param resto
	 * 				- Resto da divis�o, quando a divis�o de Linhas pela quantidade de Threads n�o for exata
	 * @param ultimaPosicao
	 * 				- Ultima posi��o + 1 que foi utilizada para a thread anterior
	 * @param contadorThreads
	 * 				- Contador controle das Threads que ir� confirmar se j� � a �ltima Thread que dever� ser gerada.
	 * @param finalPosicao
	 * 				- Posi��o final que a Thread poder� trabalhar 
	 * @return objeto com o Intervalo de posi��o de Linhas.
	 */
	private static IntervaloLinhas criarIntervaloLinhas(int qtdThreads, int resto, int ultimaPosicao,
			int contadorThreads, int finalPosicao) {
		IntervaloLinhas intervaloLinhas = new IntervaloLinhas();
		intervaloLinhas.setInicio(ultimaPosicao);
		intervaloLinhas.setFim(finalPosicao);
		if (resto > 0 && contadorThreads == qtdThreads) {
			intervaloLinhas.setFim(finalPosicao + resto);
		}
		return intervaloLinhas;
	}

}
