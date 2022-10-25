package model;

public class TableElement extends TagElement {
	private Element[][] elements;
	private int totalSpace;
	//Defines the array and initializes the attributes.
	public TableElement(int rows, int cols, String attributes) {
		super("table", true, null, attributes);
		elements = new Element[rows][cols];
		this.totalSpace = rows * cols;
	}
	//Adds/updates the element with the specified indices.
	public void addItem(int rowIndex, int colIndex, Element item) {
		elements[rowIndex][colIndex] = item;
	}
	//Returns a string that represents the HTML associated with the element.
	public double getTableUtilization() {
		int count = 0;
		double percent = 0.0;
		for(int i = 0;i < elements.length;i++) {
			for(int j = 0;j < elements[i].length;j++) {
				if (!(elements[i][j] == null)) {
					count++;
				}
			}
		}
		
		percent = (100.0*count/totalSpace);
		return percent;
	}
	//Returns the percentage of table cells currently in used (those storing references to objects).
	public String genHTML(int indentation) {
		String indent = Utilities.spaces(indentation), content;
		if (idEnable) {
			content = getStartTag() + "\n" ;
		} else {
			content = getStartTag() + "\n";
		}
		
		for (int i = 0;i < elements.length;i++) {
			content += "<tr>";
			for(int j = 0;j < elements[i].length;j++) {
				if (!(elements[i][j] == null)) {
					content += "<td>" + elements[i][j].genHTML(indentation) + "</td>";
				} else {
					content += "<td>" + "</td>";
				}
			}
			content+= "</tr>";
			content+= "\n";
		}
		content += indent + getEndTag();
		return indent + content;
	}
}
