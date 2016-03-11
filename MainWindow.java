import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	private static String[] prereqCourses;
	private ArrayList<Course> arrayListCourses;
	private static ArrayList<Course> proposedSchedule = new ArrayList<Course>();

	private String[] shortPrereqsPassed;
	private String[] longPrereqsPassed;
	private String[] longPrereqCoursesRemaining;
	private String[] longProposedSchedule;
	private String[] longCSECoursesRemaining;
	private int totalCreditsEarned;
	private boolean prerequisitesFinalized = false;
	private boolean addCoursesFinalized = false;
	private boolean freshman;
	private boolean sophomore;
	private boolean junior;
	private boolean senior;

	private JLabel prereqsListLabel;
	private JLabel passedCoursesLabel;
	private JLabel courseListLabel;
	private JLabel coursesToAddLabel;
	private JLabel creditsEarnedLabel;
	private JButton processButton;
	private JButton addCoursesButton;
	private JList leftSideList;
	private JList rightSideList;
	private SortedListModel sortedLeftSideList;
	private SortedListModel sortedRightSideList;
	private JScrollPane scrollSortedLeftSideList;
	private JScrollPane scrollSortedRightSideList;
	private JCheckBox freshmanBox;
	private JCheckBox sophomoreBox;
	private JCheckBox juniorBox;
	private JCheckBox seniorBox;
	private JTextField creditsEarned;
	private ButtonGroup collegeYear;

	public MainWindow() throws FileNotFoundException {
		getPrereqCourses();
		getCourses();
		setUpMainWindow();
	}

	private void getPrereqCourses() throws FileNotFoundException {
		File file = new File("Prerequisite Courses.txt");
		prereqCourses = PrerequisiteCoursesParser.parsePrereqCoursesFile(file);

	}

	private void getCourses() throws FileNotFoundException {
		File file = new File("CSECourses.csv");
		arrayListCourses = CourseParser.parseFile(file);
	}

	private void setUpMainWindow() { // ============================================================== HOMEPAGE FUNCTIONALITY

		JFrame mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(200, 100);
		mainWindow.setResizable(false);
		mainWindow.setLayout(null);

		JButton setUpPreReqButton = new JButton("Add Courses");
		setUpPreReqButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		setUpPreReqButton.setBounds(51, 25, 100, 23);
		setUpPreReqButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourseWindow();
				if (prerequisitesFinalized()) { // If prerequisites have been set before, reopen window showing those prerequisites
					clearLeftListModel();
					clearRightListModel();
					addLeftList(getLongPrereqsRemaining());
					addRightList(getLongPrereqsPassed());
					// Sets the state of the check boxes that were set in the previous set prerequisites window
					freshmanBox.setSelected(freshman);
					sophomoreBox.setSelected(sophomore);
					juniorBox.setSelected(junior);
					seniorBox.setSelected(senior);
					// Sets the credits earned JTextField from the previous set prerequisites window
					creditsEarned.setText(String.valueOf(totalCreditsEarned));
				}
				// If it's the first time setting prerequisites, open a fresh window with CSE course prerequisite classes in the left list
				else {
					addLeftList(prereqCourses);
				}
			}
		});

		mainWindow.add(setUpPreReqButton);

		mainWindow.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void addCourseWindow() { // ============================================================== ADD COURSE FUNCTIONALITY

		JFrame addCourseWindow = new JFrame("Add Course Window");
		addCourseWindow.setSize(1228, 490);
		addCourseWindow.setResizable(false);
		addCourseWindow.setLayout(null);

		prereqsListLabel = new JLabel("Prerequisites List");
		prereqsListLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		prereqsListLabel.setBounds(93, 10, 100, 14);
		addCourseWindow.add(prereqsListLabel);
		prereqsListLabel.setVisible(true);

		passedCoursesLabel = new JLabel("Prerequisites Passed");
		passedCoursesLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		passedCoursesLabel.setBounds(705, 10, 120, 14);
		addCourseWindow.add(passedCoursesLabel);
		passedCoursesLabel.setVisible(true);

		courseListLabel = new JLabel("Course List");
		courseListLabel.setBounds(531, 10, 62, 14);
		courseListLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addCourseWindow.add(courseListLabel);
		courseListLabel.setVisible(false);

		coursesToAddLabel = new JLabel("Proposed Schedule");
		coursesToAddLabel.setBounds(1095, 10, 110, 14);
		coursesToAddLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		addCourseWindow.add(coursesToAddLabel);
		coursesToAddLabel.setVisible(false);

		sortedLeftSideList = new SortedListModel();
		leftSideList = new JList(sortedLeftSideList);
		leftSideList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollSortedLeftSideList = new JScrollPane(leftSideList);
		scrollSortedLeftSideList.setBounds(93, 30, 500, 386);
		addCourseWindow.add(scrollSortedLeftSideList);

		sortedRightSideList = new SortedListModel();
		rightSideList = new JList(sortedRightSideList);
		rightSideList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollSortedRightSideList = new JScrollPane(rightSideList);
		scrollSortedRightSideList.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollSortedRightSideList.setBounds(705, 30, 500, 386);
		addCourseWindow.add(scrollSortedRightSideList);

		JButton addButton = new JButton("Add >>");
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addButton.setBounds(603, 182, 92, 24);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Object selected[] = leftSideList.getSelectedValues();
				addRightList(selected);
				clearLeftListSelected();
			}
		});
		addCourseWindow.add(addButton);

		JButton removeButton = new JButton("<< Remove");
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		removeButton.setBounds(603, 240, 92, 24);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Object selected[] = rightSideList.getSelectedValues();
				addLeftList(selected);
				clearRightListSelected();
			}
		});
		addCourseWindow.add(removeButton);

		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		clearButton.setBounds(603, 211, 92, 24);
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		addCourseWindow.add(clearButton);

		processButton = new JButton("Process Prerequisites");
		processButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		processButton.setBounds(705, 421, 245, 23);
		processButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean error1 = false;
				boolean error2 = false;
				String errors = "";

				// Get the contents of each JList, to be used to check prerequisites and for when the interface is run again
				shortPrereqsPassed = getSubNumPrereqsPassed(rightSideList);
				longPrereqsPassed = getLongRightSideList(rightSideList);
				longPrereqCoursesRemaining = getLongLeftSideList(leftSideList);

				try {
					if (getTotalCreditsEarned() < 0 || getTotalCreditsEarned() > 150)
						error1 = true;
					else
						totalCreditsEarned = getTotalCreditsEarned();
				} catch (Exception ex) {
					error1 = true;
				}
				if (error1)
					errors += "\n-     Please enter a valid number of credits earned";

				if (!freshmanBox.isSelected() && !sophomoreBox.isSelected() && 
					!juniorBox.isSelected() && !seniorBox.isSelected())
					error2 = true;
				if (error2)
					errors += "\n-     Please enter your year in school";

				yearInSchool(); // Sets your year in school to boolean variables, for when the interface is run again

				if (error1 || error2)
					JOptionPane.showMessageDialog(null, "The following error(s) occurred:" + errors, " Error Message",
							JOptionPane.INFORMATION_MESSAGE);

				if (!error1 && !error2)
					prerequisitesFinalized = true;

				// When prerequisites are finalized, change the window to accommodate adding courses to the students schedule
				if (prerequisitesFinalized()) {
					prereqsListLabel.setVisible(false);
					passedCoursesLabel.setVisible(false);
					courseListLabel.setVisible(true);
					freshmanBox.setEnabled(false);
					sophomoreBox.setEnabled(false);
					juniorBox.setEnabled(false);
					seniorBox.setEnabled(false);
					creditsEarned.setEnabled(false);
					coursesToAddLabel.setVisible(true);
					processButton.setEnabled(false);
					addCoursesButton.setEnabled(true);
					repaint();
					clearLeftListModel();
					clearRightListModel();
					addLeftList(CSECourseListToJList(arrayListCourses));
					// If student has already completed this step and the window is run again, update the JList with the correct lists
					if (addCoursesFinalized()) {
						clearLeftListModel();
						clearRightListModel();
						addLeftList(getLongCSECoursesRemaining());
						addRightList(getLongProposedSchedule());
					}
				}
			}
		});
		addCourseWindow.add(processButton);

		addCoursesButton = new JButton("Update Schedule");
		addCoursesButton.setBounds(960, 421, 245, 23);
		addCoursesButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		addCoursesButton.setEnabled(false);
		addCoursesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Gets the contents of the right JList so when the interface is run again, it contains the correct schedule
				longProposedSchedule = getLongRightSideList(rightSideList);  
				updateSchedule(longProposedSchedule);	
				// Gets the contents of the left JList so when the interface is run again, it contains the correct CSE course list
				longCSECoursesRemaining = getLongLeftSideList(leftSideList);
				addCoursesFinalized = true;
				addCourses(longProposedSchedule);
				addCourseWindow.dispose();
			}
		});
		addCourseWindow.add(addCoursesButton);

		seniorBox = new JCheckBox("Senior");
		seniorBox.setHorizontalTextPosition(SwingConstants.LEFT);
		seniorBox.setHorizontalAlignment(SwingConstants.RIGHT);
		seniorBox.setBounds(11, 242, 80, 22);
		seniorBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		seniorBox.setSelected(senior);
		addCourseWindow.add(seniorBox);

		juniorBox = new JCheckBox("Junior");
		juniorBox.setHorizontalTextPosition(SwingConstants.LEFT);
		juniorBox.setHorizontalAlignment(SwingConstants.RIGHT);
		juniorBox.setBounds(11, 216, 80, 22);
		juniorBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		seniorBox.setSelected(junior);
		addCourseWindow.add(juniorBox);

		sophomoreBox = new JCheckBox("Sophomore");
		sophomoreBox.setHorizontalTextPosition(SwingConstants.LEFT);
		sophomoreBox.setHorizontalAlignment(SwingConstants.RIGHT);
		sophomoreBox.setBounds(11, 190, 80, 22);
		sophomoreBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		seniorBox.setSelected(sophomore);
		addCourseWindow.add(sophomoreBox);

		freshmanBox = new JCheckBox("Freshman");
		freshmanBox.setHorizontalTextPosition(SwingConstants.LEFT);
		freshmanBox.setHorizontalAlignment(SwingConstants.RIGHT);
		freshmanBox.setBounds(11, 164, 80, 22);
		freshmanBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		seniorBox.setSelected(freshman);
		addCourseWindow.add(freshmanBox);

		collegeYear = new ButtonGroup();
		collegeYear.add(freshmanBox);
		collegeYear.add(sophomoreBox);
		collegeYear.add(juniorBox);
		collegeYear.add(seniorBox);

		creditsEarnedLabel = new JLabel("Credits Earned");
		creditsEarnedLabel.setBounds(97, 421, 71, 23);
		creditsEarnedLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addCourseWindow.add(creditsEarnedLabel);

		creditsEarned = new JTextField();
		creditsEarned.setBounds(178, 422, 30, 20);
		creditsEarned.setHorizontalAlignment(JTextField.CENTER);
		addCourseWindow.add(creditsEarned);

		addCourseWindow.setVisible(true);
	}

	/**
	 * Takes in the array of display names from the interface that the student would like
	 * to add to their proposed schedule and adds those courses to their proposed schedule
	 * if prerequisites have been passed, or there are no prerequisites.
	 * @param coursesToAdd An array of display name strings from the SortedListModel
	 */
	public void addCourses(String[] coursesToAdd) {
		
		String errors = "";
		boolean errorCheck = false;

		for (int i = 0; i < coursesToAdd.length; i++) {
			String courseDisplayName = coursesToAdd[i];								// Get the display name for the class to be added
			Course courseToAdd = findCourse(arrayListCourses, courseDisplayName);	// Get the course object, if possible
			// If the student can add that course, add that course to the student's proposed schedule
			if (canAddCourse(courseToAdd).equals("")) {								
				proposedSchedule.add(courseToAdd);
			} else if (canAddCourse(courseToAdd).equals("Duplicate")) {				// If already on their schedule, do nothing
			} else {																// If prerequisites not met, show an error message
				errorCheck = true;
				errors += "\nThe following prerequisite(s) have not been met for " + courseToAdd.getCourseSubNum() + ":";
				errors += canAddCourse(courseToAdd);
			}
		}
		if (errorCheck) JOptionPane.showMessageDialog(null, errors);

		displayProposedSchedule();
	}

	/**
	 * Checks whether a course can be added to a student's proposed schedule. Checks whether
	 * a course has prerequisites or not. If the course has prerequisites, it checks whether
	 * or not those prerequisites have been satisfied in order to take the course
	 * @param courseToAdd The course object the student wishes to add to their proposed schedule
	 * @return An empty string if the student can successfully add the course to their proposed schedule
	 * 		   A string "Duplicate" if the course is already in their proposed schedule
	 * 		   A string containing an error message if the prerequisites have not been satisfied
	 */
	public String canAddCourse(Course courseToAdd) {

		String courseSubNum = courseToAdd.getCourseSubNum();						// Get the course's subject and number
		String courseDisplayName = courseToAdd.toDisplay();							// Get the display name for the course to be added
		String canAdd = "";
		enumCoursePreReqs course;													// The enum for the course to be added
		try {
			course = enumCoursePreReqs.valueOf(courseSubNum);                     	// Creates an enum variable for the course
			if (course.hasPrerequisites()) {										// Checks whether a course has prerequisites
				// Checks whether or not the prerequisites have been satisfied
				if (checkPrereqs(courseSubNum, course.getCoursePrereqs(), getShortPrereqsPassed()).equals("")) {
					if (alreadySignedUp(getProposedSchedule(), courseDisplayName))
						canAdd = "Duplicate";
					else
						canAdd = "";
				} else { 
					// Prerequisites have not been satisfied: remove from schedule, add to CSE course list and get the error message
					removeFromProposedSchedule(courseToAdd);
					addToCSECourses(courseToAdd);
					canAdd = (checkPrereqs(courseSubNum, course.getCoursePrereqs(), getShortPrereqsPassed()));
				}
			} else {																// Course does not have prerequisites
				if (alreadySignedUp(getProposedSchedule(), courseDisplayName))
					canAdd = "Duplicate";
				else
					canAdd = "";
			}
		} catch (IllegalArgumentException e) {}
		return canAdd;
	}

	/**
	 * Checks whether a student's proposed schedule already contains a certain course
	 * @param schedule Their current schedule
	 * @param courseDisplayName The display name of the course
	 * @return True, if the student is already signed up for the course
	 * 		   False, if the student is not currently signed up for the course
	 */
	public boolean alreadySignedUp(ArrayList<Course> schedule, String courseDisplayName) {
		
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).toDisplay().equals(courseDisplayName))
				return true;
		}
		return false;
		
	}

	/**
	 * This method is used to update the student's proposed schedule by removing any courses 
	 * from the proposed schedule that the student removed in the Add Courses interface
	 * @param updatedSchedule An array of Strings consisting of the display names for CSE courses
	 * 						  the student wishes have on their proposed schedule.
	 */
	public void updateSchedule(String[] updatedSchedule) {
		
		ArrayList<Course> schedule = new ArrayList<Course>();

		/* Loops through their previously made proposed schedule (if done already) and matches
		 their previous schedule with their updated SortedListModel of strings. If a course 
		 remains, add it to their new updated schedule */
		for (int i = 0; i < proposedSchedule.size(); i++) {
			for (int j = 0; j < updatedSchedule.length; j++) {
				if (updatedSchedule[j].equals(proposedSchedule.get(i).toDisplay()))
					schedule.add(proposedSchedule.get(i));
			}
		}
		// Set their proposed schedule to their newest, updated proposed schedule
		setProposedSchedule(schedule);
		
	}

	public void displayProposedSchedule() {
		
		String print = "";

		for (int i = 0; i < proposedSchedule.size(); i++) {
			print += proposedSchedule.get(i).toDisplay() + "\n";
		}
		System.out.println(print);
		
	}
	
	/**
	 * Finds the course trying to be added to the student's schedule. 
	 * @param courses An ArrayList of all the CSE courses for a given semester
	 * @param courseDisplayName The display name of the course on the SortedListModel
	 * @return If found, returns the course object of the course to be added OR
	 * 		   returns null if the course was not found
	 */
	public Course findCourse(ArrayList<Course> courses, String courseDisplayName) {
		
		Course courseToAdd = null;
		boolean stillLooking = true;
		int i = 0;

		// Loops through the ArrayList of CSE courses
		while (stillLooking && (i < courses.size())) {
			// If the display name of the course object matches the class selected in the SortedListModel
			if (courses.get(i).toDisplay().equals(courseDisplayName)) {
				courseToAdd = courses.get(i);
				stillLooking = false;
			} else i++;
		}
		return courseToAdd;
		
	}

	/**
	 * Checks whether a courses prerequisites have been met or not. If the prerequisites have been met
	 * the method returns an empty string and if the prerequisites have not been met the method returns
	 *  a string containing the necessary prerequisites needed to satisfy taking that course.
	 * @param courseSubNum The courses subject and number (IE: CSE174).
	 * @param prereqs An array of prerequisites needed to satisfy taking a course, in courseSubNum format.
	 * @param prereqsPassed An array of strings containing the prerequisites already passed, in courseSubNums format.
	 * @return An empty string if the prerequisites have been satisfied to sign up for a courses OR
			   an error message string containing the necessary prerequisites needed to sign up for that class.
	 */
	public String checkPrereqs(String courseSubNum, String[] prereqs, String[] prereqsPassed) {

		boolean firstCond = false;
		boolean secondCond = false;
		boolean thirdCond = false;
		boolean found = false;
		int courseCount = 0;
		String errors = "";

		/* CSE252 is a special case for prerequisites, needing students to have passed
		   CSE153 OR CSE163 OR CSE174 */
		if (courseSubNum.equals("CSE252")) {

			// Condition 1 - Checks whether CSE153, CSE163, OR CSE174 have been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("CSE153") || prereqsPassed[j].equals("CSE163")
						|| prereqsPassed[j].equals("CSE174"))
					firstCond = true;
			}
			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond) {
				errors += "\n-     CSE153 - Introduction to C/C++ Programming OR"
						+ "\n-     CSE163 - Introduction to Computer Concepts and Programming OR"
						+ "\n-     CSE174 - Fundamentals of Programming and Problem Solving";
				return errors;
			} else
				return "";
		}

		/* CSE262 is a special case for prerequisites, needing students to have passed
		   ENG111 and have at least 20 credits hours earned */
		else if (courseSubNum.equals("CSE262")) {

			int totalCreditsEarned = getTotalCreditsEarned(); 

			// Condition 1 - Checks whether the student has earned more than 20 credits
			if (totalCreditsEarned >= 20)
				firstCond = true;
			else
				errors += "\n-     Minimum of 20 credits hours earned";

			// Condition 2 - Check if ENG111 has already been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("ENG111"))
					secondCond = true;
			}
			if (!secondCond)
				errors += "\n-     ENG111 - Rhetorical Analysis";

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond || !secondCond)
				return errors;
			else
				return "";
		}

		/* CSE372 is a special case for prerequisites, needing students to have passed
		   STA368 or have concurrent registration in STA401 */
		else if (courseSubNum.equals("CSE372")) {

			// Condition 1 - Check whether the student is currently signed up for STA401
			for (int i = 0; i < proposedSchedule.size(); i++) {
				if (proposedSchedule.get(i).getCourseSubNum().equals("STA401"))
					firstCond = true;
			}

			// Condition 2 - Check if STA368 has already been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("STA368"))
					secondCond = true;
			}
			if (!firstCond && !secondCond)
				errors = "\n-     STA368 - Introduction to Statistics OR"
						+ "\n-     Concurrent registration in STA401 - Probability";

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond && !secondCond)
				return errors;
			else
				return "";
		}

		/* CSE385 is a special case for prerequisites, needing students to have passed
		   CSE274 or have concurrent registration in CSE274 */
		else if (courseSubNum.equals("CSE385")) {

			// Condition 1 - Check whether the student is currently signed up for CSE274
			for (int i = 0; i < proposedSchedule.size(); i++) {
				if (proposedSchedule.get(i).getCourseSubNum().equals("CSE274"))
					firstCond = true;
			}

			// Condition 2 - Check if CSE274 has already been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("CSE274"))
					secondCond = true;
			}
			if (!firstCond && !secondCond)
				errors = "\n-     CSE274 - Data Abstraction & Data Structures OR"
						+ "\n-     Concurrent registration in CSE274 - Data Abstraction & Data Structures";

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond && !secondCond)
				return errors;
			else
				return "";
		}

		/* CSE441 is a special case for prerequisites, needing students to have passed
		   CSE102 AND STA368 AND MTH245 OR MTH347 */
		else if (courseSubNum.equals("CSE441")) {

			// Condition 1 - Check if MTH245 OR MTH347 have already been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("MTH245") || prereqsPassed[j].equals("MTH347"))
					firstCond = true;
			}
			if (!firstCond)
				errors += "\n-     MTH245 - Differential Equations for Engineers OR\n-     MTH347 - Differential Equations";

			// Condition 2 - Check if CSE102 AND STA368 have already been passed
			for (int i = 0; i < prereqs.length; i++) {
				for (int j = 0; j < prereqsPassed.length; j++) {
					if (prereqs[i].equals(prereqsPassed[j])) {
						found = true;
						courseCount++;
					}
				}
				if (!found) {
					for (int k = 0; k < prereqCourses.length; k++) {
						if (prereqs[i].equals(prereqCourses[k].substring(0, 6)))
							errors += "\n-     " + prereqCourses[k];
					}
				}
				found = false;
				if (courseCount == prereqs.length)
					secondCond = true;
			}
			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond || !secondCond)
				return errors;
			else
				return "";
		}
		
		/* CSE448 is a special case for prerequisites, needing students to have passed
		   CSE201, CSE274 and be considered a senior */
		else if (courseSubNum.equals("CSE448") || courseSubNum.equals("CSE449")) {

			// Condition 1 - Check if senior standing
			if (senior)
				firstCond = true;
			else
				errors += "\n-     Senior standing in major";

			// Condition 2 - Check if CSE201 AND CSE274 have already been passed for CSE448
			// Condition 2 - Check if CSE448 has already been passed for CSE449
			for (int i = 0; i < prereqs.length; i++) {
				for (int j = 0; j < prereqsPassed.length; j++) {
					if (prereqs[i].equals(prereqsPassed[j])) {
						found = true;
						courseCount++;
					}
				}
				if (!found) {
					for (int k = 0; k < prereqCourses.length; k++) {
						if (prereqs[i].equals(prereqCourses[k].substring(0, 6)))
							errors += "\n-     " + prereqCourses[k];
					}
				}
				found = false;
				if (courseCount == prereqs.length)
					secondCond = true;
			}

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond || !secondCond)
				return errors;
			else
				return "";
		}
		
		/* CSE466 is a special case for prerequisites, needing students to have passed
		   BOT116 OR BOT342 */
		else if (courseSubNum.equals("CSE466")) {

			// Condition 1 - Check if BOT116 OR BOT342 have already been passed
			for (int j = 0; j < prereqsPassed.length; j++) {
				if (prereqsPassed[j].equals("BOT116") || prereqsPassed[j].equals("BOT342"))
					firstCond = true;
			}
			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond) {
				errors += "\n-     BOT116 - Biological Concepts: Structure, Function, Cellular and Molecular Biology OR"
						+ "\n-     BOT342 - Genetics";
				return errors;
			} else
				return "";
		}
		
		/* CSE471 is a special case for prerequisites, needing students to have passed
		   CSE174 AND STA368 OR STA401 */
		else if (courseSubNum.equals("CSE471")) {

			// Condition 1 - Check if CSE174 has already been passed
			for (int i = 0; i < prereqsPassed.length; i++) {
				if (prereqsPassed[i].equals("CSE174"))
					firstCond = true;
			}
			if (!firstCond)
				errors += "\n-     CSE174 - Fundamentals of Programming and Problem Solving";

			// Condition 2 - Check if STA386 OR STA401 have already been passed
			for (int i = 0; i < prereqsPassed.length; i++) {
				if (prereqsPassed[i].equals("STA368") || prereqsPassed[i].equals("STA401"))
					secondCond = true;
			}
			if (!secondCond)
				errors += "\n-     STA368 - Introduction to Statistics OR\n-     STA401 - Probability";

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond || (!secondCond && !thirdCond))
				return errors;
			else
				return "";
		}

		/* CSE481 is a special case for prerequisites, needing students to be in senior standing */
		else if (courseSubNum.equals("CSE481")) {

			// Condition 1 - Check if senior standing
			if (senior)
				firstCond = true;
			else
				errors += "\n-     Senior standing in major";

			// If conditions have not been met, return the error message, else return an empty string
			if (!firstCond)
				return errors;
			else
				return "";
		}

		/* All other prerequisite checking is done here, as other classes don't have any special
		   requirements (OR's or concurrent registration) */
		else {
			// Loops through the courses prerequisites and checks if the student has passes those courses
			for (int i = 0; i < prereqs.length; i++) {
				for (int j = 0; j < prereqsPassed.length; j++) {
					if (prereqs[i].equals(prereqsPassed[j])) {
						found = true;
						courseCount++;
					}
				}
				// If the course is not found, add it to the error message, showing the student which
				// prerequisites have not been met
				if (!found) {
					for (int k = 0; k < prereqCourses.length; k++) {
						if (prereqs[i].equals(prereqCourses[k].substring(0, 6)))
							errors += "\n-     " + prereqCourses[k];
					}
				}
				found = false; // Reset found to false in case of multiple prerequisites for a course
			}
			// If conditions have not been met, return the error message, else return an empty string
			if (courseCount != prereqs.length)
				return errors;
			else
				return "";
		}
		
	}

	/**
	 * Removes a course whose prerequisites have not been met from the student's SortedListModel of
	 * display names and the student's proposed schedule
	 * @param courseToRemove The course to be removed
	 */
	public void removeFromProposedSchedule(Course courseToRemove) {

		// Convert the SortedListModel proposed schedule to an ArrayList (easier to remove an element)
		ArrayList<String> tempProposedSchedule = new ArrayList<String>(Arrays.asList(getLongProposedSchedule()));

		// Remove the specified course from the student's proposed schedule
		for (int i = 0; i < proposedSchedule.size(); i++) {
			if (proposedSchedule.get(i).equals(courseToRemove))
				proposedSchedule.remove(i);
		}

		// Remove the specified course from the SortedListModel of the proposed schedule
		for (int i = 0; i < tempProposedSchedule.size(); i++) {
			if (tempProposedSchedule.get(i).equals(courseToRemove.toDisplay()))
				tempProposedSchedule.remove(i);
		}

		// Convert the temporary proposed schedule for the SortedListModel back into an array and set it
		longProposedSchedule = new String[tempProposedSchedule.size()];
		longProposedSchedule = tempProposedSchedule.toArray(longProposedSchedule);

	}

	/**
	 * Adds a course whose prerequisites have not been met back into the CSE course list for a given semester
	 * @param courseToAdd The course to be added
	 */
	public void addToCSECourses(Course courseToAdd) {

		ArrayList<String> tempCoursesRemaining = new ArrayList<String>(Arrays.asList(getLongCSECoursesRemaining()));

		// Add the course to the temporary ArrayList and then convert it back to an array to be put into the CSE course list
		tempCoursesRemaining.add(courseToAdd.toDisplay());
		longCSECoursesRemaining = new String[tempCoursesRemaining.size()];
		longCSECoursesRemaining = tempCoursesRemaining.toArray(longCSECoursesRemaining);

	}

	/**
	 * Converts the CSE course list in the ArrayList to an array for the SortedListModel
	 * @param courseList An ArrayList of CSE courses
	 * @return
	 */
	public String[] CSECourseListToJList(ArrayList<Course> courseList) {

		String[] semesterCourseList = new String[courseList.size()];

		for (int i = 0; i < courseList.size(); i++) {
			semesterCourseList[i] = courseList.get(i).toDisplay();
		}
		return semesterCourseList;
		
	}

	/**
	 * Get the contents of the right side of the SortedListModel
	 * @param rightSideList The JList on the right
	 * @return An array containing the contents of the JList
	 */
	public String[] getLongRightSideList(JList rightSideList) {

		ListModel model = rightSideList.getModel();
		String[] rightSide = new String[model.getSize()];

		for (int i = 0; i < model.getSize(); i++) {
			Object o = model.getElementAt(i);
			rightSide[i] = o.toString();
		}
		return rightSide;
	}

	/**
	 * Get the contents of the left side of the SortedListModel
	 * @param leftSideList The JList on the left
	 * @return An array containing the contents of the JList
	 */
	public static String[] getLongLeftSideList(JList leftSideList) {

		ListModel model = leftSideList.getModel();
		String[] leftSide = new String[model.getSize()];

		for (int i = 0; i < model.getSize(); i++) {
			Object o = model.getElementAt(i);
			leftSide[i] = o.toString();
		}
		return leftSide;
	}

	/**
	 * Returns an array of strings containing all the prerequisites passed by the student
	 * in the form of that courses subject and course number
	 * @param passedList The contents in the JList of the prerequisites passed
	 * @return
	 */
	public String[] getSubNumPrereqsPassed(JList passedList) {

		ListModel model = passedList.getModel();
		String[] preReqsPassed = new String[model.getSize()];

		for (int i = 0; i < model.getSize(); i++) {
			Object o = model.getElementAt(i);
			preReqsPassed[i] = o.toString();
		}
		
		// Cuts down each string to just contain the courses subject and course number
		for (int j = 0; j < preReqsPassed.length; j++) {
			String courseSubName = preReqsPassed[j];
			courseSubName = courseSubName.substring(0, 6);
			preReqsPassed[j] = courseSubName;
		}
		return preReqsPassed;
	}

	/**
	 * Gets the total credits earned by the student
	 * @return The total credits earned
	 */
	public int getTotalCreditsEarned() {
		String totalCredits = creditsEarned.getText().trim();
		return Integer.parseInt(totalCredits);
	}

	/** Set the state of the prerequisite interface for when the student reruns the interface */
	public void yearInSchool() {
		freshman = freshmanBox.isSelected();
		sophomore = sophomoreBox.isSelected();
		junior = juniorBox.isSelected();
		senior = seniorBox.isSelected();
	}

	/** Clears the contents back to their original state in the process prerequisites interface. */
	public void clear() {
		if (prerequisitesFinalized()) {
			addLeftList(sortedRightSideList);
			clearRightListModel();
		} else {
			addLeftList(sortedRightSideList);
			clearRightListModel();
			collegeYear.clearSelection();
			creditsEarned.setText("");
			repaint();
		}
	}

	public String[] getShortPrereqsPassed() 								{	return shortPrereqsPassed;							}
	public String[] getLongPrereqsPassed() 									{	return longPrereqsPassed;							}
	public String[] getLongPrereqsRemaining() 								{	return longPrereqCoursesRemaining;					}	
	public String[] getLongProposedSchedule() 								{	return longProposedSchedule;						}
	public String[] getLongCSECoursesRemaining() 							{	return longCSECoursesRemaining;						}
	public boolean prerequisitesFinalized() 								{	return prerequisitesFinalized;						}
	public boolean addCoursesFinalized() 									{	return addCoursesFinalized;							}
	public void setPrerequisitesFinalized(boolean finalized)				{	this.prerequisitesFinalized = finalized;			}
	public void setAddCoursesFinalized(boolean finalized)					{	this.addCoursesFinalized = finalized;				}
	public ArrayList<Course> getProposedSchedule() 							{	return proposedSchedule;							}
	public void setProposedSchedule(ArrayList<Course> schedule) 			{	this.proposedSchedule = schedule;					}

	// ============================================================  SORTED LIST MODEL METHODS

	public void clearLeftListModel() 										{	sortedLeftSideList.clear();							}
	public void clearRightListModel() 										{	sortedRightSideList.clear();						}
	public void addLeftList(ListModel newValue) 							{	fillJListModel(sortedLeftSideList, newValue);		}
	public void addLeftList(Object newValue[]) 								{	fillJListModel(sortedLeftSideList, newValue);		}
	public void addRightList(ListModel newValue) 							{	fillJListModel(sortedRightSideList, newValue);		}
	public void addRightList(Object newValue[]) 							{	fillJListModel(sortedRightSideList, newValue);		}
	private void fillJListModel(SortedListModel model, Object newValues[]) 	{	model.addAll(newValues);							}

	public void setLeftList(ListModel newValue) {
		clearLeftListModel();
		addLeftList(newValue);
	}

	public void setLeftList(Object newValue[]) {
		clearLeftListModel();
		addLeftList(newValue);
	}

	private void fillJListModel(SortedListModel model, ListModel newValues) {
		int size = newValues.getSize();
		for (int i = 0; i < size; i++) {
			model.add(newValues.getElementAt(i));
		}
	}

	private void clearLeftListSelected() {
		@SuppressWarnings("deprecation")
		Object selected[] = leftSideList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sortedLeftSideList.removeElement(selected[i]);
		}
		leftSideList.getSelectionModel().clearSelection();
	}

	private void clearRightListSelected() {
		@SuppressWarnings("deprecation")
		Object selected[] = rightSideList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sortedRightSideList.removeElement(selected[i]);
		}
		rightSideList.getSelectionModel().clearSelection();
	}

	public static void main(String[] args) throws FileNotFoundException {

		MainWindow window = new MainWindow();
		
	}
	
}
