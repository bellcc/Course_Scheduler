
import java.util.ArrayList;

public class test {

	public static void main(String [] args) {

		CheckPrerequisites checker = new CheckPrerequisites();

		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();

		list1.add("CSE174");
		list2.add("CSE271");

		ArrayList<String> list = checker.retrieveOffenders(list1, list2, "CSE283");

	}

}
