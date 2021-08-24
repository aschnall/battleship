package battleship;

public class Cruiser extends Ship {

	public Cruiser() {
		length = 3;
	}
	
	@Override
	public String getShipType() {
		return "cruiser";
	}

	@Override
	public int getLength() {
		return length;
	}

}
