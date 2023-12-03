package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosic;

public class Programa{

	public static void main(String[] args) {
		
		Scanner tt = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		
		while(true) {
			UI.printTabua(partidaXadrez.getPecas());
			System.out.println();
			System.out.print("Source: ");
			XadrezPosic source = UI.readXadrezPosic(tt);
			
			System.out.println();
			System.out.print("Target: ");
			XadrezPosic target = UI.readXadrezPosic(tt);
			
			PecaXadrez capturedPeca = partidaXadrez.performXadrezMove(source, target);
		}
	} 
}