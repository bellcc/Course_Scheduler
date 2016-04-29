package types;

public class Exam {
	
	private int     start_time;
	private String  days;
	
	private char    exam_day;
	private int     exam_time;
	
	public Exam(Course course) {
		start_time = course.getCourseStartTime().getValue().intValue();
		days = course.getCourseDays().toString();
		
		setExam();
	}
	
	public String getCourseExamDay() {
		return Character.toString(exam_day);
	}
	
	public int getCourseExamTime() {
		return exam_time;
	}

	public void setExam() {
			
		if (start_time < 1000) {
			between8And10();
		} else if ((start_time < 1130) && (start_time >= 1000)) {
			between10And1130();
		} else if ((start_time < 1300) && (start_time >= 1130)) {
			between1130And1();
		} else if ((start_time < 1430) && (start_time >= 1300)) {
			between1And230();
		} else if ((start_time < 1600) && (start_time >= 1430)) {
			between230And4();
		} else if ((start_time < 1730) && (start_time >= 1600)) {
			between4And530();
		} else if ((start_time >= 1730)) {
			past530();
		} else {
			exam_day = ' ';
			exam_time = 0;
		}
	}
		
	private void between8And10() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 800;
		} else {
			exam_day = 'T';
			exam_time = 800;
		}
	}
		
	// For classes between 10 and 11:30
	private void between10And1130() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'F';
			exam_time = 1015;
		} else {
			exam_day = 'R';
			exam_time = 800;
		}
	}
		
	// For classes between 11:30 and 1
	private void between1130And1() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 1015;
		} else {
			exam_day = 'T';
			exam_time = 1245;
		}
	}
		
	// For classes between 1 and 2:30
	private void between1And230() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'M';
			exam_time = 1245;
		} else {
			exam_day = 'R';
			exam_time = 1245;
		}
	}
		
	// For classes between 2:30 and 4
	private void between230And4() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 1500;
		} else {
			exam_day = 'T';
			exam_time = 1500;
		}
	}
		
	// For classes between 4 and 5:30
	private void between4And530() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'M';
			exam_time = 1500;
		} else {
			exam_day = 'R';
			exam_time = 1500;
		}
	}
		
	// For classes at or after 5:30pm
	private void past530() {
			
		switch(days) {
			case "M": 
				exam_day = 'M';
				exam_time = 1945;
				break;
			case "T":
				exam_day = 'T';
				exam_time = 1945;
				break;
			case "W":
				exam_day = 'W';
				exam_time = 1945;
				break;
			case "R":
				exam_day = 'R';
				exam_time = 1945;
				break;
			case "MW":
				exam_day = 'W';
				exam_time = 1730;
				break;
			case "TR":
				exam_day = 'T';
				exam_time = 1730;
				break;
				
		}
	}
	
}