package guiWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clientpackage.ClientMain;

public class ButtonRegistrationActionListener implements ActionListener {
	JFrame frame, frameLogin;
	JTextField tFUser;
	JTextField tFPassword;
	ClientMain client;

	public ButtonRegistrationActionListener(JFrame frame, JFrame frameLogin, JTextField tF1, JTextField tF2, ClientMain client) {
		this.frame = frame;
		this.frameLogin = frameLogin;
		this.tFUser = tF1;
		this.tFPassword = tF2;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String text = b.getText();
		if ("Cancel".equals(text)) {
			frame.setVisible(false);
			frameLogin.setVisible(true);
		} else {
			if (("".equals(tFUser.getText()))) {
				JOptionPane.showMessageDialog(frame, "The username is missing");
			} else if (("".equals(tFPassword.getText()))) {
				JOptionPane.showMessageDialog(frame, "No password");
			} else {
				client.RegistrationUser(tFUser.getText(), tFPassword.getText());
			}
		}
	}
}
