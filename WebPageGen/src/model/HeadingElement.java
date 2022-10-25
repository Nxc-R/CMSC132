package model;

public class HeadingElement extends TagElement {
	public HeadingElement(Element content, int level, String attributes) {
		super("h" + level, true, content, attributes);
		if (level > 6 || level < 1) {
			setTagName("h1");
		}
	}
	
}
