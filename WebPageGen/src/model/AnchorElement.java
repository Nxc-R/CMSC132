package model;

public class AnchorElement extends TagElement {
	private String linkText, url;
	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, new TextElement(linkText), attributes);
		if (attributes == null) {
			setAttributes("href=" + "\"" + url + "\"");
		} else {
			setAttributes("href=" + "\"" + url + "\"" + " " + attributes);
		}
		this.linkText = linkText;
		this.url = url;
	}
	
	public String getLinkText() {
		return linkText;
	}
	
	public String getUrlText() {
		return url;
	}
	
	
}
