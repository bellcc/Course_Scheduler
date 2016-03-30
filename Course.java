
public class Course {

	String course_num;
	String course;
	String hours;
	String enrollment;
	String max_enrollment;
	String room;
	String meeting_category;
	String subject;
	String section;
	String title;
	String days;
	String building;
	String instructor;
	String meeting_type;
	char exam_day;

	int start_time;
	int end_time;
	int exam_time;

	public Course() {

		course_num = "";
		course = "";
		hours = "";
		enrollment = "";
		max_enrollment = "";
		room = "";
		meeting_category = "";
		subject = "";
		section = "";
		title = "";
		days = "";
		building = "";
		instructor = "";
		meeting_type = "";
		exam_day = ' ';

		start_time = 0;
		end_time = 0;
		exam_time = 0;

	}

	public Course(String crn, String co, String hr, String en, String me, String rm, String mc, String sb, String se, String ti, String ds, String bu, String in, String mt, int st, int et) {

		course_num = crn;
		course = co;
		hours = hr;
		enrollment = en;
		max_enrollment = me;
		room = rm;
		meeting_category = mc;
		subject = sb;
		section = se;
		title = ti;
		days = ds;
		building = bu;
		instructor = in;
		meeting_type = mt;

		start_time = st;
		end_time = et;
		
		setExam();

	}

	public void setCourseNumber(String crn) {

		course_num = crn;

	}

	public void setCourse(String cr) {

		course = cr;

	}

	public void setHours(String hr) {

		hours = hr;

	}

	public void setEnrollment(String en) {

		enrollment = en;

	}

	public void setMaxEnrollment(String me) {

		max_enrollment = me;

	}

	public void setStartTime(int st) {

		start_time = st;

	}

	public void setEndTime(int et) {

		end_time = et;

	}

	public void setRoom(String rm) {

		room = rm;

	}

	public void setMeetingCategory(String mc) {

		meeting_category = mc;

	}

	public void setSubject(String sb) {

		subject = sb;

	}

	public void setSection(String se) {

		section = se;

	}

	public void setTitle(String ti) {

		title = ti;

	}

	public void setDays(String ds) {

		days = ds;

	}

	public void setBuilding(String bu) {

		building = bu;

	}

	public void setInstructor(String in) {

		instructor = in;

	}

	public void setMeetingType(String mt) {

		meeting_type = mt;

	}

	public String getCourseNum() {

		return course_num;

	}

	public String getCourse() {

		return course;

	}

	public String getHours() {

		return hours;

	}

	public String getEnrollment() {

		return enrollment;

	}

	public String getMaxEnrollment() {

		return max_enrollment;

	}

	public int getStartTime() {

		return start_time;

	}

	public int getEndTime() {

		return end_time;

	}

	public String getRoom() {

		return room;

	}

	public String getMeetingCategory() {

		return meeting_category;

	}

	public String getSubject() {

		return subject;

	}

	public String getSection() {

		return section;

	}

	public String getTitle() {

		return title;

	}

	public String getDays() {

		return days;

	}

	public String getBuilding() {

		return building;

	}

	public String getInstructor() {

		return instructor;

	}

	public String getMeetingType() {

		return meeting_type;

	}
	
	public char getExamDay() {
		
		return exam_day;
		
	}
	
	public int getExamTime() {
		
		return exam_time;
		
	}

	public String toString() {

		String str = "Course Num: " + course_num + "\nSubject: " + subject + "\nCourse: " + course + "\nSection: " + section + "\nTitle:" + title + "\nHours: " + hours + "\nEnrollment: " + enrollment + "\nMax Enrollment: " + max_enrollment + "\nStart Time: " + start_time + "\nEnd Time: " + end_time + "\nDays: " + days + "\nBuilding: " + building + "\nRoom: " + room + "\nInstructor: " + instructor + "\nMeeting Type: " + meeting_type + "\nMeeting Category: " + meeting_category;

		return str;

	}
	
	 public String getCourseSubNum() {
	 	
 		return subject + course;
 		
 	}
 	
 	public String toDisplay() {
 		
 		String str = "";
 		if(getDays().equals(""))
 			str = subject + course + " (" + getSection().trim() + ") - " + title + " (" + hours + ")"  +  " (" + instructor + ")";
 		else
 			str = subject + course + " (" + getSection().trim() + ") - " + title + " (" + hours + ") - " + days + ": " + start_time + "-" + end_time + " (" + instructor + ")";
 		return str;
 		
 	}
 	
 	// Set exam day and startTime for class
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
		
	// For classes between 8 and 10
	public void between8And10() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 800;
		} else {
			exam_day = 'T';
			exam_time = 800;
		}
	}
		
	// For classes between 10 and 11:30
	public void between10And1130() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'F';
			exam_time = 1015;
		} else {
			exam_day = 'R';
			exam_time = 800;
		}
	}
		
	// For classes between 11:30 and 1
	public void between1130And1() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 1015;
		} else {
			exam_day = 'T';
			exam_time = 1245;
		}
	}
		
	// For classes between 1 and 2:30
	public void between1And230() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'M';
			exam_time = 1245;
		} else {
			exam_day = 'R';
			exam_time = 1245;
		}
	}
		
	// For classes between 2:30 and 4
	public void between230And4() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'W';
			exam_time = 1500;
		} else {
			exam_day = 'T';
			exam_time = 1500;
		}
	}
		
	// For classes between 4 and 5:30
	public void between4And530() {
			
		if(days.contains("M") || days.contains("W") || days.contains("F")) {
			exam_day = 'M';
			exam_time = 1500;
		} else {
			exam_day = 'R';
			exam_time = 1500;
		}
	}
		
	// For classes at or after 5:30pm
	public void past530() {
			
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
