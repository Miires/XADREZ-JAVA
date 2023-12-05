package xadrezPeca;

import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

	public Peao(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinhas()][getTabua().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		if (getCor() == Cor.WHITE) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			} 
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)&& getTabua().posicaoExists(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabua().posicaoExists(p) && isThereOpponentPeca(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			} 
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabua().posicaoExists(p) && isThereOpponentPeca(p) ) {
				mat[p.getLinha()][p.getColuna()] = true;
			} 
		}
		else {
				p.setValues(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p) ) {
					mat[p.getLinha()][p.getColuna()] = true;
				} 
				p.setValues(posicao.getLinha() + 2, posicao.getColuna());
				Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
				if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)&& getTabua().posicaoExists(p2) && getMoveCount() == 0) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if (getTabua().posicaoExists(p) && isThereOpponentPeca(p) ) {
					mat[p.getLinha()][p.getColuna()] = true;
				} 
				p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if (getTabua().posicaoExists(p) && isThereOpponentPeca(p) ) {
					mat[p.getLinha()][p.getColuna()] = true;
				} 
			}
			return mat;
		}
	@Override
	public String toString() {
		return "P";
	}
}
