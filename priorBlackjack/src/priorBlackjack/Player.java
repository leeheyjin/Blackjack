package priorBlackjack;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Player extends JFrame {
	private JButton hit;
	private JButton stand;
	private JPanel buttons;
	private JTextArea displayArea;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String chatServer;
	private Socket client;
	private int cardamt = 0;

	public Player(String host) {
		super("Player");

		chatServer = host;

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		hit = new JButton("Hit");
		hit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendData("hit");
			}
		});
		buttons.add(hit, BorderLayout.SOUTH);

		stand = new JButton("Stand");
		stand.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendData("stand");
			}
		});
		buttons.add(stand, BorderLayout.SOUTH);
		buttons.setVisible(true);
		add(buttons, BorderLayout.SOUTH);

		displayArea = new JTextArea();
		add(new JScrollPane(displayArea), BorderLayout.CENTER);

		setSize(400, 400);
		setVisible(true);
	}

	public void runClient() {
		try {
			connectToServer();
			getStreams();
			processConnection();
		} catch (EOFException e) {
			displayMessage("\n Clinet terminated connection\n");
		} catch (IOException e) {

		} finally {
			closeConnection();
		}
	}

	private void connectToServer() throws IOException {
		displayMessage("Attempting connection\n");
		client = new Socket(InetAddress.getByName(chatServer), 8765);
		displayMessage("Connected to: " + client.getInetAddress().getHostName());

	}

	private void getStreams() throws IOException {
		output = new ObjectOutputStream(client.getOutputStream());
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
		displayMessage("\n Got I/O streams");
	}

	private void processConnection() throws IOException {
		do {
			try {
				message = (String) input.readObject();
				displayMessage("\n" + message);
				if (message.contains("Bust") || message.contains("Please wait")) {
					buttons.setVisible(false);
				}
			} catch (ClassNotFoundException e) {
				displayMessage("\n Unknown object type received");
			}
		} while (!message.equals("SERVER >>> TERMINATED"));
	}

	private void closeConnection() {
		displayMessage("\nClosing connection");
		try {
			output.close();
			input.close();
			client.close();
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

	private void displayMessage(final String messageToDisplay) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				displayArea.append(messageToDisplay);
			}
		});
	}
}
