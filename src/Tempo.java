
/**
 * Classe responsavel por calcular o tempo da multiplicação de matrizes
 *
 */
public class Tempo {
	public long tempoInicial;
	public long tempoFinal;

	/**
	 * Retorna o tempo inicial da execução da multiplicação
	 * 
	 * @return tempo inicial da execucao em nanossegundos
	 */
	public long getTempoInicial() {
		return tempoInicial;
	}

	/**
	 * Altera o tempo inicial da execução da multiplicação
	 * 
	 * @param tempoInicial novo tempo a ser atribuido
	 */
	public void setTempoInicial(long tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	/**
	 * Retorna o tempo final da execução da multiplicação
	 * 
	 * @return tempo final da execucao em nanossegundos
	 */
	public long getTempoFinal() {
		return tempoFinal;
	}

	/**
	 * Altera o tempo final da execução da multiplicação
	 * 
	 * @param tempoFinal novo tempo a ser atribuido
	 */
	public void setTempoFinal(long tempoFinal) {
		this.tempoFinal = tempoFinal;
	}

	/**
	 * Retorna o tempo total da execução da multiplicação
	 * 
	 * @return tempo total da execucao em milissegundos
	 */
	public long getTempTotal() {
		return (this.tempoFinal - this.tempoInicial) / 1000000;
	}
}
