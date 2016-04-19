package application;

import java.util.function.Consumer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * User model class. Manages login, sign up, and properties of Users.
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class User {

	/*
	 * DO NOT set the currentUser from outside the User class. Should only be set by class operations (login, signup)
	 */
	private static User currentUser;
	
	private StringProperty username;
	private ObservableList<Course> currentCourses;
	
	/**
	 * Constructor is private. To create a new user, use signupWithUsernameAndPassword().
	 */
	private User(String username) {
		//TODO: Update constructor to include all properties
		this.username = new SimpleStringProperty(username);
	}
	
	/**
	 * 
	 * Retrieves the currentUser property.
	 * 
	 * @return User The currently logged in User, or null if no User is logged in
	 */
	public static User currentUser() {
		return currentUser;
	}
	
	/**
	 * 
	 * Retrieves the username property.
	 * 
	 * @return StringProperty the username of this User.
	 */
	public StringProperty username() {
		return username;
	}
	
	/**
	 * Retrieves the courses of the User.
	 * 
	 * @return ObservableList<Course> The courses for this User
	 */
	public ObservableList<Course> getCurrentCourses() {
		return currentCourses;
	}
	
	/**
	 * Sets the courses of the User.
	 * 
	 * @param currentCourses The list of new courses for the User
	 */
	public void setCurrentCourses(ObservableList<Course> currentCourses) {
		this.currentCourses = currentCourses;
	}
	
	/**
	 * 
	 * Attempts to log in a user with a given username and password. The completion will be run after a login attempt,
	 * taking TRUE if the user was successfully logged in, and FALSE if the attempt failed.
	 *  
	 * @param username The username to log in
	 * @param password The password matching the username
	 * @param completion The completion to be run after a log in attempt
	 */
	public static void loginWithUsernameAndPassword(String username, String password, Consumer<Boolean> completion) {
		//TODO: Test username and password against the server. If they match, create new user object with data from server,
		//and set the currentUser property, then run the completion with TRUE. If they do not match (failed login),
		//do not set the currentUser, and run the completion with FALSE.
		
		//Incomplete implementation
		currentUser = new User(username);
		currentUser.currentCourses = FXCollections.observableArrayList();
		currentUser.currentCourses.add(new Course("CSE 201"));
		currentUser.currentCourses.add(new Course("CSE 102"));
		currentUser.currentCourses.add(new Course("CSE 271"));
		currentUser.currentCourses.add(new Course("CSE 278"));
		currentUser.currentCourses.add(new Course("CSE 274"));
		completion.accept(true);
	}
	
	/**
	 * Attempts to log out the current user. The completion will be run with TRUE if the logout is successful,
	 * FALSE if it is not.
	 * 
	 * @param completion The completion to be run after a log out attempt
	 */
	public static void logout(Consumer<Boolean> completion) {
		//TODO: Logout the user by saving the current state to the server. After the save is complete, set the
		//currentUser to NULL, and run the completion with TRUE. If for some reason it fails, run the 
		//completion with FALSE, though this should only happen if the connection is interrupted.
		
		//Incomplete implementation
		currentUser = null;
		completion.accept(true);
	}
	
	/**
	 * 
	 * Attemps to register a new user with the given username and password. The completion will be run with FALSE if 
	 * the registration fails, and TRUE if it succeeds. If the registration is successful, the user will be automatically
	 * logged in after registration.
	 * 
	 * @param username The username to register with
	 * @param password The password to register with
	 * @param completion The completion to be run after a sign up attempt
	 */
	public static void signUpWithUsernameAndPassword(String username, String password, Consumer<Boolean> completion) {
		//TODO: Create a new user with username and password as given. If the user already exists, run the completion
		//with FALSE. If the registration is successful, set the currentUser to the newly created user (logging them in),
		//and run the completion with TRUE.
		
		//Incomplete implementation
		currentUser = new User(username);
		completion.accept(true);
	}
}
