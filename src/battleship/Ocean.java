package battleship;

public class Ocean {
	private Ship[][] ships = new Ship[10][10];
	private boolean[][] shotAt = new boolean[10][10];
	private int shotsFired;
	private int hitCount;
	
	public Ocean() {
		hitCount = 0;
		shotsFired = 0;
		for (int row = 0; row < ships.length; row++) {
			for (int col = 0; col < ships[row].length; col++) {
				ships[row][col] = new EmptySea();
				shotAt[row][col] = false;
			}
		}
	}
	
	public void placeAllShipsRandomly() {
		Ship[] initialShips = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), new Destroyer(),
				new Destroyer(), new Submarine(), new Submarine(), new Submarine(), new Submarine()};
		for (int i = 0; i < initialShips.length; i++) {
			int randomRow = (int) (Math.random()*10);
			int randomCol = (int) (Math.random()*10);
			boolean randomHorizontal;
			if (Math.random() < 0.5) {
				randomHorizontal = true;
			} else {
				randomHorizontal = false;
			}
			while(!initialShips[i].okToPlaceShipAt(randomRow, randomCol, randomHorizontal, this)) {
				randomRow = (int) (Math.random()*10);
				randomCol = (int) (Math.random()*10);
				if (Math.random() < 0.5) {
					randomHorizontal = true;
				} else {
					randomHorizontal = false;
				}

			}
			initialShips[i].placeShipAt(randomRow, randomCol, randomHorizontal, this);
		}
		
	}
	
	public boolean isOccupied(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			return false;
		}
		if (ships[row][column].getShipType() != "emptysea") {
			return true;
		}
		return false;
	}

	public boolean shootAt(int row, int column) {
		shotsFired++;
		shotAt[row][column] = true;
		if (ships[row][column].getShipType() != "emptysea" && ships[row][column].shootAt(row, column)) {
			hitCount++;
			return true;
		}
		return false;
	}
	
	public int getShotsFired() {
		return this.shotsFired;
	}
	
	public int getHitCount() {
		return this.hitCount;
	}
	
	public boolean isGameOver() {
		int sunkCount = 0;
		for (int row = 0; row < ships.length; row++) {
			for (int col = 0; col < ships[row].length; col++) {
				if (ships[row][col].toString() == "x") {
					sunkCount++;
				}
			}
		}
		if (sunkCount == 20) {
			return true;
		}
		return false;
	}
	
	public Ship[][] getShipArray() {
		return this.ships;
	}
	
	public void print() {
		System.out.print("  ");
		for (int j = 0; j < 10; j++) {
			System.out.print(j + " ");
		}
		for (int i = 0; i < ships.length; i++) {
			System.out.println();
			System.out.print(i + " ");
			for (int j = 0; j < ships[i].length; j++) {	
				if (!shotAt[i][j]) {
					System.out.print("." + " ");
				} else {
					System.out.print(ships[i][j].toString() + " ");
				}
			}
		}
	}

}
