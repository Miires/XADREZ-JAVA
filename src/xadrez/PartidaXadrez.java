package xadrez;

import tabuleiro.Tabua;
import xadrezPeca.Rei;
import xadrezPeca.Torre;

public class PartidaXadrez {
	
	private Tabua tabua;
	
	public PartidaXadrez() {
		tabua = new Tabua(8, 8);
		initialSetup();
	}

	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabua.getLinhas()][tabua.getColunas()];            
	    for (int i=0; i<tabua.getLinhas(); i++) {
	    	for (int j=0; j<tabua.getColunas(); j++) {	
	    		mat[i][j] = (PecaXadrez) tabua.peca(i,j);
	    	}
	    }
	    return mat;
	}
	
	private void placeNewPeca(char coluna, int linha, PecaXadrez peca) {
		tabua.placePeca(peca, new XadrezPosic(coluna, linha).toPosicao());
	}
	private void initialSetup() {
		placeNewPeca('b', 6, new Torre(tabua, Cor.WHITE));
		placeNewPeca('e', 8, new Rei(tabua, Cor.BLACK));
		placeNewPeca('e', 1, new Rei(tabua, Cor.WHITE));
	}
}	