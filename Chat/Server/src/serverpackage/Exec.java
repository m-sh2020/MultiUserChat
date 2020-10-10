package serverpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Exec implements Runnable {

	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private ServerMain server;
	private String login,password;
	
	public String getLogin() {
		return login;
	}

	public Exec(Socket s, ServerMain server) {
		socket = s;
		this.server = server;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		try {
			while (true) {
				while (!reader.ready()) {
					Thread.sleep(100);
				}
				workWithClient();
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void workWithClient() throws IOException {
		String str;
		while ((str = reader.readLine()) != null) {
			String[] tokens = str.split(" ");
			if (tokens != null && tokens.length > 0) {
				String cmd = tokens[0];
				switch (cmd) {
				case "authorization":
					checkingLoginPassword(tokens);
					break;
				case "registration":
					addUser(tokens);
					break;
				case "message":
					System.out.println(str);
					server.sendMessageToAllClients(str);
					UserHandler uh1 = new UserHandler();
					uh1.writeMessageinFile(str);
					break;
				case "removeUser":
					server.removeClient(this, tokens[1]);
					break;
				case "numUs":
					sendAnswer("Number_Users:" + " " + ServerMain.numUserOnline);
					break;
				}
			}
		}
	}

	public void checkingLoginPassword(String[] tokens) {
		login = tokens[1];
		password = tokens[2];
		UserHandler uh1 = new UserHandler();
		int numberUsers = uh1.readingFile();
		User currentUser = uh1.getUserByName(login);
		if (currentUser == null) {
			System.out.println("The user was not found.");
			sendAnswer("noUser");
		} else if (password.equals(currentUser.Password())) {
			System.out.println("A new user has joined the chat");
			sendAnswer("Enter" + " " + login);
			server.addSpisok(this, login);
		} else {
			System.out.println("Password is incorrect");
			sendAnswer("noPassword");
		}
	}
	
	//adding a new user
	public void addUser(String[] tokens) {
		String login = tokens[1];
		String password = tokens[2];

		UserHandler uh1 = new UserHandler();
		int numberUsers = uh1.readingFile();
		User currentUser = uh1.getUserByName(login);

		if (currentUser == null) {
			System.out.println("The user was not found.");
			User newUser = new User(numberUsers + 1, login, password);
			uh1.addNewUser(newUser);
			sendAnswer("regUser" + " " + login);
			sendAnswer("Enter" + " " + login);
			server.addSpisok(this, login);
		} else {
			sendAnswer("noRegUser");
		}
	}

	//sending a message to the client
	public void sendAnswer(String answer){
		writer.println(answer); 
		writer.flush();
	}
}


	
	
	



