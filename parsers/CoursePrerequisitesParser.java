import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CoursePrerequisitesParser {

	public static ArrayList<CoursePrerequisites> parseCourses(File file) throws FileNotFoundException {
		
		ArrayList<CoursePrerequisites> temp = new ArrayList<CoursePrerequisites>();
		Scanner input = new Scanner(file);
		
		String fileInfo = input.nextLine() + "\n" + input.nextLine() + input.nextLine();
			
		while(input.hasNextLine()) {
			CoursePrerequisites coursePrereq = new CoursePrerequisites();

			String[] tempCourseInfo = new String[5];
			ArrayList<String> coursePrerequisites = new ArrayList<String>();
			
			String courseInfo = input.nextLine().trim();
			courseInfo = courseInfo.replaceAll(" ", "");
			
			int j = 0;
			
 			for(int i = 0; i < courseInfo.length(); i += 6) {
				String tempInfo = courseInfo.substring(i, i + 6);
				tempCourseInfo[j] = tempInfo;
				j++;
			}
			
			coursePrereq.setCourseSubNum(tempCourseInfo[0]);
			
			if(!(tempCourseInfo[1] == null)) coursePrerequisites.add(tempCourseInfo[1]);
			if(!(tempCourseInfo[2] == null)) coursePrerequisites.add(tempCourseInfo[2]);
			if(!(tempCourseInfo[3] == null)) coursePrerequisites.add(tempCourseInfo[3]);
			if(!(tempCourseInfo[4] == null)) coursePrerequisites.add(tempCourseInfo[4]);

			coursePrereq.setPrerequisitesCourses(coursePrerequisites);
			
			temp.add(coursePrereq);
		}
		return temp;	
	}
}
