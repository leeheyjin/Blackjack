package priorBlackjack;

import java.util.ArrayList;

public class Playbj {
	private boolean bust = false;
	private int cardTotal = 0;

	private ArrayList<String> cards;
	private ArrayList<String> aces;

	public Playbj(String c1, String c2) {
		cards = new ArrayList<>();
		aces = new ArrayList<>();

		if (c1 == "Ace") {
			aces.add(c1);
		} else {
			cards.add(c1);
		}

		if (c2 == "Ace") {
			aces.add(c2);
		} else {
			cards.add(c2);
		}
		setTotal();
	}

	public int getCardTotal() {
		return cardTotal;
	}

	public void cardHit(String c) {
		if (c == "Ace") {
			aces.add("Ace");
		} else {
			cards.add(c);
		}

		if (aces.size() != 0) {
			setTotal();
		} else if (c == "J" || c == "Q" || c == "K") {
			cardTotal += 10;
		} else {
			cardTotal += Integer.parseInt(c);
		}
		checkBust();
	}

	private void setTotal() {
		cardTotal = 0;
		for (String c : cards) {
			if (c == "J" || c == "Q" || c == "K") {
				cardTotal += 10;
			} else {
				cardTotal += Integer.parseInt(c);
			}
		}

		for (String a : aces) {
			if (cardTotal <= 10) {
				cardTotal += 11;
			} else {
				cardTotal += 1;
			}
		}
	}

	public boolean checkBust() {
		if (cardTotal > 21) {
			bust = true;
		} else {
			bust = false;
		}
		return bust;
	}
}
