
import java.io.*;
import java.net.Socket;
import java.util.*;

public class WorkerRunnable implements Runnable {

	protected Socket clientSocket     = null;
	private BufferedReader inFromUser = null;

	private boolean connected = true;
	private boolean verified = false;

	private UserProfile user;
	private String usernameActual;

	public WorkerRunnable(Socket clientSocket) {

		this.clientSocket = clientSocket;

	}

	public void run() {

		try {

			OutputStream output = clientSocket.getOutputStream();
			inFromUser = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			output.write(("You are connected\n").getBytes());

			while(connected) {

				String clientMessage = inFromUser.readLine();

				clientMessage.trim();

				if(clientMessage.trim().equals("QUIT")) {

					output.write(("You are now disconnected.\n").getBytes());

					connected = false;

				}else if(clientMessage.trim().equals("RETR:COURSES") && verified) {

					SemesterInformation info = new SemesterInformation();
					ArrayList<Course> list = info.getSemester();

					for(int i=0;i<list.size();i++) {

						String temp = list.get(i).toString() + "\n";
						output.write((temp).getBytes());

						if(i == list.size()-1) {

							output.write(("DONE\n").getBytes());

						}

					}

				}else if(clientMessage.substring(0, 7).equals("VERIFY:")) {

					user = new UserProfile();

					String temp = clientMessage.substring(7, clientMessage.length());
					String[] parts = temp.split("\\*");

					String username = parts[0];
					String pass = parts[1];

					String passActual = user.retrieveUserPassword(username);

					if(pass.equals(passActual)) {

						verified = true;
						usernameActual = username;
						output.write(("GOOD\n").getBytes());

					}else {

						output.write(("BAD\n").getBytes());

					}		

				}else if(clientMessage.equals("COURSES") && verified) {

					user = new UserProfile();

					ArrayList<String> list = user.readUserProfile(usernameActual);

					String temp = "";

					for(int i=0;i<list.size();i++) {

						temp += list.get(i);

						if(i != list.size()-1) {

							temp += "*";

						}

					}	

					temp += "\n";

					output.write((temp).getBytes());

				}else if(clientMessage.substring(0,8).equals("CREATE0:")) {

					user = new UserProfile();

					String temp = clientMessage.substring(8, clientMessage.length());
					String[] parts = temp.split("\\*");
					String username = parts[0];
					String password = parts[1];

					user.createUserProfile(username, password);

				}else if(clientMessage.substring(0,8).equals("CREATE1:")) {
				
					user = new UserProfile();

					String temp = clientMessage.substring(8, clientMessage.length());
					String[] parts = temp.split("\\*");
					String username = parts[0];
					String password = parts[1];

					ArrayList<String> list = new ArrayList<String>();

					for(int i=2;i<parts.length;i++) {

						list.add(parts[i]);

					}	

					user.createUserProfile(username, password, list);

				}else if(clientMessage.substring(0,11).equals("ADDCOURSES:") && verified) {

					user = new UserProfile();

					String temp = clientMessage.substring(11, clientMessage.length());
					String[] parts = temp.split("\\*");

					ArrayList<String> list = new ArrayList<String>();

					for(int i=0;i<parts.length;i++) {

						list.add(parts[i]);

					}

					user.appendUserProfile(usernameActual, list);

				}
	

			}

			output.close();

		} catch (IOException e) {

			System.out.println("Error");

		}

	}

}
