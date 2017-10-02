import java.io.BufferedReader;
import java.io.IOException;

public class ManipuladorMatrizes {
	
	public int[][] multiplicar(int[][] matA, int[][] matB, int linA, int colA, int linB, int colB) {
		int[][] matC = new int[colA][linB];
		int soma = 0;
		for (int i = 0; i < linA; i++) {
			for (int j = 0; j < colB; j++) {
				for (int x = 0; x < colA; x++) {
					soma += matA[i][x] * matB[x][j];
				}
				matC[i][j] = soma;
				soma = 0;
			}
		}
		return matC;
	}
	
	public void imprimeMatriz(int[][] matrizImprimir, int linha, int coluna) {
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				System.out.print(matrizImprimir[i][j] + " ");
			}
			System.out.println();
		}

	}
	
	public void preencheMatriz(int lin, int col, int[][] matA, BufferedReader lerArq) throws IOException {
		String linha;
		String[] valores;
		for (int i = 0; i < lin; i++) {
			linha = lerArq.readLine();
			valores = linha.split(" ");
			for (int j = 0; j < col; j++) {
				matA[i][j] = Integer.parseInt(valores[j]);
			}
		}
	}
	
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
