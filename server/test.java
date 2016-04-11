
import java.io.*;
import java.util.ArrayList;

public class test {

	public static void main(String [] args) throws IOException{

		Client client = new Client(8080);

		//This must be done first
		String temp = client.connect();
		System.out.println(temp);

		//Verifies the user account "chris"
		boolean testVerify = client.verifyUser("chris", "bell");
		System.out.println(testVerify);

		//Retrieve all courses in a semester
		ArrayList<Course> courseList = client.retrieveCourses();
		for(int i=0;i<courseList.size();i++) {

			System.out.println(courseList.get(i).toDisplay());

		}

		//Retrieves all courses for user "chris"
		ArrayList<String> list = client.retrieveUserCourses();
		for(int i=0;i<list.size();i++) {

			System.out.println(list.get(i));

		}		

		//Create new user "alec"
		client.createUserProfile("alec", "bell", list);

		//Create new user "robert"
		client.createUserProfile("robert", "bell");

		//Add course to previously taken courses for account "chris"
		list.add("MTH 231");
		client.addUserCourses("chris", list);		

		//This must always be done last. No exceptions.	
		client.disconnect();

	}

}
