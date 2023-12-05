package xadrezPeca;

//import javax.swing.text.Position;

import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrez.PartidaXadrez;
import xadrez.Cor;


public class Peao extends PartidaXadrez {

	public Peao(Tabua tabua, Cor cor) {
		super(tabua, cor);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getTabua().getLinha()][getTabua().getColuna()];
		
		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.WHITE) {
			p.setValues(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p) && getTabua().posicaoExists(p2) && !getTabua().thereIsAPeca(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabua().posicaoExists(p) && isThereOpponentPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
		}
		else {
			p.setValues(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabua().positionExists(p) && !getTabua().thereIsAPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 2, posicao.getColuna());
			Position p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabua().positionExists(p) && !getTabua().thereIsAPeca(p) && getTabua().posicaoExists(p2) && !getTabua().thereIsAPeca(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabua().positionExists(p) && isThereOpponentPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabua().positionExists(p) && isThereOpponentPeca(p)) {
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