package xadrezPeca;

import tabuleiro.Tabua;
import tabuleiro.Posicao;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Torre extends PecaXadrez {

	public Torre(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}

	@Override
	public String toString() {
		return "T";
	}
	
    @Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
			
		Posicao p = new Posicao(0, 0);
		
		// acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna(), - 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna(), + 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}