
/**
 * Author           : Christopher Bell
 * Date             : 04-16-2016
 * Info             : Reads the prerequisite reqirements from a .xml file.
 */

package prereq;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

import java.util.ArrayList;

public class PrerequisitesParser {

	/**
	 * This method retrieves all of the course prerequisite requirements.
	 * @return A list of all of the prerequisites for every course in CSE.
	 */
	public ArrayList<CoursePrerequisites> getCourses() {

		String filepath = "prereq.xml";

		return readPreReq(filepath);

	}

	/**
	 * This method retrieves all of the course requirements for a particular major.
	 * @param filepath The path to the file to be read.
	 * @return A list of all of the prerequisites for every course.
	 */
	private ArrayList<CoursePrerequisites> readPreReq(String filepath) {

		ArrayList<CoursePrerequisites> list = new ArrayList<CoursePrerequisites>();

		try {

			File fXmlFile = new File(filepath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("course");

			for(int i=0;i<nList.getLength();i++) {

				Node nNode = nList.item(i);

				CoursePrerequisites course = new CoursePrerequisites();

				String course_num;
				String aca_year;

				int credits;

				ArrayList<String> anPreReq = new ArrayList<String>();
				ArrayList<String> orPreReq = new ArrayList<String>();
				ArrayList<String> coPreReq = new ArrayList<String>();

				if(nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					course_num = eElement.getElementsByTagName("name").item(0).getTextContent();
					course.setCourseNum(course_num);

					try {

						String level = eElement.getElementsByTagName("level").item(0).getTextContent();
						course.setAcademicYear(level);

					}catch (NullPointerException e) {}

					try {

						String credit = eElement.getElementsByTagName("credit").item(0).getTextContent();
						int num = Integer.parseInt(credit);
						course.setCredits(num);	

					} catch (NullPointerException e) {}

					NodeList anList = eElement.getElementsByTagName("anPreReq");

					for(int j=0;j<anList.getLength();j++) {

						String course_prereq = anList.item(j).getTextContent();
						anPreReq.add(course_prereq);

						if(j == anList.getLength()-1) {

							course.setAndPreReqCourses(anPreReq);

						}

					}

					NodeList orList = eElement.getElementsByTagName("orPreReq");

					for(int j=0;j<orList.getLength();j++) {

						String course_prereq = orList.item(j).getTextContent();
						orPreReq.add(course_prereq);

						if(j == orList.getLength()-1) {

							course.setOrPreReqCourses(orPreReq);

						}

					}

					NodeList coList = eElement.getElementsByTagName("coPreReq");

					for(int j=0;j<coList.getLength();j++) {

						String course_prereq = coList.item(j).getTextContent();
						coPreReq.add(course_prereq);

						if(j == coList.getLength()-1) {

							course.setConcurrentCourses(coPreReq);

						}

					}

					list.add(course);

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return list;

	}

}
