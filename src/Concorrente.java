import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Concorrente {

	public static void main(String[] args) {
		Tempo tempo = new Tempo();
		tempo.tempoInicial = System.nanoTime();

		String[] valores;

		File directory = new File("");
		final String diretorio = directory.getAbsolutePath().toString();

		ManipuladorArquivos mArquivos = new ManipuladorArquivos(diretorio);
		ManipuladorMatrizes mMatrizes = new ManipuladorMatrizes();

		Scanner ler = new Scanner(System.in);
		valores = ler.nextLine().split("");

		if (valores.length != 3 || Character.isLetter(valores[0].charAt(0)) || Character.isLetter(valores[2].charAt(0))) {
			throw new Error("Entrada invalida, e necessario dois valores numericos");
		}

		int tamanho = Integer.parseInt(valores[0]);
		int qtdThreads = Integer.parseInt(valores[2]);

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
			Hashtable<PosicaoPorThread, ManipuladorMatrizesThread> hashPosicaoThread = new Hashtable<PosicaoPorThread, ManipuladorMatrizesThread>();

			int contadorLinhas = 0;
			int ultimaPosicao = 0;
			// divisor de linhas por thread, considerando que cada a �ltima
			// threa ficar� com o sua parte e o resto da divis�o
			int contadorThreads = 0;
			int finalPosicao = 0;
			while (contadorThreads != qtdThreads) {

				contadorLinhas++;
				if ((contadorLinhas == qtdLinhasPorThreads)) {
					contadorThreads++;
					PosicaoPorThread addPosicao = new PosicaoPorThread();
					addPosicao.setStart(ultimaPosicao);
					addPosicao.setEnd(finalPosicao);
					if (resto > 0 && contadorThreads == qtdThreads) {
						addPosicao.setEnd(finalPosicao + resto);
					}

					ManipuladorMatrizesThread addThread = new ManipuladorMatrizesThread();
					addThread.setColA(matrizA[0].length);
					addThread.setColB(matrizB[0].length);
					addThread.setLinA(matrizA.length);
					addThread.setLinB(matrizB.length);
					addThread.setMatA(matrizA);
					addThread.setMatB(matrizB);
					addThread.setPosicao(addPosicao);

					hashPosicaoThread.put(addPosicao, addThread);
					System.out.println("Thread para posicao " + addPosicao.start + " At� posicao " + addPosicao.end);

					ultimaPosicao = finalPosicao + 1;
					contadorLinhas = 0;
				}
				finalPosicao++;
			}

			try {
				for (PosicaoPorThread key : hashPosicaoThread.keySet()) {
					hashPosicaoThread.get(key).run();
					hashPosicaoThread.get(key).join();
				}
			} catch (InterruptedException e) {
				System.err.println("Erro na sincroniza��o: " + e.getMessage());
			}

			for (PosicaoPorThread key : hashPosicaoThread.keySet()) {
				int[][] mat = hashPosicaoThread.get(key).getMatResultado();
				mMatrizes.juntarMatrizesPorPosicao(mat, key, matrizA);
			}

			mMatrizes.imprimeMatriz(mMatrizes.getMatrizResultado());
			mArquivos.escreverArquivo(mMatrizes.getMatrizResultado(), tempo);

		} else {
			throw new Error("Não é possivel multiplicar essas matrizes!");
		}
	}
}
