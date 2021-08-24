package battleship;

public class Destroyer extends Ship {

	public Destroyer() {
		length = 2;
	}
	
	@Override
	public String getShipType() {
		return "destroyer";
	}

	@Override
	public int getLength() {
		return length;
	}

}
