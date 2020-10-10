package guiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;
import clientpackage.ClientMain;

public class ButtonMessageActionListener implements ActionListener {
	JTextField textmsg;
	ClientMain client;
	String login;

	public ButtonMessageActionListener(JTextField textmsg, ClientMain client, String login) {
		this.textmsg = textmsg;
		this.client = client;
		this.login=login;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!textmsg.getText().trim().isEmpty()) {
			String textmessage = textmsg.getText();
			try {
				client.sendMessageOnServer(login + ":"+" "+ textmessage);
				textmsg.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//focus on the text field with the message
			textmsg.grabFocus();
		}
	}
}
