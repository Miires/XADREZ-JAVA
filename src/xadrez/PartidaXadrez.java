package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrezPeca.Rei;
import xadrezPeca.Torre;

public  class PartidaXadrez {
	
	private int vez;
	private Cor jogadorAtual;
	private Tabua tabua;
	
	private List<Peca> PecasNoTabuleiro = new ArrayList<Peca>();
	private List<Peca> PecasCapturadas = new ArrayList<Peca>();

	public PartidaXadrez() {
		tabua = new Tabua(8, 8);
		vez = 1;
		jogadorAtual = Cor.WHITE;
		initialSetup();
	}
	
	public int getVez() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		return vez;
	}
	
	public Cor getJogadorAtual () {
		return jogadorAtual;
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
		nextVez();
		return (PecaXadrez)capturedPeca;
	}
	
	private Peca makeMove(Posicao source, Posicao target) {
		Peca p = tabua.removePeca(source);
		Peca capturedPeca = tabua.removePeca(target);
		tabua.placePeca(p, target);
		
		if (PecasCapturadas != null) {
			PecasNoTabuleiro.remove(PecasCapturadas);
		}
		
		return capturedPeca;
	}
	
	private void validateSourcePosicao(Posicao posicao) {
		if (!tabua.thereIsAPeca(posicao)) {
			throw new XadrezExc("Não há nenhuma peça na posição de origem");
		}
		if (jogadorAtual != ((PecaXadrez)tabua.peca(posicao)).getCor()) {
			throw new XadrezExc("a peça esscolhida não é sua");
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
	
	private void nextVez() {
		vez++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private void placeNewPeca(char coluna, int linha, PecaXadrez peca) {
		tabua.placePeca(peca, new XadrezPosic(coluna, linha).toPosicao());		
		PecasNoTabuleiro.add(peca);
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