package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import client.ClientSet;

public class GamePanel extends JPanel implements ActionListener {
	public ClientSet clientSet;
	public MainFrame mf;
	private Cards cards = new Cards();
	private Random random = new Random();
//	private ActionHandler aHandler;

	private JPanel backButtonPanel;
	private JButton back;

	private JPanel serverPanel;
	private JLabel guide;
	private JPanel dealerPanel;
	private JLabel[] dealerCardLabels = new JLabel[6];
	private JLabel dealerScore;

	private JButton start;

	private JPanel clientPanel;
	private JPanel clientInfo;
	private JPanel playerPanel;
	private JLabel[] playerCardLabels = new JLabel[6];
	private JLabel playerScore;
	private JLabel id;
	private JLabel nowMoney;
	private JLabel nowBet;
	private String userId;
	private int money;
	private int betMoney = 0;
	private JButton join;
	private JButton b1000, b5000, b10000, b50000, bet, reset;
	private JButton hit, stand, continueButton;
	private JButton playAgain, quit;
	private JLabel message;
	private JLabel matchResult;

	private int pickedCardNum;
	private int dealerHas = 0;
	private int[] dealerCardNum = new int[6];
	private int[] dealerCardValue = new int[6];
	private int dealerTotalValue;
	private int playerHas = 0;
	private int[] playerCardNum = new int[6];
	private int[] playerCardValue = new int[6];
	private int playerTotalValue;
	private ImageIcon dealerSecondCard;
	private String situation = "";

	private int bWidth = 140, bHeight = 40;
	private int cardWidth = 130, cardHeight = 187;

	public GamePanel(MainFrame mf) {
		this.mf = mf;
		this.clientSet = ClientSet.getInstance();
		this.userId = clientSet.getUserID();
		this.money = clientSet.getMoney();

		initSetting();
	}

	private void initSetting() {
		setLayout(null);

		addBack();
		addServer();
		addStratButton();
		addClient();
		addListener();
	}

	private void addBack() {
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(0, 0, 80, bHeight);

		back = new JButton("Return");
		back.setSize(80, 40);
		back.setFont(new Font("times new roman", Font.BOLD, 12));

		backButtonPanel.add(back);
		add(backButtonPanel);

	}

	private void addServer() {
		serverPanel = new JPanel();
		serverPanel.setBounds(0, 40, mf.SCREEN_WIDTH, 245);
		serverPanel.setBorder(new LineBorder(Color.gray));
		serverPanel.setLayout(null);

		guide = new JLabel("게임을 시작하려면 Join하여 베팅금을 걸고 Game Start버튼을 클릭하세요.", JLabel.CENTER);
		guide.setBounds(370, 0, 540, 30);
		guide.setFont(new Font("굴림", Font.BOLD, 15));
		serverPanel.add(guide);

		dealerPanel = new JPanel();
		dealerPanel.setBounds(315, 70, cardWidth * 5, cardHeight);
		dealerPanel.setLayout(new GridLayout(1, 5));
		dealerPanel.setVisible(false);
		this.add(dealerPanel);

		for (int i = 1; i < 6; i++) {
			dealerCardLabels[i] = new JLabel();
			dealerCardLabels[i].setVisible(false);
			dealerPanel.add(dealerCardLabels[i]);
		}

		dealerScore = new JLabel("", JLabel.CENTER);
		dealerScore.setBounds(570, 205, bWidth, bHeight);
		dealerScore.setFont(new Font("times new roman", Font.BOLD, 20));
		serverPanel.add(dealerScore);

		add(serverPanel);

	}

	private void addStratButton() {
		start = new JButton("Start!");
		start.setBounds(540, 285, 200, 75);
		start.setEnabled(false);
		add(start);

		playAgain = new JButton("Play agian");
		playAgain.setBounds(315, 285, 200, 75);
		playAgain.setVisible(false);
		add(playAgain);

		quit = new JButton("Quit");
		quit.setBounds(765, 285, 200, 75);
		quit.setVisible(false);
		add(quit);
	}

