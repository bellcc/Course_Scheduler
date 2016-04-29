
/**
 * @author Chris Bell
 */

package application;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	private static MainApp sharedInstance;
	
	private Stage primaryStage;

	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		sharedInstance = this;
		this.primaryStage = primaryStage;
		
        Scene scene = new HomeController().getHomeScene();
		//Scene scene = new SelectionController().getSelectionScene();
		//Scene scene = new CourseSelectionController().getCourseSelectionScene();
		//Scene scene = new ScheduleController().getScheduleScene();
		//Scene scene = new CourseScheduleController().getCourseSchedulerScene();
		
        primaryStage.setTitle("Course Scheduler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public void showSelectionStage() {
		
		Scene selection = new SelectionController().getSelectionScene();
		
		primaryStage.setScene(selection);
		primaryStage.show();
	}
	
	public void showCourseSelectionStage() throws FileNotFoundException{
		
		Scene courseSelection = new CourseSelectionController().getCourseSelectionScene();
		
		primaryStage.setScene(courseSelection);
		primaryStage.show();
	}
	
	public void showScheduleStage() {
		
		Scene schedule = new ScheduleController().getScheduleScene();
		
		primaryStage.setScene(schedule);
		primaryStage.show();
	}
	
	public void showCourseScheduleStage() {
		
		Scene courseSchedule = new CourseScheduleController().getCourseSchedulerScene();
		
		primaryStage.setScene(courseSchedule);
		primaryStage.show();
	}
	
	public void incorrectCSVFormat() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Incorrect File Format");
		alert.setHeaderText(null);
		alert.setContentText("The provided semester schedule file (.csv) is in the incorrect format.");
		alert.showAndWait();
		
	}
	
	public void incorrectProfileFormat() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Incorrect File Format");
		alert.setHeaderText(null);
		alert.setContentText("The provided user profile file was not in the correct format.");
		alert.showAndWait();
		
	}
	
	public static MainApp getSharedInstance() {
		return sharedInstance;
	}
	
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
		
}
