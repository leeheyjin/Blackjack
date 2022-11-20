package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import gui.HomePanel;
import gui.MainFrame;

public class ClientSet {
	private OutputStream sendMsg;
	private InputStream reMsg;

	private Socket clientSocket;
	private MainFrame mf;

	private String tempMsg;
	private String num;
	private String out = "";

	private String whosIn = "";
	private String chat = "";

	private String userID;
	private int money;

	public ClientSet() {
		setClient();
		try {
			reMsg = clientSocket.getInputStream();
			sendMsg = clientSocket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (clientSocket != null) {
			mf = new MainFrame(this);
		}
	}

	public void setClient() {
		try {
			clientSocket = new Socket("localhost", 9847);
			System.out.println("클라이언트 정보: " + clientSocket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ClientSet getInstance() {
		return LazyHolder.IT;
	}

	private class LazyHolder {
		private final static ClientSet IT = new ClientSet();
	}

	public String memberchk(String attempt) {
		try {
			num = "";
			sendMsg.write(attempt.getBytes());
			System.out.println("서버로 전송");

			byte[] reBuffer = new byte[100];
			reMsg.read(reBuffer);
			num = new String(reBuffer);
			num = num.trim();

			System.out.println(num);
			if (num.startsWith("3/")) {
				String[] ua = num.split("/");
				userID = ua[1];
				money = Integer.parseInt(ua[2]);
				num = "3";
				receive();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

	private void receive() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (userID != null) {
					try {
						byte[] reBuffer = new byte[10000];
						reMsg.read(reBuffer);
						tempMsg = new String(reBuffer);
						tempMsg = tempMsg.trim();
						if (tempMsg.equals("logout/")) {
							break;
						} else if (tempMsg.startsWith("<접속회원 목록>\n")) {
							whosIn = tempMsg.replace("<접속회원 목록>\n", "");
						} else {
							chat = chat + tempMsg + "\n";
							mf.hp.addChat(chat);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("메시지 수신 정지");
			}
		}).start();
	}

	public void send(String msg) {
		try {
			sendMsg.write(msg.getBytes());
			sendMsg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getWhosIn() {
		return whosIn;
	}

	public void setWhosIn(String whosIn) {
		this.whosIn = whosIn;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

}
