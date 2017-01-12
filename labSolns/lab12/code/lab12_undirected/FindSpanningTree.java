package lab12_undirected;

import java.util.*;

public class FindSpanningTree extends BreadthFirstSearch {
	private ArrayList<Edge> tree = new ArrayList<Edge>();
	public FindSpanningTree(Graph graph) {
		super(graph);
	}
	protected void processEdge(Edge e) {
			tree.add(e);
	}
	
	public Graph computeSpanningTree() {
		start();
		//tree is loaded
		Object[] edges = tree.toArray();
		return new Graph(edges);
	}
	
	
	
}
