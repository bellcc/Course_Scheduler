
public class ClassConflicts {

	//return true if classes overlap at any time
	public static boolean isConflict(CourseInfo class1, CourseInfo class2) {
		
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
}
