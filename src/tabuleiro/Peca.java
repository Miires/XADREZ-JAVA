package tabuleiro;

public class Peca {
	
	protected Posicao posicao;
	private Tabua tabua;
	
	public Peca(Tabua tabua) {
		this.tabua = tabua;
		posicao = null;
	}

	protected Tabua getTabua() {
		return tabua;
	}
}
