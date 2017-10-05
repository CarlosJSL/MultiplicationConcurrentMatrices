import java.io.File;
import java.util.Hashtable;

/**
 * Classe responsavel por conter o metodo
 * que realiza a multiplicaÃ§Ã£o de matrizes quadradas	
 * no formato concorrente
 * 
 */
public class Concorrente {
	
	/**
     * Metodo principal que se encarregarÃ¡
     * de prover todos os recursos necessarios
     * para a multiplicaÃ§Ã£o de matrizes no formato
     * concorrente 
     *
     * @param args Parametro passado pelo usuario no terminal
     * * @param tempo variavel que ficarÃ¡ encarregada de calcular o tempo
     * 				  total da execuÃ§Ã£o
     */
	public void executar(String[] args, Tempo tempo) {

		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();

		int tamanho = Integer.parseInt(args[0]);
		int qtdThreads = Integer.parseInt(args[1]);

		if (qtdThreads > tamanho) {
			throw new Error("A quantidade de Threads nï¿½o pode ser maior que quantidade de linhas");
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
				System.err.println("Erro na sincronizaï¿½ï¿½o: " + e.getMessage());
			}

			for (IntervaloLinhas key : hashIntervaloThread.keySet()) {
				int[][] mat = hashIntervaloThread.get(key).getMatResultado();
				mMatrizes.juntarMatrizesPorIntervalo(mat, key, matrizA.length, matrizB[0].length);
			}

			mArquivos.escreverArquivo(mMatrizes.getMatrizResultado(), tempo);

		} else {
			throw new Error("NÃ£o Ã© possivel multiplicar essas matrizes!");
		}
	}

	/**
	 * Criação da HashTable tendo sua chave o intervalo de posições, e seu valor a Thread
	 * 
	 * @param qtdThreads
	 * 				- Quantidade de Threads solicitado pelo usuário que não poderá ser maior que a Quantidade de Linhas
	 * @param matrizA
	 * 				- Matriz A para multiplicação
	 * @param matrizB
	 * 				- Matriz B para multiplicação
	 * @param qtdLinhasPorThreads
	 * 				- Quantidade de linhas por Thread, valor entre Linhas/qtdThreads
	 * @param resto
	 * 				- Resto da divisão quando ela não é exata
	 * @return
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
	 * Método responsável por criar os intervalos de posição que a thread poderá trabalhar
	 * atentando que a última Thread sempre terá mais trabalho pois ficará com o resto da divisão 
	 * quando não for possível dividir igualmente
	 * 
	 * @param qtdThreads
	 * 				- Quantidade de Threads solicitada pelo usuários
	 * @param resto
	 * 				- Resto da divisão, quando a divisão de Linhas pela quantidade de Threads não for exata
	 * @param ultimaPosicao
	 * 				- Ultima posição + 1 que foi utilizada para a thread anterior
	 * @param contadorThreads
	 * 				- Contador controle das Threads que irá confirmar se já é a última Thread que deverá ser gerada.
	 * @param finalPosicao
	 * 				- Posição final que a Thread poderá trabalhar 
	 * @return objeto com o Intervalo de posição de Linhas.
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
