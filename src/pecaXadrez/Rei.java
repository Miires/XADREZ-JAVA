package pecaXadrez;

import tabuleiro.Tabua;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabua tabua, Cor cor) {
		super(tabua, cor);	
	}
	
	@Override
	public String toString() {
		return "R";
	}
}
