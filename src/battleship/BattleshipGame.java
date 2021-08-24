package battleship;

import java.util.Scanner;

public class BattleshipGame {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
		while (!ocean.isGameOver()) {
			System.out.println();
			System.out.println("Choose a row to shoot at");
			int row = scan.nextInt();
			System.out.println();
			System.out.println("Choose a column to shoot at");
			int col = scan.nextInt();
			ocean.shootAt(row, col);
			ocean.print();
		}
	}
}
