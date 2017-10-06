/**
 * Classe responsavel por conter a posi��o inicial e final 
 * que ser� usado pelo m�todo de Concorrente para realizar
 * como limite para realizar suas multiplica��es
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
	 * Retorna a posicao inicial
	 * 
	 * @return a posicao incial em Inteiro
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * Altera a posicao Inicial
	 * 
	 * @param inicio nova posicao a ser atribuida
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	/**
	 * Retorna a posicao final
	 * 
	 * @return a posicacao final em Inteiro
	 */
	public int getFim() {
		return fim;
	}

	/**
	 * Altera a posicao final 
	 * 
	 * @param fim nova posiaco que sera atribida
	 */
	public void setFim(int fim) {
		this.fim = fim;
	}

}
