package processor;

public class Items implements Comparable<Items>{
	private String description;
	private double cost;
	
	public Items(String description, double cost) {
		this.description = description;
		this.cost = cost;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getCost() {
		return cost;
	}
	
	public String toString() {
		return description + "(" + cost + ")";
	}
	
	public boolean equals(Items item) {
		return (description.equals(item.description) && cost == item.cost);
	}
	
	public int compareTo(Items item) {
		return description.compareTo(item.description);
	}
}
