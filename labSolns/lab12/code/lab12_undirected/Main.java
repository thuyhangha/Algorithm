package lab12_undirected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Pair> l = new ArrayList<Pair>();
		l.add(new Pair("A","B"));
		l.add(new Pair("B","C"));
		l.add(new Pair("C","D"));
		l.add(new Pair("F","E"));
		//l.add(new Pair("E","A"));
		//l.add(new Pair("F","G"));
		//l.add(new Pair("G","H"));
		//l.add(new Pair("H","F"));
		
		Graph g = new Graph(l);
		String s = g.toString();
		System.out.println("The main graph:\n "+s);
		System.out.println("Are B and C adjacent? "+g.areAdjacent(new Vertex("B"),new Vertex("C")));
		System.out.println("Are A and C adjacent? "+g.areAdjacent(new Vertex("A"),new Vertex("C")));
		System.out.println();
		//System.out.println("The spanning tree:");
		//Graph t = g.getSpanningTree();
		List<Graph> list = g.getConnectedComponents();
		System.out.println("Connected components: \n" + list);
		/*
		System.out.println(t);
		System.out.println();
		System.out.println("Has odd cycle?");
		OddCycle oc = new OddCycle(g);
		System.out.println(oc.hasOddCycle());
		System.out.println("Is there a path from A to B? "+g.existsPathBetween(new Vertex("A"),new Vertex("B")));
		System.out.println("Is the graph connected? "+g.isConnected());
		System.out.println("Does it contain a cycle? "+g.containsCycle());
		System.out.println("Is the graph itself a cycle? "+g.isACycle());
		Edge e1 = new Edge(new Vertex("A"), new Vertex("B"));
		Edge e2 = new Edge(new Vertex("A"), new Vertex("B"));
		Edge e3 = new Edge(new Vertex("B"), new Vertex("A"));
		System.out.println((e1.equals(e2)) + ", " + (e1.equals(e3)));
		HashSet<Edge> test = new HashSet<Edge>();
		test.add(e1);
		test.add(e2);
		test.add(e3);
		System.out.println(test);*/
		
		
	}

}
