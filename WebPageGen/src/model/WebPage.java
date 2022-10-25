package model;
import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {
	private ArrayList<Element> elements;
	private String title;
	//Initializes the object with the specified title and creates the ArrayList
	public WebPage(String title) {
		this.title = title;
		elements = new ArrayList<Element>();
	}
	//Adds an element to the page by adding the element to the end of the ArrayList.
	public int addElement(Element element) {
		if (element instanceof TagElement) {
			elements.add(element);
			return elements.size() - 1;
		} else {
			return -1;
		}
	}
	//Generates HTML WebPage
	public String getWebPageHTML(int indentation) {
		String indent = Utilities.spaces(indentation), content = new String();
		content += "<!doctype html>" + "\n" + "<html>" + "\n";
		content += indent + "<head>" + "\n" + indent + "<meta charset=\"utf-8\"/>" + "\n" 
		+ "<title>" + title + "</title>" + "\n" + "</head>" + "\n" + "<body>" + "\n";
		for (int i = 0;i < elements.size();i++) {
			content += elements.get(i).genHTML(indentation) + "\n";
		}
		content += indent + "</body>" + "\n" + "</html>";
		return content;
	}
	//Writes to the specified file the web page page using the provided indentation.
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}
	//Returns a reference to a particular element based on the id
	public Element findElem(int id) {
		if (elements.get(id) != null) {
			return elements.get(id);
		} else {
			return null;
		}
	}
	//Returns information about the number of lists, paragraphs, and tables present in the page.
	public String stats() {
		String stats = new String();
		int lists = 0, para = 0, tableCount = 0;
		double tableUtil = 0.0, avgUtil = 0.0;
		for (int i = 0;i < elements.size();i++) {
			if (elements.get(i) instanceof ListElement) {
				lists++;
			}
			if (elements.get(i) instanceof ParagraphElement) {
				para++;
			}
			if (elements.get(i) instanceof TableElement) {
				tableCount++;
				TableElement t = (TableElement)elements.get(i);
				tableUtil+= t.getTableUtilization();
			}
		}
		avgUtil = tableUtil/tableCount;
		stats += "List Count: " + lists + "\n" + "Paragraph Count: " + para + "\n" + "Table Count: " + tableCount + "\n" + "TableElement Utilization: " + avgUtil;
		return stats;
	}
	
	public int compareTo(WebPage webPage) {
		return title.compareTo(webPage.title);
	}
	//Enables the ids associated with tag elements.
	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}
}
