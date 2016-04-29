
/**
 * Author       : Audrey Winzeler
 * Date         : 03-31-2016
 * Info         : This class checks for conflicts between courses.
 */

package conflict;

import types.Course;

public class ClassConflicts {

	public boolean hasConflict(Course course1, Course course2) {
		
		if(course1.equals(course2)) {
			return false;
		}
		
		int start1 = course1.getCourseStartTime().getValue().intValue();
		int end1   = course1.getCourseEndTime().getValue().intValue();
		
		int start2 = course2.getCourseStartTime().getValue().intValue();
		int end2   = course2.getCourseEndTime().getValue().intValue();
		
		if((start1 <= end2) && (start2 <= end1)) {
			
			int n = course1.getCourseDays().toString().length();
			
			for(int i=0;i<n;i++) {
				
				char day = course1.getCourseDays().toString().charAt(i);
				int value = course2.getCourseDays().toString().indexOf(day);
				
				if(value != -1) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean hasExamConflict(Course course1, Course course2) {

		if(course1.equals(course2)) {
			return false;
		}
		
		int time = course1.getCourseExamTime().getValue().intValue();
		
		if(course1.getCourseExamDay().equals(course2.getCourseExamDay()) && 
		  (course1.getCourseExamTime() == course2.getCourseExamTime())   && 
		  (time != 0)) {
			
			  return true;
		}
		
		return false;
	}
}
