package model;

public class Driver {

	public static void main(String[] args) {
		TextElement element = new TextElement("John");
		//System.out.println(element.genHTML(5));
		
		TagElement test = new TagElement("sdd", false, null, "a");
		//System.out.println(test.getId());
		TagElement test1 = new TagElement("sg", false, null, "a");
		TagElement test2 = new TagElement("sh", true, element, null);
		//System.out.println(test2.getId());
		TagElement.enableId(true);
		//System.out.println(test1.getStartTag() + test1.getEndTag());
		//System.out.println(test2.genHTML(3));
		
		int indentation = 3, level = 6;
		String attributes = null;

		TagElement.resetIds();
		TagElement.enableId(true);
		HeadingElement element1 = new HeadingElement(new TextElement("Introduction"), level, attributes);
		//System.out.println(element1.genHTML(indentation));
		AnchorElement element2 = new AnchorElement("http://www.cs.umd.edu", "umd", "g");
		//System.out.println(element2.genHTML(on));
		TagElement.resetIds();
		ParagraphElement element3 = new ParagraphElement(attributes);
		element3.addItem(new TextElement("Fear the turtle"));
		element3.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", attributes));
		element3.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		//System.out.println(element3.genHTML(indentation));
		//System.out.println();
		indentation = 6;
		attributes = "style=\"color:red\"";
		ParagraphElement element4 = new ParagraphElement(attributes);
		element4.addItem(new TextElement("Fear the turtle"));
		element4.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", ""));
		//System.out.println(element4.genHTML(indentation));
		boolean orderedList = false;
		TagElement.resetIds();
		indentation = 6;
		ListElement element5 = new ListElement(orderedList, attributes);
		element5.addItem(new TextElement("Superman"));
		element5.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		//System.out.println(element5.genHTML(indentation));
		
		TableElement tableElement = new TableElement(2, 2, attributes);
		tableElement.addItem(0, 0, new TextElement("John"));
		tableElement.addItem(0, 1, new TextElement("Laura"));
		tableElement.addItem(1, 0, new TextElement("Rose"));
		System.out.println(tableElement.genHTML(indentation));
	}

}
