
package guiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clientpackage.ClientMain;

public class ButtonLoginActionListener implements ActionListener {
	JFrame frame;
	JTextField tFUser;
	JTextField tFPassword;
	ClientMain client;

	public ButtonLoginActionListener(JFrame frame, JTextField tF1, JTextField tF2, ClientMain client) {
		this.frame = frame;
		this.tFUser = tF1;
		this.tFPassword = tF2;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String text = b.getText();

		if ("Cancel".equals(text))
			System.exit(0);
		else if ("Registration".equals(text)) {
			RegistrationWindow rw = new RegistrationWindow();
			rw.createGUI(frame, client);
			frame.setVisible(false);
		} else if ("OK".equals(text)) {
			if (("".equals(tFUser.getText()))) {
				JOptionPane.showMessageDialog(frame, "The username is missing");
			} else if (("".equals(tFPassword.getText()))) {
				JOptionPane.showMessageDialog(frame, "No password");
			} else {
				try {
					client.sendLogPasswOnServer(tFUser.getText(), tFPassword.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}
}