
/**
 * Author          : Christopher Bell
 * Date            : 04-16-2016
 * Info            : Class that represents a course's prerequisites
 */

import java.util.ArrayList;

public class CoursePrerequisites {
	
	private String             courseNum;
	private String             academicYear;
	private int                credits;
	private ArrayList<String>  andPrereqCourses;
	private ArrayList<String>  orPrereqCourses;
	private ArrayList<String>  concurrentCourses;
	
	public CoursePrerequisites() {
	
		this.courseNum            = "";
		this.academicYear         = "";
		this.credits              = 0;
		this.andPrereqCourses     = new ArrayList<String>();
		this.orPrereqCourses      = new ArrayList<String>();
		this.concurrentCourses    = new ArrayList<String>();

	}

	public void setCourseNum (String courseNum) {

		this.courseNum = courseNum;

	}

	public void setAcademicYear (String academicYear) {

		this.academicYear = academicYear;

	}

	public void setCredits(int credits) {

		this.credits = credits;

	}

	public void setAndPreReqCourses (ArrayList<String> andPrereqCourses) {

		this.andPrereqCourses = andPrereqCourses;

	}

	public void setOrPreReqCourses (ArrayList<String> orPrereqCourses) {

		this.orPrereqCourses = orPrereqCourses;

	}

	public void setConcurrentCourses (ArrayList<String> concurrentCourses) {

		this.concurrentCourses = concurrentCourses;

	}

	public String getCourseNum () {

		return this.courseNum;

	}

	public String getAcademicYear () {

		return this.academicYear;

	}

	public int getCredits () {

		return this.credits;

	}

	public ArrayList<String> getAndPreReqCourses () {

		return this.andPrereqCourses;

	}

	public ArrayList<String> getOrPreReqCourses () {

		return this.orPrereqCourses;

	}

	public ArrayList<String> getConcurrentCourses () {

		return this.concurrentCourses;

	}

	/**
	 * This method returns a string representation of a CoursePrerequisites object.
	 * @return The string representation.
	 */
	public String toString() {

		String str = "[ " +
			     "Course Number: "       + courseNum                   +
			     ", Academic Year: "     + academicYear                +
			     ", Credits: "           + credits                     +
			     ", And Prerequisites: " + toString(andPrereqCourses)  +
			     ", Or Prerequisistes: " + toString(orPrereqCourses)   +
                             ", Corequisistes: "     + toString(concurrentCourses) +
			     " ]";

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
