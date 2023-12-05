package xadrezPeca;

import tabuleiro.Tabua;
import tabuleiro.Posicao;
import xadrez.PecaXadrez;
import xadrez.Cor;

public class Bispo extends PecaXadrez {

	public Bispo(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}

	@Override
	public String toString() {
		return "B";
	}
	
    @Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
			
		Posicao p = new Posicao(0, 0);
		
		// noroeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() -1);
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() -1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nordeste
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudoeste
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValues(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}