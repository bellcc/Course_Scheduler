
/**
 * @author Chris Bell
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import types.UserInfo;

public class SelectionController {
	
	private File scheduleFile;
	private File profileFile;
	
	private TextField profileField;
	private TextField scheduleField;

	public Scene getSelectionScene() {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Image img = new Image("file:miami_logo.png");
		ImageView imageView = new ImageView(img);
		GridPane.setHalignment(imageView, HPos.CENTER);
		grid.add(imageView, 0, 0, 3, 1);
		
		Label scheduleLabel = new Label("Semester Schedule:");
		grid.add(scheduleLabel, 0, 1);
		
		scheduleField = new TextField("Path/To/Semester/Schedule/.csv");
		scheduleField.setMinSize(300, 20);
		grid.add(scheduleField, 1, 1);
		
		Button browseButton = new Button("Browse");
		grid.add(browseButton, 2, 1);
		
		Label profileLabel = new Label("User Profile:");
		grid.add(profileLabel, 0, 2);
		
		profileField = new TextField("Path/To/User/Profile/.xml (Optional)");
		grid.add(profileField, 1, 2);
		
		Button profileBrowseButton = new Button("Browse");
		grid.add(profileBrowseButton, 2, 2);
		
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(20);
		
		Button scratchButton = new Button("Start from Scratch");
		scratchButton.setMinSize(150, 75);
		scratchButton.setFont(new Font(15));
		buttonBox.getChildren().add(scratchButton);
		
		Button uploadButton = new Button("Upload a Profile");
		uploadButton.setMinSize(150, 75);
		uploadButton.setFont(new Font(15));
		buttonBox.getChildren().add(uploadButton);
		
		browseButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Profile File");
				
				scheduleFile = fileChooser.showOpenDialog(MainApp.getSharedInstance().getPrimaryStage());
				
				if(scheduleFile != null) {
					String filePath = scheduleFile.getAbsolutePath();
					scheduleField.setText(filePath);
				}
				
			}
		});
		
		profileBrowseButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Profile File");
				
				profileFile = fileChooser.showOpenDialog(MainApp.getSharedInstance().getPrimaryStage());
				
				if(profileFile != null) {
					String filePath = profileFile.getAbsolutePath();
					profileField.setText(filePath);
				}
				
			}
		});
		
		scratchButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				
				if(scheduleFile == null) {
					return;
				}
				
				User.getSharedUser().createCourseList(scheduleFile);
				
				if(User.getSharedUser().getCourseList().size() != 0) {
					
					try {
						MainApp.getSharedInstance().showCourseSelectionStage();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					
					}
				}
				
			}
		});
		
		uploadButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				
				if(scheduleFile == null || profileFile == null) {
					return;
				}
				
				User.getSharedUser().createCourseList(scheduleFile);
				User.getSharedUser().createUserCourseList(profileFile);
				
				if(User.getSharedUser().getCourseList().size() != 0 && (!User.getCurrentUser().getAcademicYear().equals("") || User.getCurrentUser().getCourses().size() != 0) || User.getCurrentUser().getCredits() != 0 || !User.getCurrentUser().getDegree().equals("")) {
					
					try {
						MainApp.getSharedInstance().showCourseSelectionStage();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			
			}
		});
		
		grid.add(buttonBox, 1, 3);
		
		Scene scene = new Scene(grid, 600, 600);
		
		return scene;
	}
}
