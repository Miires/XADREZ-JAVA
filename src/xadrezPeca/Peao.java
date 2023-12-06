package xadrezPeca;

import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
	
	private PartidaXadrez partidaXadrez;

	public Peao(Tabua tabua, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabua, cor);
		this.partidaXadrez = partidaXadrez;
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
			
			// specialmove en passant white
			if (posicao.getLinha() == 3) {
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabua().posicaoExists(left) && isThereOpponentPeca(left) && getTabua().peca(left) == partidaXadrez.getEnPassantVulnerable()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabua().posicaoExists(right) && isThereOpponentPeca(right) && getTabua().peca(right) == partidaXadrez.getEnPassantVulnerable()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
			}
		}
		else {
				p.setValues(posicao.getLinha() + 1, posicao.getColuna());
				if (getTabua().posicaoExists(p) && !getTabua().thereIsAPeca(p) ) {
					mat[p.getLinha()][p.getColuna()] = true;
				} 
				p.setValues(posicao.getLinha() + 2, posicao.getColuna());
				Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
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
				
				// specialmove en passant black
				if (posicao.getLinha() == 4) {
					Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabua().posicaoExists(left) && isThereOpponentPeca(left) && getTabua().peca(left) == partidaXadrez.getEnPassantVulnerable()) {
						mat[left.getLinha() + 1][left.getColuna()] = true;
					}
					Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
					if (getTabua().posicaoExists(right) && isThereOpponentPeca(right) && getTabua().peca(right) == partidaXadrez.getEnPassantVulnerable()) {
						mat[right.getLinha() + 1][right.getColuna()] = true;
					}
				}
			}
			return mat;
		}
	
	@Override
	public String toString() {
		return "P";
	}
}