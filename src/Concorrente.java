import java.io.File;
import java.util.Hashtable;

public class Concorrente {

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
				mMatrizes.juntarMatrizesPorIntervalo(mat, key, matrizA);
			}

			mArquivos.escreverArquivo(mMatrizes.getMatrizResultado(), tempo);

		} else {
			throw new Error("Não é possivel multiplicar essas matrizes!");
		}
	}

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
