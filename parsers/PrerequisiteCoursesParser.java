import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class PrerequisiteCoursesParser {

	public static String[] parsePrereqCoursesFile(File file) throws FileNotFoundException {
		
		ArrayList<String> temp = new ArrayList<String>();
		Scanner input = new Scanner(file);
		
		while(input.hasNextLine()) {
			String course = input.nextLine();
			temp.add(course);
		}
		
		String[] prerequisiteCourses = new String[temp.size()];
		prerequisiteCourses = temp.toArray(prerequisiteCourses);
		return prerequisiteCourses;
	}
}
