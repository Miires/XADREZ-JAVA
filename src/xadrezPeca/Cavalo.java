package xadrezPeca;

import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

	public Cavalo(Tabua tabua, Cor cor) {
		super(tabua, cor);	
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean canMove(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabua().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() + 2);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() -1, posicao.getColuna() + 2);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() +1, posicao.getColuna() + 2);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getTabua().posicaoExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}