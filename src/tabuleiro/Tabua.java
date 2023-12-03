package tabuleiro;

public class Tabua {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabua(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new TabuaExc("Erro ao criar o quadro: deve haver pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];	
	}

	public int getColunas() {
		return colunas;
	}

	public int getLinhas() {
		return linhas;
	}

	public Peca peca(int linha, int coluna) {
		if (!posicaoExists(linha, coluna)) {
			throw new TabuaExc("Não é possivel mexer no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if (!posicaoExists(posicao)) {
			throw new TabuaExc("Não é possivel mexer no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void placePeca(Peca peca, Posicao posicao) {
		if (thereIsAPeca(posicao)) {
			throw new TabuaExc("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	} 
	public Peca removePeca(Posicao posicao) {
		if (!posicaoExists(posicao)) {
			throw new TabuaExc("Essa peosição não tem no tabuleiro");
		}
		if (peca(posicao) == null) {
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}

	private boolean posicaoExists(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;	
	}
	
	public boolean posicaoExists(Posicao posicao) {
		return posicaoExists(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean thereIsAPeca(Posicao posicao) {
		if (!posicaoExists(posicao)) {
			throw new TabuaExc ("Não é possivel mexer no tabuleiro");
		}
		return peca(posicao) != null;
	}
}

