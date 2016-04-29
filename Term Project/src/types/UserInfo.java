
/**
 * @author Chris Bell
 */

package types;

import java.util.ArrayList;

public class UserInfo {

	private ArrayList<String> courses;
	private String academicYear;
	private String degree;
	private int credits;
	
	public UserInfo() {
		this.courses = new ArrayList<String>();
		this.academicYear = "";
		this.degree = "";
		this.credits = 0;
	}
	
	public UserInfo(ArrayList<String> courses, String academicYear, String degree, int credits) {
		this.courses = courses;
		this.academicYear = academicYear;
		this.degree = degree;
		this.credits = credits;
	}
	
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	public ArrayList<String> getCourses() {
		return this.courses;
	}
	
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	public String getAcademicYear() {
		return this.academicYear;
	}
	
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getCredits() {
		return this.credits;
	}
	
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public String getDegree() {
		return this.degree;
	}
}