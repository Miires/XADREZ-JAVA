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
		
		while (!partidaXadrez.getCheckMate()) {
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
						
						if (partidaXadrez.getPromoted() != null) {
							System.out.print("Inserir peça para promoção (B/C/T/R):");
							String type = tt.nextLine().toUpperCase();
							while (!type.equals("B") && !type.equals("C") && !type.equals("T") & !type.equals("R")) {
								System.out.print("Valor inválido! Insira peça para promoção (B/C/T/R): ");
								type = tt.nextLine().toUpperCase();
							}
							partidaXadrez.replacePromotedPiece(type);
						}
					} catch (XadrezExc e) {
					System.out.println(e.getMessage());
					tt.nextLine();
				}
				catch (InputMismatchException e) {
					System.out.println(e.getMessage());
					tt.nextLine();
				}
			}		
			UI.clearScreen();
			UI.printMatch(partidaXadrez, captured);
		} 
	}