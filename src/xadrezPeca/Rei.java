package xadrezPeca;

import tabuleiro.Tabua;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Rei extends PecaXadrez {

	public Rei(Tabua tabua, Cor cor) {
		super(tabua, cor);	
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
		return mat;
	}
}
