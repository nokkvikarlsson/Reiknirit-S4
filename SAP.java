package S4;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

public class SAP {
	
	private Digraph digraph;
	private BreadthFirstDirectedPaths BFD;
	
	// constructor takes a digraph ( not necessarily a DAG )
	public SAP ( Digraph G) {
		
		digraph = new Digraph(G);
		
		//Create a DirectedCycle data type to and copy digraph G into it so we can check if the graph is a DAG.
		DirectedCycle cycle = new DirectedCycle(G);
		
		if(cycle.hasCycle()){
			throw new IllegalArgumentException("The graph is not DAG");
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
