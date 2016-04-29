
/**
 * @author Chris Bell
 */

package application;

import types.Course;
import types.UserInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import parsers.CourseParser;
import parsers.UserProfile;

public class User {
	
	private static User sharedUser;
	private static UserInfo currentUser;

	private static ArrayList<Course> courseList = new ArrayList<Course>();
	private static ArrayList<Course> proposedSchedule = new ArrayList<Course>();
	
	public void createCourseList(File courseFile) {
		
		initialize();
		
		CourseParser parser = new CourseParser();
		ArrayList<Course> list = new ArrayList<Course>();
		
		try {
			list = parser.parseFile(courseFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setCourseList(list);

	}
	
	public void createUserCourseList(File userCourseList) {
		
		initialize();
		
		UserProfile profile = new UserProfile();
		UserInfo userInfo = new UserInfo();
		
		userInfo = profile.retrieveUserInfo(userCourseList);
		
		currentUser.setCourses(userInfo.getCourses());
		currentUser.setAcademicYear(userInfo.getAcademicYear());
		currentUser.setCredits(userInfo.getCredits());
		currentUser.setDegree(userInfo.getDegree());
		
	}
	
	public static void initialize() {
		if(sharedUser == null) {
			sharedUser = new User();
		}
		if(currentUser == null) {
			currentUser = new UserInfo();
		}
	}
	
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	public ArrayList<Course> getProposedSchedule() {
		return proposedSchedule;
	}
	
	public void addProposedCourse(Course aCourse) {
		proposedSchedule.add(aCourse);
	}

	public static UserInfo getCurrentUser() {
		initialize();
		
		return currentUser;
	}
	
	public static User getSharedUser() {
		initialize();
		
		return sharedUser;
	}	
	
}
