package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Course Model Object
 * 
 * @author AJ Priola
 * @version 1.0
 *
 */

public class Course {

	private StringProperty courseName, courseInstructor, courseSection, courseTitle;
	private IntegerProperty courseHours, courseNumber, currentEnrollment, maxEnrollment;
	
	public Course(String courseName) {
		this.courseName = new SimpleStringProperty(courseName);
	}

	public StringProperty getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName.set(courseName);
	}
	
	public static ObservableList<Course> getAllCourses() {
		//TODO: Get list of all available courses for display
		return FXCollections.observableArrayList();
	}
}