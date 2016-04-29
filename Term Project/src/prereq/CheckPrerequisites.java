
/**
 * Author          : Christopher Bell
 * Date            : 04-16-2016
 * Info            : Methods that check if a proposed schedule meets the prerequisite requirements
 */

package prereq;

import java.util.ArrayList;

public class CheckPrerequisites {

	private ArrayList<CoursePrerequisites> allCourses;

	public CheckPrerequisites () {

		this.allCourses = new ArrayList<CoursePrerequisites>();
		getAllCourses();

	}

	/**
	 * This method checks if all the prerequisites have been met for a given course.
	 * @param courseList The list of courses that the student has taken.
	 * @param proposedList The list of courses the student wants to take.
	 * @param course The course the student wants to take and is checking against the prerequisites.
	 * @return Returns true if the prerequisites are met and false otherwise.
	 */
	public boolean check (ArrayList<String> courseList, ArrayList<String> proposedList, String course) {

		CoursePrerequisites coursePrereq = getRequirements(course);

		ArrayList<String> anCourses = coursePrereq.getAndPreReqCourses();
		ArrayList<String> orCourses = coursePrereq.getOrPreReqCourses();
		ArrayList<String> coCourses = coursePrereq.getConcurrentCourses();

		for (int i=0;i<anCourses.size();i++) {

			String tempCourse = anCourses.get(i);

			if (!contains(courseList, tempCourse)) {

				return false;

			}
	
		}

		boolean flag = false;

		for (int i=0;i<orCourses.size();i++) {

			String tempCourse = orCourses.get(i);

			if (contains(courseList, tempCourse)) {

				flag = true;

			}

			if (i == orCourses.size()-1 && flag == false) {

				return false;	

			}	

		}

		for (int i=0;i<coCourses.size();i++) {

			String tempCourse = coCourses.get(i);

			if (!contains(proposedList, tempCourse)) {

				return false;

			}
	
		}

		return true;
	
	}

	/**
	 * This method retrieves all of the missed prerequisites for a particular course.
	 * @param courseList The list of all courses taken by the student.
	 * @param proposedList The list of all courses in the student's proposed schedule.
	 * @param course The course we are checking against the prerequisites algorithm.
	 * @return An ArrayList of strings containing all of the offending courses. For an "or" series the courses are contained within one string and seperated by commas.
	 */ 
	public ArrayList<String> retrieveOffenders (ArrayList<String> courseList, ArrayList<String> proposedList, String course) {

		ArrayList<String> list = new ArrayList<String>();

		CoursePrerequisites coursePrereq = getRequirements(course);

		ArrayList<String> anCourses = coursePrereq.getAndPreReqCourses();
		ArrayList<String> orCourses = coursePrereq.getOrPreReqCourses();
		ArrayList<String> coCourses = coursePrereq.getConcurrentCourses();

		for (int i=0;i<anCourses.size();i++) {

			String courseTemp = anCourses.get(i);

			if (!contains(courseList, courseTemp)) {

				list.add(courseTemp);

			}

		}

		boolean flag = false;

		for (int i=0;i<orCourses.size();i++) {

			String courseTemp = orCourses.get(i);

			if (contains(courseList, courseTemp)) {

				flag = true;
			}

		}

		if (flag == false && orCourses.size() != 0) {

			String str = "";

			for (int i=0;i<orCourses.size();i++) {

				str += orCourses.get(i);

				if (i != orCourses.size()-1) {

					str += ",";

				}

			}

			list.add(str);

		}

		for (int i=0;i<coCourses.size();i++) {

			String courseTemp = coCourses.get(i);

			if (!contains(proposedList, courseTemp)) {

				list.add(courseTemp);
			}

		}	

		return list;
	
	}

	/**
	 * This method checks if there is a credit hour minimum for a course.
	 * @param course The course you are interested in testing.
	 * @return Returns true if there is a minumum credit hour requirement and false otherwise.
	 */
	public boolean checkCreditRequirement (String course) {

		CoursePrerequisites temp = getRequirements(course);

		int credits = temp.getCredits();

		if(credits != 0) {

			return true;

		}

		return false;

	}

	/**
	 * This method returns the minimum credit hours that must be taken for a class.
	 * @param course The course you want to know the minumum credit hour requirement of.
	 * @return The number of credit hours that must be taken to take a course.
	 */
	public int retrieveCreditRequirement (String course) {

		CoursePrerequisites temp = getRequirements(course);

		int credits = temp.getCredits();

		return credits;

	}

	/**
	 * This method checks if there is an academic year requirement for a course.
	 * @param course The course to check.
	 * @return Returns true if there is an academic year requirement and false otherwise.
	 */
	public boolean checkAcademicYear (String course) {

		CoursePrerequisites temp = getRequirements(course);

		String academicYear = temp.getAcademicYear();

		if(academicYear.equals("")) {

			return false;

		}

		return true;

	}

	/**
	 * This method retrieves the academic year requirement for a particular course.
	 * @param course The course you want to know the academic year requirement of.
	 * @return The academic year requirement.
	 */
	public String retrieveAcademicYear (String course) {

		CoursePrerequisites temp = getRequirements(course);

		String academicYear = temp.getAcademicYear();

		return academicYear;

	}

	/**
	 * This method checks whether or not a course is contained within a list of courses.
	 * @param courseList The list of courses to check against.
	 * @param course The course to check the list against.
	 * @return Returns true if the course is contained in the list and false otherwise.
	 */
	private boolean contains(ArrayList<String> courseList, String course) {

		for (int i=0;i<courseList.size();i++) {

			if (courseList.get(i).equals(course)) {

				return true;

			}

		}

		return false;

	}

	/**
	 * This method retrieves the requirements for a particular course.
	 * @param courseNum The number of the course to be evaluated.
	 * @return The course object that contains all of the prerequisites for a particular course.
	 */
	private CoursePrerequisites getRequirements (String courseNum) {

		int tempNum = 0;

		for (int i=0;i<allCourses.size();i++) {

			String temp = allCourses.get(i).getCourseNum();

			if(temp.equals(courseNum)) {

				tempNum = i;
				break;

			}

		}

		return allCourses.get(tempNum);

	}

	/**
	 * This method retrieves all of the course prerequisites.
	 */
	private void getAllCourses () {

		PrerequisitesParser parser = new PrerequisitesParser();
		allCourses = parser.getCourses();				

	}

	
}
