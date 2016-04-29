package application;

import java.util.ArrayList;
import java.util.List;

import conflict.ClassConflicts;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import prereq.CheckPrerequisites;
import types.Course;

public class ScheduleController {
	
	private boolean buttonDisable = true;
	private static Course selectedCourse;
	private TableView table;
	private ObservableList data;
	
	public Scene getScheduleScene() {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(30);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		GridPane gridInner = new GridPane();
		gridInner.setAlignment(Pos.CENTER);
		gridInner.setHgap(10);
		
		Button editButton = new Button("Edit Proposed Schedule");
		editButton.setMinHeight(30);
		gridInner.add(editButton, 0, 0);
		
		Button deleteButton = new Button("-");
		deleteButton.setMinSize(30, 30);
		deleteButton.setDisable(buttonDisable);
		gridInner.add(deleteButton, 1, 0);
		
		Button addButton = new Button("+");
		addButton.setMinSize(30, 30);
		addButton.setDisable(buttonDisable);
		gridInner.add(addButton, 2, 0);
		
		grid.add(gridInner, 0, 0);
		
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
		
		/**table.setRowFactory( tv -> {
			TableRow<TableCourse> row = new TableRow<>();
			
			TableCourse rowData = row.getItem();
			if(row.isEmpty() != false && rowData.getCrn().equals("71367")) {
				row.setStyle("-fx-background-color: yellow");
			}
			
			if(row.getItem() != null) {
				System.out.println("ugh");
			}
			
			
		    return row ;
		});*/
		
		table.setRowFactory( tv -> {
			TableRow<TableCourse> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
		        	TableCourse rowData = row.getItem();
		        	Course aCourse = getCourse(rowData);
		        	setSelectedCourse(aCourse);
		        	MainApp.getSharedInstance().showScheduleStage();
		        }
		    });
		    return row ;
		});
		
		grid.add(table, 0, 1);
		
		Image goodImage = new Image("file:good.png");
		Image badImage = new Image("file:bad.png");
		Image warningImage = new Image("file:warning.png");
		
		VBox vBox = new VBox();
		
		if(selectedCourse != null) {
			
			HBox hBox1 = new HBox();
			
			CheckPrerequisites checker = new CheckPrerequisites();
			ArrayList<String> temp = new ArrayList<String>();
			for(int i=0;i<User.getSharedUser().getProposedSchedule().size();i++) {
				temp.add("CSE " + User.getSharedUser().getProposedSchedule().get(i).getCourseSequence().getValue().toString());
			}
			
			boolean prereq = checker.check(User.getCurrentUser().getCourses(), temp, "CSE " + selectedCourse.getCourseSequence().getValue().toString());

			boolean creditsMet = true;
			boolean creditReq = false;
			creditReq = checker.checkCreditRequirement("CSE " + selectedCourse.getCourseSequence().getValue().toString());
			int credits = checker.retrieveCreditRequirement("CSE " + selectedCourse.getCourseSequence().getValue().toString());
			
			if(creditReq) {
				int userCredits = User.getCurrentUser().getCredits();
				
				if(userCredits < credits) {
					creditsMet = false;
				}
			}
			
			boolean yearReq = checker.checkAcademicYear("CSE " + selectedCourse.getCourseSequence().getValue().toString());
			boolean yearMet = true;
			
			if(yearReq) {
				
				String userYear = User.getCurrentUser().getAcademicYear();
				String courseYear = checker.retrieveAcademicYear("CSE " + selectedCourse.getCourseSequence().getValue().toString());
				
				if(courseYear.equals("SENIOR") && !userYear.equals("SENIOR")) {
					yearMet = false;
				}
			}
			
			if(prereq && creditsMet && yearMet) {
				ImageView imageView = new ImageView(goodImage);
				GridPane.setHalignment(imageView, HPos.LEFT);
				
				Label prereqLabel = new Label("   Prerequisites Met");
				prereqLabel.setFont(new Font(20));
				hBox1.getChildren().add(imageView);
				hBox1.getChildren().add(prereqLabel);
				
			}else {
				ImageView imageView = new ImageView(badImage);
				GridPane.setHalignment(imageView, HPos.LEFT);
				
				Label prereqLabel = new Label("   Prerequisites Not Met");
				prereqLabel.setFont(new Font(20));
				hBox1.getChildren().add(imageView);
				hBox1.getChildren().add(prereqLabel);
			}
			
			HBox hBox2 = new HBox();
			
			boolean conflicts = false;
			
			ArrayList<Course> schedule = User.getSharedUser().getProposedSchedule();
			
			for(int i=0;i<schedule.size();i++) {
				ClassConflicts conflict = new ClassConflicts();
				conflicts = conflict.hasConflict(selectedCourse, schedule.get(i));
				
				if(conflicts == true) {
					break;
				}
			}
			
			for(int i=0;i<schedule.size();i++) {
				if(conflicts == true) {
					break;
				}
				
				ClassConflicts conflict = new ClassConflicts();
				conflicts = conflict.hasExamConflict(selectedCourse, schedule.get(i));
				
			}
			
			if(!conflicts) {
				ImageView imageView = new ImageView(goodImage);
				GridPane.setHalignment(imageView, HPos.LEFT);
				
				Label conflictLabel = new Label("  There is not a conflict");
				conflictLabel.setFont(new Font(20));
				hBox2.getChildren().add(imageView);
				hBox2.getChildren().add(conflictLabel);
				
			}else {
				ImageView imageView = new ImageView(badImage);
				GridPane.setHalignment(imageView, HPos.LEFT);
				
				Label conflictLabel = new Label("  There is a conflict");
				conflictLabel.setFont(new Font(20));
				hBox2.getChildren().add(imageView);
				hBox2.getChildren().add(conflictLabel);
			}
			
			//TODO Attempting to take previously taken courses and courses not part of your major.
			HBox hBox3 = new HBox();
			
			if(selectedCourse != null) {
				ImageView imageView = new ImageView(warningImage);
				GridPane.setHalignment(imageView, HPos.LEFT);
				hBox3.getChildren().add(imageView);
			}
						
			vBox.setSpacing(10);
			vBox.getChildren().addAll(hBox1, hBox2, hBox3);
			
			selectedCourse = null;
		}
		
		
		grid.add(vBox, 0, 2);
		
		Button calendarButton = new Button("View Calendar");
		calendarButton.setMinHeight(30);
		GridPane.setHalignment(calendarButton, HPos.RIGHT);
		grid.add(calendarButton, 0, 3);
		
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				buttonDisable = !buttonDisable;
				addButton.setDisable(buttonDisable);
				deleteButton.setDisable(buttonDisable);
				table.setEditable(buttonDisable);
			}
		});
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				MainApp.getSharedInstance().showCourseScheduleStage();
			}
		});
		
		Scene scene = new Scene(grid,800,700);
		
		return scene;
	}
	
	private ObservableList getCourseData() {
		
		List list = new ArrayList();
		
		int n = User.getSharedUser().getProposedSchedule().size();
		for(int i=0;i<n;i++) {
			
			Course temp = User.getSharedUser().getProposedSchedule().get(i);
			
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
	
	public void setSelectedCourse(Course aCourse) {
		
		this.selectedCourse = aCourse;
	}
}