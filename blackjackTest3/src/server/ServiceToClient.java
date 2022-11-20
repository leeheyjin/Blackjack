package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import jdbc.MemberDAO;
import jdbc.MemberDTO;

public class ServiceToClient extends Thread {
	private OutputStream sendMsg;
	private InputStream reMsg;

	private Socket socket;
	private ServerSet serverSet;
	private String userId;

	private Member member = new Member();
	private MemberDAO mDao = MemberDAO.getInstance();
	private MemberDTO mDto;

	public ServiceToClient(Socket socket, ServerSet serverSet) {
		this.socket = socket;
		this.serverSet = serverSet;
		try {
			reMsg = socket.getInputStream();
			sendMsg = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		infiniteStream();
	}

	private void infiniteStream() {
		try {
			while (true) {
				byte[] reBuffer = new byte[100];
				reMsg.read(reBuffer);
				String attempt = new String(reBuffer);
				attempt = attempt.trim();
				whatInfo(attempt);
			}
		} catch (SocketException e) {
			serverSet.exitWL(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void whatInfo(String attempt) {
		if (attempt.startsWith("login") || attempt.startsWith("signup")) {
			memberCheck(attempt);
		} else if (attempt.startsWith("chat")) {
			serverSet.chat(attempt, userId, this);
		} else if (attempt.startsWith("logout")) {
			System.out.println("클라이언트 로그아웃");
			userId = null;
			logout(attempt);
		} else if (attempt.startsWith("접속자 목록")) {
			serverSet.updateOnUser(this);
		} else if (attempt.startsWith("gg/")) {
			gameOver(attempt);
		}
	}

	private void gameOver(String attempt) {
		try {
			String[] ar = attempt.split("/");
			mDto = new MemberDTO();
			mDto.setId(ar[1]);
			mDto.setMoney(Integer.parseInt(ar[2]));
			mDao.moneyUpdate(mDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void logout(String attempt) {
		try {
			sendMsg.write(attempt.getBytes());
			System.out.println("클라이언트 로그아웃 완료");
			sendMsg.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void memberCheck(String attempt) {
		String[] info = attempt.split("/");
		String num = null;
		if (info[0].equals("login")) {
			num = "" + member.login(info[1], info[2]);
		} else if (info[0].equals("signup")) {
			if (!member.iddup(info[1])) {
				num = "1";
			} else {
				member.signUp(info[1], info[2]);
				num = "2";
			}
		}

		if (num.equals("3")) {
			userId = info[1];
			mDto = mDao.selectOne(userId);
			num = "3/" + mDto.getId() + "/" + mDto.getMoney();
			System.out.println(num);
		}

		try {
			sendMsg.write(num.getBytes());
			sendMsg.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OutputStream getSendMsg() {
		return sendMsg;
	}

}
