package application;

import javafx.beans.property.SimpleStringProperty;

public class TableCourse {
	
	private SimpleStringProperty crn;
	private SimpleStringProperty course;
	private SimpleStringProperty section;
	private SimpleStringProperty title;
	private SimpleStringProperty hours;
	private SimpleStringProperty startTime;
	private SimpleStringProperty endTime;
	private SimpleStringProperty days;
	private SimpleStringProperty building;
	private SimpleStringProperty room;
	private SimpleStringProperty instructor;
	
	public TableCourse() {
		
	}
	
	public TableCourse(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11) {
		crn = new SimpleStringProperty(s1);
		course = new SimpleStringProperty(s2);
		section = new SimpleStringProperty(s3);
		title = new SimpleStringProperty(s4);
		hours = new SimpleStringProperty(s5);
		startTime = new SimpleStringProperty(s6);
		endTime = new SimpleStringProperty(s7);
		days = new SimpleStringProperty(s8);
		building = new SimpleStringProperty(s9);
		room = new SimpleStringProperty(s10);
		instructor = new SimpleStringProperty(s11);
	}
	
	public String getCrn() {
		return crn.get();
	}

	public void setCrn(String s) {
		crn.set(s);
	}
	
	public String getCourse() {
		return course.get();
	}
	
	public void setCourse(String s) {
		course.set(s);
	}
	
	public String getSection() {
		return section.get();
	}
	
	public void setSection(String s) {
		section.set(s);
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String s) {
		title.set(s);
	}
	
	public String getHours() {
		return hours.get();
	}
	
	public void setHours(String s) {
		hours.set(s);
	}
	
	public String getStartTime() {
		return startTime.get();
	}
	
	public void setStartTime(String s) {
		startTime.set(s);
	}
	
	public String getEndTime() {
		return endTime.get();
	}
	
	public void setEndTime(String s) {
		endTime.set(s);
	}
	
	public String getDays() {
		return days.get();
	}
	
	public void setDays(String s) {
		days.set(s);
	}
	
	public String getBuilding() {
		return building.get();
	}
	
	public void setBuilding(String s) {
		building.set(s);
	}
	
	public String getRoom() {
		return room.get();
	}
	
	public void setRoom(String s) {
		room.set(s);
	}
	
	public String getInstructor() {
		return instructor.get();
	}
	
	public void setInstructor(String s) {
		instructor.set(s);
	}
	
	@Override
	public String toString() {
		
		String str = "";
	
		str = "[CRN: " + crn.get() + 
			  ", Course: " + course.get() + 
			  ", Section: " + section.get() + 
			  ", Title: " + title.get() + 
			  ", Hours: " + hours.get() + 
			  ", Start Time: " + startTime.get() + 
			  ", End Time: " + endTime.get() +
			  ", Days: " + days.get() +
			  ", Building: " + building.get() +
			  ", Room: " + room.get() +
			  ", Instructor: " + instructor.get() +
			  "]";
		
		return str;
	}
}