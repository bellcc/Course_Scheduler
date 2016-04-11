import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public final class CourseParser {

	public static ArrayList<Course> parseFile(File file) throws FileNotFoundException {

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

				Course aCourse = new Course(parts_tmp[0], parts_tmp[2], parts_tmp[5], parts_tmp[6], parts_tmp[7], parts_tmp[12], parts_tmp[15], parts_tmp[1], parts_tmp[3], parts_tmp[4], parts_tmp[10], parts_tmp[11], parts_tmp[13], parts_tmp[14], st, et);

				schedule.add(aCourse);

			}
	
			scanner.close();

		}
		catch (FileNotFoundException er) {

			System.out.println("The course schedule file is missing.");

		}

		return schedule;

	}

	private static void printSchedule(ArrayList<Course> schedule) {

		for(int i=0;i<schedule.size();i++) {

			Course tmp = schedule.get(i);

			System.out.println(tmp.toString());

			System.out.println("- - - - - - - - - - - - - - - - - - - -");

		}

	}

}