	private void addClient() {
		clientPanel = new JPanel();
		clientPanel.setBounds(0, 360, mf.SCREEN_WIDTH, 325);
		clientPanel.setBorder(new LineBorder(Color.gray));
		clientPanel.setLayout(null);

		clientInfo = new JPanel();
		clientInfo.setBounds(2, 2, 300, bHeight);
		clientInfo.setLayout(new GridLayout(1, 2));

		id = new JLabel("ID " + userId);
		id.setFont(new Font("times new roman", Font.BOLD, 20));
		clientInfo.add(id);

		nowMoney = new JLabel("Money " + money);
		nowMoney.setFont(new Font("times new roman", Font.BOLD, 20));
		clientInfo.add(nowMoney);
		clientPanel.add(clientInfo);

		nowBet = new JLabel("Bet money: " + betMoney, JLabel.LEFT);
		nowBet.setFont(new Font("times new roman", Font.BOLD, 20));
		nowBet.setBounds(2, 45, 300, bHeight);
		clientPanel.add(nowBet);

		playerPanel = new JPanel();
		playerPanel.setBounds(315, 402, cardWidth * 5, cardHeight);
		playerPanel.setLayout(new GridLayout(1, 5));
		playerPanel.setVisible(false);
		this.add(playerPanel);

		for (int i = 1; i < 6; i++) {
			playerCardLabels[i] = new JLabel();
			playerCardLabels[i].setVisible(false);
			playerPanel.add(playerCardLabels[i]);
		}

		playerScore = new JLabel("", JLabel.CENTER);
		playerScore.setBounds(570, 2, bWidth, bHeight);
		playerScore.setFont(new Font("times new roman", Font.BOLD, 20));
		clientPanel.add(playerScore);

		join = new JButton("Join");
		join.setBounds(570, 125, bWidth, bHeight);
		join.setVisible(true);
		clientPanel.add(join);

		b1000 = new JButton("1000");
		b1000.setBounds(144, 110, bWidth, bHeight);
		b1000.setVisible(false);
		clientPanel.add(b1000);
		b5000 = new JButton("5000");
		b5000.setBounds(428, 110, bWidth, bHeight);
		b5000.setVisible(false);
		clientPanel.add(b5000);
		b10000 = new JButton("10000");
		b10000.setBounds(712, 110, bWidth, bHeight);
		b10000.setVisible(false);
		clientPanel.add(b10000);
		b50000 = new JButton("50000");
		b50000.setBounds(996, 110, bWidth, bHeight);
		b50000.setVisible(false);
		clientPanel.add(b50000);

		bet = new JButton("Bet");
		bet.setBounds(570, 180, bWidth, bHeight);
		bet.setVisible(false);
		clientPanel.add(bet);
		reset = new JButton("↺");
		reset.setBounds(730, 200, bWidth / 3, bHeight / 2);
		reset.setVisible(false);
		clientPanel.add(reset);

		hit = new JButton("Hit");
		hit.setBounds(500, 280, bWidth, bHeight);
		hit.setVisible(false);
		hit.setEnabled(false);
		clientPanel.add(hit);
		stand = new JButton("Stand");
		stand.setBounds(650, 280, bWidth, bHeight);
		stand.setVisible(false);
		stand.setEnabled(false);
		clientPanel.add(stand);

		message = new JLabel("", JLabel.CENTER);
		message.setFont(new Font("굴림", Font.BOLD, 15));
		message.setBounds(340, 240, 600, 40);
		message.setVisible(false);
		clientPanel.add(message);

		matchResult = new JLabel("", JLabel.CENTER);
		matchResult.setBounds(540, 285, 200, 75);
		matchResult.setVisible(true);
		matchResult.setFont(new Font("Times new roman", Font.BOLD, 40));
		this.add(matchResult);

		add(clientPanel);
	}

