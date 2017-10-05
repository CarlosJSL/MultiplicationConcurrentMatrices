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
	 * Retorna a posi��o inicial
	 * 
	 * @return a posi��o incial em Inteiro
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * Altera a posi��o Inicial
	 * 
	 * @params inicio nova posi��o a ser atribuida
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	/**
	 * Retorna a posicao final
	 * 
	 * @return a posica��o final em Inteiro
	 */
	public int getFim() {
		return fim;
	}

	/**
	 * Altera a posi��o final 
	 * 
	 * @param fim nova posi��o que ser� atribida
	 */
	public void setFim(int fim) {
		this.fim = fim;
	}

}
