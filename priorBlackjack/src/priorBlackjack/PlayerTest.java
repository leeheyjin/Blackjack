package priorBlackjack;

import javax.swing.JFrame;

public class PlayerTest {
	public static void main(String[] args) {
		Player player;
		if (args.length == 0)
			player = new Player("localhost");
		else
			player = new Player(args[0]);

		player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player.setLocationRelativeTo(null);
		player.runClient();
	}

}
