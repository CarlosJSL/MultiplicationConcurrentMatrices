
public class MainSequencial {

	public static void main(String[] args) {
		Tempo tempo = new Tempo();
		tempo.tempoInicial = System.nanoTime();
		Sequencial rodarSequencial = new Sequencial();
		rodarSequencial.executar(args,tempo);
	}
}
