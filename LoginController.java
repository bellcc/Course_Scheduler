package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * LoginController class. Controls user interaction with LoginController.fxml
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class LoginController {
	
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	
	/**
	 * Constructor, called before initialize().
	 */
	public LoginController() {
		
	}
	
	/**
	 * Initializes the new controller. Called after the fxml file is loaded.
	 */
	@FXML private void initialize() {
		
	}
	
	@FXML private void loginButtonClicked() {
		String username = usernameField.textProperty().getValue();
		String password = passwordField.textProperty().getValue();
		if (username.equals("") || password.equals("")) {
			//Show error dialog
			MainApp.sharedInstance().showErrorMessage("Error", null, "You must enter a username and password.");
		} else {
			//Perform login attempt
			User.loginWithUsernameAndPassword(username, password, (success) -> {
				if (success) {
					//Login was successful
					MainApp.sharedInstance().showLoginOrHome();
				} else {
					//Login was unsuccessful
					MainApp.sharedInstance().showErrorMessage("Error", null, "Your username and password do not match.");
				}
			});
		}
	}
}
