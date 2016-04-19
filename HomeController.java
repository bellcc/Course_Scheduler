package application;

import java.awt.TextArea;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * HomeController class. Controls user interaction with HomeController.fxml
 * 
 * @author AJ Priola
 * @version 1.0
 */

public class HomeController {
	
	@FXML private Button editTableButton, addCourseButton, removeCourseButton;
	@FXML private DatePicker datePicker;
	@FXML private TableView<Course> courseTable;
	@FXML private TableColumn<Course, String> courseNameColumn;
	@FXML private TextArea textArea;
	
	private ObservableList<Course> currentCourses;
	
	private boolean editingTable;
	
	public HomeController() {
		
	}
	
	@FXML private void initialize() {
		editingTable = false;
		toggleTableViewEditing(editingTable);
		courseNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCourseName());
		loadCurrentCourses();
		
		courseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> courseSelected(newValue));
	}
	
	private void loadCurrentCourses() {
		currentCourses = User.currentUser().getCurrentCourses();
		courseTable.setItems(currentCourses);
	}
	
	private void saveChanges() {
		User.currentUser().setCurrentCourses(currentCourses);
	}
	
	private void courseSelected(Course selected) {
		if (editingTable) {
			removeCourseButton.setDisable(false);
		}
	}
	
	private void toggleTableViewEditing(boolean editing) {
		courseTable.setEditable(editing);
		courseTable.getSelectionModel().clearSelection();
		editTableButton.setText(editing ? "Save changes" : "Edit courses");
		addCourseButton.setDisable(!editing);
		removeCourseButton.setDisable(true);
	}
	
	@FXML private void dateValueChanged() {
		
	}
	
	@FXML private void addCourseClicked() {
		
	}
	
	@FXML private void removeCourseClicked() {
		int selectedIndex = courseTable.getSelectionModel().getSelectedIndex();
		courseTable.getItems().remove(selectedIndex);
	}
	
	@FXML private void editCoursesClicked() {
		if (editingTable) {
			saveChanges();
		}
		toggleTableViewEditing(!editingTable);
		editingTable = !editingTable;
	}
}
