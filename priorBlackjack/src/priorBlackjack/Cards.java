package priorBlackjack;

public class Cards {
	private String face;
	
	public Cards(String cardValue) {
		face = cardValue;
	}
	
	@Override
	public String toString() {
		return face;
	}
}
