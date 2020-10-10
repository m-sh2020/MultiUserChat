package guiWindows;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientpackage.ClientMain;

public class LoginWindow {
	public static JFrame mainFrame = new JFrame("Chat program");
	private JTextField textFieldUser = new JTextField("", 20);
	private JTextField textFieldPassword = new JTextField("", 20);
	String username, password;
	
	public void createGUI(ClientMain client) {
	//	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				client.closeCoonnect();
				System.exit(0);
			}
		});
		
		
		
		mainFrame.setBounds(500, 200, 400, 300);

		Container contentPane = mainFrame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JLabel userLabel = new JLabel("Username :");
		JLabel passLabel = new JLabel("Password : ");
		JLabel LabelTitle = new JLabel("Login Form");

		JButton butOK = new JButton("OK");
		JButton butCancel = new JButton("Cancel");
		JButton butReg = new JButton("Registration");

		ButtonLoginActionListener buttonAct = new ButtonLoginActionListener(mainFrame, textFieldUser, textFieldPassword, client);
		butCancel.addActionListener(buttonAct);
		butCancel.setActionCommand("Cancel");
		butReg.addActionListener(buttonAct);
		butCancel.setActionCommand("Registration");
		
		butOK.addActionListener(buttonAct);
		butOK.setActionCommand("Œ ");
		
		JPanel panelLogFields = new JPanel(new GridBagLayout());
		contentPane.add(panelLogFields, BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		c.gridwidth = 2;
		panelLogFields.add(LabelTitle, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		c.weightx = 1;
		panelLogFields.add(userLabel, c);
		userLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		c.gridx = 1;
		panelLogFields.add(textFieldUser, c);
		c.gridy = 2;
		c.gridx = 0;
		panelLogFields.add(passLabel, c);
		passLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		c.gridx = 1;
		panelLogFields.add(textFieldPassword, c);
		c.gridy = 3;
		c.gridx = 0;
		c.insets = new Insets(0, 100, 0, 0);
		panelLogFields.add(butOK, c);
		c.gridx = 1;
		panelLogFields.add(butCancel, c);

		JPanel panelButReg = new JPanel(new GridBagLayout());
		contentPane.add(panelButReg, BorderLayout.SOUTH);
		panelButReg.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		panelButReg.add(butReg);

		mainFrame.setVisible(true);
	}
	
    public String  getNameUser () {
        return  textFieldUser.getName();
    }
	
     public  String  gettextFieldPassword() {
        return  textFieldPassword.getName();
    }
}