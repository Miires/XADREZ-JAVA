package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabua;
import xadrezPeca.Bispo;
import xadrezPeca.Cavalo;
import xadrezPeca.Peao;
import xadrezPeca.Rainha;
import xadrezPeca.Rei;
import xadrezPeca.Torre;

public  class PartidaXadrez {
	
	private int vez;
	private Cor jogadorAtual;
	private Tabua tabua;
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassantVulnerable;
	private PecaXadrez promoted;
	
	private List<Peca> PecasNoTabuleiro = new ArrayList<>();
	private List<Peca> capturedPecas = new ArrayList<>();

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
	
	public PecaXadrez getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public PecaXadrez getPromoted() {
		return enPassantVulnerable;
	}
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabua.getLinhas()][tabua.getColunas()];            
	    for (int i=0; i<tabua.getLinhas(); i++) {
	    	for (int j=0; j<tabua.getColunas(); j++) {	
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
		
		PecaXadrez movedPeca = (PecaXadrez)tabua.peca(target);
		
		// #specialmove promotion
		promoted = null;
		if (movedPeca instanceof Peao) {
			if ((movedPeca.getCor() == Cor.WHITE && target.getLinha() == 0) || (movedPeca.getCor() == Cor.BLACK && target.getLinha() == 7)) {
				promoted = (PecaXadrez)tabua.peca(target);
				promoted = (PecaXadrez) replacePromotedPeca("R");
			}
		}
		
		check = (testCheck(opponent(jogadorAtual))) ? true : false;
		
		if (testCheckMate(opponent(jogadorAtual))) {
			checkMate = true;
		}
		else {
			nextVez();
		}
		
		// #specialmove en passant
		if (movedPeca instanceof Peao && (target.getLinha() == source.getLinha() - 2 || target.getLinha() == source.getLinha() + 2)) {
			enPassantVulnerable = movedPeca;
		}
		else {
			enPassantVulnerable = null;
		}
		
		return (PecaXadrez)capturedPeca;
 	}
	
	
	public Peca replacePromotedPeca(String type) {
		if (promoted == null) {
			throw new IllegalStateException("Não há nenhuma peça a ser promovida");
		}
		if (!type.equals("B") && !type.equals("C") && !type.equals("T") & !type.equals("R")) {
			return promoted;
		}
		
		Posicao pos = promoted.getXadrezPosic().toPosicao();
		Peca p = tabua.removePeca(pos);
		PecasNoTabuleiro.remove(p);
		
		Peca newPeca = newPeca(type, promoted.getCor());
		tabua.placePeca(newPeca, pos);
		PecasNoTabuleiro.add(newPeca);
		
		return newPeca;
	}
	
	private Peca newPeca(String type, Cor cor) {
		if (type.equals("B")) return new Bispo(tabua, cor);
		if (type.equals("C")) return new Cavalo(tabua, cor);
		if (type.equals("R")) return new Rainha(tabua, cor);
		
		return new Torre(tabua, cor);
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
		
		//movimento especial castling Reiside torre
		if (p instanceof Rei && target.getColuna() == source.getColuna() + 2) {
			Posicao sourceT = new Posicao(source.getLinha(), source.getColuna() + 3);
			Posicao targetT = new Posicao(source.getLinha(), source.getColuna() + 1);
			PecaXadrez torre = (PecaXadrez)tabua.removePeca(sourceT);
			tabua.placePeca(torre, targetT);
			torre.increaseMoveCount();
		}

		// #specialmove castling queenside rook
		if (p instanceof Rei && target.getColuna() == source.getColuna() - 2) {
			Posicao sourceT = new Posicao(source.getLinha(), source.getColuna() - 4);
			Posicao targetT = new Posicao(source.getLinha(), source.getColuna() - 1);
			PecaXadrez rook = (PecaXadrez)tabua.removePeca(sourceT);
			tabua.placePeca(rook, targetT);
			rook.increaseMoveCount();
		}		
		
		// #specialmove en passant
		if (p instanceof Peao) {
			if (source.getColuna() != target.getColuna() && capturedPeca == null) {
				Posicao peaoPosicao;
				if (p.getCor() == Cor.WHITE) {
					peaoPosicao = new Posicao(target.getLinha() + 1, target.getColuna());
				}
				else {
					peaoPosicao = new Posicao(target.getLinha() - 1, target.getColuna());
				}
				capturedPeca = tabua.removePeca(peaoPosicao);
				capturedPecas.add(capturedPeca);
				PecasNoTabuleiro.remove(capturedPeca);
			}
		}
		
		return capturedPeca;
	}
	
	private void undoMove(Posicao source, Posicao target, Peca capturedPeca) {
		PecaXadrez p = (PecaXadrez)tabua.removePeca(target);
		p.decreaseMoveCount();
		tabua.placePeca(p, source);
		
		if (capturedPeca != null) {
			tabua.placePeca(capturedPeca, target);
			capturedPecas.remove(capturedPeca);
			PecasNoTabuleiro.add(capturedPeca);
		}

		// #specialmove castling Reiside rook
		if (p instanceof Rei && target.getColuna() == source.getColuna() + 2) {
			Posicao sourceT = new Posicao(source.getLinha(), source.getColuna() + 3);
			Posicao targetT = new Posicao(source.getLinha(), source.getColuna() + 1);
			PecaXadrez rook = (PecaXadrez)tabua.removePeca(targetT);
			tabua.placePeca(rook, sourceT);
			rook.decreaseMoveCount();
		}

		// #specialmove castling queenside rook
		if (p instanceof Rei && target.getColuna() == source.getColuna() - 2) {
			Posicao sourceT = new Posicao(source.getLinha(), source.getColuna() - 4);
			Posicao targetT = new Posicao(source.getLinha(), source.getColuna() - 1);
			PecaXadrez rook = (PecaXadrez)tabua.removePeca(targetT);
			tabua.placePeca(rook, sourceT);
			rook.decreaseMoveCount();
		}
		
		// #specialmove en passant
		if (p instanceof Peao) {
			if (source.getColuna() != target.getColuna() && capturedPecas == enPassantVulnerable) {				PecaXadrez peao = (PecaXadrez)tabua.removePeca(target);
				Posicao peaoPosicao;
				if (p.getCor() == Cor.WHITE) {
					peaoPosicao = new Posicao(3, target.getColuna());
				}
				else {
					peaoPosicao = new Posicao(4, target.getColuna());
				}
				tabua.placePeca(peao, peaoPosicao);
			}
		}
	}
	
	private void validateSourcePosicao(Posicao Posicao) {
		if (!tabua.thereIsAPeca(Posicao)) {
			throw new XadrezExc("There is no Peca on source Posicao");
		}
		if (jogadorAtual != ((PecaXadrez)tabua.peca(Posicao)).getCor()) {
			throw new XadrezExc("The chosen peca is not yours");
		}
		if (!tabua.peca(Posicao).isThereAnyPossibleMove()) {
			throw new XadrezExc("There is no possible moves for the chosen peca");
		}
	}
	
	private void validateTargetPosicao(Posicao source, Posicao target) {
		if (!tabua.peca(source).possibleMove(target)) {
			throw new XadrezExc("The chosen peca can't move to target Posicao");
		}
	}
	
	private void nextVez() {
		vez++;
		jogadorAtual = (jogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private Cor opponent(Cor cor) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private PecaXadrez Rei(Cor cor) {
		List<Peca> list = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("There is no " + cor + " Rei on the tabua");
	}
	
	private boolean testCheck(Cor cor) {
		Posicao reiPosicao = Rei(cor).getXadrezPosic().toPosicao();
		List<Peca> opponentPecas = PecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == opponent(cor)).collect(Collectors.toList());
		for (Peca p : opponentPecas) {
			boolean[][] mat = p.possibleMoves();
			if (mat[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
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
    placeNewPeca('a', 1, new Torre(tabua, Cor.WHITE));
    placeNewPeca('b', 1, new Cavalo(tabua, Cor.WHITE));
    placeNewPeca('c', 1, new Bispo(tabua, Cor.WHITE));
    placeNewPeca('d', 1, new Rainha(tabua, Cor.WHITE));
    placeNewPeca('e', 1, new Rei(tabua, Cor.WHITE, this));
    placeNewPeca('f', 1, new Bispo(tabua, Cor.WHITE));
    placeNewPeca('g', 1, new Cavalo(tabua, Cor.WHITE));
    placeNewPeca('h', 1, new Torre(tabua, Cor.WHITE));
    placeNewPeca('a', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('b', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('c', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('d', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('e', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('f', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('g', 2, new Peao(tabua, Cor.WHITE, this));
    placeNewPeca('h', 2, new Peao(tabua, Cor.WHITE, this));

    placeNewPeca('a', 8, new Cavalo(tabua, Cor.BLACK));
    placeNewPeca('b', 8, new Torre(tabua, Cor.BLACK));
    placeNewPeca('c', 8, new Bispo(tabua, Cor.BLACK));
    placeNewPeca('d', 8, new Rainha(tabua, Cor.BLACK));
    placeNewPeca('e', 8, new Rei(tabua, Cor.BLACK, this));
    placeNewPeca('f', 8, new Bispo(tabua, Cor.BLACK));
    placeNewPeca('g', 8, new Torre(tabua, Cor.BLACK));
    placeNewPeca('h', 8, new Torre(tabua, Cor.BLACK));
    placeNewPeca('a', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('b', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('c', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('d', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('e', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('f', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('g', 7, new Peao(tabua, Cor.BLACK, this));
    placeNewPeca('h', 7, new Peao(tabua, Cor.BLACK, this));

	}

	public int getMoveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}	