package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.PrivateKeyEntry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientSet;

public class HomePanel extends JPanel implements ActionListener {
	public MainFrame mf;
	public ClientSet clientSet;

	private JPanel logoutPanel;
	private JButton logout;

	private JPanel usersPanel;
	private JScrollPane onScroll;
	private JTextArea inList;

	private JPanel profilePanel;
	private JLabel id;
	private JLabel moneyView;
	private String userId;
	private int money;

	private JPanel chatPanel;
	private JScrollPane chatScroll;
	private JTextArea showChat;
	private JTextField writeChat;
	private JButton sendChat;

	private JPanel ruleInfoPanel;
	private JButton ruleButton;
	private JDialog ruleDialog;
	private JLabel ruleLabel;
	private JButton okButton;

	private JPanel gameStartPanel;
	private JLabel playLabel;
	private JButton play;
	private JLabel usersNumLabel;
	private int userNum;

	private boolean out;

	public HomePanel(MainFrame mf) {
		this.mf = mf;
		this.clientSet = ClientSet.getInstance();
		this.userId = clientSet.getUserID();
		this.money = clientSet.getMoney();
		initSetFrame();
		inUpdate();
	}

	private void initSetFrame() {
		setLayout(null);
		addBack();
		addChatting();
		addUser();
		addProfile();
		addRule();
		addStart();
		addListener();
		addChat(clientSet.getChat());
	}

	private void addBack() {
		logoutPanel = new JPanel();
		logoutPanel.setBounds(0, 0, 80, 40);

		logout = new JButton("Return");
		logout.setSize(80, 40);
		logout.setFont(new Font("times new roman", Font.BOLD, 12));
		logoutPanel.add(logout);

		add(logoutPanel);
	}

	private void addUser() {
		usersPanel = new JPanel();
		usersPanel.setBounds(5, 40, 410, 310);
		usersPanel.setLayout(null);

		inList = new JTextArea();
		onScroll = new JScrollPane(inList);
		onScroll.setSize(410, 310);

		usersPanel.add(onScroll);

		add(usersPanel);
	}

	private void addProfile() {
		profilePanel = new JPanel();
		profilePanel.setBounds(5, 365, 410, 310);
		profilePanel.setLayout(null);

		id = new JLabel("ID        " + userId);
		id.setBounds(5, 0, 410, 50);
		id.setFont(new Font("times new roman", Font.BOLD, 20));

		moneyView = new JLabel("Money " + money);
		moneyView.setFont(new Font("times new roman", Font.BOLD, 20));
		moneyView.setBounds(5, 30, 410, 50);

		profilePanel.add(id);
		profilePanel.add(moneyView);

		add(profilePanel);
	}

	private void addChatting() {
		chatPanel = new JPanel();
		chatPanel.setBounds(425, 40, 410, 635);
		chatPanel.setLayout(null);

		showChat = new JTextArea();
		chatScroll = new JScrollPane(showChat);
		chatScroll.setSize(410, 595);

		writeChat = new JTextField();
		writeChat.setColumns(10);
		writeChat.setBounds(0, 595, 330, 40);

		sendChat = new JButton("↑");
		sendChat.setBounds(330, 595, 80, 40);

		chatPanel.add(chatScroll);
		chatPanel.add(writeChat);
		chatPanel.add(sendChat);

		add(chatPanel);
	}

	private void addRule() {
		ruleInfoPanel = new JPanel();
		ruleInfoPanel.setBounds(845, 40, 410, 185);
		ruleInfoPanel.setLayout(null);

		ruleLabel = new JLabel("Check the Blackjack rule ↓");
		ruleLabel.setBounds(0, 0, 410, 30);
		ruleLabel.setFont(new Font("times new roman", Font.BOLD, 20));

		ruleButton = new JButton("Rule");
		ruleButton.setFont(new Font("times new roman", Font.BOLD, 20));
		ruleButton.setBounds(0, 55, 410, 125);

		ruleInfoPanel.add(ruleLabel);
		ruleInfoPanel.add(ruleButton);

		add(ruleInfoPanel);
	}

	private void addStart() {
		gameStartPanel = new JPanel();
		gameStartPanel.setBounds(845, 240, 410, 435);
		gameStartPanel.setLayout(null);

		playLabel = new JLabel("Do you want to start? click 'Start' button!");
		playLabel.setFont(new Font("times new roman", Font.BOLD, 20));
		playLabel.setBounds(35, 30, 410, 40);

		play = new JButton("Start!");
		play.setFont(new Font("times new roman", Font.BOLD, 20));
		play.setBounds(0, 100, 410, 185);

		usersNumLabel = new JLabel("Current number of connectors: " + userNum);
		usersNumLabel.setFont(new Font("times new roman", Font.BOLD, 20));
		usersNumLabel.setBounds(65, 300, 410, 40);

		gameStartPanel.add(playLabel);
		gameStartPanel.add(play);
		gameStartPanel.add(usersNumLabel);

		add(gameStartPanel);
	}

	private void addListener() {
		logout.addActionListener(this);
		sendChat.addActionListener(this);
		writeChat.addActionListener(this);
		ruleButton.addActionListener(this);
		play.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(play)) {
			out = true;
			mf.goGame();
		} else if (e.getSource().equals(logout)) {
			out = true;
			clientSet.setChat("");
			String msg = "logout/";
			clientSet.send(msg);
			clientSet.setUserID(null);
			System.out.println("로그아웃" + out);
			mf.goMain();
		} else if (e.getSource().equals(writeChat) || e.getSource().equals(sendChat)) {
			String msg = "chat/" + writeChat.getText();
			clientSet.send(msg);
			writeChat.setText("");
		} else if (e.getSource().equals(ruleButton)) {
			addDialog();
		}
	}

	private void addDialog() {
		ruleDialog = new JDialog(mf, "Blackjack rule", true);
		ruleDialog.setLayout(null);
		ruleDialog.setSize(640, 360);
		ruleDialog.setLocationRelativeTo(null);
		ruleDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		ruleDialog.setResizable(false);

		JPanel rulePanel = new JPanel();
		rulePanel.setBounds(0, 0, 640, 360);
		rulePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		String rule = "<html>게임규칙<br></html>";
		JLabel ruleLabel = new JLabel(rule, JLabel.LEFT);
		rulePanel.add(ruleLabel);

		okButton = new JButton("OK");
		okButton.setFont(new Font("times new roman", Font.BOLD, 12));
		okButton.setBounds(530, 290, 80, 20);
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ruleDialog.setVisible(false);
			}
		});

		ruleDialog.add(okButton);
		ruleDialog.add(rulePanel);
		ruleDialog.setVisible(true);
	}

	public void addChat(String chatList) {
		showChat.setText(chatList);
		showChat.setCaretPosition(showChat.getDocument().getLength());
	}

	private void inUpdate() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					out = false;
					while (!out) {
						clientSet.send("접속자 목록");
						inList.setText(clientSet.getWhosIn());
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public JTextArea getShowChat() {
		return showChat;
	}

	public void setShowChat(JTextArea showChat) {
		this.showChat = showChat;
	}

	public JTextArea getInList() {
		return inList;
	}

	public void setInList(String inList) {
		this.inList.setText(inList);
	}
}
