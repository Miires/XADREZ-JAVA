package xadrez;

import tabuleiro.Tabua;

public class PartidaXadrez {
	
	private static Tabua tabua;
	
	public PartidaXadrez() {
		tabua = new Tabua(8, 8);
	}
	
	public static PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabua.getLinhas()][tabua.getColunas()];            
	    for (int i=0; i<tabua.getLinhas(); i++) {
	    	for (int j=0; j<tabua.getColunas(); j++); {
	    		int j = 0;
				mat[i][j] = (PecaXadrez) tabua.peca(i, j);
	    	}
	    }
	    return mat;
	}	
}