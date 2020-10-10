package guiWindows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clientpackage.ClientMain;

public class MessagesWindow {

	public static JFrame mainFrame;
	public JTextArea textArea, textArea2;
	JTextField messageField;
	private final ClientMain client = new ClientMain();

	public void createGUI(String login) {
		mainFrame = new JFrame("Chat program");
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					client.sendUserRemove(login);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				client.closeCoonnect();
				System.exit(0);
			}
		});

		mainFrame.setSize(500, 500);
		Container contentPane = mainFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		textArea = new JTextArea(20, 33);
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.WEST);
		textArea2 = new JTextArea(20, 10);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		contentPane.add(scrollPane2, BorderLayout.EAST);
		try {
			client.sendNumUsers();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		messageField = new JTextField(30);
		JButton buttonS = new JButton("Send");
		ButtonMessageActionListener buttonAct = new ButtonMessageActionListener(messageField, client, login);
		buttonS.addActionListener(buttonAct);
		JPanel panel2 = new JPanel();
		panel2.add(messageField);
		panel2.add(buttonS);
		contentPane.add(panel2, BorderLayout.SOUTH);
		mainFrame.setResizable(false); // fixed window size
		mainFrame.setVisible(true);
	}

	public void writeMessage(String message) {

		textArea.append(message + "\n");
	}

	public void writeNumUser(String answer) {
		textArea2.setText(answer);
		// TODO Auto-generated method stub
	}
}
