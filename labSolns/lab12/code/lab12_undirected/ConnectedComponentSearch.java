package lab12_undirected;

import java.util.*;

public class ConnectedComponentSearch extends DepthFirstSearch {
	private int currentComponentNumber = 0;
	private HashMap<Vertex,Integer> vertexComponentMap = new HashMap<Vertex,Integer>();
	private ArrayList<List<Vertex>> verticesByComponent = new ArrayList<List<Vertex>>();
	
	public ConnectedComponentSearch(Graph graph) {
		super(graph);	
		verticesByComponent.add(new ArrayList());	
	}
	protected void additionalProcessing() {
		++currentComponentNumber;
		verticesByComponent.add(new ArrayList());
	}
	
	protected void processVertex(Vertex v) {
		vertexComponentMap.put(v, currentComponentNumber);
		List<Vertex> l = verticesByComponent.get(currentComponentNumber);
		l.add(v);
	}
	
	/* Return: list of components, vertexComponents map */
	Object[] getComponents() {
		start();
		Object[] retVal = new Object[2];
		List<Graph> components = new ArrayList<Graph>();
		Object[] nextEdgeArr=null;
		List<Vertex> nextVertexList = null;
		Set<Edge> nextEdgeSet = null;
		for(int i = 0; i < currentComponentNumber; ++i){
			nextEdgeSet = new HashSet<Edge>();
			nextVertexList = verticesByComponent.get(i);
			for(Vertex v: nextVertexList) {
				List<Vertex> adjList = graph.getListOfAdjacentVerts(v);
				for(Vertex w: adjList) {
					if(!nextEdgeSet.contains(new Edge(w,v)))
					    nextEdgeSet.add(new Edge(v,w));		
				}
			}
			nextEdgeArr = nextEdgeSet.toArray();	
			Graph g = new Graph(nextEdgeArr);
			components.add(g);
		}
		retVal[0] = components;
		retVal[1] = vertexComponentMap;
		
		return retVal;
	}
}
