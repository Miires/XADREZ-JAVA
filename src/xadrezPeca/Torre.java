package xadrezPeca;

import tabuleiro.Tabua;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
}
