
/**
 * @author Wesley Reuel
 *
 */
public class Tempo {
	public long tempoInicial;
	public long tempoFinal;
	
	public Tempo(){
		
	}
	
	public long getTempoInicial() {
		return tempoInicial;
	}
	public void setTempoInicial(long tempoInicial) {
		this.tempoInicial = tempoInicial;
	}
	public long getTempoFinal() {
		return tempoFinal;
	}
	public void setTempoFinal(long tempoFinal) {
		this.tempoFinal = tempoFinal;
	}
	
	public long getTempTotal(){
		return (this.tempoFinal - this.tempoInicial)/1000;
	}
	

}
