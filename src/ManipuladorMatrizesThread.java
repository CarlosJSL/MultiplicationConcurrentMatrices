/**
 * Classe respons�vel por criar a Thread e guardar o resultao de sua multiplica��o.
 * 
 *
 */
public class ManipuladorMatrizesThread extends Thread {

	protected int[][] matResultado;
	protected int[][] matA;
	protected int[][] matB;
	protected int colA;
	protected int linA;
	protected int colB;
	protected int linB;
	protected IntervaloLinhas posicao;
	protected boolean par;
	
	
	public ManipuladorMatrizesThread() {

	}

	
	public ManipuladorMatrizesThread(int[][] matA, int[][] matB, IntervaloLinhas pos) {
		this.matA = matA;
		this.matB = matB;
		this.colA = matA[0].length;
		this.linA = matA.length;
		this.colB = matB[0].length;
		this.linB = matB.length;
		this.posicao = pos;
	}
	
	/**
	 * Retorna a Matriz A 
	 * @return Matriz A
	 */
	public int[][] getMatA() {
		return matA;
	}

	/**
	 * Altera o valor da Matriz A 
	 * @param matA nova matriz a ser atribuida
	 */
	public void setMatA(int[][] matA) {
		this.matA = matA;
	}

	/**
	 * Retorna o valor da Matriz B
	 * @return Matriz B
	 */
	public int[][] getMatB() {
		return matB;
	}

	/**
	 * Altera o valo da Matriz B
	 * @param matB
	 */
	public void setMatB(int[][] matB) {
		this.matB = matB;
	}

	/**
	 * Retorna a quantidade de colunas da Matriz A
	 * @return Quantidade de colunas da Matriz A
	 */
	public int getColA() {
		return colA;
	}

	/**
	 * Altera o valor de colA, quantidade de colunas da Matriz A
	 * @param colA
	 */
	public void setColA(int colA) {
		this.colA = colA;
	}

	/**
	 * Retorna a quantidade de Linhas da Matriz A
	 * @return Quantidade de linhas da Matriz A
	 */
	public int getLinA() {
		return linA;
	}

	/**
	 * Altera o valor da quantidade de linhas da Matriz A
	 * @param linA
	 */
	public void setLinA(int linA) {
		this.linA = linA;
	}

	/**
	 * Retorna a quantidade de Colunas da Matriz B
	 * @return Quantidade de colunas da Matriz B
	 */
	public int getColB() {
		return colB;
	}

	/**
	 * Altera o valor da quantidade de Colunas da Matriz B
	 * @param colB 
	 */
	public void setColB(int colB) {
		this.colB = colB;
	}

	/**
	 * Retorna a quantidade de Linhas da Matriz B
	 * @return Quantidade de linhas da Matriz B
	 */
	public int getLinB() {
		return linB;
	}

	/**
	 * Altera o valor da quantidade de linhas da Matriz B
	 * @param linB
	 */
	public void setLinB(int linB) {
		this.linB = linB;
	}

	/**
	 * Retorna a Matriz resultante, tendo apenas valores validos nas linhas que foram multiplicadas
	 *  
	 * @return retorna a Matriz Resultado
	 */
	public int[][] getMatResultado() {
		return matResultado;
	}

	/**
	 * Altera o valor da matriz resultado
	 * @param matResultado
	 */
	public void setMatResultado(int[][] matResultado) {
		this.matResultado = matResultado;
	}

	/**
	 * Retorna o objeto respons�vel por guardar o intervalo que a Thread poder� trabalhar.
	 * @return 
	 */
	public IntervaloLinhas getPosicao() {
		return posicao;
	}

	/**
	 * Altera o valor do objeto respons�vel por guardar o intervalo que a Thread poder� trabalhar
	 * @param  posicao  - nova posicao da thread
	 */
	public void setPosicao(IntervaloLinhas posicao) {
		this.posicao = posicao;
	}

	/**
	 * M�todo executado pela Thread quando � "startada"
	 */
	@Override
	public void run() {
		multiplicar();
	}

	/**
	 * M�todo respons�vel por realizar a multiplica��o da matriz.
	 */
	private void multiplicar() {
		this.matResultado = new int[linA][colB];
		int soma = 0;

		for (int i = posicao.inicio; i <= posicao.fim; i++) {
			for (int j = 0; j < colB; j++) {
				for (int x = 0; x < colA; x++) {
					soma += matA[i][x] * matB[x][j];
				}
				this.matResultado[i][j] = soma;
				soma = 0;
			}
		}
	}
}
