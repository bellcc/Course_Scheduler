
/**
 * Author         : Christopher Bell
 * Date           : 04-04-2016
 * Purpose        : This class is responsible for all of the messages that can be sent from the client to the server.
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {

	Socket clientSocket          = null;
	BufferedReader inFromUser    = null;
	BufferedReader inFromServer  = null;
	DataOutputStream outToServer = null;

	public Client(int port) throws IOException{

		this.clientSocket = new Socket("localhost", port);
		
	}

	public Client(Socket clientSocket) {
			
		this.clientSocket = clientSocket;

	}

	/**
	 * This method must called before any of the other because it connects you to the server however it does not verify the user's authenticity.
	 * @return A message from the server
	 */
	public String connect() throws IOException{

		this.inFromUser   = new BufferedReader(new InputStreamReader(System.in));
		this.inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		this.outToServer  = new DataOutputStream(clientSocket.getOutputStream());

		String serverMessage;
		serverMessage = inFromServer.readLine();

		return serverMessage.trim();

	}

	public void addUserCourses(String username, ArrayList<String> list) throws IOException {

		String temp = "";

		for(int i=0;i<list.size();i++) {

			temp += list.get(i);

			if(i != list.size()-1) {

				temp += "*";

			}

		}

		outToServer.write(("ADDCOURSES:" + temp + "\n").getBytes());

	}

	/**
	 * This method verifies a user with the server or in other words, checks that they have an account.
	 * @param username This is the username of the user.
	 * @param password This is the password that secures a user's account.
	 * @return Returns true if the user has been verified by the server and false otherwise.
	 */
	public boolean verifyUser(String username, String password) throws IOException {

		outToServer.write(("VERIFY:" + username + "*" + password + "\n").getBytes());

		String serverMessage = inFromServer.readLine();
		serverMessage.trim();

		if(serverMessage.equals("GOOD")) {

			return true;

		}

		return false;

	}

	/**
	 * This method retrieves all of the previously taken courses for the user.
	 * @return This method returns an array list of strings that contains the course titles such as 'CSE201'
	 */
	public ArrayList<String> retrieveUserCourses() throws IOException {

		String temp = "";

		outToServer.write(("COURSES\n").getBytes());
		temp = inFromServer.readLine().trim();

		String[] parts = temp.split("\\*");
		ArrayList<String> list = new ArrayList<String>();

		for(int i=0;i<parts.length;i++) {

			list.add(parts[i]);

		}	

		return list;

	}

	/**
	 * This method creates a user profile with the server given a username, password, and list of previously taken courses.
	 * @param username The user name of the user.
	 * @param password The password that authenticates the user.
	 * @param list The list of previously taken courses.
	 */
	public void createUserProfile(String username, String password, ArrayList<String> list) throws IOException {

		String temp = "";

		for(int i=0;i<list.size();i++) {

			temp += list.get(i);

			if(i != list.size()-1) {

				temp += "*";
	
			}

		}

		outToServer.write(("CREATE1:" + username + "*" + password + "*" + temp + "\n").getBytes());

		outToServer.write((temp + "\n").getBytes());

	}

	/**
	 * This method creates a user account given a username and password.
	 * @param username The username for the user.
	 * @param password The password that authenticates the user.
	 */
	public void createUserProfile(String username, String password) throws IOException {

		outToServer.write(("CREATE0:" + username + "*" + password + "\n").getBytes());

	}

	/**
	 * This method retrieves all of the classes for a given semester.
	 * @return This method returns an arraylist of type Course.
	 */
	public ArrayList<Course> retrieveCourses() throws IOException {

		ArrayList<Course> list = new ArrayList<Course>();

		outToServer.write(("RETR:COURSES\n").getBytes());

		String serverMessage = "";

		while(!(serverMessage.equals("DONE"))) {

			serverMessage = inFromServer.readLine();

			if(!(serverMessage.equals("DONE"))) {

				Course course = parseMessage(serverMessage.trim());
				list.add(course);

			}

		}

		return list;

	}

	/**
	 * This method must be called for a user to disconnect.
	 */
	public void disconnect() throws IOException {

		outToServer.write(("QUIT\n").getBytes());
		String message = inFromServer.readLine().trim();
		System.out.println(message);

	}

	/**
	 * This is a helper method which parses a message from the server containing a Course object.
	 * @param message The course object message from the server.
	 * @return A course object representing a course from the server.
	 */
	private Course parseMessage(String message) {

		String[] parts = message.split("\\*");

		int st = 0;
		int et = 0;
//		int ext = 0;

		try {

			st = Integer.parseInt(parts[14]);
			et = Integer.parseInt(parts[15]);
//			ext = Integer.parseInt(parts[17]);

		}
		catch(NumberFormatException e) {

			st = -1;
			et = -1;
//			ext = -1;

		}

//		char exam_day = parts[16].charAt(0);


		Course course = new Course(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13], st, et);

		return course;

	}

}
