package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
	public MainFrame mf;
	public GamePanel gp;

	public ActionHandler(GamePanel gp) {

		this.gp = gp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		gp.resetButtons();

		switch (command) {
		case "exit":
			System.exit(0);
			break;
		case "1":
			if (gp.situation.equals("playerTurn")) {
				gp.playerTurn();
			} else if (gp.situation.equals("playerNatural")) {
				gp.dealerOpen();
			} else if (gp.situation.equals("dealerTurnContinue")) {
				gp.dealerTurn();
			} else if (gp.situation.equals("gameFinished")) {
				gp.resetEverything();
			}
			break;
		case "2":
			if (gp.situation.equals("playerTurn")) {
				gp.dealerOpen();
			} else if (gp.situation.contentEquals("gameFinished")) {
				mf.goHome();
			}
			break;
		}
	}
}