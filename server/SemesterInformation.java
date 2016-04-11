
import java.io.*;
import java.util.ArrayList;

public class SemesterInformation {

	private ArrayList<Course> semester;

	private void createSemester() throws FileNotFoundException {

		String filepath = System.getProperty("user.dir") + "/thing.csv";

		CourseParser parser = new CourseParser();
		semester = parser.parseFile(new File(filepath));

	}

	public ArrayList<Course> getSemester() throws FileNotFoundException {

		createSemester();

		return semester;

	}

}	
