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
		
		//Keep count of the number of vertexes that have no directed path to another vertex.
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
		
		//TODO: Check if out of bounds
		
		BFD_V = new BreadthFirstDirectedPaths(digraph, v);
		BFD_W = new BreadthFirstDirectedPaths(digraph, w);
		
		//We use shortest to keep track of our shortest path, we initialize it as -1.
		int shortestPath = -1;
		//A variable we use to keep track of our current length between the vertexes.
		int length = 0;
		
		for(int i = 0; i < digraph.V(); i++) {
			//Checks if there is a path from v and w to i.
			if(BFD_V.hasPathTo(i) && BFD_W.hasPathTo(i)) {
				//If there is a path from both v and w then add the distance from both paths.
				length = BFD_V.distTo(i) + BFD_W.distTo(i);
			}
			//If length is smaller than shorterstPath then shortestpath gets the value from length.
			if(length <= shortestPath) {
				shortestPath = length;
			}
		}
		
		//Return shortestPath, it's -1 if there is no path
		return shortestPath;
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
