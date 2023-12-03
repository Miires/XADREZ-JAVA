package aplicacao;

import tabuleiro.Posicao;
import xadrez.PartidaXadrez;

public class Programa{

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.printTabua(partidaXadrez.getPecas());
	}
}
