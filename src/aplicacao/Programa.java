package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezExc;
import xadrez.XadrezPosic;

public class Programa{

	public static void main(String[] args) {
		
		Scanner tt = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez() {
		};;
		
		while(true) {
			try {
				UI.clearScreen();
				UI.printTabua(partidaXadrez.getPecas());
				System.out.println();
				System.out.print("Source: ");
				XadrezPosic source = UI.readXadrezPosic(tt);
				
				boolean[][] possibleMoves = partidaXadrez.possibleMoves(source);
				UI.clearScreen();
				UI.printTabua(partidaXadrez.getPecas(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				XadrezPosic target = UI.readXadrezPosic(sc);
				
				PecaXadrez capturedXadrez = partidaXadrez.performXadrezMove(source, target);
			}
			catch (XadrezExc e) {
				System.out.println(e.getMessage());
				tt.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				tt.nextLine();
			}
		}
	} 
}