
public class ClassConflicts {

	//return true if classes overlap at any time
	public static boolean hasConflict(CourseInfo class1, CourseInfo class2) {
		
		//check if times overlap
		if((class1.getStartTime() <= class2.getEndTime()) && 
				(class2.getStartTime() <= class1.getEndTime())) {
			
			//check if days overlap
			for(int i = 0; i < class1.getDays().length(); i++) {
				
				if(class2.getDays().indexOf(class1.getDays().charAt(i)) != -1) {
					return true;
				}
			
			}
		
		}
		
		return false;
	
	}
	
	// Return true if two courses have conflicting exam times
	public static boolean hasExamConflict(CourseInfo first, CourseInfo second) {
		
		if((first.getExamDay() == second.getExamDay()) &&
				(first.getExamTime() == second.getExamTime()) &&
				(first.getExamTime() != 0)) {
			return true;
		}
		
		return false;
	}
}
