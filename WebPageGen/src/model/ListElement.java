package model;

import java.util.ArrayList;

public class ListElement extends TagElement {
	private boolean ordered;
	private ArrayList<Element> elements;
	public ListElement(boolean ordered, String attributes) {
		super("ul", true, null, attributes);
		this.ordered = ordered;
		elements = new ArrayList<Element>();
	}
	
	public void addItem(Element item) {
		elements.add(item);
	}
	
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation), content = new String();
		if (ordered) {
			setTagName("ol");
		} 
		
		if (idEnable) {
			content = getStartTag() + "\n";
		} else {
			content = getStartTag() + "\n";
		}
		
		for (int i = 0;i < elements.size();i++) {
			content += indent + "<li>" + "\n" + elements.get(i).genHTML(indentation) + "\n" + indent + "</li>" + "\n";
		}
		content += indent + getEndTag();
		return indent + content;
	}
	
}
