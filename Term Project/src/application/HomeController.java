
/**
 * @author Chris Bell
 */

package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class HomeController {
	
	public Scene getHomeScene() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(100);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Image img = new Image("file:miami_logo.png");
		ImageView imageView = new ImageView(img);
		GridPane.setHalignment(imageView, HPos.CENTER);
		grid.add(imageView, 0, 0, 3, 1);
		
		Button startButton = new Button("Start");
		startButton.setMinSize(150, 75);
		startButton.setFont(new Font(20));
		grid.add(startButton, 0, 1);
		
		Button helpButton = new Button("Help");
		helpButton.setMinSize(150, 75);
		helpButton.setFont(new Font(20));
		grid.add(helpButton, 1, 1);
		
		Button quitButton = new Button("Quit");
		quitButton.setMinSize(150, 75);
		quitButton.setFont(new Font(20));
		grid.add(quitButton, 2, 1);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				//TODO When the start button is pressed open the selection menu.
				MainApp.getSharedInstance().showSelectionStage();
			}
		});
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("About");
				alert.setHeaderText(null);
				alert.setContentText("This is an application to help you schedule for courses in Miami University's Computer Science and Software Engineering Department.");
				alert.showAndWait();
			}
		});
		
		quitButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});

		Scene scene = new Scene(grid, 600, 600);
		
		return scene;
	}

}