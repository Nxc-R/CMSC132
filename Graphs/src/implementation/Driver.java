package implementation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		/*GraphFile graph = new GraphFile();
		graph.generateMap();
		System.out.println(graph);
		ArrayList<String> shortestPath = new ArrayList<String>();
		Set<String> vertices = graph.getVertices();
		TreeSet<String> sortedVertices = new TreeSet<String>(vertices);
		String startVertex = "O";
		StringBuffer results = new StringBuffer();
		for (String endVertex : sortedVertices) {
			int shortestPathCost = graph.doDijkstras(startVertex, endVertex, shortestPath);
			results.append("Shortest path cost between " + startVertex + " and " + endVertex + ": " + shortestPathCost);
			results.append(", Path: " + shortestPath + "\n");
			shortestPath.clear();
		}
		System.out.println(results.toString());*/
		Vertex x = new Vertex("s", 0);
		Vertex y = new Vertex("s", 0);
		System.out.println(x.equals(y));
		
		
	}

}
