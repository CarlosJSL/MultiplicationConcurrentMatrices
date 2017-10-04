public class MainConcorrente {

	public static void main(String[] args) {
		Tempo tempo = new Tempo();
		tempo.tempoInicial = System.nanoTime();

		if (args.length != 2 || Character.isLetter(args[0].charAt(0)) || Character.isLetter(args[1].charAt(0))) {
			throw new Error("Entrada invalida, e necessario dois valores numericos");
		} else {
			Concorrente rodarConcorrente = new Concorrente();
			rodarConcorrente.executar(args, tempo);
		}
	}
}
