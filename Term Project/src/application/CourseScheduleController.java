package application;

import java.util.List;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import types.Course;

public class CourseScheduleController {
	
	private TableView table;
	private ObservableList data;

	public Scene getCourseSchedulerScene() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Label tableLabel = new Label("Double Click on a Course:");
		tableLabel.setFont(new Font(20));
		grid.add(tableLabel, 0, 0,2,1);
		
		table = new TableView();
		data = getCourseData();
		table.setItems(data);
		
		TableColumn crnCol = new TableColumn("CRN");
		crnCol.setCellValueFactory(new PropertyValueFactory("crn"));
		
		TableColumn courseCol = new TableColumn("Course");
		courseCol.setCellValueFactory(new PropertyValueFactory("course"));
		
		TableColumn sectionCol = new TableColumn("Section");
		sectionCol.setCellValueFactory(new PropertyValueFactory("section"));
		
		TableColumn titleCol = new TableColumn("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory("title"));
		
		TableColumn hoursCol = new TableColumn("Hours");
		hoursCol.setCellValueFactory(new PropertyValueFactory("hours"));
		
		TableColumn startTimeCol = new TableColumn("Start Time");
		startTimeCol.setCellValueFactory(new PropertyValueFactory("startTime"));
		
		TableColumn endTimeCol = new TableColumn("End Time");
		endTimeCol.setCellValueFactory(new PropertyValueFactory("endTime"));
		
		TableColumn daysCol = new TableColumn("Days");
		daysCol.setCellValueFactory(new PropertyValueFactory("days"));
		
		TableColumn buildingCol = new TableColumn("Building");
		buildingCol.setCellValueFactory(new PropertyValueFactory("building"));
		
		TableColumn roomCol = new TableColumn("Room");
		roomCol.setCellValueFactory(new PropertyValueFactory("room"));
		
		TableColumn instructorCol = new TableColumn("Instructor");
		instructorCol.setCellValueFactory(new PropertyValueFactory("instructor"));
		
		table.getColumns().setAll(crnCol, courseCol, sectionCol, titleCol, hoursCol, startTimeCol, endTimeCol, daysCol, buildingCol, roomCol, instructorCol);
		
		table.setRowFactory( tv -> {
			TableRow<TableCourse> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	TableCourse rowData = row.getItem();
		        	Course aCourse = getCourse(rowData);
		        	User.getSharedUser().addProposedCourse(aCourse);
		        	MainApp.getSharedInstance().showScheduleStage();
		        }
		    });
		    return row ;
		});
		
		grid.add(table, 0, 1);
		
		Scene scene = new Scene(grid, 1000, 600);
		  
		return scene;
	}
	
	private ObservableList getCourseData() {
		
		List list = new ArrayList();
		
		int n = User.getSharedUser().getCourseList().size();
		for(int i=0;i<n;i++) {
			
			Course temp = User.getSharedUser().getCourseList().get(i);
			
			String crnCol = String.valueOf(temp.getCourseNumber().getValue().intValue());
			String courseCol = "CSE" + temp.getCourseSequence().getValue();
			String sectionCol = temp.getCourseSection().getValue();
			String titleCol = temp.getCourseTitle().getValue();
			String hoursCol = temp.getCourseHours().getValue();
			String startTimeCol = String.valueOf(temp.getCourseStartTime().getValue().intValue());
			String endTimeCol = String.valueOf(temp.getCourseEndTime().getValue().intValue());
			String daysCol = temp.getCourseDays().getValue();
			String buildingCol = temp.getCourseBuilding().getValue();
			String roomCol = temp.getCourseRoom().getValue();
			String instructorCol = temp.getCourseInstructor().getValue();
			
			list.add(new TableCourse(crnCol, courseCol, sectionCol, titleCol, hoursCol, startTimeCol, endTimeCol, daysCol, buildingCol, roomCol, instructorCol));
			
		}

		ObservableList temp = FXCollections.observableArrayList(list);
		
		return temp;
	}
	
	private Course getCourse(TableCourse course) {
		
		Course tempCourse = new Course();
		
		int n = User.getSharedUser().getCourseList().size();
		for(int i=0;i<n;i++) {
			int crn = User.getSharedUser().getCourseList().get(i).getCourseNumber().getValue().intValue();
			
			if(Integer.parseInt(course.getCrn()) == crn) {
				tempCourse = User.getSharedUser().getCourseList().get(i);
			}
			
		}
		
		return tempCourse;
	}
	
}
