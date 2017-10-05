/**
 * Classe responsavel por conter a posição inicial e final 
 * que será usado pelo método de Concorrente para realizar
 * como limite para realizar suas multiplicações
 * 
 */
public class IntervaloLinhas {
	int inicio;
	int fim;

	public IntervaloLinhas() {

	}

	public IntervaloLinhas(int inicio, int fim) {

		this.inicio = inicio;
		this.fim = fim;
	}

	/**
	 * Retorna a posição inicial
	 * 
	 * @return a posição incial em Inteiro
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * Altera a posição Inicial
	 * 
	 * @params inicio nova posição a ser atribuida
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	/**
	 * Retorna a posicao final
	 * 
	 * @return a posicação final em Inteiro
	 */
	public int getFim() {
		return fim;
	}

	/**
	 * Altera a posição final 
	 * 
	 * @param fim nova posição que será atribida
	 */
	public void setFim(int fim) {
		this.fim = fim;
	}

}
