package serverpackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

	public static int numUserOnline = 0;
	// list of clients, who are online
	private static ArrayList<Exec> users = new ArrayList<Exec>();

	public static void main(String[] args) throws IOException {
		System.out.println("Server is ready");
		ServerSocket serverSocket = new ServerSocket(7890);
		ServerMain server = new ServerMain();
		while (true) {
			System.out.println("Waiting...");
			Socket socket = serverSocket.accept();
			Exec cl = new Exec(socket, server);
			new Thread(cl).start();
			System.out.println("Connected! User: " + socket.getRemoteSocketAddress());
		}
	}

	// adding a client to the list of online users
	public void addSpisok(Exec cl, String login) {
		users.add(cl);
		numUserOnline++;
		sendMessageToAllClients("newUser" + " " + login + " " + numUserOnline);
	}

	//sending a message to all clients
	public void sendMessageToAllClients(String msg) {
		for (Exec o : users) {
			o.sendAnswer(msg);
		}

	}

	// deleting a client from the list when they exit the chat
	public void removeClient(Exec client, String login) {
		users.remove(client);
		numUserOnline--;
		sendMessageToAllClients("removeUser" + " " + login + " " + numUserOnline);
		System.out.println("User " + login + " logged out of the chat.");
	}

}
	 
