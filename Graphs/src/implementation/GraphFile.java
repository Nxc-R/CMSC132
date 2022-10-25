package implementation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;


public class GraphFile {
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;

	public GraphFile() {
		adjacencyMap = new HashMap<>();
	}
	public void generateMap() throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter data file name: ");
		String dataFile = scanner.next();
		Scanner dataScan = new Scanner(new File(dataFile));
		while (dataScan.hasNext()) {
			String vertex = dataScan.next();
			String edge = dataScan.next();
			int cost = dataScan.nextInt();
			if (!adjacencyMap.containsKey(vertex)) {
				addVertex(vertex);
			}
			adjacencyMap.get(vertex).put(edge, cost);
		}
		scanner.close();
	}
	public void addVertex(String vertexName) {
		if (adjacencyMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("Vertex already exist");
		}
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) {
		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}

	public String toString() {
		String graph = "Vertices: ";
		TreeSet<String> treeSet = new TreeSet<>();
		for (String vertex : adjacencyMap.keySet()) {
			treeSet.add(vertex);
		}
		graph += treeSet.toString() + "\n" + "Edges:" + "\n";
		TreeMap<String, HashMap<String, Integer>> treeMap = new TreeMap<>(adjacencyMap);
		for (Map.Entry<String, HashMap<String, Integer>> entry : treeMap.entrySet()) {
			graph += "Vertex(" + entry.getKey() + ")" + "--->" + entry.getValue() + "\n";
		}
		return graph;
	}

	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}

	public int getCost(String startVertexName, String endVertexName) {
		if (!(adjacencyMap.containsKey(startVertexName)) || !(adjacencyMap.containsKey(endVertexName))) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		return adjacencyMap.get(startVertexName).get(endVertexName);
	}

	public Set<String> getVertices() {
		Set<String> setVert = new HashSet<>();
		for (String vertex : adjacencyMap.keySet()) {
			setVert.add(vertex);
		}
		return setVert;
	}


	public void doDepthFirstSearch(String startVertexName) {
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(startVertexName);
		visited.add(startVertexName);
		while (stack.size() != 0) {
			startVertexName = stack.peek();
			stack.pop();
			HashMap<String, Integer> map = adjacencyMap.get(startVertexName);
			for (String x : map.keySet()) {
				if (!visited.contains(x)) {
					visited.add(x);
					stack.push(x);
				}
			}
		}

	}

	public void doBreadthFirstSearch(String startVertexName) {
		ArrayList<String> visited = new ArrayList<String>();
		LinkedList<String> queue = new LinkedList<String>();
		queue.add(startVertexName);
		visited.add(startVertexName);
		while (queue.size() != 0) {
			startVertexName = queue.poll();
			HashMap<String, Integer> map = adjacencyMap.get(startVertexName);
			for (String x : map.keySet()) {
				if (!visited.contains(x)) {
					visited.add(x);
					queue.add(x);
				}
			}
		}
	}

	public int doDijkstras(String startVertexName, String endVertexName, ArrayList<String> shortestPath) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		Set<String> visited = new HashSet<>();
		Vertex v = new Vertex(startVertexName, 0);
		queue.add(v);
		while (queue.size() != 0) {
			v = queue.poll();
			HashMap<String, Integer> map = adjacencyMap.get(v.getName());
			v.setEdges(map);
			if (visited.contains(v.getName())) {
				continue;
			}
			visited.add(v.getName());
			if (v.getName().equals(endVertexName)) {
				shortestPath.add(endVertexName);
				int cost = v.getCost();
				while (v.getPred() != null) {
					shortestPath.add(v.getPred().getName());
					v = v.getPred();
				}
				Collections.reverse(shortestPath);
				return cost;
			} else {
				if (v.getEdges() == null) {
					continue;
				}
				for (Map.Entry<String, Integer> entry : v.getEdges().entrySet()) {
					if (!visited.contains(entry.getKey())) {
						Vertex newV = new Vertex(entry.getKey(), entry.getValue() + v.getCost());
						newV.setPred(v);
						queue.add(newV);
					}
				}
			}
		}
		shortestPath.add("None");
		return -1;
	}
}
