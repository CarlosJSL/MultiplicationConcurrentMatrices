
public class ManipuladorMatrizesThread extends Thread {
	
	protected int[][] matResultado;
	protected int[][] matA;
	protected int[][] matB;
	protected int colA;
	protected int linA;
	protected int colB;
	protected int linB;
	protected boolean par;
	

	
	public ManipuladorMatrizesThread(int[][] matA, int[][] matB, int colA, int linA, int colB, int linB, boolean tipo) {
		super();
		this.matA = matA;
		this.matB = matB;
		this.colA = colA;
		this.linA = linA;
		this.colB = colB;
		this.linB = linB;
		this.par = tipo;
	}
	
	@Override
	public void run() {
		this.matResultado = new int[colA][linB];
		int soma = 0;
		for (int i = 0; i < linA; i++) {
			if(par && (i == 0 || i%2 == 0)){
				for (int j = 0; j < colB; j++) {
					for (int x = 0; x < colA; x++) {
						soma += matA[i][x] * matB[x][j];
					}
					this.matResultado[i][j] = soma;
					soma = 0;
				}	
			}
			else if(!par && (i != 0 || i%2!=0)){
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


	/*public int[][] multiplicarPares(int[][] matA, int[][] matB, int linA, int colA, int linB, int colB) {
		int[][] matC = new int[colA][linB];
		int soma = 0;
		for (int i = 0; i < linA; i++) {
			if(i == 0 || i%2 == 0){
				for (int j = 0; j < colB; j++) {
					for (int x = 0; x < colA; x++) {
						soma += matA[i][x] * matB[x][j];
					}
					matC[i][j] = soma;
					soma = 0;
				}	
			}
		}
		return matC;
	}*/
	
	
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

/*	public int[][] multiplicarImpares(int[][] matA, int[][] matB, int linA, int colA, int linB, int colB) {
		int[][] matC = new int[colA][linB];
		int soma = 0;
		for (int i = 0; i < linA; i++) {
			if(i != 0 || i%2 != 0){
				for (int j = 0; j < colB; j++) {
					for (int x = 0; x < colA; x++) {
						soma += matA[i][x] * matB[x][j];
					}
					matC[i][j] = soma;
					soma = 0;
				}	
			}
		}
		return matC;
	} */
	
	
	public int[][] juntarMatrizes(int[][] matPar, int[][] matImpar, int lin, int col){
		int[][] matResultadoFinal = new int[lin][col];
		for(int i = 0; i < lin; i++){
			for(int j = 0; j<col; j++){
				if(i==0 || i %2==0){
					matResultadoFinal[i][j] = matPar[i][j];
				}
				else {
					matResultadoFinal[i][j] = matImpar[i][j];
				}
			}
		}
		
		
		return matResultadoFinal;
	}

}
