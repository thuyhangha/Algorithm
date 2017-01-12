package lab12_undirected;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	Graph spanningTree;
	List<Graph> connectedComponents;
	HashMap<Vertex,Integer> vertexComponents;
	
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	LinkedList<Edge> edges = new LinkedList<Edge>();
	HashMap<Vertex,LinkedList<Vertex>> adjList = new HashMap<Vertex,LinkedList<Vertex>>();
	/* new public methods */
	 
	public Graph(Object[] inputEdges) {
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();
		
		for(Object ob: inputEdges) {
			if(ob.getClass() != Edge.class) continue;
			Edge e = (Edge)ob;
			//assume no dup edges
			edges.add(e);
			Vertex v1 = e.u;
			Vertex v2 = e.v;
			if(!dupverts.containsKey(v1)){
				dupverts.put(v1,v1);
				vertices.add(v1);
				
			}
			if(!dupverts.containsKey(v2)){
				dupverts.put(v2,v2);
				vertices.add(v2);
				
			}
			LinkedList<Vertex> l = adjList.get(v1);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1,l);
			
			LinkedList<Vertex> l2 = adjList.get(v2);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2,l2);	
			
		}
		
	}
	public Graph(List<Pair> pairs){
		HashMap<Vertex,Vertex> dupverts = new HashMap<Vertex,Vertex>();
		HashMap<Edge,Edge> dupedges = new HashMap<Edge,Edge>();
		for(Pair e : pairs){
			//handle the vertices and edges simultaneously
			Vertex v1 = new Vertex(e.ob1);
			Vertex v2 = new Vertex(e.ob2);
			Edge edge = new Edge(v1,v2);
			if(!dupverts.containsKey(v1)){
				dupverts.put(v1,v1);
				vertices.add(v1);
				
			}
			if(!dupverts.containsKey(v2)){
				dupverts.put(v2,v2);
				vertices.add(v2);
				
			}
			
			if(!dupedges.containsKey(edge)){
				dupedges.put(edge,edge);
				edges.add(edge);
			}
			
			LinkedList<Vertex> l = adjList.get(v1);
			if(l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1,l);
			
			LinkedList<Vertex> l2 = adjList.get(v2);
			if(l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2,l2);
		}
		
	}
	
	private void loadMajorStructures() {
		FindSpanningTree fst = new FindSpanningTree(this);
		spanningTree = fst.computeSpanningTree();
		
		ConnectedComponentSearch ccs = new ConnectedComponentSearch(this);
		Object[] results = ccs.getComponents();
		connectedComponents = (List<Graph>)results[0];
		vertexComponents = (HashMap)results[1];
		
		
		
	}
	public Graph getSpanningTree() {
		if(spanningTree == null){
			loadMajorStructures();
		}
		return spanningTree;
	}
	
	public List<Graph> getConnectedComponents() {
		if(connectedComponents == null) {
			loadMajorStructures();
		}
		return connectedComponents;
	}
	
	public boolean isConnected() {
		if(connectedComponents == null) {
			loadMajorStructures();
		}
		return connectedComponents.size() == 1;
	}
	
	public boolean hasPathBetween(Vertex u, Vertex v) {
		if(vertexComponents==null) {
			loadMajorStructures();
		}
		return vertexComponents.get(u).equals(vertexComponents.get(v));
	}
	
	public boolean containsCycle() {
		if(connectedComponents==null) {
			loadMajorStructures();
		}
		int numComponents = connectedComponents.size();
		for(int i = 0; i< numComponents; ++i) {
			Graph component = connectedComponents.get(i);
			if(component.edges.size() != component.vertices.size()-1) return true;
		}
		return false;
	}

	/**
	 * Important to return only a copy of the adjacency list. Running time for making 
	 * this copy: For each vertex v, the number of adjacent vertices to v that need to
	 * be copied into a new list (matched with v in the copy of the map) is deg v. Also, each
	 * vertex is processed (cloned and the clone is added to its list); processing is O(1) Therefore,
	 * it is the sum over v in V of 1 + deg(v), which is O(n+m).
	 */
	public HashMap<Vertex,LinkedList<Vertex>> getAdjacencyList() {
		HashMap<Vertex,LinkedList<Vertex>> copy = new HashMap<Vertex,LinkedList<Vertex>>();
		for(Vertex v : adjList.keySet()) {
			//LinkedList<Vertex> original = adjList.get(v);
			copy.put(v, getListOfAdjacentVerts(v));
			
		}
		return copy;
		
	}
	
	/**
	 * Returns a copy of the list of adjacent vertices
	 */
	public LinkedList<Vertex> getListOfAdjacentVerts(Vertex v) {
		List<Vertex> theList = adjList.get(v);
		LinkedList<Vertex> copy = new LinkedList<Vertex>();
		if (theList != null) {
			for(Vertex vert : theList) {
				copy.add(vert.clone());
			}
		}
		return copy;
	}
	
	public boolean areAdjacent(Vertex v, Vertex w) {
		LinkedList<Vertex> l = adjList.get(v);
		if(l == null) return false;
		return l.contains(w);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder("Vertices: \n"+" ");
		for(Vertex v : vertices) {
			sb.append(v+" ");
		}
		sb.append("\nEdges:\n"+" ");
		sb = appendEdgesToString(this,sb);
		if(connectedComponents != null) {
			sb.append("\nConnected Components:\n");
			int numComponents = connectedComponents.size();
			for(int i = 0; i < numComponents; ++i ){
				sb.append(i+": ");
				sb = appendEdgesToString(connectedComponents.get(i),sb);
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	private StringBuilder appendEdgesToString(Graph g, StringBuilder sb) {
		HashMap<String,String> dups = new HashMap<String,String>();
		List<Vertex> verts = g.vertices;
		
		for(Vertex v: verts){
			LinkedList<Vertex> l  = g.adjList.get(v);
			
			for(Vertex w : l){
				String edge = v.toString()+"-"+w.toString();
				String edgeRev = reverse(edge);
				if(!dups.containsKey(edge) && !dups.containsKey(edgeRev)){
					sb.append(edge+" ");
					dups.put(edge,edge);
				}
			}
		}
		return sb;
	}
	private String reverse(String edge) {
		String[] vals = edge.split("-");
		return vals[1]+"-"+vals[0];
	}
	
	public boolean isTree(){
		if (isConnected() && !containsCycle())
			return true;
		else
			return false;
		
	}
}
