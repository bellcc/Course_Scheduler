package application;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

/**
 * RootLayoutController class. Controls user interaction with RootLayout.fxml
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class RootLayoutController {

	@FXML private MenuBar menu;
	
	@FXML private void initialize() {
		if (User.currentUser() == null) {
			menu.setVisible(false);
		}
	}
	
	public void toggleMenu(boolean on) {
		menu.setVisible(on);
	}
	
	@FXML private void logoutButtonClicked() {
		User.logout(success -> {
			if (success) {
				MainApp.sharedInstance().showLoginOrHome();
			}
		});
	}
}