	private void addListener() {
		back.addActionListener(this);
		start.addActionListener(this);
		join.addActionListener(this);
		b1000.addActionListener(this);
		b5000.addActionListener(this);
		b10000.addActionListener(this);
		b50000.addActionListener(this);
		bet.addActionListener(this);
		reset.addActionListener(this);
		hit.addActionListener(this);
		stand.addActionListener(this);
		playAgain.addActionListener(this);
		quit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) {
			mf.goHome();
		} else if (e.getSource().equals(join)) {
			b1000.setVisible(true);
			b5000.setVisible(true);
			b10000.setVisible(true);
			b50000.setVisible(true);
			bet.setVisible(true);
			reset.setVisible(true);
			join.setVisible(false);
		} else if (e.getSource().equals(b1000)) {
			betMoney += 1000;
//			money -= 1000;
			nowBet.setText("Bet money: " + betMoney);
//			nowMoney.setText("Money " + money);
		} else if (e.getSource().equals(b5000)) {
			betMoney += 5000;
//			money -= 5000;
			nowBet.setText("Bet money: " + betMoney);
//			nowMoney.setText("Money " + money);
		} else if (e.getSource().equals(b10000)) {
			betMoney += 10000;
//			money -= 10000;
			nowBet.setText("Bet money: " + betMoney);
//			nowMoney.setText("Money " + money);
		} else if (e.getSource().equals(b50000)) {
			betMoney += 50000;
//			money -= 50000;
			nowBet.setText("Bet money: " + betMoney);
//			nowMoney.setText("Money " + money);
		} else if (e.getSource().equals(bet)) {
			if (money < betMoney && money != 0) {
				JOptionPane.showMessageDialog(null, "현재 보유 머니보다 많게 베팅할 수 없습니다.", "베팅 금액 오류", 1);
				b1000.setVisible(true);
				b5000.setVisible(true);
				b10000.setVisible(true);
				b50000.setVisible(true);
				bet.setVisible(true);
				reset.setVisible(true);
				betMoney = 0;
				money = clientSet.getMoney();
				nowBet.setText("Bet money: " + betMoney);
				nowMoney.setText("Money " + money);
			} else if (betMoney == 0) {
				JOptionPane.showMessageDialog(null, "베팅금을 0원 이하로 베팅할 수 없습니다.", "베팅 금액 오류", 1);
				b1000.setVisible(true);
				b5000.setVisible(true);
				b10000.setVisible(true);
				b50000.setVisible(true);
				bet.setVisible(true);
				reset.setVisible(true);
			} else if (money == 0) {
				JOptionPane.showMessageDialog(null, "보유 머니가 0원이라면 베팅할 수 없습니다. 홈에서 리필받으세요", "베팅 금액 오류", 1);
				b1000.setVisible(true);
				b5000.setVisible(true);
				b10000.setVisible(true);
				b50000.setVisible(true);
				bet.setVisible(true);
				reset.setVisible(true);
			}else {
				money -= betMoney;
				nowMoney.setText("Money " + money);
				b1000.setVisible(false);
				b5000.setVisible(false);
				b10000.setVisible(false);
				b50000.setVisible(false);
				bet.setVisible(false);
				reset.setVisible(false);

				start.setVisible(true);
				start.setEnabled(true);
				hit.setVisible(true);
				stand.setVisible(true);
			}
		} else if (e.getSource().equals(reset)) {
			betMoney = 0;
			money = clientSet.getMoney();
			nowBet.setText("Bet money: " + betMoney);
			nowMoney.setText("Money " + money);
		} else if (e.getSource().equals(start)) {
			start.setVisible(false);
			guide.setVisible(false);
			playerPanel.setVisible(true);
			dealerPanel.setVisible(true);
			hit.setEnabled(true);
			stand.setEnabled(true);
			playerScore.setVisible(true);
			dealerScore.setVisible(true);
			startGame();
		} else if (e.getSource().equals(hit)) {
			if (situation.equals("playerTurn")) {
				playerTurn();
			} else if (situation.equals("playerNatural")) {
				dealerOpen();
			} else if (situation.equals("dealerTurnContinue")) {
				dealerTurn();
			} else if (situation.equals("gameFinished")) {
				resetEverything();
			}
		} else if (e.getSource().equals(stand)) {
			if (situation.equals("playerTurn")) {
				dealerOpen();
			} else if (situation.contentEquals("gameFinished")) {
				mf.goHome();
			} else if (situation.equals("dealerTurnContinue")) {
				dealerTurn();
				dealerOpen();
			}
		} else if (e.getSource().equals(playAgain)) {
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			dealerScore.setVisible(false);
			resetEverything();
		} else if (e.getSource().equals(quit)) {
			mf.goHome();
		} else if (e.getSource().equals(continueButton)) {
			dealerTurn();
		}
	}

	private void startGame() {
		dealerDraw();
		playerDraw();
		dealerDraw();
		dealerCardLabels[2].setIcon(cards.front);
		playerTurn();
	}

	private void dealerDraw() {
		dealerHas++;

		ImageIcon pickedCard = pickRandomCard();
		if (dealerHas == 2) {
			dealerSecondCard = pickedCard;
		}

		dealerCardNum[dealerHas] = pickedCardNum;
		dealerCardValue[dealerHas] = checkCardValue();

		dealerCardLabels[dealerHas].setVisible(true);
		dealerCardLabels[dealerHas].setIcon(pickedCard);

		dealerTotalValue = dealerTotalValue();
		dealerScore.setText("");
	}

	private void playerDraw() {
		playerHas++;

		ImageIcon pickedCard = pickRandomCard();

		playerCardNum[playerHas] = pickedCardNum;
		playerCardValue[playerHas] = checkCardValue();

		playerCardLabels[playerHas].setVisible(true);
		playerCardLabels[playerHas].setIcon(pickedCard);

		playerTotalValue = playerTotalValue();
		playerScore.setText("" + playerTotalValue);
	}

	private void playerTurn() {
		situation = "playerTurn";
		playerDraw();

		if (playerTotalValue > 21) {
			dealerOpen();
		} else if (playerTotalValue == 21 && playerHas == 2) {
			playerNatural();
		} else {
			if (playerHas > 1 && playerHas < 5) {
				message.setText("카드를 더 받고 싶다면? HIT!");
				hit.setVisible(true);
				stand.setVisible(true);
			}
			if (playerHas == 5) {
				dealerOpen();
			}
		}
	}

	private void playerNatural() {
		situation = "playerNatural";
	}

	private void dealerOpen() {
		dealerCardLabels[2].setIcon(dealerSecondCard);
		dealerScore.setText("" + dealerTotalValue);

		if (playerHas == 2 && playerTotalValue == 21) {
			checkResult();
		} else if (dealerTotalValue <= 16 && playerTotalValue <= 21) {
			dealerTurnContinue();
		} else {
			checkResult();
		}
	}

	private void dealerTurn() {
		if (dealerTotalValue < 17) {

			dealerDraw();

			if (dealerHas == 5 || dealerTotalValue >= 17) {
				checkResult();
			} else {
				dealerTurnContinue();
			}
		} else {
			checkResult();
		}
	}

	private void dealerTurnContinue() {
		situation = "dealerTurnContinue";
		message.setText("딜러의 카드가 16보다 작으므로 Hit 해야합니다. Stand를 클릭해주세요.");
	}

	private void checkResult() {
		situation = "checkResult";

		if (dealerTotalValue > playerTotalValue && dealerTotalValue < 21) { // 딜러 합 > 플레이어 합 + 딜러 합 < 21
			matchResult.setText("LOSE!");
			matchResult.setForeground(Color.red);
			matchResult.setVisible(true);
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (dealerTotalValue > 21) { // 딜러 합 > 21
			matchResult.setText("WIN!");
			matchResult.setForeground(Color.blue);
			matchResult.setVisible(true);
			money += betMoney * 2;
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (playerTotalValue > 21) { // 플레이어 합 > 21
			matchResult.setText("Bust!");
			matchResult.setForeground(Color.red);
			matchResult.setVisible(true);
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (dealerTotalValue == playerTotalValue) { // 딜러 합 == 플레이어 합
			matchResult.setText("Push!");
			matchResult.setForeground(Color.black);
			matchResult.setVisible(true);
			money += betMoney;
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (dealerTotalValue < playerTotalValue && playerTotalValue < 21) { // 딜러 합 < 플레이어 합 + 플레이어 합 < 21
			matchResult.setText("WIN!");
			matchResult.setForeground(Color.blue);
			matchResult.setVisible(true);
			money += betMoney * 2;
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (dealerTotalValue == 21) { // 딜러 합 == 21
			matchResult.setText("LOSE!");
			matchResult.setForeground(Color.red);
			matchResult.setVisible(true);
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		} else if (playerTotalValue == 21) { // 플레이어 합 == 21
			matchResult.setText("Blackjack!");
			matchResult.setForeground(Color.green);
			matchResult.setVisible(true);
			money += betMoney * 1.5;
			nowMoney.setText("Money " + money);
			clientSet.setMoney(money);
			betMoney = 0;
			nowBet.setText("Bet money: " + betMoney);
			gameFinished();
		}

		String gg = "gg/" + userId + "/" + clientSet.getMoney();
		clientSet.send(gg);
	}

	private void gameFinished() {
		situation = "gameFinished";
		hit.setEnabled(false);
		stand.setEnabled(false);
		playAgain.setVisible(true);
		quit.setVisible(true);
	}

	private void resetEverything() {
		for (int i = 1; i < 6; i++) {
			playerCardLabels[i].setVisible(false);
			dealerCardLabels[i].setVisible(false);
		}

		for (int i = 1; i < 6; i++) {
			playerCardNum[i] = 0;
			playerCardValue[i] = 0;
			dealerCardNum[i] = 0;
			dealerCardValue[i] = 0;
		}
		playerHas = 0;
		dealerHas = 0;

		resetButtons();
	}

	private int playerTotalValue() {
		playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4]
				+ playerCardValue[5];

		if (playerTotalValue > 21) {
			adjustPlayerAceValue();
		}

		playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4]
				+ playerCardValue[5];
		return playerTotalValue;
	}

	private int dealerTotalValue() {
		dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4]
				+ dealerCardValue[5];

		if (dealerTotalValue > 21) {
			adjustDealerAceValue();
		}

		dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4]
				+ dealerCardValue[5];
		return dealerTotalValue;
	}

	private void adjustPlayerAceValue() {
		for (int i = 1; i < 6; i++) {
			if (playerCardNum[i] == 1) {
				playerCardValue[i] = 1;
				playerTotalValue = playerCardValue[1] + playerCardValue[2] + playerCardValue[3] + playerCardValue[4]
						+ playerCardValue[5];
				if (playerTotalValue < 21) {
					break;
				}
			}
		}
	}

	private void adjustDealerAceValue() {
		for (int i = 1; i < 6; i++) {
			if (dealerCardNum[i] == 1) {
				dealerCardValue[i] = 1;
				dealerTotalValue = dealerCardValue[1] + dealerCardValue[2] + dealerCardValue[3] + dealerCardValue[4]
						+ dealerCardValue[5];
				if (dealerTotalValue < 21) {
					break;
				}
			}
		}
	}

	private ImageIcon pickRandomCard() {
		ImageIcon pickedCard = null;

		pickedCardNum = random.nextInt(13) + 1;
		int pickedMark = random.nextInt(4) + 1;

		switch (pickedMark) {
		case 1:
			pickedCard = cards.spade[pickedCardNum];
			break;
		case 2:
			pickedCard = cards.heart[pickedCardNum];
			break;
		case 3:
			pickedCard = cards.clover[pickedCardNum];
			break;
		case 4:
			pickedCard = cards.diamond[pickedCardNum];
			break;
		}
		return pickedCard;
	}

	private int checkCardValue() {
		int cardValue = pickedCardNum;
		if (pickedCardNum == 1) {
			cardValue = 1;
		}
		if (pickedCardNum > 10) {
			cardValue = 10;
		}
		return cardValue;
	}

	private void resetButtons() {
		playAgain.setVisible(false);
		quit.setVisible(false);
		matchResult.setVisible(false);
		playerPanel.setVisible(false);
		dealerPanel.setVisible(false);
		hit.setVisible(false);
		stand.setVisible(false);
		playerScore.setVisible(false);

		guide.setVisible(true);
		b1000.setVisible(true);
		b5000.setVisible(true);
		b10000.setVisible(true);
		b50000.setVisible(true);
		bet.setVisible(true);
		reset.setVisible(true);
	}
}
