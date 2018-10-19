package S4;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	
	private Digraph digraph;
	private BreadthFirstDirectedPaths BFD_V;
	private BreadthFirstDirectedPaths BFD_W;
	
	
	// constructor takes a digraph ( not necessarily a DAG )
	public SAP ( Digraph G) {
		
		//Copy the digraph G to our digraph variable.
		digraph = new Digraph(G);
		
		//Create a DirectedCycle data type to and copy digraph G into it so we can check if the graph is a DAG.
		DirectedCycle cycle = new DirectedCycle(G);
		
		if(cycle.hasCycle()){
			throw new IllegalArgumentException("Graph is not acyclic");
		}
		
		//Keep count of number of vertexes that have no directed path to another vertex.
		int rootCounter = 0;
		
		//Loops over every vertex and checks if any vertex has no directed path to another.
		for(int i = 0; i < digraph.V(); i++) {
			if(!digraph.adj(i).iterator().hasNext()) {
				rootCounter++; //If a vertex has no directed path it's a root.
			}
		}
		
		//If root counter isn't exactly one the graph is not rooted.
		if(1 != rootCounter) {
			throw new IllegalArgumentException("Graph is not rooted");
		}
		
	}
	
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length ( int v , int w) {
		
		return 1;
	}
	
	// a shortest common common ancestor of v and w; -1 if no such path
	public int ancestor ( int v , int w) {
		
		return 1;
	}
	
	// length of shortest ancestral path of vertex subsets A and B ; -1 if no such path
	public int length ( Iterable < Integer > A , Iterable < Integer > B) {
		
		return 1;
	}
	
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor ( Iterable < Integer > A , Iterable < Integer > B) {
		
		return 1;
	}
	
	// do unit testing of this class
	public static void main ( String [] args ) {
		
	}
		

}
