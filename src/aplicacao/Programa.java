package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezExc;
import xadrez.XadrezPosic;

public class Programa {

	public static void main(String[] args) {
		
		Scanner tt = new Scanner(System.in);
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		List<PecaXadrez> captured = new ArrayList<>();
		
			while(true) {
					try {
						UI.clearScreen();
						UI.printMatch(partidaXadrez, captured);
						System.out.println();
						System.out.print("Source: ");
						XadrezPosic source = UI.readXadrezPosicao(tt);
						
						boolean[][] possibleMoves = partidaXadrez.possibleMoves(source);
						UI.clearScreen();
						UI.printTabua(partidaXadrez.getPecas(), possibleMoves);
						System.out.println();
						System.out.print("Target: ");
						XadrezPosic target = UI.readXadrezPosicao(tt);
						
						PecaXadrez capturedPeca = partidaXadrez.performXadrezMove(source, target);
						
						if (capturedPeca != null) {
							captured.add(capturedPeca);
						}
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