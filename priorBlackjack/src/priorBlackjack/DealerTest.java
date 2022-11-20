package priorBlackjack;

import javax.swing.JFrame;

public class DealerTest {
	public static void main(String[] args) {
		Dealer dealer = new Dealer();
		dealer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dealer.runDeal();
	}

}
