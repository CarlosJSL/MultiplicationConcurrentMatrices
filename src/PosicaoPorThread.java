
public class PosicaoPorThread {
	int start;
	int end;
	
	public PosicaoPorThread() {
	
	}
	
	/**
	 * @param start
	 * @param end
	 */
	public PosicaoPorThread(int start, int end) {
	
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}

