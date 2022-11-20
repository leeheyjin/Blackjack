package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.ClientSet;

public class SignUpPanel extends JPanel implements Settable, ActionListener {
	private MainFrame mf;
	private ClientSet clientSet;

	private JPanel goBackPanel;
	private JButton goBack;

	private JPanel signupPanel;
	private JTextField id, pw, pwchk;
	private JLabel idReg, pwReg, pwchkReg;
	private JButton signup;

	private String idok = "사용 가능한 ID";
	private String pwok = "사용 가능한 비밀번호";
	private String pwchkok = "비밀번호가 일치합니다.";

	private GridBagLayout gbLayout;
	private GridBagConstraints gbConstraints;

	public SignUpPanel(MainFrame mf) {
		this.mf = mf;
		this.clientSet = ClientSet.getInstance();
		initSetFrame();
	}

	private void initSetFrame() {
		setLayout(null);
		addBack();
		addCreateAccount();
		addListener();

	}

	void addBack() {
		goBackPanel = new JPanel();
		goBackPanel.setBounds(0, 0, 80, 40);

		goBack = new JButton("Return");
		goBack.setSize(80, 40);
		goBackPanel.add(goBack);

		add(goBackPanel);
	}

	void addCreateAccount() {
		gbLayout = new GridBagLayout();
		gbConstraints = new GridBagConstraints();
		gbConstraints.weightx = 1.0;
		gbConstraints.weighty = 1.0;

		signupPanel = new JPanel();
		signupPanel.setBounds(440, 400, 400, 150);
		signupPanel.setLayout(gbLayout);

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("times new roman", Font.BOLD, 12));
		gridBagMake(ID, 0, 0, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(ID, gbConstraints);

		id = new JTextField();
		gridBagMake(id, 1, 0, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		id.setColumns(10);
		signupPanel.add(id, gbConstraints);

		idReg = new JLabel("4~15\uC790 \uC774\uB0B4\uB85C \uC785\uB825");
		idReg.setFont(new Font("굴림", Font.PLAIN, 10));
		gridBagMake(idReg, 1, 1, 3, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(idReg, gbConstraints);

		JLabel PW = new JLabel("Password");
		PW.setFont(new Font("times new roman", Font.BOLD, 12));
		gridBagMake(PW, 0, 2, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(PW, gbConstraints);

		pw = new JTextField(10);
		gridBagMake(pw, 1, 2, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		pw.setColumns(10);
		signupPanel.add(pw, gbConstraints);

		pwReg = new JLabel("\uD2B9\uC218\uBB38\uC790 \uD3EC\uD568 6~15\uC790 \uC774\uD558\uB85C \uC785\uB825");
		pwReg.setFont(new Font("굴림", Font.PLAIN, 10));
		gridBagMake(pwReg, 1, 3, 3, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(pwReg, gbConstraints);

		JLabel PWchk = new JLabel("Password Check");
		PWchk.setFont(new Font("times new roman", Font.BOLD, 12));
		gridBagMake(PWchk, 0, 4, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(PWchk, gbConstraints);

		pwchk = new JTextField();
		gridBagMake(pwchk, 1, 4, 1, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		pwchk.setColumns(10);
		signupPanel.add(pwchk, gbConstraints);

		pwchkReg = new JLabel("\uBE44\uBC00\uBC88\uD638 \uB2E4\uC2DC \uC785\uB825");
		pwchkReg.setFont(new Font("굴림", Font.PLAIN, 10));
		gridBagMake(pwchkReg, 1, 5, 3, 1);
		gbConstraints.fill = GridBagConstraints.NONE;
		signupPanel.add(pwchkReg, gbConstraints);

		signup = new JButton("Sign Up");
		signup.setFont(new Font("times new roman", Font.BOLD, 12));
		gridBagMake(signup, 0, 6, 3, 1);
		gbConstraints.fill = GridBagConstraints.BOTH;
		signupPanel.add(signup, gbConstraints);

		add(signupPanel);
	}

	@Override
	public void gridBagMake(Component c, int x, int y, int w, int h) {
		gbConstraints.gridx = x;
		gbConstraints.gridy = y;
		gbConstraints.gridwidth = w;
		gbConstraints.gridheight = h;

		gbLayout.setConstraints(c, gbConstraints);
	}

	private boolean idLeng(String id) {
		if (id.length() < 4 || id.length() > 15)
			return false;
		return true;
	}

	public String idchk(String id) {
		String txt = "";
		if (!idLeng(id))
			txt = "ID는 4~15자 이내로만 입력 가능합니다";
		return txt;
	}

	private boolean pwsign(String pw) {
		String sign = "~!@#$%^&*";
		for (int i = 0; i < sign.length(); i++) {
			if (pw.contains("" + sign.charAt(i)))
				return true;
		}
		return false;
	}

	private boolean pwLeng(String pw) {
		if (pw.length() < 6 || pw.length() > 20) {
			return false;
		}
		return true;
	}

	public String pwChk(String pw) {
		String txt = "";
		if (!pwsign(pw))
			txt = "비밀번호는 반드시 특수문자를 포함해야 합니다.";
		else if (!pwLeng(pw))
			txt = "비밀번호는 6~15자 이내로만 입력 가능합니다.";
		return txt;
	}

	private void addListener() {
		goBack.addActionListener(this);
		signup.addActionListener(this);
		id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = idchk(id.getText());
				if (txt.equals("")) {
					txt = idok;
					idReg.setForeground(Color.blue);
				} else {
					idReg.setForeground(Color.red);
				}
				idReg.setText(txt);
			}
		});
		pw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String txt = pwChk(pw.getText());
				if (txt.equals("")) {
					txt = pwok;
					pwReg.setForeground(Color.blue);
				} else {
					pwReg.setForeground(Color.red);
				}
				pwReg.setText(txt);
			}
		});
		pwchk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (pwchk.getText().equals(pw.getText())) {
					pwchkReg.setForeground(Color.blue);
					pwchkReg.setText(pwchkok);
				} else {
					pwchkReg.setForeground(Color.red);
					pwchkReg.setText("비밀번호가 일치하지 않습니다.");
				}
			}
		});
		mf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(signup)) {
			if (idReg.getText().equals(idok) && pwReg.getText().equals(pwok) && pwchkReg.getText().equals(pwchkok)) {
				String attempt = "signup/" + id.getText() + "/" + pw.getText();
				String sign = clientSet.memberchk(attempt);
				if (sign.equals("1")) {
					idReg.setForeground(Color.red);
					idReg.setText("이미 사용중인 ID입니다.");
				} else if (sign.equals("2")) {
					signUpWell();
				}
			}
		} else if (e.getSource().equals(goBack)) {
			mf.goMain();
		}
	}

	private void signUpWell() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JInternalFrame itf = new JInternalFrame("회원가입 성공", false, false, false, false);
				itf.setBounds(320, 180, 640, 360);
				itf.setBackground(Color.white);
				itf.setLayout(null);

				JLabel great = new JLabel("회원가입 성공");
				great.setBounds(220, 90, 200, 40);
				great.setFont(new Font("굴림", Font.BOLD, 20));
				itf.add(great);

				JLabel oneMore = new JLabel("로그인 창으로 돌아갑니다.");
				oneMore.setBounds(220, 125, 200, 40);
				great.setFont(new Font("굴림", Font.BOLD, 15));
				itf.add(oneMore);
				
				itf.setVisible(true);
				add(itf);
				mf.setVisible(true);

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mf.goMain();
			}
		}).start();
	}

}
