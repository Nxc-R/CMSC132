package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {
	private ArrayList<Element> elements;
	//Class Constructor
	public ParagraphElement(String attributes) {
		super("p", true, null, attributes);
		elements = new ArrayList<Element>();
	}
	//Adds an element to the paragraph.
	public void addItem(Element item) {
		elements.add(item);
	}
	//Returns a string that represents the HTML associated with the element.
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation), content;
		if (idEnable) {
			content = getStartTag() + "\n";
		} else {
			content = getStartTag() + "\n";
		}
		
		for (int i = 0;i < elements.size();i++) {
			content += elements.get(i).genHTML(indentation) + "\n";
		}
		content += indent + getEndTag();
		return indent + content;
	}
}
