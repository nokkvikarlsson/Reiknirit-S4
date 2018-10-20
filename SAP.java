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
	
	// constructor takes a digraph ( not necessarily a DAG )
	public SAP(Digraph G) {
		
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
		if(rootCounter != 1) {
			throw new IllegalArgumentException("Graph is not rooted");
		}
		
	}
	
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		
		checkOneInput(v, w);
		
		BreadthFirstDirectedPaths BFD_V = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths BFD_W = new BreadthFirstDirectedPaths(digraph, w);
		
		//Finds the shortest ancestor between the two vertices.
		int shortestAncestor = ancestor(v, w);

		//If there is no ancestor return -1.
		if(shortestAncestor == -1) {
			return -1;
		}
		
		//Return the sum of the lengths from the ancestor to vertex v and w.
		return BFD_V.distTo(shortestAncestor) + BFD_W.distTo(shortestAncestor);
	}
	
	// a shortest common common ancestor of v and w; -1 if no such path.
	public int ancestor (int v, int w) {
		
		checkOneInput(v, w);
		
		BreadthFirstDirectedPaths BFD_V = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths BFD_W = new BreadthFirstDirectedPaths(digraph, w);
		
		int shortestCurrentAncestor = -1;
		int lengthSCA = Integer.MAX_VALUE;
		int length = 0;
		
		//Find the shortest common ancestor.
		for(int i = 0; i < digraph.V(); i++) {
			//Checks if there is a path from vertex i to v and w.
			if(BFD_V.hasPathTo(i) && BFD_W.hasPathTo(i)) {
				//Sum the distance from i to vertex v and w and store it in length.
				length = BFD_V.distTo(i) + BFD_W.distTo(i);
				if(length < lengthSCA) {
					
					lengthSCA = length;
					shortestCurrentAncestor = i;
				}
			}
		}
		return shortestCurrentAncestor;
	}
	
	// length of shortest ancestral path of vertex subsets A and B ; -1 if no such path
	public int length(Iterable<Integer> A, Iterable<Integer> B) {
		
		checkMultipleInputs(A, B);
		
		BreadthFirstDirectedPaths BFD_A = new BreadthFirstDirectedPaths(digraph, A);
		BreadthFirstDirectedPaths BFD_B = new BreadthFirstDirectedPaths(digraph, B);
		
		int shortestAncestor = ancestor(A, B);
		if(shortestAncestor == -1) {
			return -1;
		}
		
		return BFD_A.distTo(shortestAncestor) + BFD_B.distTo(shortestAncestor);
	}
	
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor(Iterable<Integer>A, Iterable<Integer> B) {
		
		checkMultipleInputs(A, B);
		
		BreadthFirstDirectedPaths BFD_A = new BreadthFirstDirectedPaths(digraph, A);
		BreadthFirstDirectedPaths BFD_B = new BreadthFirstDirectedPaths(digraph, B);
		
		int shortestCurrentAncestor = -1;
		int lengthSCA = Integer.MAX_VALUE;
		int length = 0;
		
		for(int i = 0; i < digraph.V(); i++) {
			if(BFD_A.hasPathTo(i) && BFD_B.hasPathTo(i)) {
				length = BFD_A.distTo(i) + BFD_B.distTo(i);
				if(length < lengthSCA) {
					lengthSCA = length;
					shortestCurrentAncestor = i;
				}
			}
		}
		return shortestCurrentAncestor;
	}
	
	private void checkOneInput(int v, int w) {
		if(v < 0 || w < 0 || v >= digraph.V() || w >= digraph.V()) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkMultipleInputs(Iterable<Integer>A, Iterable<Integer> B) {
		for(int a: A) {
			if(a < 0  || a >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}
		for(int b: B) {
			if(b < 0  || b >= digraph.V()) {
				throw new IndexOutOfBoundsException();
			}
		}
	}
	
	// do unit testing of this class
	public static void main (String [] args) {
       
		In in = new In("./wordnet-data/digraph1.txt");
        //In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
	}
}
