package types;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;

public class Course {

	private StringProperty courseName, courseSequence, courseSection, courseTitle, courseHours, courseDays, courseBuilding, courseRoom, courseInstructor, courseMeetingType, courseMeetingCategory, courseExamDay;
	private IntegerProperty courseNumber, currentEnrollment, maxEnrollment, courseStartTime, courseEndTime, courseExamTime;
	
	private static ArrayList<Course> courseList;
	
	public Course() {
		this.courseName = new SimpleStringProperty("");
		this.courseSequence = new SimpleStringProperty("");
		this.courseSection = new SimpleStringProperty("");
		this.courseTitle = new SimpleStringProperty("");
		this.courseHours = new SimpleStringProperty("");
		this.courseDays = new SimpleStringProperty("");
		this.courseBuilding = new SimpleStringProperty("");
		this.courseRoom = new SimpleStringProperty("");
		this.courseInstructor = new SimpleStringProperty("");
		this.courseMeetingType = new SimpleStringProperty("");
		this.courseMeetingCategory = new SimpleStringProperty("");
		this.courseExamDay = new SimpleStringProperty("");

		this.courseNumber = new SimpleIntegerProperty(0);
		this.currentEnrollment = new SimpleIntegerProperty(0);
		this.maxEnrollment = new SimpleIntegerProperty(0);
		this.courseStartTime = new SimpleIntegerProperty(0);
		this.courseEndTime = new SimpleIntegerProperty(0);
		this.courseExamTime = new SimpleIntegerProperty(0);
		
		courseList = new ArrayList<Course>();
	}

	public StringProperty getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = new SimpleStringProperty(courseName);
	}
	
	public StringProperty getCourseSequence() {
		return this.courseSequence;
	}
	
	public void setCourseSequence(String courseSequence) {
		this.courseSequence = new SimpleStringProperty(courseSequence);
	}
	
	public StringProperty getCourseSection() {
		return this.courseSection;
	}
	
	public void setCourseSection(String courseSection) {
		this.courseSection = new SimpleStringProperty(courseSection);
	}
	
	public StringProperty getCourseTitle() {
		return this.courseTitle;
	}
	
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = new SimpleStringProperty(courseTitle);
	}
	
	public StringProperty getCourseHours() {
		return this.courseHours;
	}
	
	public void setCourseHours(String courseHours) {
		this.courseHours = new SimpleStringProperty(courseHours);
	}
	
	public StringProperty getCourseDays() {
		return this.courseDays;
	}
	
	public void setCourseDays(String courseDays) {
		this.courseDays = new SimpleStringProperty(courseDays);
	}
	
	public StringProperty getCourseBuilding() {
		return this.courseBuilding;
	}
	
	public void setCourseBuilding(String courseBuilding) {
		this.courseBuilding = new SimpleStringProperty(courseBuilding);
	}
	
	public StringProperty getCourseRoom() {
		return this.courseRoom;
	}
	
	public void setCourseRoom(String courseRoom) {
		this.courseRoom = new SimpleStringProperty(courseRoom);
	}
	
	public StringProperty getCourseInstructor() {
		return this.courseInstructor;
	}
	
	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = new SimpleStringProperty(courseInstructor);
	}
	
	public StringProperty getCourseMeetingType() {
		return this.courseMeetingType;
	}
	
	public void setCourseMeetingType(String courseMeetingType) {
		this.courseMeetingType = new SimpleStringProperty(courseMeetingType);
	}
	
	public StringProperty getCourseMeetingCategory() {
		return this.courseMeetingCategory;
	}
	
	public void setCourseMeetingCategory(String courseMeetingCategory) {
		this.courseMeetingCategory = new SimpleStringProperty(courseMeetingCategory);
	}
	
	public StringProperty getCourseExamDay() {
		return this.courseExamDay;
	}
	
	public void setCourseExamDay(String courseExamDay) {
		this.courseExamDay = new SimpleStringProperty(courseExamDay);
	}
	
	public IntegerProperty getCourseNumber() {
		return this.courseNumber;
	}
	
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = new SimpleIntegerProperty(courseNumber);
	}
	
	public IntegerProperty getCurrentEnrollment() {
		return this.currentEnrollment;
	}
	
	public void setCurrentEnrollment(int currentEnrollment) {
		this.currentEnrollment = new SimpleIntegerProperty(currentEnrollment);
	}
	
	public IntegerProperty getMaxEnrollment () {
		return this.maxEnrollment;
	}
	
	public void setMaxEnrollment(int maxEnrollment) {
		this.maxEnrollment = new SimpleIntegerProperty(maxEnrollment);
	}
	
	public IntegerProperty getCourseStartTime() {
		return this.courseStartTime;
	}
	
	public void setCourseStartTime(int courseStartTime) {
		this.courseStartTime = new SimpleIntegerProperty(courseStartTime);
	}
	
	public IntegerProperty getCourseEndTime() {
		return this.courseEndTime;
	}
	
	public void setCourseEndTime(int courseEndTime) {
		this.courseEndTime = new SimpleIntegerProperty(courseEndTime);
	}
	
	public IntegerProperty getCourseExamTime() {
		return this.courseExamTime;
	}
	
	public void setCourseExamTime(int courseExamTime) {
		this.courseExamTime = new SimpleIntegerProperty(courseExamTime);
	}
	
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	public ArrayList<Course> getCourseList() {
		return this.courseList;
	}
	
	public String toString() {
		
		String str = "Number            : " + this.courseNumber.toString()           + "\n" +
					 "Subject           : " + this.courseName.toString()             + "\n" +
					 "Sequence          : " + this.courseSequence.toString()         + "\n" +
					 "Section           : " + this.courseSection.toString()          + "\n" +
					 "Title             : " + this.courseTitle.toString()            + "\n" +
					 "Credit Hours      : " + this.courseHours.toString()            + "\n" + 
					 "Enrollment        : " + this.currentEnrollment.toString()      + "\n" +
					 "Max Enrollment    : " + this.maxEnrollment.toString()          + "\n" +
					 "Start Time        : " + this.courseStartTime.toString()        + "\n" +
					 "End Time          : " + this.courseEndTime.toString()          + "\n" +
					 "Days              : " + this.courseDays.toString()             + "\n" +
					 "Building          : " + this.courseBuilding.toString()         + "\n" +
					 "Room              : " + this.courseRoom.toString()             + "\n" +
					 "Instructor        : " + this.courseInstructor.toString()       + "\n" +
					 "Meeting Type      : " + this.courseMeetingType.toString()      + "\n" +
					 "Meeting Category  : " + this.courseMeetingCategory.toString()  + "\n" +
					 "Exam Day          : " + this.courseExamDay.toString()          + "\n" +
					 "Exam Time         : " + this.courseExamTime.toString();
		return str;
	}
	
}