package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * MainApp class. Controls general application events and interactions,
 * as well as the top menu bar actions.
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class MainApp extends Application {

	private static MainApp sharedInstance;
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController rootController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Course Scheduler");
        sharedInstance = this;
        initRootLayout();

        showLoginOrHome();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            RootLayoutController controller = loader.getController();
            rootController = controller;

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the shared instance of MainApp
     * 
     * @return MainApp the global MainApp instance
     */
    public static MainApp sharedInstance() {
    	return sharedInstance;
    }
    
    /**
     * Shows a generic error message on top of the root view.
     * 
     * @param title
     * @param error
     * @param message
     */
    public void showErrorMessage(String title, String error, String message) {
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle(title);
    	alert.setHeaderText(error);
    	alert.setContentText(message);
    	
    	alert.showAndWait();
    }

    /**
     * Shows the login page if no User is logged in, otherwise shows the home page.
     */
    public void showLoginOrHome() {
        try {
        	FXMLLoader loader = new FXMLLoader();
        	
        	//Check if user is logged in
        	if (User.currentUser() == null) {
        		//Show login
        		loader.setLocation(MainApp.class.getResource("Login.fxml"));
        	} else {
        		loader.setLocation(MainApp.class.getResource("Home.fxml"));
        		rootController.toggleMenu(true);
        	}
        	
            AnchorPane loadedView = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(loadedView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the primaryStage property.
     * 
     * @return The primary Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}