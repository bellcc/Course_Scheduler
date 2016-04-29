
/**
 * @author Chris Bell
 */

package parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import application.MainApp;

import java.util.ArrayList;
import types.Course;
import types.Exam;

public class CourseParser {

	public ArrayList<Course> parseFile(File courseFile) throws FileNotFoundException {

		File file = courseFile;

		ArrayList<Course> schedule = new ArrayList<Course>();

		try {

			Scanner scanner = new Scanner(file);

			scanner.useDelimiter(",");

			scanner.nextLine();

			while(scanner.hasNext()) {

				String course_str = scanner.nextLine();
				String parts[] = course_str.split(",");

				String parts_tmp[] = new String[16];
				int counter = 0;

				for(int i=0;i<parts.length;i++) {

					if(parts[i].startsWith("\"")) {

						int index = -1;

						for(int j=i;j<parts.length;j++){

							if(parts[j].endsWith("\"")) {
								index = j;
								break;

							}

						}

						String concat_str = "";

						if(i == index) {

							concat_str = parts[i];

						}
						else{

							concat_str = parts[i];

							for(int j=i+1;j<=index;j++) {

								concat_str += "," +  parts[j];

							}

						}

						parts_tmp[counter] = concat_str;
						counter++;
						i = index;


					}
					else{

						parts_tmp[counter] = parts[i];
						counter++;

					}

				}

				int st = 0;
				int et = 0;

				try {

					st = Integer.parseInt(parts_tmp[8]);
					et = Integer.parseInt(parts_tmp[9]);
				
				}
				catch (NumberFormatException e) {

					st = -1;
					et = -1;

				}
				
				int     courseNumber           = Integer.parseInt(parts_tmp[0]);
				String  courseSubject          = parts_tmp[1].trim();
				String  courseSequence         = parts_tmp[2];
				String  courseSection          = parts_tmp[3].trim();
				String  courseTitle            = parts_tmp[4].trim();
				String  courseHours            = parts_tmp[5].trim();
				int     currentEnrollment      = Integer.parseInt(parts_tmp[6]);
				int     maxEnrollment          = Integer.parseInt(parts_tmp[7]);
				int     courseStartTime        = st;
				int     courseEndTime          = et;
				String  courseDays             = parts_tmp[10].trim();
				String  courseBuilding         = parts_tmp[11].trim();
				String  courseRoom             = parts_tmp[12];
				String  courseInstructor       = parts_tmp[13].trim();
				String  courseMeetingType      = parts_tmp[14].trim();
				String  courseMeetingCategory  = parts_tmp[15].trim();
				
				Course aCourse = new Course();
				
				aCourse.setCourseNumber(courseNumber);
				aCourse.setCourseName(courseSubject);
				aCourse.setCourseSequence(courseSequence);
				aCourse.setCourseSection(courseSection);
				aCourse.setCourseTitle(courseTitle);
				aCourse.setCourseHours(courseHours);
				aCourse.setCurrentEnrollment(currentEnrollment);
				aCourse.setMaxEnrollment(maxEnrollment);
				aCourse.setCourseStartTime(courseStartTime);
				aCourse.setCourseEndTime(courseEndTime);
				aCourse.setCourseDays(courseDays);
				aCourse.setCourseBuilding(courseBuilding);
				aCourse.setCourseRoom(courseRoom);
				aCourse.setCourseInstructor(courseInstructor);
				aCourse.setCourseMeetingType(courseMeetingType);
				aCourse.setCourseMeetingCategory(courseMeetingCategory);
				
				Exam finalExam = new Exam(aCourse);
				
				String  courseExamDay  = finalExam.getCourseExamDay();
				int     courseExamTime = finalExam.getCourseExamTime();
				
				aCourse.setCourseExamDay(courseExamDay);
				aCourse.setCourseExamTime(courseExamTime);
				
				schedule.add(aCourse);

			}
	
			scanner.close();

		}
		catch (FileNotFoundException er) {

			System.out.println("The course schedule file is missing.");

		} catch (NumberFormatException et) {
			
			MainApp.getSharedInstance().incorrectCSVFormat();
			
		}

		return schedule;

	}

}
