package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import client.ClientSet;

public class GamePanel extends JPanel implements ActionListener {
	public ClientSet clientSet;
	public MainFrame mf;

	private JPanel backButtonPanel;
	private JButton backButton;

	private JPanel serverPanel;

	private JButton startButton;

	private JPanel client;
	private JPanel infoPanel;
	private JLabel id, userLabel, moneyView, moneyLabel, betLabel, betCheck;
	private JButton b1000, b5000, b10000, b50000, hit, stand, join, bet, reset;

	private int width = 320, height = 285;
	private int bWidth = 140, bHeight = 40;
	private String userId;
	private int money;
	private int betMoney = 0;

	public GamePanel(MainFrame mf) {
		this.mf = mf;
		this.clientSet = ClientSet.getInstance();
		this.userId = clientSet.getUserID();
		this.money = clientSet.getMoney();

		initSetting();
	}

	private void initSetting() {
		setLayout(null);

		addReturn();
		addServer();
		addStratButton();
		addClient();
		addListener();
	}

	private void addReturn() {
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(0, 0, 80, 40);

		backButton = new JButton("Return");
		backButton.setSize(80, 40);
		backButton.setFont(new Font("times new roman", Font.BOLD, 12));

		backButtonPanel.add(backButton);
		add(backButtonPanel);

	}

	private void addServer() {
		serverPanel = new JPanel();
		serverPanel.setBounds(480, 40, width, height);
		serverPanel.setBorder(new LineBorder(Color.black));
		
		JLabel info1 = new JLabel("<html><center>게임을 시작하려면<br>아래 버튼을 클릭하세요.<center></html>");
		info1.setBounds((width - bWidth) / 2, 140, width, height);
		serverPanel.add(info1);
		
		add(serverPanel);
	}

	private void addStratButton() {
		startButton = new JButton("Start!");
		startButton.setBounds(540, 325, 200, 75);
		add(startButton);
	}

	private void addClient() {
		client = new JPanel();
		client.setBounds(480, 400, width, height);
		client.setBorder(new LineBorder(Color.black));
		client.setLayout(null);

		infoPanel = new JPanel();
		infoPanel.setBounds(2, 2, width - 5, 40);
		infoPanel.setLayout(new GridLayout(1, 4));

		id = new JLabel("ID");
		id.setFont(new Font("굴림", Font.BOLD, 20));
		infoPanel.add(id);

		userLabel = new JLabel("" + userId);
		userLabel.setFont(new Font("굴림", Font.ITALIC, 20));
		infoPanel.add(userLabel);

		moneyView = new JLabel("Money");
		moneyView.setFont(new Font("굴림", Font.BOLD, 20));
		infoPanel.add(moneyView);

		moneyLabel = new JLabel("" + money);
		moneyLabel.setFont(new Font("굴림", Font.ITALIC, 20));
		infoPanel.add(moneyLabel);
		client.add(infoPanel);

		betLabel = new JLabel("bet money: ");
		betLabel.setFont(new Font("굴림", Font.BOLD, 15));
		betLabel.setBounds(110, 50, 100, 30);
		betLabel.setVisible(false);
		client.add(betLabel);

		betCheck = new JLabel("" + betMoney);
		betCheck.setFont(new Font("굴림", Font.BOLD, 15));
		betCheck.setBounds(210, 50, 100, 30);
		betCheck.setVisible(false);
		client.add(betCheck);

		b1000 = new JButton("1,000");
		b1000.setBounds(10, 90, bWidth, bHeight);
		b1000.setVisible(false);
		client.add(b1000);

		b5000 = new JButton("5,000");
		b5000.setBounds(170, 90, bWidth, bHeight);
		b5000.setVisible(false);
		client.add(b5000);

		b10000 = new JButton("10,000");
		b10000.setBounds(10, 140, bWidth, bHeight);
		b10000.setVisible(false);
		client.add(b10000);

		b50000 = new JButton("50,000");
		b50000.setBounds(170, 140, bWidth, bHeight);
		b50000.setVisible(false);
		client.add(b50000);

		hit = new JButton("Hit");
		hit.setBounds(10, 240, bWidth, bHeight);
		hit.setVisible(false);
		client.add(hit);

		stand = new JButton("Stand");
		stand.setBounds(170, 240, bWidth, bHeight);
		stand.setVisible(false);
		client.add(stand);

		bet = new JButton("Bet");
		bet.setBounds((width - bWidth) / 2, 190, bWidth, bHeight);
		bet.setVisible(false);
		client.add(bet);

		join = new JButton("Join");
		join.setBounds((width - bWidth) / 2, 140, bWidth, bHeight);
		client.add(join);

		reset = new JButton("↺");
		reset.setBounds(250, 210, bWidth / 3, bHeight / 2);
		reset.setVisible(false);
		client.add(reset);

		add(client, BorderLayout.CENTER);
	}
	
	private void addListener() {
		backButton.addActionListener(this);
		startButton.addActionListener(this);
		b1000.addActionListener(this);
		b5000.addActionListener(this);
		b10000.addActionListener(this);
		b50000.addActionListener(this);
		hit.addActionListener(this);
		stand.addActionListener(this);
		join.addActionListener(this);
		bet.addActionListener(this);
		reset.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(backButton)) {
			mf.goHome();
		} else if (e.getSource().equals(startButton)) {

		} else if (e.getSource().equals(b1000)) {
			betMoney += 1000;
			money -= 1000;
			moneyLabel.setText("" + money);
			betCheck.setText("" + betMoney);
		} else if (e.getSource().equals(b5000)) {
			betMoney += 5000;
			money -= 5000;
			moneyLabel.setText("" + money);
			betCheck.setText("" + betMoney);
		} else if (e.getSource().equals(b10000)) {
			betMoney += 10000;
			money -= 10000;
			moneyLabel.setText("" + money);
			betCheck.setText("" + betMoney);
		} else if (e.getSource().equals(b50000)) {
			betMoney += 50000;
			money -= 50000;
			moneyLabel.setText("" + money);
			betCheck.setText("" + betMoney);
		} else if (e.getSource().equals(hit)) {

		} else if (e.getSource().equals(stand)) {

		} else if (e.getSource().equals(join)) {
			betLabel.setVisible(true);
			betCheck.setVisible(true);
			b1000.setVisible(true);
			b5000.setVisible(true);
			b10000.setVisible(true);
			b50000.setVisible(true);
			bet.setVisible(true);
			reset.setVisible(true);
			join.setVisible(false);
		} else if (e.getSource().equals(bet)) {
			if (money <= 0) {
				JOptionPane.showMessageDialog(null, "0원보다 적게 베팅할 수 없습니다.", "베팅 금액 오류", 1);
				b1000.setVisible(true);
				b5000.setVisible(true);
				b10000.setVisible(true);
				b50000.setVisible(true);
				bet.setVisible(true);
			} else {
				b1000.setVisible(false);
				b5000.setVisible(false);
				b10000.setVisible(false);
				b50000.setVisible(false);
				bet.setVisible(false);
				reset.setVisible(false);

				hit.setVisible(true);
				stand.setVisible(true);
			}
		} else if (e.getSource().equals(reset)) {
			betMoney = 0;
			money = clientSet.getMoney();
			moneyLabel.setText("" + money);
			betCheck.setText("" + betMoney);
		}

	}

}
