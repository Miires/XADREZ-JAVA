package xadrez;

import tabuleiro.Tabua;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca{
	
	private Cor cor;
	private int moveCount;
	
	public PecaXadrez(Tabua tabua, Cor cor) {
			super(tabua);
			this.cor = cor;
	}

	public Cor getCor() {
			return cor;
	}
	
	public int getMoveCount() {
		return moveCount;
	}

	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}

	public XadrezPosic getXadrezPosic() {
		return XadrezPosic.fromPosicao(posicao);
	}
	
	protected boolean isThereOpponentPeca(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabua().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}