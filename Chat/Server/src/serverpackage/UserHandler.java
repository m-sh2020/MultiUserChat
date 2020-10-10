package serverpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class UserHandler {
	private String[] subStr;
	private int numberUsers = 0;
	private ArrayList<User> u = new ArrayList<User>();
	private File file = new File("Users.txt");
	private File fileMessage = new File("Messages.txt");
	
    //file reading and creating an array of users
	public int readingFile() {
		try (BufferedReader is = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = is.readLine()) != null) {
				subStr = s.split(" ");
				u.add(new User(Integer.parseInt(subStr[0]), subStr[1], Cipher.decryption(subStr[2])));
				numberUsers++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numberUsers;
	}

	//
	public User getUserByName(String name) {
		for (User user : u)
			if (user.getName().equals(name))
				return user;
		return null;
	}

	//add a new user to the file
	public void addNewUser(User newUser) {
		try (FileWriter os = new FileWriter(file, true)) {
			os.write(newUser.toString());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//writing a message to a file
	public void writeMessageinFile(String msg) {
		try (FileWriter os = new FileWriter(fileMessage, true)) {
			 int startNum = 8;
		     int endNum = msg.length();
		     msg = msg.substring(startNum, endNum);
		     Date date = new Date();
			os.write("("+ date.toString()+")"+msg + "\n");
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//displaying the file content on the screen (with decryption)
	public void readingAllFile() {
		try (BufferedReader is = new BufferedReader(new FileReader(file))) {
			String s;
			while ((s = is.readLine()) != null) {
				subStr = s.split(" ");
				subStr[2] = Cipher.decryption(subStr[2]);
				for (int i = 0; i < subStr.length; i++) {
					System.out.print(subStr[i] + " ");
				}
				System.out.println("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
