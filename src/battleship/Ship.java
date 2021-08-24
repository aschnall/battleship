package battleship;

public abstract class Ship {
	int bowRow;
	int bowColumn;
	int length;
	boolean horizontal;
	boolean[] hit = new boolean[4];
	
	//getters and setters
	public int getBowRow() {
		return bowRow;
	}
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	public int getBowColumn() {
		return bowColumn;
	}
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	public boolean isHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	//abstract methods
	public abstract int getLength();
	public abstract String getShipType();
	
	//other methods
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (horizontal) {
			if (column + getLength() > 9) {
				return false;
			}
			for (int col = column - 1; col <= column + getLength(); col++) {
				if (ocean.isOccupied(row-1, col) || ocean.isOccupied(row, col) || ocean.isOccupied(row+1, col)) {
					return false;
				}
			}
			return true;
		} else {
			if (row + getLength() > 9) {
				return false;
			}
			for (int row1 = row - 1; row1 <= row + getLength(); row1++) {
				if (ocean.isOccupied(row1, column-1) || ocean.isOccupied(row1, column) || ocean.isOccupied(row1, column+1)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		if (okToPlaceShipAt(row, column, horizontal, ocean)) {
			setBowRow(row);
			setBowColumn(column);
			setHorizontal(horizontal);
			Ship[][] ships = ocean.getShipArray();
			if (horizontal) {
				for (int i = column; i < column + getLength(); i++) {
					ships[row][i] = this;
				}
			} else {
				for (int j = row; j < row + getLength(); j++) {
					ships[j][column] = this;
				}
			}
		}
	}
	
	public boolean shootAt(int row, int column) {
		if (horizontal) {
			if (row == bowRow) {
				for (int i = bowColumn; i < bowColumn + getLength(); i++) {
					if (i == column) {
						hit[i-bowColumn] = true;
						return true;
					}
				}
			}
		} else {
			if (column == bowColumn) {
				for (int j = bowRow; j < bowRow + getLength(); j++) {
					if (j == row) {
						hit[j-bowRow] = true;
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean isSunk() {
		int hitSum = 0;
		for (int i = 0; i < hit.length; i++) {
			if (hit[i]) {
				hitSum++;
			}
		}
		if (hitSum == getLength()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (isSunk()) {
			return "x";
		} else {
			return "S";
		}
	}
}
