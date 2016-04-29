
/**
 * @author Chris Bell
 */

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import types.UserInfo;

public class CourseSelectionController {
	
	private ArrayList<String> preCourses = new ArrayList<String>();

	public Scene getCourseSelectionScene() throws FileNotFoundException{
		
		ArrayList<String> userCourseList = User.getCurrentUser().getCourses();
		String academicYear = User.getCurrentUser().getAcademicYear();
		String degree = User.getCurrentUser().getDegree();
		int credits = User.getCurrentUser().getCredits();
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Label degreeLabel = new Label("Select Major:");
		degreeLabel.setMinWidth(100);
		grid.add(degreeLabel, 0, 0);
		
		RadioButton compSciButton = new RadioButton("Computer Science");
		
		if(degree.equals("Computer Science")) {
			compSciButton.setSelected(true);
		}
		
		grid.add(compSciButton, 1, 0);
		
		RadioButton softEngiButton = new RadioButton("Software Engineering");
		
		if(degree.equals("Software Engineering")) {
			softEngiButton.setSelected(true);
		}
		
		grid.add(softEngiButton, 1, 1);
		
		Label creditsLabel = new Label("Credits");
		creditsLabel.setMinWidth(100);
		grid.add(creditsLabel, 0, 2);
		
		TextField creditsField = new TextField();
		creditsField.setMaxWidth(110);
		creditsField.setText(String.valueOf(credits));
		grid.add(creditsField, 1, 2);
		
		Label yearLabel = new Label("Academic Year:");
		yearLabel.setMinWidth(100);
		grid.add(yearLabel, 0, 3);
		
		ComboBox<String> yearBox = new ComboBox<String>();
		yearBox.getItems().add("Freshman");
		yearBox.getItems().add("Sophomore");
		yearBox.getItems().add("Junior");
		yearBox.getItems().add("Senior");
		yearBox.setValue(academicYear);
		grid.add(yearBox, 1, 3);
		
		Label courseLabel = new Label("Courses:");
		grid.add(courseLabel, 0, 4);

		ListView<Item> courseView = new ListView<>();
		courseView.setMinSize(450, 300);
		ArrayList<String> courses = getAllCourses();
		
		for(int i=0;i<courses.size();i++) {
			Item item = new Item(courses.get(i), false);
			
			item.onProperty().addListener((obs, wasOn, isNowOn) -> {

				String course = item.getName().substring(0, 7);
				
				if(preCourses.contains(course)) {
					preCourses.remove(course);
				} else {
					preCourses.add(course);
				}
				
			});
			
			if(userCourseList.contains(item.getName().substring(0, 7))) {
				item.setOn(true);
			}
			
			courseView.getItems().add(item);
		}
		
		courseView.setCellFactory(CheckBoxListCell.forListView(new Callback<Item, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Item item) {
                return item.onProperty();
            }
        }));
		
		grid.add(courseView, 1, 4);
		
		Button nextButton = new Button("Next");
		GridPane.setHalignment(nextButton, HPos.RIGHT);
		grid.add(nextButton, 1, 5);
		
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				//TODO When the start button is pressed open the selection menu.
				MainApp.getSharedInstance().showScheduleStage();
			}
		});
		
		Scene scene = new Scene(grid, 600, 600);
		
		return scene;
	}
	
	private ArrayList<String> getAllCourses() throws FileNotFoundException{
		
		ArrayList<String> courses = new ArrayList<String>();
		
		try {
			Scanner reader = new Scanner(new File("courses.txt"));
			
			while(reader.hasNext()) {
				courses.add(reader.nextLine());
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
	public static class Item {
        private final StringProperty name = new SimpleStringProperty();
        private final BooleanProperty on = new SimpleBooleanProperty();

        public Item(String name, boolean on) {
            setName(name);
            setOn(on);
        }

        public final StringProperty nameProperty() {
            return this.name;
        }

        public final String getName() {
            return this.nameProperty().get();
        }

        public final void setName(final String name) {
            this.nameProperty().set(name);
        }

        public final BooleanProperty onProperty() {
            return this.on;
        }

        public final boolean isOn() {
            return this.onProperty().get();
        }

        public final void setOn(final boolean on) {
            this.onProperty().set(on);
        }

        @Override
        public String toString() {
            return getName();
        }

    }
}
