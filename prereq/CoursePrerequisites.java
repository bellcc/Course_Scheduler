
import java.util.ArrayList;

public class CoursePrerequisites {
	
	private String             courseSubNum;
	private String             academicYearNeeded;
	private int                creditsNeeded;
	private ArrayList<String>  andPrereqCourses;
	private ArrayList<String>  orPrereqCourses;
	private ArrayList<String>  concurrentCourses;
	
	public CoursePrerequisites() {
	
		this.courseSubNum         = "";
		this.academicYearNeeded   = "";
		this.creditsNeeded        = 0;
		this.andPrereqCourses     = new ArrayList<String>();
		this.orPrereqCourses      = new ArrayList<String>();
		this.concurrentCourses    = new ArrayList<String>();

	}

	public void setCourseSubNum(String courseSubNum) {

		this.courseSubNum = courseSubNum;

	}

	public String getCourseSubNum()	{ 

		return courseSubNum;
	}

	public void setAcademicYearNeeded(String academicYear) {

		this.academicYearNeeded = academicYear;

	}

	public String getAcademicYearNeeded() {

		return academicYearNeeded;

	}

	public boolean hasAcademicYearPrereq(){

		return (!getAcademicYearNeeded().equals(""));

	}
	
	public void setCreditsNeeded(int creditsNeeded)	{

		this.creditsNeeded = creditsNeeded;

	}

	public int getCreditsNeeded() {

		return creditsNeeded;

	}

	public boolean hasCreditsNeededPrereq()	{

		return (getCreditsNeeded() != 0);

	}
	
	public void setAndPrereqCourses(ArrayList<String> andPrerequisites) {

		this.andPrereqCourses = andPrerequisites;

	}
	
	public ArrayList<String> getAndPrereqCourses() {

		return andPrereqCourses;

	}

	public boolean hasAndPrerequisites() {

		return (!andPrereqCourses.isEmpty());

	}
	
	public void setOrPrereqCourses(ArrayList<String> orPrerequisites) {

		this.orPrereqCourses = orPrerequisites;

	}

	public ArrayList<String> getOrPrereqCourses() {

		return orPrereqCourses;

	}

	public boolean hasOrPrerequisites() {

		return (!orPrereqCourses.isEmpty());

	}
	
	public void setConcurrentPrereqCourses(ArrayList<String> concurrentPrereqs) {

		this.concurrentCourses = concurrentPrereqs;

	}

	public ArrayList<String> getConncurrentPrereqCourses() {

		return concurrentCourses;

	}

	public boolean hasConcurrentPrereqs() {

		return (!concurrentCourses.isEmpty());

	}
	
	public boolean hasPrerequisites() {

		if(hasAcademicYearPrereq()) return true;
		if(hasCreditsNeededPrereq()) return true;
		if(hasAndPrerequisites()) return true;
		if(hasOrPrerequisites()) return true;
		if(hasConcurrentPrereqs()) return true;
		return false;

	}

	/**
	 * This method returns a string representation of a CoursePrerequisites object.
	 * @return The string representation.
	 */
	public String toString() {

		String str = "[Course Number: " + courseSubNum + ", Academic Year: " + academicYearNeeded + ", Credits: " + creditsNeeded + ", And Prerequisites: " + toString(andPrereqCourses) + ", Or Prerequisistes: " + toString(orPrereqCourses) + ", Corequisistes: " + toString(concurrentCourses) + " ]";

		return str;

	}

	/**
	 * This is a helper method to the toString method that gives a string representation of a String ArrayList.
	 * @param list The String ArrayList to be represented.
	 * @return The string representation.
	 */
	public String toString(ArrayList<String> list) {

		String str = "{ ";

		for(int i=0;i<list.size();i++) {

			str += list.get(i);

			if(i != list.size()-1) {

				str += ", ";

			}

		}

		str += " }";

		return str;

	}

}
