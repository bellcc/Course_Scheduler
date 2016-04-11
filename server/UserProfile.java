
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;

import org.xml.sax.SAXException;

public class UserProfile {

	private String filepath = System.getProperty("user.dir") + "/users/";

	public void createUserProfile(String username, String password) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("student");
			doc.appendChild(rootElement);

			Element user = doc.createElement("user");
			user.appendChild(doc.createTextNode(username));
			rootElement.appendChild(user); 

			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(password));
			rootElement.appendChild(pass);

			Element courses = doc.createElement("courses");
			rootElement.appendChild(courses);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + username + ".xml"));
		
			transformer.transform(source, result);
	
		}catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		}catch (TransformerException tfe) {

			tfe.printStackTrace();

		}

	}



	public void createUserProfile(String username, String password, ArrayList<String> temp) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("student");
			doc.appendChild(rootElement);

			Element user = doc.createElement("user");
			user.appendChild(doc.createTextNode(username));
			rootElement.appendChild(user); 

			Element pass = doc.createElement("password");
			pass.appendChild(doc.createTextNode(password));
			rootElement.appendChild(pass);

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
			StreamResult result = new StreamResult(new File(filepath + username + ".xml"));
		
			transformer.transform(source, result);

			System.out.println("File saved!");
	
		}catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		}catch (TransformerException tfe) {

			tfe.printStackTrace();

		}

	}

	public void appendUserProfile(String username, ArrayList<String> coursesTaken) {

		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> temp = readUserProfile(username);

		for(int i=0;i<coursesTaken.size();i++) {

			String course1 = coursesTaken.get(i);

			for(int j=0;j<temp.size();j++) {

				String course2 = temp.get(j);

				if(course1.equals(course2)) {

					break;

				}else if(j == temp.size()-1) {

					list.add(course1);

				}

			}

		}	

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath + username + ".xml");

			Node student = doc.getFirstChild();

			Node courses = doc.getElementsByTagName("courses").item(0);

			for(int i=0;i<list.size();i++) {

				Element course = doc.createElement("course");
				course.appendChild(doc.createTextNode(list.get(i)));
				courses.appendChild(course);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath + username + ".xml"));

			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		} catch (TransformerException tfe) {

			tfe.printStackTrace();

		} catch (IOException ioe) {

			ioe.printStackTrace();

		} catch (SAXException sae) {

			sae.printStackTrace();

		}

	}

	public ArrayList<String> readUserProfile(String username) {

		ArrayList<String> list = new ArrayList<String>();

		try {

			File fXmlFile = new File(filepath + username + ".xml");
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

			e.printStackTrace();

		}

		return list;

	}
				
	public String retrieveUserPassword(String username) {

		String password = "";

		try {

			File fXmlFile = new File(filepath + username + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("password");

			Node nNode = nList.item(0);
			Element eElement = (Element) nNode;

			password = eElement.getTextContent();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return password;

	}

}
