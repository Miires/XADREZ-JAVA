package pecaXadrez;

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
}
