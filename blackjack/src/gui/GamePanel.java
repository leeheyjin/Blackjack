package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	private MainFrame mf;

	private JPanel backButtonPanel;
	private JButton backButton;

	private JPanel serverPanel;

	private JButton startButton;

	private JPanel client1, client2, client3, client4;

	public GamePanel(MainFrame mf) {
		this.mf = mf;
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
		serverPanel.setBounds(480, 40, 320, 285);
		serverPanel.setBackground(Color.LIGHT_GRAY);
		add(serverPanel);
	}

	private void addStratButton() {
		startButton = new JButton("Start!");
		startButton.setBounds(540, 325, 200, 75);
		add(startButton);
	}

	private void addClient() {
		int width = 320;
		int height = 285;

		client1 = new JPanel();
		client1.setBounds(0, 400, width, height);
		client1.setBackground(Color.orange);
		add(client1);

		client2 = new JPanel();
		client2.setBounds(320, 400, width, height);
		client2.setBackground(Color.green);
		add(client2);

		client3 = new JPanel();
		client3.setBounds(640, 400, width, height);
		client3.setBackground(Color.yellow);
		add(client3);

		client4 = new JPanel();
		client4.setBounds(960, 400, width, height);
		client4.setBackground(Color.pink);
		add(client4);

	}

	private void addListener() {
		backButton.addActionListener(this);
		startButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(backButton)) {
			mf.goHome();
		}
		if (e.getSource().equals(startButton)) {
			if (true)
				startButton.setVisible(false);
//			else startButton.setVisible(true);
		}
	}
}
