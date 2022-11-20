package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import client.ClientSet;

public class MainFrame extends JFrame implements Settable, ActionListener {
	final static int SCREEN_WIDTH = 1280;
	final static int SCREEN_HEIGHT = 720;

	public HomePanel hp;
	public GamePanel gp;
	public ClientSet clientSet;

	private JPanel contentPane;

	private JPanel loginPanel;
	private JLabel ID, PW, SIGNUP;
	private JTextField inputID;
	private JPasswordField inputPW;
	private JButton login, signup;
	private JLabel idReg, pwReg;

	private GridBagLayout gbLayout;
	private GridBagConstraints gbConstraints;

	private String userId;

//	private JPanel serverInfoPanel;
//	private JLabel serverIPLabel, portNoLabel;
//	private JTextField serverIPField, portNoField;

	public MainFrame(ClientSet clientSet) {
		this.clientSet = clientSet;
		initSetting();
		initSetFrame();
		setVisible(true);
	}

	private void initSetting() {
		setTitle("Blackjack");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
	}

	private void initSetFrame() {
		addMainPanel();
//		addServerInfo();
		addLogin();
		addListener();

	}

	private void addMainPanel() {
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
	}

//	private void addServerInfo() {
//		serverInfoPanel = new JPanel();
//		serverInfoPanel.setBounds(920, 10, 300, 20);
//		serverInfoPanel.setLayout(new GridLayout(1, 4, 5, 5));
//
//		serverIPLabel = new JLabel("��踰�IP");
//		serverInfoPanel.add(serverIPLabel);
//
//		serverIPField = new JTextField(10);
//		serverInfoPanel.add(serverIPField);
//
//		portNoLabel = new JLabel("�ы�몃���");
//		serverInfoPanel.add(portNoLabel);
//
//		portNoField = new JTextField(5);
//		serverInfoPanel.add(portNoField);
//
//		contentPane.add(serverInfoPanel);
//	}

	private void addLogin() {
		gbLayout = new GridBagLayout();
		gbConstraints = new GridBagConstraints();
		gbConstraints.weightx = 1.0;
		gbConstraints.weighty = 1.0;

		loginPanel = new JPanel();
		loginPanel.setBounds(440, 400, 400, 150);
		loginPanel.setLayout(gbLayout);

		ID = new JLabel("ID");
		ID.setFont(new Font("times new york", Font.BOLD, 12));
		gridBagMake(ID, 0, 0, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(ID, gbConstraints);

		inputID = new JTextField();
		inputID.setColumns(10);
		gridBagMake(inputID, 1, 0, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(inputID, gbConstraints);

		idReg = new JLabel("");
		idReg.setFont(new Font("굴림", Font.PLAIN, 10));
		idReg.setForeground(Color.red);
		gridBagMake(idReg, 1, 1, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(idReg, gbConstraints);

		PW = new JLabel("Password");
		PW.setFont(new Font("times new york", Font.BOLD, 12));
		gridBagMake(PW, 0, 2, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(PW, gbConstraints);

		inputPW = new JPasswordField();
		inputPW.setColumns(10);
		gridBagMake(inputPW, 1, 2, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(inputPW, gbConstraints);

		pwReg = new JLabel("");
		pwReg.setFont(new Font("굴림", Font.PLAIN, 10));
		gridBagMake(pwReg, 1, 3, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		loginPanel.add(pwReg, gbConstraints);

		login = new JButton("Login");
		gridBagMake(login, 2, 0, 1, 4);
		gbConstraints.fill = GridBagConstraints.BOTH;
		loginPanel.add(login, gbConstraints);

		SIGNUP = new JLabel("Do you want to create account? >>");
		gridBagMake(SIGNUP, 0, 4, 2, 1);
		gbConstraints.fill = GridBagConstraints.BOTH;
		loginPanel.add(SIGNUP, gbConstraints);

		signup = new JButton("Sign Up");
		gridBagMake(signup, 2, 4, 1, 1);
		gbConstraints.fill = GridBagConstraints.BOTH;
		loginPanel.add(signup);

		contentPane.add(loginPanel);
	}

	@Override
	public void gridBagMake(Component c, int x, int y, int w, int h) {
		gbConstraints.gridx = x;
		gbConstraints.gridy = y;
		gbConstraints.gridwidth = w;
		gbConstraints.gridheight = h;

		gbLayout.setConstraints(c, gbConstraints);
	}

	private void addListener() {
		login.addActionListener(this);
		signup.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signup)) {
			this.remove(contentPane);
			contentPane = new SignUpPanel(this);
			this.setContentPane(contentPane);
			this.setVisible(true);
		} else if (e.getSource().equals(login)) {
			if (inputID.getText().equals("")) {
				idReg.setText("ID를 입력하세요");
			} else if (inputPW.getText().equals("")) {
				pwReg.setText("비밀번호를 입력하세요");
			} else {
				String attempt = "login/" + inputID.getText() + "/" + inputPW.getText();
				System.out.println(attempt + "로 로그인 시도");
				String log = clientSet.memberchk(attempt);
				if (log.equals("1")) {
					idReg.setText("존재하지 않는 ID입니다");
				} else if (log.equals("2")) {
					idReg.setText("");
					pwReg.setText("비밀번호가 틀립니다");
				} else if (log.equals("3")) {
					userId = inputID.getText();
					goHome();
				}
			}
		}
	}

	public void goHome() {
		this.remove(contentPane);
		hp = new HomePanel(this);
		contentPane = hp;
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public void goMain() {
		this.remove(contentPane);
		initSetFrame();
		this.setContentPane(contentPane);
		this.setVisible(true);
	}

	public void goGame() {
		this.remove(contentPane);
		gp = new GamePanel(this);
		contentPane = gp;
		this.setContentPane(contentPane);
		this.setVisible(true);
	}
}
