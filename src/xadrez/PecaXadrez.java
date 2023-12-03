package xadrez;

import tabuleiro.Tabua;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca{
	
	private Cor cor;

	public PecaXadrez(Tabua tabua, Cor cor) {
			super(tabua);
			this.cor = cor;
	}

	public Cor getCor() {
			return cor;
	}
	protected boolean isThereOpponentPeca(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabua().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}