package model;

public class ImageElement extends TagElement {
	private String imageURL, attributes, alt;
	private int width, height;
	private boolean isNull;
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, null, attributes);
		this.imageURL = imageURL;
		this.width = width;
		this.height = height;
		this.alt = alt;
		if (attributes == null || attributes.isBlank()) {
			isNull = true;
		} else {
			isNull = false;
		}
	}
	
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation), attr = new String();
		if (idEnable) {
			attr = "<" + getTagName() + " id=" + "\"" + getStringId() + "\""+ " src=" + "\"" + imageURL + "\"" + " width=" + "\"" + width + "\"" + " height=" + "\""
					+ height + "\"" + " alt=" + "\"" + alt + "\"" + ">";
		} else {
			attr = "<" + getTagName() + " src=" + "\"" + imageURL + "\"" + " width=" + "\"" + width + "\"" + " height=" + "\""
					+ height + "\"" + " alt=" + "\"" + alt + "\"" + ">";
		}
		if (isNull) {
			return indent + attr;
		} else {
			return indent + attr + " " +attributes;
		}
	}
}
