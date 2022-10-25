package implementation;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated
 * with a vertex.
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		adjacencyMap = new HashMap<>();
		dataMap = new HashMap<>();
	}
	
	public void addVertex(String vertexName, E data) {
		if (dataMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("Vertex already exist");
		}
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
		dataMap.put(vertexName, data);
	}

	public void addDirectedEdge(String startVertexName, String endVertexName, int cost) {
		if (!(dataMap.containsKey(startVertexName)) || !(dataMap.containsKey(endVertexName))) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
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
		if (!(dataMap.containsKey(startVertexName)) || !(dataMap.containsKey(endVertexName))) {
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

	public E getData(String vertex) {
		if (!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException("Vertex does not exist");
		}
		return dataMap.get(vertex);
	}

	public void doDepthFirstSearch(String startVertexName, CallBack<E> callback) {
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(startVertexName);
		visited.add(startVertexName);
		while (stack.size() != 0) {
			startVertexName = stack.peek();
			stack.pop();
			callback.processVertex(startVertexName, dataMap.get(startVertexName));
			HashMap<String, Integer> map = adjacencyMap.get(startVertexName);
			for (String x : map.keySet()) {
				if (!visited.contains(x)) {
					visited.add(x);
					stack.push(x);
				}
			}
		}

	}

	public void doBreadthFirstSearch(String startVertexName, CallBack<E> callback) {
		ArrayList<String> visited = new ArrayList<String>();
		LinkedList<String> queue = new LinkedList<String>();
		queue.add(startVertexName);
		visited.add(startVertexName);
		while (queue.size() != 0) {
			startVertexName = queue.poll();
			callback.processVertex(startVertexName, dataMap.get(startVertexName));
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