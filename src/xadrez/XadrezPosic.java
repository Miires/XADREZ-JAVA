package xadrez;

import tabuleiro.Posicao;

public class XadrezPosic {

	private char coluna;
	private int linha;
	
	public XadrezPosic(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezExc("Erro ao instanciar XadrezPosic. Os valores válidos são de a1 a h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao toPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static XadrezPosic fromPosicao(Posicao posicao) {
		return new XadrezPosic((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}