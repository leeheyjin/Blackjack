package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;
import java.util.ArrayList;

public class ServerSet {
	private ServiceToClient newC;
	private ServerSocket server;
	private Socket socket;
	private ArrayList<ServiceToClient> cList = new ArrayList<>();
	private ServiceToClient[] wr = new ServiceToClient[1];

	public ServerSet() {
		setServer();
	}

	private void setServer() {
		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 9847));
			while (true) {
				System.out.println("서버 설정 완료. 클라이언트 접속 대기...");
				socket = server.accept();

				InetAddress ia = socket.getInetAddress();
				System.out.println("접속 IP: " + ia.getHostAddress());
				System.out.println(socket + "님이 방문하셨습니다.");
				System.out.println(socket.hashCode());

				newC = new ServiceToClient(socket, this);
				newC.start();
				cList.add(newC);
				System.out.println(cList.size() + "명이 접속 중");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chat(String attempt, String userId, ServiceToClient stc) {
		attempt = attempt.replace("chat/", "");
		sendAll(userId, attempt);
	}

	private void sendAll(String userId, String attempt) {
		String realMsg = "[" + userId + "]" + attempt;
		for (int i = 0; i < cList.size(); i++) {
			try {
				cList.get(i).getSendMsg().write(realMsg.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateOnUser(ServiceToClient stc) {
		String onList = "<접속회원 목록>\n";
		if (cList.size() > 0) {
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).getUserId() != null) {
					onList = onList + cList.get(i).getUserId() + "\n";
				}
			}
		}

		try {
			stc.getSendMsg().write(onList.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void exitWL(ServiceToClient stc) {
		for (int i = 0; i < cList.size(); i++) {
			if (cList.get(i).equals(stc)) {
				cList.remove(i);
				stc = null;
			}
		}
	}
}
