package model;

public class TextElement implements Element {
	private String text;
	
	//Constructor that takes in a string parameter
	public TextElement(String text) {
		this.text = text;
	}
	
	// Returns a string that represents the HTML assocaited with the element.
	public String genHTML(int indentation) {
		return Utilities.spaces(indentation) + text;
	}
}
