package clientpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import guiWindows.LoginWindow;
import guiWindows.MessagesWindow;
import guiWindows.RegistrationWindow;

public class ClientMain {
	Socket socket;
	private static Connection connection;
	private static MessagesWindow mw;

	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Socket socket = new Socket("127.0.0.1", 7890);
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		connection = new Connection(socket);

		LoginWindow form = new LoginWindow();
		ClientMain client = new ClientMain();
		form.createGUI(client);

		while (true) {
			while (!reader.ready()) {
				Thread.sleep(100);
			}
			String serverAnswer = connection.receive();
			client.checkAnswer(serverAnswer);
		}
	}

	public void sendLogPasswOnServer(String login, String password) throws IOException {
		connection.send("authorization" + " " + login + " " + password);
	}

	public void sendMessageOnServer(String msg) throws IOException {
		connection.send("message" + " " + msg);
	}

    //request to delete a user
	public void sendUserRemove(String login) throws IOException {
		connection.send("removeUser" + " " + login);
	}
	
	//request the number of connected users
	public void sendNumUsers() throws IOException {
		connection.send("numUs");
	}

	//check the response from the server
	public void checkAnswer(String answer) {
		String[] tokens = answer.split(" ");
		String answerT = tokens[0];
		switch (answerT) {
		case "noUser":
			JOptionPane.showMessageDialog(LoginWindow.mainFrame, "There is no user with this name.");
			break;
		case "noPassword":
			JOptionPane.showMessageDialog(LoginWindow.mainFrame, "Incorrect password.");
			break;
		case "Enter":
			LoginWindow.mainFrame.setVisible(false);
			mw = new MessagesWindow();
			mw.createGUI(tokens[1]);
			break;
		case "regUser":
			JOptionPane.showMessageDialog(RegistrationWindow.mainFrame, "The user has been added.");
			RegistrationWindow.mainFrame.setVisible(false);
			LoginWindow.mainFrame.setVisible(true);
			break;
		case "noRegUser":
			JOptionPane.showMessageDialog(RegistrationWindow.mainFrame, "A user with this username already exists.");
			break;
		case "message":
			int startNum = 8;
			int endNum = answer.length();
			answer = answer.substring(startNum, endNum);
			mw.writeMessage(answer);
			break;
		case "removeUser":
			mw.writeNumUser("Number_Users:" + " " + tokens[2]);
			mw.writeMessage(tokens[1] + " logged out of the chat.");
			break;
		case "newUser":
			mw.writeNumUser("Number_Users:" + " " + tokens[2]);
			mw.writeMessage(tokens[1] + " joined the chat.");
			break;
		}
	}

	public void RegistrationUser(String login, String password) {
		try {
			connection.send("registration" + " " + login + " " + password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeCoonnect() {
		try {
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
