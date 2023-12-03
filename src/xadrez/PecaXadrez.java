package xadrez;

import tabuleiro.Tabua;
import tabuleiro.Peca;

public class PecaXadrez extends Peca{
	
	private Cor cor;

	public PecaXadrez(Tabua tabua, Cor cor) {
		super(tabua);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
}