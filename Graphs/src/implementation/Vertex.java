package implementation;

import java.util.HashMap;

public class Vertex implements Comparable<Vertex> {
	private String vertexName;
	private Vertex pred;
	private int cost;
	private HashMap<String, Integer> edges;
	
	public Vertex(String vertexName, int cost) {
		this.vertexName = vertexName;
		this.cost = cost;
	}
	
	public String getName() {
		return vertexName;
	}
	
	public void setEdges(HashMap<String, Integer> edges) {
		this.edges = edges;
	}
	
	public HashMap<String, Integer> getEdges() {
		return edges;
	}
	public void setPred(Vertex v) {
		pred = v;
	}
	public Vertex getPred() {
		return pred;
	}
	public int getCost() {
		return cost;
	}
	public int compareTo(Vertex vertex) {
		if (cost > vertex.cost) {
			return 1;
		} else if (cost == vertex.cost){
			return 0;
		} else {
			return -1;
		}
	}
}
