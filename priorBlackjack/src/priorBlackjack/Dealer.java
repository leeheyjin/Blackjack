package priorBlackjack;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.loading.PrivateClassLoader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Dealer extends JFrame {
	private JButton deal;
	private Deck deckNew;
	private JTextArea displayArea;
	private ExecutorService executor;
	private ServerSocket server;
	private SockServer[] sockServer;
	private Playbj dcards;

	private ArrayList<Playbj> players;
	private String dcard1, dcard2;
	private int counter = 1;
	private int playersLeft;
	private boolean roundover = true;

	public Dealer() {
		super("Dealer");

		players = new ArrayList<>();
		sockServer = new SockServer[100];
		executor = Executors.newFixedThreadPool(100);
		deal = new JButton("Deal Cards");
		deal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deal.setEnabled(true);
				deckNew = new Deck();
				roundover = false;
				dealCards();
				displayMessage("\n\nCards dealt\n\n");
			}
		});
		add(deal, BorderLayout.SOUTH);

		displayArea = new JTextArea();
		displayArea.setEditable(false);
		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		setSize(400, 400);
		setVisible(true);
	}

	public void runDeal() {
		try {
			server = new ServerSocket(8765, 100);
			while (true) {
				try {
					sockServer[counter] = new SockServer(counter);
					sockServer[counter].waitForConnection();
					executor.execute(sockServer[counter]);
				} catch (EOFException e) {
					displayMessage("\n Server terminated connection");
				} finally {
					++counter;
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	private void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});
	}

	private void dealCards() {
		try {
			playersLeft = counter - 1;
			deckNew.shuffle();
			dcard1 = deckNew.dealCard();
			dcard2 = deckNew.dealCard();
			displayMessage("\n\n" + dcard1 + "" + dcard2);
			for (int i = 1; i <= counter; i++) {
				String c1, c2;
				c1 = deckNew.dealCard();
				c2 = deckNew.dealCard();
				Playbj p = new Playbj(c1, c2);
				players.add(p);
				sockServer[i].sendData("You were Dealt: \n" + c1 + "" + c2);
				sockServer[i].sendData("Your total: " + p.getCardTotal());

			}
		} catch (NullPointerException e) {
		}
	}

	private void results() {
		try {
			for (int i = 1; i <= counter; i++) {
				sockServer[i].sendData("Dealer has " + dcards.getCardTotal());

				if ((dcards.getCardTotal() <= 21) && (players.get(i - 1).getCardTotal() <= 21)) {
					if (dcards.getCardTotal() > players.get(i - 1).getCardTotal()) {
						sockServer[i].sendData("\n You Lose!");
					} else if (dcards.getCardTotal() < players.get(i - 1).getCardTotal()) {
						sockServer[i].sendData("\n You Win!");
					} else if (dcards.getCardTotal() == players.get(i - 1).getCardTotal()) {
						sockServer[i].sendData("\n Draw!");
					}
				} else if (dcards.checkBust()) {
					if (players.get(i - 1).getCardTotal() <= 21) {
						sockServer[i].sendData("\n You Win!");
					} else if (players.get(i - 1).checkBust()) {
						sockServer[i].sendData("\n Draw!");
					}
				} else if (dcards.getCardTotal() <= 21 && players.get(i - 1).checkBust()) {
					sockServer[i].sendData("\n You Lose!");
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	}

	private class SockServer implements Runnable {
		private ObjectOutputStream output;
		private ObjectInputStream input;
		private Socket connection;
		private int myConId;

		public SockServer(int counterIn) {
			myConId = counterIn;
		}

		@Override
		public void run() {
			try {
				try {
					getStreams();
					processConnection();
				} catch (EOFException e) {
					displayMessage("\n Server" + myConId + "terminated connection");
				} finally {
					closeConnection();
				}
			} catch (IOException e) {
			}
		}

		private void waitForConnection() throws IOException {
			displayMessage("Waiting for connection" + myConId + "\n");
			connection = server.accept();
			displayMessage("Connection " + myConId + "received from: " + connection.getInetAddress().getHostName());
		}

		private void getStreams() throws IOException {
			output = new ObjectOutputStream(connection.getOutputStream());
			output.flush();

			input = new ObjectInputStream(connection.getInputStream());

			displayMessage("\n Got I/O streams\n");
		}

		private void processConnection() throws IOException {
			String message = "Connection" + myConId + " successful";
			sendData(message);

			do {
				try {
					if (message.contains("hit")) {
						cardHit();
					} else if (message.contains("stand")) {
						this.sendData("please wait");
						playersLeft--;
						checkDone();
					}
					message = (String) input.readObject();
				} catch (ClassNotFoundException e) {
					displayMessage("\nUnknown object type received");
				}
			} while (!message.equals("CLIENT >>> TERMINATED"));
		}

		private void dealerGo() {
			dcards = new Playbj(dcard1, dcard2);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (dcards.getCardTotal() < 16) {
				while (dcards.getCardTotal() < 16) {
					String card1 = deckNew.dealCard();
					dcards.cardHit(card1);
					displayMessage("Dealer hits..." + card1 + "\n" + "Total: " + dcards.getCardTotal() + "\n");
				}
			} else if (dcards.checkBust()) {
				displayMessage("Dealer busts!");
			} else {
				displayMessage("Dealer has " + dcards.getCardTotal());
			}
			results();
		}

		private void cardHit() {
			String nextc = deckNew.dealCard();
			sendData(nextc);
			players.get(this.myConId - 1).cardHit(nextc);
			sendData("Your total: " + players.get(this.myConId - 1).getCardTotal());

			if (players.get(this.myConId - 1).checkBust()) {
				sendData("Bust.\n");
				playersLeft--;
				if (playersLeft == 0) {
					dealerGo();
				}
			}
		}

		private void checkDone() {
			if (playersLeft == 0) {
				dealerGo();
			}
		}

		private void closeConnection() {
			displayMessage("\nTerminating connection" + myConId + "\n");
			try {
				output.close();
				input.close();
				connection.close();
			} catch (IOException e) {
			}
		}

		private void sendData(String message) {
			try {
				output.writeObject(message);
				output.flush();
			} catch (IOException e) {
				displayArea.append("\nError writing object");
			}
		}
	}

}
