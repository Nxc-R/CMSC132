package model;

public class TagElement implements Element {
	private String tagName, attributes;
	private boolean endTag;
	private Element content;
	private int id = 0;
	private static int idNum = 0;
	protected static boolean idEnable;
	//Initializes a tag element.
	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		this.tagName = tagName;
		this.endTag= endTag;
		this.content = content;
		this.attributes = attributes;
		idNum++;
		this.id= idNum;
		
	}
	//Returns Id
	public int getId() {
		return id;
	}
	//Returns StringId
	public String getStringId() {
		return tagName + id;
	}
	//Returns the start tag.
	public String getStartTag() {
		if (attributes != null && !(attributes.isBlank()) && idEnable) {
			return "<" + tagName + " id=" + "\"" + getStringId() + "\""+ " " + attributes + ">";
		}
		if (attributes == null) {
			if (idEnable) {
				return "<" + tagName + " id=" + "\"" + getStringId() + "\""+  ">";
			} else {
				return "<" + tagName  + ">";
			}
		}
		if (idEnable) {
			return "<" + tagName + " id=" + "\"" + getStringId() + "\""+ " " + attributes + ">";
		} else {
			return "<" + tagName  + " " + attributes + ">";
		}
		
	}
	//Returns end tag
	public String getEndTag() {
		return "<" + "/" +  tagName + ">";
	}
	//Updates attributes field.
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	//Allow us to start assigning ids at 1 again.
	public static void resetIds() {
		idNum = 0;
	}
	
	public static void enableId(boolean choice) {
		if (choice) {
			idEnable = true;
		} else {
			idEnable = false;
		}
	}
	//Returns a string that represents the HTML associated with the element.
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation);
		if (!(content == null)) {
			if (endTag) {
				return indent + getStartTag() + content.genHTML(indentation) + getEndTag();
			} else {
				return indent + getStartTag() + content.genHTML(indentation);
			}
		} else {
			if (endTag) {
				return indent + getStartTag() + getEndTag();
			} else {
				return indent + getStartTag();
			}
		}
	}
	
	protected String getTagName() {
		return tagName;
	}
	
	protected void setTagName(String name) {
		tagName = name;
	}
	
}
