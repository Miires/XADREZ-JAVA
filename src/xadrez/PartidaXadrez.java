package xadrez;

import tabuleiro.Tabua;
import tabuleiro.Posicao;
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
	private void initialSetup() {
		tabua.placePeca(new Torre(tabua, Cor.WHITE), new Posicao(2, 1)) ;
		tabua.placePeca(new Rei(tabua, Cor.BLACK), new Posicao(0, 4));
		tabua.placePeca(new Rei(tabua, Cor.WHITE), new Posicao(7, 4));
	}
}