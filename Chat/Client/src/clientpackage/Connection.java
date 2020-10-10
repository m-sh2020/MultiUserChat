package clientpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter out;

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new PrintWriter(socket.getOutputStream());
		this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	// send a message to the server
	public void send(String msg) throws IOException {
		synchronized (this.out) {
			out.println(msg);
			out.flush();
		}
	}

	// get a message from the server
	public String receive() throws IOException,  InterruptedException {
		synchronized (this.br) {

			String message = br.readLine();
			return message;
		}
	}

	// the method of covering the flows of the read, write, and socket
	public void close() throws IOException {
		br.close();
		out.close();
		socket.close();
	}
}
