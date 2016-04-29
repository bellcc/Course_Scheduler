
/**
 * @author Chris Bell
 */

package parsers;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import application.MainApp;

import org.w3c.dom.Node;
import java.util.ArrayList;
import types.UserInfo;

public class UserProfile {
	
	public UserInfo retrieveUserInfo(File file) {

		ArrayList<String> courseList = readUserProfile(file);
		String academicYear = getUserLevel(file);
		String degree = getUserDegree(file);
		int credits = getUserCredits(file);
		
		UserInfo user = new UserInfo(courseList, academicYear, degree, credits);
		
		return user;
	}

	private ArrayList<String> readUserProfile(File file) {

		ArrayList<String> list = new ArrayList<String>();

		try {

			File fXmlFile = file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("course");

			for(int i=0;i<nList.getLength();i++) {

				Node nNode = nList.item(i);
				Element eElement = (Element) nNode;

				String course = eElement.getTextContent();
				list.add(course);

			}

		} catch (Exception e) {

			//e.printStackTrace();
			MainApp.getSharedInstance().incorrectProfileFormat();

		}

		return list;

	}
	
	private String getUserLevel(File file) {
		
		String level = "";

		try {

			File fXmlFile = file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("level");
			Node nNode = nList.item(0);
			
			level = nNode.getTextContent();


		} catch (Exception e) {

			e.printStackTrace();

		}

		return level;

	}
	
	private String getUserDegree(File file) {
		
		String degree = "";

		try {

			File fXmlFile = file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("degree");
			Node nNode = nList.item(0);
			
			degree = nNode.getTextContent();


		} catch (Exception e) {

			e.printStackTrace();

		}

		return degree;
	}
	
	private int getUserCredits(File file) {
		
		String temp = "";
		int credits = 0;

		try {

			File fXmlFile = file;
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("credits");
			Node nNode = nList.item(0);
			
			temp = nNode.getTextContent();


		} catch (Exception e) {

			e.printStackTrace();

		}
		
		try {
			credits = Integer.parseInt(temp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return credits;

	}
	
	public void createUserProfile(UserInfo currentUser) {
		
		ArrayList<String> temp = currentUser.getCourses();
		String academicYear = currentUser.getAcademicYear();
		String degree = currentUser.getDegree();
		String credits = String.valueOf(currentUser.getCredits());

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("student");
			doc.appendChild(rootElement);

			Element user = doc.createElement("level");
			user.appendChild(doc.createTextNode(academicYear));
			rootElement.appendChild(user); 

			Element pass = doc.createElement("credits");
			pass.appendChild(doc.createTextNode(credits));
			rootElement.appendChild(pass);
			
			Element deg = doc.createElement("degree");
			deg.appendChild(doc.createTextNode(degree));
			rootElement.appendChild(deg);

			Element courses = doc.createElement("courses");
			rootElement.appendChild(courses);

			for(int i=0;i<temp.size();i++) {

				Element course = doc.createElement("course");
				course.appendChild(doc.createTextNode(temp.get(i)));
				courses.appendChild(course);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("course_scheduler.xml"));
		
			transformer.transform(source, result);

			System.out.println("File saved!");
	
		}catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		}catch (TransformerException tfe) {

			tfe.printStackTrace();

		}

	}

}
