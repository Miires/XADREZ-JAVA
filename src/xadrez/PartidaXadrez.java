package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrezPeca.Rei;
import xadrezPeca.Torre;

public  class PartidaXadrez {
	
	private int vez;
	private Cor jogadorAtual;
	private Tabua tabua;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> PecasNoTabuleiro = new ArrayList<Peca>();
	private List<Peca> capturedPecas = new ArrayList<Peca>();

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
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
		
		if (testCheck(jogadorAtual)) {
			undoMove(source, target, capturedPeca);
			throw new XadrezExc ("Voce nao pode se colocar em check");
		}
		
		check = (testCheck(opponent(jogadorAtual))) ? true : false;
		
		if (testCheckMate(opponent(jogadorAtual))) {
			checkMate = true;
		}
		else {
			nextVez();
		}
		
		return (PecaXadrez)capturedPeca;
	}
	
	private Peca makeMove(Posicao source, Posicao target) {
		PecaXadrez p = (PecaXadrez)tabua.removePeca(source);
		p.increaseMoveCount();
		Peca capturedPeca = tabua.removePeca(target);
		tabua.placePeca(p, target);
		
		if (capturedPeca != null) {
			PecasNoTabuleiro.remove(capturedPeca);
			capturedPecas.add(capturedPeca);
		}
		
		return capturedPeca;
	}
	
	private void undoMove(Posicao source, Posicao target, Peca capturedPeca) {
		PecaXadrez p = (PecaXadrez)tabua.removePeca(target);
		p.decreaseMoveCount(); p = (PecaXadrez)tabua.removePeca(target);
		tabua.placePeca(p, source);

		if (capturedPeca != null) {
			tabua.placePeca(capturedPeca, target);
			capturedPecas.remove(capturedPeca);
			PecasNoTabuleiro.add(capturedPeca);
		}
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
	
	private Cor opponent(Cor cor) {
	    return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}

	private PecaXadrez rei(Cor cor) {
	    List<Peca> list = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
	    for (Peca p : list) {
	        if (p instanceof Rei) {
	            return (PecaXadrez) p;
			}
		}
		throw new IllegalStateException("Não existe um rei da cor " + cor);
	}
	
	private boolean testCheck(Cor cor) {
		Posicao posicaoRei = rei(cor).getXadrezPosic().toPosicao();
		List<Peca> opponentPecas = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == opponent(cor)).collect(Collectors.toList());
		for (Peca p : opponentPecas) {
			boolean[][] mat = p.possibleMoves();
			if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true; 
			}
		}
		return false;
	}
		private boolean testCheckMate(Cor cor) {
			if (!testCheck(cor)) {
				return false;
			}
			List<Peca> list = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
			for (Peca p : list) {
				boolean[][] mat = p.possibleMoves();
				for (int i=0; i<tabua.getLinhas(); i++) {
					for (int j=0; j<tabua.getColunas(); j++) {
						if (mat[i][j]) {
							Posicao source = ((PecaXadrez)p).getXadrezPosic().toPosicao();
							Posicao target = new Posicao(i, j);
							Peca capturedPeca = makeMove(source, target);
							boolean testCheck = testCheck(cor);
							undoMove(source, target, capturedPeca);
							if (!testCheck) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}	
	
	private void placeNewPeca(char coluna, int linha, PecaXadrez peca) {
		tabua.placePeca(peca, new XadrezPosic(coluna, linha).toPosicao());		
		PecasNoTabuleiro.add(peca);
	}

	private void initialSetup() {
		placeNewPeca('h', 7, new Torre(tabua, Cor.WHITE));
        placeNewPeca('d', 1, new Torre(tabua, Cor.WHITE));
        placeNewPeca('e', 1, new Rei(tabua, Cor.WHITE));
        
        placeNewPeca('b', 8, new Torre(tabua, Cor.BLACK));
        placeNewPeca('a', 8, new Rei(tabua, Cor.BLACK));
	}
}	