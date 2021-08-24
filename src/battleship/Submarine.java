package battleship;

public class Submarine extends Ship {

	public Submarine() {
		length = 1;
	}
	
	@Override
	public String getShipType() {
		return "submarine";
	}

	@Override
	public int getLength() {
		return length;
	}

}
