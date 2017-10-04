
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

	public int[][] getMatA() {
		return matA;
	}

	public void setMatA(int[][] matA) {
		this.matA = matA;
	}

	public int[][] getMatB() {
		return matB;
	}

	public void setMatB(int[][] matB) {
		this.matB = matB;
	}

	public int getColA() {
		return colA;
	}

	public void setColA(int colA) {
		this.colA = colA;
	}

	public int getLinA() {
		return linA;
	}

	public void setLinA(int linA) {
		this.linA = linA;
	}

	public int getColB() {
		return colB;
	}

	public void setColB(int colB) {
		this.colB = colB;
	}

	public int getLinB() {
		return linB;
	}

	public void setLinB(int linB) {
		this.linB = linB;
	}

	public int[][] getMatResultado() {
		return matResultado;
	}

	public void setMatResultado(int[][] matResultado) {
		this.matResultado = matResultado;
	}

	public IntervaloLinhas getPosicao() {
		return posicao;
	}

	public void setPosicao(IntervaloLinhas posicao) {
		this.posicao = posicao;
	}

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

	@Override
	public void run() {
		this.matResultado = new int[linA][colB];
		int soma = 0;

		for (int i = posicao.inicio; i <= posicao.fim; i++) {
			//if (i >= posicao.start && i <= posicao.end) {
			for (int j = 0; j < colB; j++) {
				for (int x = 0; x < colA; x++) {
					soma += matA[i][x] * matB[x][j];
				}
				this.matResultado[i][j] = soma;
				soma = 0;
			}
			//}
		}

	}
	
	

}
