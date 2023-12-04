package xadrezPeca;

import tabuleiro.Tabua;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Torre extends PecaXadrez {

	public Torre(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
		return mat;
	}
}