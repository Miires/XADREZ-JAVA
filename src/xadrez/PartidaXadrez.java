package xadrez;

import tabuleiro.Tabua;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrezPeca.Rei;
import xadrezPeca.Torre;

public  class PartidaXadrez {
	
	private Tabua tabua;
	
	public PartidaXadrez() {
		tabua = new Tabua(8, 8);
		initialSetup();
		
	}
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabua.getLinhas()][tabua.getColunas()];            
	    for (int i = 0; i < tabua.getLinhas(); i++) {
	    	for (int j = 0; j < tabua.getColunas(); j++) {	
	    		mat[i][j] = (PecaXadrez) tabua.peca(i,j);
	    	}
	    }
	    return mat;
	}
	
	public boolean[][] possibleMoves(XadrezPosic sourcePosicao) {
		Posicao posicao = sourcePosicao.toPosicao();
		validateSourcePosicao(posicao);
		return tabua.peca(posicao).possibleMoves();
	}
	
	public PecaXadrez performXadrezMove(XadrezPosic sourcePosicao, XadrezPosic targetPosicao) {
		Posicao source = sourcePosicao.toPosicao();
		Posicao target = targetPosicao.toPosicao();
		validateSourcePosicao(source);
		validateTargetPosicao(source, target);
		Peca capturedPeca = makeMove(source, target);
		return (PecaXadrez)capturedPeca;
	}
	private void placeNewPeca(char coluna, int linha, PecaXadrez peca) {
		tabua.placePeca(peca, new XadrezPosic(coluna, linha).toPosicao());		
	}
	
	private Peca makeMove(Posicao source, Posicao target) {
		Peca p = tabua.removePeca(source);
		Peca capturedPeca = tabua.removePeca(target);
		tabua.placePeca(p, target);
		return capturedPeca;
	}
	
	private void validateSourcePosicao(Posicao posicao) {
		if (!tabua.thereIsAPeca(posicao)) {
			throw new XadrezExc("Não há nenhuma peça na posição de origem");
		}
		if (!tabua.peca(posicao).isThereAnyPossibleMove()) {
			throw new XadrezExc("Não há movimentos possíveis para a peça escolhida");	
		}
	}
	
	private void validateTargetPosicao(Posicao source, Posicao target) {
		if (!tabua.peca(source).possibleMove(target)) {
			throw new XadrezExc("A peça escolhida não pode se mover para a posição alvo");
		}
	}
	
	private void initialSetup() {
		placeNewPeca('c', 1, new Torre(tabua, Cor.WHITE));
		placeNewPeca('c', 2, new Torre(tabua, Cor.WHITE));
		placeNewPeca('d', 2, new Torre(tabua, Cor.WHITE));
		placeNewPeca('e', 2, new Torre(tabua, Cor.WHITE));
		placeNewPeca('e', 1, new Torre(tabua, Cor.WHITE));
		placeNewPeca('d', 1, new Rei(tabua, Cor.WHITE));

		placeNewPeca('c', 7, new Torre(tabua, Cor.BLACK));
		placeNewPeca('c', 8, new Torre(tabua, Cor.BLACK));
		placeNewPeca('d', 7, new Torre(tabua, Cor.BLACK));
		placeNewPeca('e', 7, new Torre(tabua, Cor.BLACK));
		placeNewPeca('e', 8, new Torre(tabua, Cor.BLACK));
		placeNewPeca('d', 8, new Rei(tabua, Cor.BLACK));
	}
}	