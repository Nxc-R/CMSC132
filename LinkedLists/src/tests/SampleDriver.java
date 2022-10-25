package tests;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

public class SampleDriver {

	public static void main(String[] args) {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();

		basicList.addToFront("Red").addToFront("Blue").addToEnd("Yellow").addToFront("Green").addToFront("Red").addToEnd("Black");
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		System.out.println(basicList);
		basicList.addToEnd("Green");
		basicList.addToEnd("Green");
		basicList.addToEnd("Green");
		basicList.addToFront("Red");
		System.out.println(basicList);
		/*BasicLinkedList<String> basicList1 = basicList.getReverseList();
		System.out.println("Reverse List");
		System.out.println(basicList1);
		ArrayList<String> list = basicList.getReverseArrayList();
		System.out.println("Reverse ArrayList");
		System.out.println(list);
		/*basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		System.out.println("After removing Red");
		System.out.println(basicList);*/
		//basicList.remove("Yellow", String.CASE_INSENSITIVE_ORDER);
		//System.out.println(basicList);
		/*System.out.println("First: " + basicList.getFirst());
		System.out.println("Last: " + basicList.getLast());
		System.out.println("Size: " + basicList.getSize());
		System.out.println("Retrieve First: " + basicList.retrieveFirstElement());
		System.out.println("Retrieve Last: " + basicList.retrieveLastElement());
		System.out.println("Removing Red");
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		System.out.print("Iteration: ");
		for (String entry : basicList) {
			System.out.print(entry + " ");
		}
		/*System.out.println(basicList);
		BasicLinkedList<String> basicList1 = basicList.getReverseList();
		System.out.println(basicList1);
		System.out.println("Size: " + basicList.getFirst());
		System.out.println(basicList);
		ArrayList<String> list = basicList.getReverseArrayList();
		System.out.println(list);
		basicList.remove("Red", String.CASE_INSENSITIVE_ORDER);
		System.out.println(basicList);*/
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		Comparator<String> caseIn = String.CASE_INSENSITIVE_ORDER;
		//System.out.println(caseIn.compare("Yellow", "Zax"));
		sortedList.add("Yellow").add("Red");
		TreeMap<String, Integer> treeMap = basicList.getCount();
		System.out.println(treeMap.toString());
		//System.out.println("\nFirst: " + sortedList.getLast());
		/*System.out.print("\n\nIteration (for sorted list): ");
		for (String entry : sortedList) {
			System.out.print(entry + " ");
		}
		sortedList.remove("Red");
		System.out.print("\nAfter remove in sorted list first is: ");
		System.out.println(sortedList.getFirst());*/
	}
}
