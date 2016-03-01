
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

	int start_time;
	int end_time;

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

		start_time = 0;
		end_time = 0;

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

	public String toString() {

		String str = "Course Num: " + course_num + "\nSubject: " + subject + "\nCourse: " + course + "\nSection: " + section + "\nTitle:" + title + "\nHours: " + hours + "\nEnrollment: " + enrollment + "\nMax Enrollment: " + max_enrollment + "\nStart Time: " + start_time + "\nEnd Time: " + end_time + "\nDays: " + days + "\nBuilding: " + building + "\nRoom: " + room + "\nInstructor: " + instructor + "\nMeeting Type: " + meeting_type + "\nMeeting Category: " + meeting_category;

		return str;

	}

}
