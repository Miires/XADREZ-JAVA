package xadrezPeca;

import tabuleiro.Posicao;
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
	
	private boolean canMove(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabua().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// acima
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}