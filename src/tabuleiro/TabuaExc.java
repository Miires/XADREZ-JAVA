package tabuleiro;

public class TabuaExc extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TabuaExc(String msg) {
		super(msg);
	}
}