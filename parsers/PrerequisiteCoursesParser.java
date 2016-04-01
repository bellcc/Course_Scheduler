import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrereqsCourseTypeParser {

	public static String[] parseCourses(File file) throws FileNotFoundException {
		
		ArrayList<String> temp = new ArrayList<String>();
		Scanner input = new Scanner(file);
		
		while(input.hasNextLine()) {
			String courseSubNum = input.nextLine();
			temp.add(courseSubNum);
		}
		
		String[] courses = new String[temp.size()];
		courses = temp.toArray(courses);
		return courses;
		
	}
}
