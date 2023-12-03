package tabuleiro;

public abstract class Peca {
	
	protected Posicao posicao;
	private Tabua tabua;
	
	public Peca(Tabua tabua) {
		this.tabua = tabua;
		posicao = null;
	}

	protected Tabua getTabua() {
		return tabua;
	}
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Posicao posicao) {
		return possibleMoves()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}