package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void addToFrontAndEnd() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		String answer = "Blue\n" + "Black";
		basicList.addToFront("Red").addToFront("Blue").addToEnd("Yellow").addToEnd("Black");
		String result = basicList.getFirst() + "\n" + basicList.getLast();
		assertTrue(answer.equals(result));
	}
	@Test
	public void retrieveAndSizeTest() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		String answer1 = "Blue\n" + "Black";
		basicList.addToFront("Red").addToFront("Blue").addToEnd("Yellow").addToEnd("Black");
		int tSize = 4, acSize = basicList.getSize();
		String result1 = basicList.retrieveFirstElement() + "\n" + basicList.retrieveLastElement();
		String answer2 = "Red\n" + "Yellow";
		String result2 = basicList.getFirst() + "\n" + basicList.getLast();
		assertTrue(answer1.equals(result1) && answer2.equals(result2) && tSize == acSize);
	}
	@Test
	public void removeAndReverseTest() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		basicList.addToFront("Red").addToFront("Blue").addToEnd("Yellow").addToEnd("Black").addToEnd("Red").addToEnd("Green");
		String answer1 = "\" Blue Yellow Black Green \"";
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		String result1 = basicList.toString();
		BasicLinkedList<String> reverseList = basicList.getReverseList();
		String answer2 = "\" Green Black Yellow Blue \"";
		String result2 = reverseList.toString();
		ArrayList<String> list = basicList.getReverseArrayList();
		String answer3 = "[Green, Black, Yellow, Blue]";
		String result3 = list.toString();
		assertTrue(answer1.equals(result1) && answer2.equals(result2) && answer3.equals(result3));
	}
	@Test
	public void sortedListAddTest() {
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sortedList.add("Yellow").add("Red").add("Blue").add("White");
		String answer = "\" Blue Red White Yellow \"";
		String result = sortedList.toString();
		assertTrue(answer.equals(result));
	}

}
