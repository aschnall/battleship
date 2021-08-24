package battleship;

public class Battleship extends Ship {

	public Battleship() {
		length = 4;
	}
	
	@Override
	public String getShipType() {
		return "battleship";
	}

	@Override
	public int getLength() {
		return length;
	}

}
